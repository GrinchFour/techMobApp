package com.example.glavrybvod_app

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StaffAdapter(var staff: List<Staff>, var context: Context) : RecyclerView.Adapter<StaffAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val idStaff: TextView = view.findViewById(R.id.idStaff_text)
        val department: TextView = view.findViewById(R.id.department_text)
        val fullName: TextView = view.findViewById(R.id.fullName_text)
        val bStaff: Button = view.findViewById(R.id.bStaffInList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.staff_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return staff.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.idStaff.text = "id " + staff[position].idStaff.toString()
        holder.department.text = staff[position].department
        holder.fullName.text = staff[position].fullName
        Log.d("My app", "Отображён сотрудник в списке под id " + staff[position].idStaff.toString())

        holder.bStaff.setOnClickListener{
            val intent = Intent(context, SelectedEmployeeActivity::class.java)
            // TODO Добавить сюда всё описание сотрудника
            intent.putExtra("idStaff", "id " + staff[position].idStaff.toString())
            intent.putExtra("department", staff[position].department)
            intent.putExtra("fullName", staff[position].fullName)

            context.startActivity(intent)
            Log.d("My app", "Переход в активити выбранного сотрудника")
        }
    }
}