package com.example.helloandroid

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
private const val TAG = "ContactsAdapter"
class ContactsAdapter(val context: Context, val contacts: List<Person>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    // Create a new view - EXPENSIVE, called everytime a new list-item is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        Log.i(TAG, "onCreateViewHolder")
        // layout inflator converts xml-layouts to views
        // R = 'res' folder
        val view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }
    // Bind the data at the position into the ViewHolder, everytime new data needs to be bound to the ViewHolder(item)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.i(TAG, "onBindViewHolder: $position")
        val contact = contacts[position]
        holder.bind(contact)
    }
    // return how many items are in the data-set
    override fun getItemCount() = contacts.size

    // returns, to onCreateViewHolder, an enum representing the type of element at the 'position'
    // could be used to inflate different types of views based on position
//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }

    // wrapper around single item of a list
    inner class ViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvAge  = itemView.findViewById<TextView>(R.id.tvAge)

        // initialize view data
        fun bind(contact: Person) {
            // bind the data in the contact into the view
            tvName.text = contact.name
            tvAge.text = "Age: ${contact.age}"
        }
    }
}
