package com.example.glavrybvod_app

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mysql.jdbc.CallableStatement
import java.util.Date


class RequestActivity : AppCompatActivity() {

    // Функция отправки запроса системному администратору
    fun sendRequestToAdmin(selectedDate: String, textDescription: String, importance: Int, idEmpl: Int) {
        // Отправляем запрос системному администратору
        val thread = Thread {
            val database = Database()
            Log.d("My app", "Создание соединения с Базой Данных")
            val cn = database.getConnection()
            var callableStatement: CallableStatement? = null

            //Вызываю процедуру CheckLoginAndPassword
            if (cn != null) {
                callableStatement = cn.prepareCall(" { call AddRequest(?,?,?,?,?) } "
                ) as CallableStatement?
            }

            //Задаю входные параметры
            if (callableStatement != null) {
                callableStatement.setString(1, selectedDate)
                callableStatement.setString(2, textDescription)
                callableStatement.setInt(3, importance)
                callableStatement.setInt(4, 1)
                callableStatement.setInt(5, idEmpl)

                //Выполняю запрос
                callableStatement.executeUpdate()
                Log.d("My app", "Выполнение запроса")
            }
        }

        // Запуск потока
        thread.start()
        Log.d("My app", "Request был отправлен")
    }

    fun returnClicked() {
        val intent = Intent(this, MenuEmplActivity::class.java)
        startActivity(intent)
        Log.d("My app", "Возврат в окно меню сотрудника")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_request)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bSend: Button = findViewById(R.id.button_send)
        val bBack: ImageButton = findViewById(R.id.button_back)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

        val dateImp: CalendarView = findViewById(R.id.calendarViewReq)
        val now = Calendar.getInstance()
        var checkCalendar = true
        var selectedDate: Date = SimpleDateFormat("MM/dd/yyyy").parse("06/09/2012")

        dateImp.setDate(now.timeInMillis, true, true)
        dateImp.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val d1 = "$month/$dayOfMonth/$year"
            val d2 = "${now[Calendar.MONTH]}/${now[Calendar.DAY_OF_MONTH]}/${now[Calendar.YEAR]}"
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val firstDate: Date = sdf.parse(d1)
            val secondDate: Date = sdf.parse(d2)
            selectedDate = sdf.parse("${month+1}/$dayOfMonth/$year")
            when {
                firstDate.after(secondDate) -> {
                    checkCalendar = true
                    Log.d("My app", "$d1 is after $d2")
                }
                firstDate.before(secondDate) -> {
                    checkCalendar = false
                    Log.d("My app", "$d1 is before $d2")
                }
                firstDate == secondDate -> {
                    checkCalendar = true
                    Log.d("My app", "$d1 is equal $d2")
                }
            }
        }

        bSend.setOnClickListener{
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            val textDesk: EditText = findViewById(R.id.editText_description)
            if (checkCalendar)
                when(checkedRadioButtonId) {
                    -1 -> {
                        Toast.makeText(this, "Выберите степень важности", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val selectedRadioButton: RadioButton = findViewById(checkedRadioButtonId)
                        var importance = 0
                        when (selectedRadioButton.contentDescription) {  // логика изменения важности в числа
                            "green" -> importance = 0
                            "yellow" -> importance = 1
                            else -> { importance = 2 }
                        }
                        val idEmpl = GlobalId.globalId.toInt()

                        sendRequestToAdmin(selectedDate.toString(), textDesk.text.toString(), importance, idEmpl)

                        val myDialogSend = MyDialogFragment()
                        val manager = supportFragmentManager
                        myDialogSend.show(manager, "myDialogSend")
                    }
                }
            else{Toast.makeText(this, "Вы неверно указали дату", Toast.LENGTH_SHORT).show()}
        }

        bBack.setOnClickListener{
            val intent = Intent(this, MenuEmplActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно меню сотрудника")
        }
    }
}

