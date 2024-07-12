package com.example.glavrybvod_app

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StaffDeleteAdapter(var staff: List<Staff>, var context: Context) : RecyclerView.Adapter<StaffDeleteAdapter.MyViewHolderDelete>() {
    class MyViewHolderDelete(view: View): RecyclerView.ViewHolder(view){
        val idStaff: TextView = view.findViewById(R.id.idStaff_text)
        val department: TextView = view.findViewById(R.id.department_text)
        val fullName: TextView = view.findViewById(R.id.fullName_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDelete {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.staff_delete_in_list, parent, false)
        return MyViewHolderDelete(view)
    }

    override fun getItemCount(): Int {
        return staff.count()
    }

    override fun onBindViewHolder(holder: MyViewHolderDelete, position: Int) {
        holder.idStaff.text = "id " + staff[position].idStaff.toString()
        holder.department.text = staff[position].department
        holder.fullName.text = staff[position].fullName
        Log.d("My app", "Отображён сотрудник в списке на удаление под id " + staff[position].idStaff.toString())
    }
}