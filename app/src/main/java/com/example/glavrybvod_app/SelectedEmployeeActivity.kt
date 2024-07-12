package com.example.glavrybvod_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SelectedEmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selected_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bBack: ImageButton = findViewById(R.id.button_back)

        val fullName: TextView = findViewById(R.id.selected_empl_label)
        val department: TextView = findViewById(R.id.selected_department_enter)
        val idStaff: TextView = findViewById(R.id.selected_idUser_enter)
        fullName.text = intent.getStringExtra("fullName")
        department.text = intent.getStringExtra("department")
        idStaff.text = intent.getStringExtra("idStaff")

        bBack.setOnClickListener{
            val intent = Intent(this, StaffActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно сотрудники")
        }
    }
}