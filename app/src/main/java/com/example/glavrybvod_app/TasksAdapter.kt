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

class TasksAdapter(var tasks: List<Tasks>, var context: Context) : RecyclerView.Adapter<TasksAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val idTask: TextView = view.findViewById(R.id.idStaff_text)
        val department: TextView = view.findViewById(R.id.department_text)
        val fullName: TextView = view.findViewById(R.id.fullName_text)
        val bTask: Button = view.findViewById(R.id.bTaskInList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.idTask.text = "id " + tasks[position].idTask.toString()
        holder.department.text = tasks[position].department
        holder.fullName.text = tasks[position].fullName
        Log.d("My app", "Отображён список задач под id " + tasks[position].idTask.toString())

        holder.bTask.setOnClickListener{
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra("idTask", tasks[position].idTask.toString())
            intent.putExtra("idStaff", "id " + tasks[position].idStaff.toString())
            intent.putExtra("department", tasks[position].department)
            intent.putExtra("fullName", tasks[position].fullName)
            intent.putExtra("department", tasks[position].department)
            intent.putExtra("date_request", tasks[position].date_request)
            intent.putExtra("text_description", tasks[position].text_description)
            intent.putExtra("importance", tasks[position].importance.toString())

            context.startActivity(intent)
            Log.d("My app", "Переход в активити выбранной задачи")
        }
    }
}