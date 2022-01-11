package com.example.helloandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

data class Person(val name: String, val age: Int) : Serializable

const val KEY_PERSON = "person"

class MainActivity : AppCompatActivity() {
    // static members
    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_CODE = 89
    }
    // declaring local, assignable variables
    private lateinit var contactsList: MutableList<Person>
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 6 STEPS OF IMPLEMENTING RecyclerView, from https://guides.codepath.com/android/using-the-recyclerview

        // 1. Add RecyclerView AndroidX library to the Gradle build file
        // 2. Define a model class to use as the data source
        contactsList = createContacts()
        // 3. Add a RecyclerView to your activity to display the items
        // 4. Create a custom row layout XML file to visualize the single item
        // 5. Create a RecyclerView.Adapter and ViewHolder to render the item
        contactsAdapter = ContactsAdapter(this, contactsList)
        rvContacts.adapter = contactsAdapter

        // 6. Bind the adapter to the data source to populate the Recycler View
        rvContacts.layoutManager = LinearLayoutManager(this)

        Log.i(TAG, "On Create")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "On Destroy")
    }

    private fun createContacts(): MutableList<Person> {
        val contacts = mutableListOf<Person>()
        for(i in 1..100) {
            contacts.add(Person("Person $i", i))
        }
        return contacts
    }
    // Menu Created
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    // Menu Item Selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.i(TAG, "onOptionsItemSelected")
        if(item.itemId == R.id.mi_add) {
            val intent = Intent(this, AddContactActivity::class.java)
//            startActivity(intent)     // For one way communication
            startActivityForResult(intent, REQUEST_CODE) // Deprecated
//            registerForActivityResult(...)s
//            Log.i(TAG, "created activity inside onOptionsItemSelected")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // called when result received from child activity, Deprecated
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // we got data back successfully from the AddContactActivity
            val person = data?.getSerializableExtra(KEY_PERSON) as Person
            contactsList.add(5, person)
            // notify recycler-view of data-change, should use specific events instead
            contactsAdapter.notifyDataSetChanged()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}