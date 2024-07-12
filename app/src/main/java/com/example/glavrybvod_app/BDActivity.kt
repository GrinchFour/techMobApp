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

class BDActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bdactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bLEmployee: Button = findViewById(R.id.button_list_empl)
       // val bLComp: Button = findViewById(R.id.button_list_comp)
        val bBack: ImageButton = findViewById(R.id.button_back)

        bBack.setOnClickListener{
            val intent = Intent(this, MenuSysActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно меню системного администратора")
        }

        bLEmployee.setOnClickListener{
            val intent = Intent(this, StaffActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Переход в окно список сотрудников")
        }
/*
        bLComp.setOnClickListener{
            Toast.makeText(this, "Переход в список предприятий\n" +
                    "\n" +
                    "Раздел находится в разработке", Toast.LENGTH_SHORT).show()
        }

 */
    }
}