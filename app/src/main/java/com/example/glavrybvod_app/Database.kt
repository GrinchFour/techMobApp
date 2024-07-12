package com.example.glavrybvod_app

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Database {
    fun getConnection(): Connection? {
        var cn: Connection? = null
        try {
            Class.forName("com.mysql.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        try {
            //establish connection
            cn = DriverManager.getConnection("jdbc:mysql://77.35.87.178:3306/prmysql" +
                    "?useUnicode=true&characterEncoding=UTF-8&" +
                    "allowPublicKeyRetrieval=true&useSSL=false",
              "yurchuk", "MAXD-82578_pr")

            if (cn != null)
                Log.d("My app", "Приложение подключилось к БД")
            else
                Log.d("My app", "Приложение НЕ подключилось к БД")

        } catch (e: ClassNotFoundException) {
            Log.d("My app", "Ошибка ClassNotFoundException")
            e.printStackTrace()
        } catch (e: SQLException) {
            Log.d("My app", "Ошибка SQLException")
            println(e.errorCode)
            println(e.localizedMessage)
            println(e.message)
            println(e.sqlState)
            println(e.suppressedExceptions)
            e.printStackTrace()
        }

        return cn
    }
}

