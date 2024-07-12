package com.example.glavrybvod_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mysql.jdbc.Statement
import java.sql.ResultSet
import java.util.concurrent.LinkedBlockingQueue


class CalendarActivity : AppCompatActivity() {

    // + триггер в таблице, который посылает запрос на аккаунт сис админа и
    // поэтому появляется оповещение

    // Функция принятия запроса на другом экране
    fun receiveRequestFromUser(): MutableMap<Int, String>? {
        val requestResult = LinkedBlockingQueue<MutableMap<Int,String>>()
        val thread = Thread {
            val database = Database()
            Log.d("My app", "Создание соединения с Базой Данных")
            val cn = database.getConnection()
            var statement: Statement? = null
            if (cn != null) {
                statement = cn.createStatement() as Statement?
            }

            Log.d("My app", "Выполнение запроса")
            //Выполним запрос
            val result: ResultSet? = statement?.executeQuery(
                "SELECT id, date_request, text_description, importance, account_id FROM request where active_request=1"
            )
            val map = mutableMapOf(0 to "test")
            var i = 0
            if (result != null) {
                while (result.next()) {
                    map.putAll(mapOf(
                        i to result.getInt("id").toString(),
                        i+1 to result.getString("date_request"),
                        i+2 to result.getString("text_description"),
                        i+3 to result.getInt("importance").toString(),
                        i+4 to result.getInt("account_id").toString())
                    )
                    i += 5
                }
            }
            requestResult.add(map)
        }

        // Запуск потока
        thread.start()
        return requestResult.take()
    }

    // Функция получения фамилии пользователя по id
    fun takeTaskUsername(id: Int): String {
        val username = LinkedBlockingQueue<String>()
        val thread = Thread {
            val database = Database()
            Log.d("My app", "Создание соединения с Базой Данных")
            val cn = database.getConnection()

            var statement: Statement? = null

            if (cn != null) {
                statement = cn.createStatement() as Statement?
            }

            Log.d("My app", "Выполнение запроса")
            //Выполним запрос
            val result: ResultSet? = statement?.executeQuery(
                "SELECT last_name FROM user where account_id=${id}"
            )

            if (result != null) {
                while (result.next()) {
                    username.add(result.getString("last_name"))
                }
            }
        }

        // Запуск потока
        thread.start()
        return username.take()
    }

    // Функция получения отдела пользователя по id
    fun takeTaskDepartment(id: Int): String {
        val username = LinkedBlockingQueue<String>()
        val thread = Thread {
            val database = Database()
            Log.d("My app", "Создание соединения с Базой Данных")
            val cn = database.getConnection()

            var statement: Statement? = null

            if (cn != null) {
                statement = cn.createStatement() as Statement?
            }

            Log.d("My app", "Выполнение запроса")
            //Выполним запрос
            val result: ResultSet? = statement?.executeQuery(
                "SELECT department FROM user where account_id=${id}"
            )

            if (result != null) {
                while (result.next()) {
                    username.add(result.getString("department"))
                }
            }
        }

        // Запуск потока
        thread.start()
        return username.take()
    }

    fun actualTasks(colorTask: RecyclerView, myMap: MutableMap<Int,String>, importance: Int){
        val tasksActual = arrayListOf<Tasks>()

        var i = 0
        for (entry in myMap.entries.iterator()) {
            if (i % 5 == 0){
                if (myMap.get(i+3) == importance.toString()){
                    val idUser = myMap.get(i+4)!!.toInt()
                    tasksActual.add(Tasks(
                        myMap.get(i)!!.toInt(),
                        takeTaskUsername(idUser),
                        takeTaskDepartment(idUser),
                        myMap.get(i+1)!!,
                        myMap.get(i+2)!!,
                        myMap.get(i+3)!!.toInt(),
                        idUser
                    ))
                }
            }
            i += 1
        }

        colorTask.layoutManager = LinearLayoutManager(this)
        colorTask.adapter = TasksAdapter(tasksActual, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calendar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bBack: ImageButton = findViewById(R.id.button_back)

        val myMap = receiveRequestFromUser()

        if (myMap != null) {
            actualTasks(findViewById(R.id.high_tasks_list), myMap, 2)
            actualTasks(findViewById(R.id.medium_tasks_list), myMap, 1)
            actualTasks(findViewById(R.id.low_tasks_list), myMap, 0)
        }

        bBack.setOnClickListener{
            val intent = Intent(this, MenuSysActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно меню системного администратора")
        }
    }
}