package com.jkdprojects.j_notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var navcontoller : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navcontoller=findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navcontoller)
    }

    override fun onNavigateUp(): Boolean {
        return navcontoller.navigateUp() || super.onNavigateUp()
    }
}