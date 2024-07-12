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

class MenuEmplActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_empl)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       // val bChat: Button = findViewById(R.id.button_chat)
        val bRequest: Button = findViewById(R.id.button_request)
        val bExit: ImageButton = findViewById(R.id.button_exit)

        bRequest.setOnClickListener{
            val intent = Intent(this, RequestActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Переход в окно сообщить о проблеме")
        }
/*
        bChat.setOnClickListener{
            Toast.makeText(this, "Переход в чат\n\nРаздел находится в разработке", Toast.LENGTH_SHORT).show()
        }


 */
        bExit.setOnClickListener{// выйти из приложения
            Log.d("My app", "Выход из приложения")
            this.finishAffinity();
        }
    }
}