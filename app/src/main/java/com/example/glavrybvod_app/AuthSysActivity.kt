package com.example.glavrybvod_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mysql.jdbc.CallableStatement
import java.util.concurrent.LinkedBlockingQueue

/*
import com.sun.jna.platform.win32.Advapi32Util
import com.sun.jna.platform.win32.Win32Exception
*/

class AuthSysActivity : AppCompatActivity() {

/*
    fun addEmployee(position: String, firstName: String, lastName: String, department: String, email: String, password: String, phoneNumber: String) {
        val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password")
        val statement = connection.createStatement()

        val query = "CALL AddEmployee('$position', '$firstName', '$lastName', '$department', '$email', '$password', '$phoneNumber')"
        statement.executeUpdate(query)

        statement.close()
        connection.close()
    }

 */


/*
    fun authenticateUser(username: String, password: String): Boolean {
        try {
            return Advapi32Util.validateCredentials(username, password)
        } catch (e: Win32Exception) {
            println("Ошибка аутентификации: ${e.message}")
            return false
        }
    }

 */

    fun checkLoginAndPasswordSys(login: String, password: String): String {
        val checkResult = LinkedBlockingQueue<String>()
        val thread = Thread {
            val database = Database()
            Log.d("My app", "Создание соединения с Базой Данных")
            var cn = database.getConnection()
            var callableStatement: CallableStatement? = null

            //Вызываю процедуру CheckLoginAndPasswordSys
            if (cn != null) {
                callableStatement = cn.prepareCall(" { call CheckLoginAndPasswordSys(?,?,?) } "
                ) as CallableStatement?
            }

            //Задаю входные параметры
            if (callableStatement != null) {
                callableStatement.setString(1, login)
                callableStatement.setString(2, password)
                callableStatement.setInt(3, 1)

                //Выполняю запрос
                val result = callableStatement.executeQuery()
                if (result != null) {
                    result.next()
                    Log.d("My app", "Проверка ввода пароля: " + result.getString("Result"))
                    if (result.getString("Result") == "Login successful") {
                        GlobalId.globalId = result.getString("id")
                        Log.d("My app", "Id текущего пользователя: " + GlobalId.globalId)
                    }
                    checkResult.add(result.getString("Result"))
                }
            }
        }

        // Запуск потока
        thread.start()
        return checkResult.take()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth_sys)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_pass)
        val bEnter: Button = findViewById(R.id.button_enter)
        val bChange: ImageButton = findViewById(R.id.button_change)

        bEnter.setOnClickListener{
            val login = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || pass == "") {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
                Log.d("My app", "Не все поля заполнены")
            }
            else {
                if (checkLoginAndPasswordSys(login, pass) == "Login successful"){
                    userEmail.text.clear()
                    userPass.text.clear()
                    Toast.makeText(this, "Вход успешно выполнен", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MenuSysActivity::class.java)
                    startActivity(intent)
                    Log.d("My app", "Вход сотрудника успешно выполнен")
                }
                else{
                    userEmail.text.clear()
                    userPass.text.clear()
                    Toast.makeText(this, "Неправильно введён логин или пароль", Toast.LENGTH_SHORT).show()
                    Log.d("My app", "Неправильно введён логин или пароль")
                }
            }

/*
            // Аутентификация системного администратора через Active Directory
            if (authenticateUser(username, password)) {
                val intent = Intent(this, MenuSysActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неправильно введён логин или пароль", Toast.LENGTH_SHORT).show()
            }
 */
        }

        bChange.setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            Log.d("My app", "Смена окна входа с системного администратора на сотрудника")
        }
    }
}