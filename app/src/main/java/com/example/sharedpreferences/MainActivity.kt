package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var enterName: EditText
    private lateinit var enterAge: EditText
    private lateinit var checkAdult: CheckBox
    private lateinit var load: Button
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        enterName = findViewById(R.id.etName)
        enterAge = findViewById(R.id.etAge)
        checkAdult = findViewById(R.id.cbAdult)
        load = findViewById(R.id.btnLoad)
        save = findViewById(R.id.btnSave)



        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        save.setOnClickListener {
            val name = enterName.text.toString()
            val age = enterAge.text.toString().toInt()
            val adult = checkAdult.isChecked
            editor.apply {
                putString("name", name)
                putString("age", age.toString())
                putString("adult", adult.toString())
                apply()         // puts the data asynchronously
//                commit()  put the data synchronously and blocks the main thread and UI
            }
        }

        load.setOnClickListener {
            val name = sharedPref.getString("name",null)
            val age = sharedPref.getString("age", 0.toString())
            val isAdult = sharedPref.getString("adult",null)

            enterName.setText(name)
            enterAge.setText(age)
            checkAdult.text = isAdult

        }

    }
}