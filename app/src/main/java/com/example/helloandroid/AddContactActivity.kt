package com.example.helloandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AddContactActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        bSave.setOnClickListener{
            val personName = etName.text.toString()
            val personAge  = etAge.text.toString().toInt()
            Log.i(TAG, "Person name: $personName, age: $personAge")
            // Add this user to the list and display it in the main screen4
            val person = Person(personName, personAge)
            // New Intent to return data
            val result = Intent()
            result.putExtra("person", person)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}