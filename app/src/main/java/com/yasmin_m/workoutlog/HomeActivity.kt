package com.yasmin_m.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bnvHome: BottomNavigationView
    lateinit var fcvHome: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castViews()
        setupButtomNav()
    }
    fun castViews(){
        bnvHome = findViewById(R.id.botton_navigation)
        fcvHome = findViewById(R.id.fragmentContainerView)
    }

    fun setupButtomNav(){
        bnvHome.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan -> {
                   var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track -> {
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, TrackFragment())
                    transaction.commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}