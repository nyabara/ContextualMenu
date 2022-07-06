package com.example.contextualmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var actionMode: ActionMode? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionTextview: TextView = findViewById(R.id.action_mode_view)

        actionTextview.setOnLongClickListener { view ->
            // Called when the user long-clicks on someView
            when (actionMode) {
                null -> {
                    // Start the CAB using the ActionMode.Callback defined above
                    actionMode = startActionMode(actionModeCallback)!!
                    view.isSelected = true
                    true
                }
                else -> false
            }
        }
    }

    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.context_menu, menu)
            mode.setTitle("Choose your option")
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.delete -> {
                    //Do your functions here
                   Toast.makeText(application,"action mode delete",Toast.LENGTH_SHORT).show()
                    mode.finish() // Action picked, so close the CAB
                    true
                }
                R.id.share->{
                    //Do your functions here
                    Toast.makeText(application,"action mode share",Toast.LENGTH_SHORT).show()
                    mode.finish() // Action picked, so close the CAB
                    true
                }
                else -> false
            }
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }
}