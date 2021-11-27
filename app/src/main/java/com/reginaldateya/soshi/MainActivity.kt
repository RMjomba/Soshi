package com.reginaldateya.soshi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reginaldateya.soshi.fragments.DashboardFragment
import com.reginaldateya.soshi.fragments.JobsPortalFragment
import com.reginaldateya.soshi.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_assessment -> {

                moveToFragment(DashboardFragment())
                return@OnNavigationItemSelectedListener true

            }
            R.id.nav_work -> {
                moveToFragment(JobsPortalFragment())
                return@OnNavigationItemSelectedListener true

            }

            R.id.nav_settings -> {
                moveToFragment(SettingsFragment())
                return@OnNavigationItemSelectedListener true


            }
        }


        false
    }



    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))


            val navView: BottomNavigationView = findViewById(R.id.nav_view)


            navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

            moveToFragment(DashboardFragment())
        }

    private fun moveToFragment(fragment : Fragment) {
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragment_container, fragment)
        fragmentTrans.commit()

    }
}



