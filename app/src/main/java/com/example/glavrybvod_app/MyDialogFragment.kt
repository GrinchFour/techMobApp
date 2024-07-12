package com.example.glavrybvod_app

import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.util.Date

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val currentTime: Date = Calendar.getInstance().time
            Log.d("My app", "Время отправки: $currentTime")
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Отлично")
            builder.setMessage("Ваше обращение от $currentTime было успешно отправлено!")
            builder.setCancelable(true)
            builder.setPositiveButton("Вернуться") { _, _ -> (activity as RequestActivity?)?.returnClicked() }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

