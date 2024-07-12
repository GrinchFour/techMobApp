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

class StaffDeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_staff_delete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bBack: ImageButton = findViewById(R.id.button_back)
        val bSettings: ImageButton = findViewById(R.id.button_settings_out)

        val staffList: RecyclerView = findViewById(R.id.staffList)
        val staff = arrayListOf<Staff>()

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
        staff.add(Staff(123,"Test User","Отдел тестов"))
        staff.add(Staff(456,"Test User 2","Отдел кадров"))

        staffList.layoutManager = LinearLayoutManager(this)
        staffList.adapter = StaffDeleteAdapter(staff, this)

        bBack.setOnClickListener{
            val intent = Intent(this, BDActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно База Данных")
        }

        bSettings.setOnClickListener{
            val intent = Intent(this, StaffActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Возврат в окно список сотрудников")
        }
    }
}