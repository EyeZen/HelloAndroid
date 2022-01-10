package com.example.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

data class Person(val name: String, val age: Int)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 6 STEPS OF IMPLEMENTING RecyclerView, from https://guides.codepath.com/android/using-the-recyclerview

        // 1. Add RecyclerView AndroidX library to the Gradle build file
        // 2. Define a model class to use as the data source
        val contacts: List<Person> = createContacts()
        // 3. Add a RecyclerView to your activity to display the items
        rvContacts.adapter = ContactsAdapter(this, contacts)
        // 4. Create a custom row layout XML file to visualize the single item

        // 5. Create a RecyclerView.Adapter and ViewHolder to render the item

        // 6. Bind the adapter to the data source to populate the Recycler View
        rvContacts.layoutManager = LinearLayoutManager(this)
    }

    private fun createContacts(): List<Person> {
        val contacts = mutableListOf<Person>()
        for(i in 1..100) {
            contacts.add(Person("Person $i", i))
        }
        return contacts
    }
}