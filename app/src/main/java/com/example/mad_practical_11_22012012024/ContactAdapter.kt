package com.example.mad_practical_11_22012012024

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter(context: Context, private val array: ArrayList<Contact>) :  RecyclerView.Adapter<ContactAdapter.PersonViewHolder>() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

        val contact = getItem(position) // Access the Contact object from the adapter

        view.findViewById<TextView>(R.id.name1).text = contact?.name
        view.findViewById<TextView>(R.id.no1).text = contact?.phoneno
        view.findViewById<TextView>(R.id.email1).text = contact?.emailid
        view.findViewById<TextView>(R.id.address1).text = contact?.address

        val button1: Button = view.findViewById(R.id.button1)

        button1.setOnClickListener {
            // Start the MapsActivity when button1 is clicked
            val intent = Intent(context, MapsActivity::class.java)
            context.startActivity(intent)
        }

        return view
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.PersonViewHolder {
        return PersonViewHolder(View)
    }

    override fun onBindViewHolder(holder: ContectViewHolder, position: Int) {
        with(holder) {
            with(array[position]) {
                bindingView.findViewById<TextView>(R.id.textView_phone_no).text = this.phoneNo
                bindingView.findViewById<TextView>(R.id.textView_name).text = this.name
                bindingView.findViewById<TextView>(R.id.textView_email).text = this.emailId
                bindingView.findViewById<TextView>(R.id.textView_address).text = this.address
                val obj = this as Serializable
                bindingView.findViewById<Button>(R.id.button_map).setOnClickListener {
                    Intent(this@PersonAdapter.context, MapActivity::class.java).apply {
                        putExtra("Object", obj)
                        this@PersonAdapter.context.startActivity(this)
                    }
                    //Toast.makeText(this@PersonAdapter.context, "Clicked on "+binding.textViewName+", Location: Lat:"+this.Latitude+"Long:"+this.Longitude, Toast.LENGTH_SHORT).show()
                }
                bindingView.findViewById<Button>(R.id.button_delete).setOnClickListener {
                    context.deletePerson(holder.adapterPosition)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}