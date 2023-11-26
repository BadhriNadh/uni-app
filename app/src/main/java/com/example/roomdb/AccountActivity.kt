package com.example.roomdb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AccountActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val editTextCellPhone = findViewById<EditText>(R.id.editTextCellPhone)
        val textViewStudentNumber = findViewById<TextView>(R.id.textViewStudentNumber)
        val editTextStreamOfStudy = findViewById<EditText>(R.id.editTextStreamOfStudy)
        val buttonUpdate = findViewById<Button>(R.id.buttonUpdate)

        val userId = 1

        lifecycleScope.launch {
            var user = userDao.getUserById(userId)
            if (user == null) {

                user = User(
                    id = userId,
                    firstName = "John",
                    lastName = "Salazar",
                    cellPhoneNumber = "7895246123",
                    studentNumber = "B00934589",
                    streamOfStudy = "Master of Applied Computer Science (MACS)"
                )
                userDao.insertUser(user)
                user = userDao.getUserById(userId)
            }

            // Update UI with user data
            user?.let {
                editTextFirstName.setText(it.firstName)
                editTextLastName.setText(it.lastName)
                editTextCellPhone.setText(it.cellPhoneNumber)
                textViewStudentNumber.text = it.studentNumber
                editTextStreamOfStudy.setText(it.streamOfStudy)
            }
        }

        buttonUpdate.setOnClickListener {
            val updatedUser = User(
                id = userId,
                firstName = editTextFirstName.text.toString(),
                lastName = editTextLastName.text.toString(),
                cellPhoneNumber = editTextCellPhone.text.toString(),
                studentNumber = textViewStudentNumber.text.toString(),
                streamOfStudy = editTextStreamOfStudy.text.toString()
            )

            lifecycleScope.launch {
                userDao.updateUser(updatedUser)
                finish()
            }
        }
    }
}
