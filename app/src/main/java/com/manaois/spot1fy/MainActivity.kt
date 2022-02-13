package com.manaois.spot1fy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(main_bottom_nav, navHostFragment.navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            main_bottom_nav.visibility = when(destination.id) {
                R.id.rankingsFragment,
                    R.id.searchFragment,
                    R.id.favouritesFragment -> View.VISIBLE
                else -> {
                    window.apply {
                        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        statusBarColor = Color.TRANSPARENT
                    }
                    View.GONE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}