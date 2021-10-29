package com.ciru.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jwhh.notekeeper.CourseRecyclerAdapter

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var nav_view: NavigationView
    lateinit var drawerLayout : DrawerLayout
    lateinit var listItems: RecyclerView
//    val navController = findNavController(R.id.nav_host_fragment)

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val noteLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val noteRecyclerAdapter by lazy {
//        by lazy - it works with val properties only and it delays the property's body execution until its first use
        NoteRecyclerAdapter(this, DataManager.notes.toList())
    }

    private  val courseLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    private val courseRecyclerAdapter by lazy {
        CourseRecyclerAdapter(this, DataManager.courses.values.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        listItems = findViewById(R.id.listItems)
        nav_view = findViewById(R.id.nav_view)
        drawerLayout  = findViewById(R.id.drawer_layout)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { _ ->
            startActivity(Intent(this, NoteActivity::class.java))
        }

//       val nav_view: NavigationView = findViewById(R.id.nav_view)

        var toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        displayNotes()
//        DataManager.notes is where you can find our notes collection
//        the adapter handles the details of displaying the content & handles the details when the user interacts with content inside  the recycler view

//        setNavigationItemSelectedListener is used when the user has clicked the navigation item
        nav_view.setNavigationItemSelectedListener (this)

    }

    private fun displayNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_notes).isChecked = true // to make notes option in the navigation drawer is  highlighted when the user opens the app
    }

    private  fun displayCourses(){
        listItems.layoutManager = courseLayoutManager
        listItems.adapter = courseRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_courses).isChecked = true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
//        it saves the note that has been added when the user clicks the back button
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_notes -> {
                displayNotes()
            }

            R.id.nav_courses -> {
                displayCourses()
            }

            R.id.nav_share -> {
                handleSelection("share")
            }

            R.id.nav_send -> {
                handleSelection("send")
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(listItems, message, Snackbar.LENGTH_LONG).show()
    }
}