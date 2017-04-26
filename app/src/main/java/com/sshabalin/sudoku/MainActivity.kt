package com.sshabalin.sudoku

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_field)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId ?: R.id.fragment
        when (id) {
            R.id.option -> setContentView(R.layout.fragment_options)
            R.id.fragment -> setContentView(R.layout.fragment_field)

            else -> {
                setContentView(R.layout.fragment_field)
            }
        }


        return true
    }
}
