package com.ciru.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity() {

    lateinit var listItems: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        listItems= findViewById(R.id.listNotes)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }

        /*LIst View uses ArrayAdapter to populate the list
        *
        * Recycler View uses RecyclerViewAdapter to populate its Views*/

        //In the previous definition you specified that listItems is a List View thats why it wanted to overide ListAdapter
        // val listItems = findViewById<ListView>(R.id.listItems)
        //That's it
//        question: mbona hii findviewById i cant just put the name only?? nlikua
//        najaribu kueka plugin but haileti so nkaopt kueka the java way

        //To get a view, we use a uneque identifier, in tis case its an Id, thats why we use R.id.xxx. In case of view and DataBinding, A binding object generates the name by using the Id
        //Q: Which Plugin ndio ilikataa
//        a min niitafute nmesahau jina 'kotlin-android-extensions' hii
        //Kotlin-android-extension was removed. The feature you are referring was called kotlin-synthetics. Right now the best way to use is viewBinding or DataBinding
//        oh poa thanks
//        sina any other question: Wazi Wazi.
// Unatumianga Emulator ama Phone ? phone. Try Running the app


        listItems.layoutManager = LinearLayoutManager(this)

        listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)

        //Try Running

    }

    override fun onResume() {
        super.onResume()
//        when the user has entered a data in the title and hits back button the data is going to be saved because of the below line.
            listItems.adapter?.notifyDataSetChanged()
    }
}