package com.example.glavrybvod_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StaffActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_staff)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bBack: ImageButton = findViewById(R.id.button_back)
        val bSettings: ImageButton = findViewById(R.id.button_settings_in)
        val bAdd: Button = findViewById(R.id.button_add)

        val staffList: RecyclerView = findViewById(R.id.staffList)
        val staff = arrayListOf<Staff>()

        staff.add(Staff(123,"Test User","Отдел тестов"))
        staff.add(Staff(456,"Test User 2","Отдел кадров"))
        staff.add(Staff(789,"Test User 3","Отдел бухгалтерии"))
        staff.add(Staff(123,"Test User","Отдел тестов"))
        staff.add(Staff(456,"Test User 2","Отдел кадров"))
        staff.add(Staff(789,"Test User 3","Отдел бухгалтерии"))
        staff.add(Staff(123,"Test User","Отдел тестов"))
        staff.add(Staff(456,"Test User 2","Отдел кадров"))
        staff.add(Staff(789,"Test User 3","Отдел бухгалтерии"))
        staff.add(Staff(123,"Test User","Отдел тестов"))
        staff.add(Staff(456,"Test User 2","Отдел кадров"))
        staff.add(Staff(789,"Test User 3","Отдел бухгалтерии"))

        staffList.layoutManager = LinearLayoutManager(this)
        staffList.adapter = StaffAdapter(staff, this)

        bBack.setOnClickListener{
            val intent = Intent(this, BDActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно База Данных")
        }

        bSettings.setOnClickListener{
            val intent = Intent(this, StaffDeleteActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Переход в окно удаление сотрудников")
        }

        bAdd.setOnClickListener{
            val intent = Intent(this, AddStaffActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Переход в окно добавить сотрудника")
        }
    }
}