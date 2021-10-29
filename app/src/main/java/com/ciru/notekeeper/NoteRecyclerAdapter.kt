package com.ciru.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

//  this is an adapter class and it extends a base class RecyclerView.Adapter

class NoteRecyclerAdapter(private val context:Context , private  val notes : List<NoteInfo>) :
        RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {

//    its used to put data in the holder
   private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        used to display individual items
//        parent: ViewGroup is where the resource is going to be inflated into
//        boolean value indicates whether the inflated layout will be attached to the parent(true) or not(false)
        val itemView =layoutInflater.inflate(R.layout.item_note_list, parent, false)
        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        it binds the data with the Viewholder
        val note = notes[position]
        holder.textCourse?.text = note.course?.title
        holder.textTitle.text = note.title
//        to get to know the position of the viewholder
        holder.notePosition = position
    }

    override fun getItemCount() = notes.size


//    viewHolder below is an extended class since we need a view holder and data  and we can have both classes at the same place
//    we put inner class so that this class will be able to access the properties of parent class

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textCourse = itemView.findViewById<TextView>(R.id.textCourse)
        var textTitle = itemView.findViewById<TextView>(R.id.textTitle)
        var  notePosition = 0
        init {
//            init means initializer
//            here when the item is selected it will take the user to noteActivity
            itemView.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }

        }

    }
}