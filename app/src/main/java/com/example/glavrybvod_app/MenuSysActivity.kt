package com.example.glavrybvod_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuSysActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_sys)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

      //  val bChat: Button = findViewById(R.id.button_chat)
        val bCalendar: Button = findViewById(R.id.button_calendar)
        val bBD: Button = findViewById(R.id.button_bd)
        val bExit: ImageButton = findViewById(R.id.button_exit)
/*
        bChat.setOnClickListener{
            Toast.makeText(this, "Переход в чат\n" +
                    "\n" +
                    "Раздел находится в разработке", Toast.LENGTH_SHORT).show()
        }

 */

        bCalendar.setOnClickListener{
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Переход в окно календарь")
        }

        bBD.setOnClickListener{
            val intent = Intent(this, BDActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Переход в окно База Данных")
            }

        bExit.setOnClickListener{// выйти из приложения
            Log.d("My app", "Выход из приложения")
            this.finishAffinity();
        }

    }
}