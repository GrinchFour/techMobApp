package com.example.glavrybvod_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mysql.jdbc.Statement

class TaskActivity : AppCompatActivity() {

    // Функция завершения заявки
    fun completeTask(id: Int){
        val thread = Thread {
            val database = Database()
            Log.d("My app", "Создание соединения с Базой Данных")
            val cn = database.getConnection()

            var statement: Statement? = null

            if (cn != null) {
                statement = cn.createStatement() as Statement?
            }

            //Выполним запрос
            if (statement != null) {
                Log.d("My app", "Выполнение запроса")
                statement.executeUpdate(
                    "UPDATE request SET active_request = 0 where id = $id"
                )
            }
        }

        // Запуск потока
        thread.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bBack: ImageButton = findViewById(R.id.button_back)
        val bComplete: Button = findViewById(R.id.button_complete)

        val fullName: TextView = findViewById(R.id.fullName_label)
        val department: TextView = findViewById(R.id.department_text)
        val idStaff: TextView = findViewById(R.id.idStaff_text)
        val text_description: TextView = findViewById(R.id.dcTask_text)
        val importance: ImageView = findViewById(R.id.imageHigh)
        val idTask = intent.getStringExtra("idTask")
        fullName.text = intent.getStringExtra("fullName")
        department.text = intent.getStringExtra("department")
        idStaff.text = intent.getStringExtra("idStaff")
        text_description.text = intent.getStringExtra("text_description")

        when (intent.getStringExtra("importance")?.toInt()) {
            0 -> importance.setImageResource(R.drawable.ic_green)
            1 -> importance.setImageResource(R.drawable.ic_yellow)
            else -> { importance.setImageResource(R.drawable.ic_red)}
        }

        /*
        val date_request: TextView = findViewById(R.id.)
        date_request.text = intent.getStringExtra("date_request")
         */


        bBack.setOnClickListener{
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно календарь системного администратора")
        }

        bComplete.setOnClickListener{
            if (idTask != null) {
                completeTask(idTask.toInt())
            }
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно календарь системного администратора")
        }
    }
}