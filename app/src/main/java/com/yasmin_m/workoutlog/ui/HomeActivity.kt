package com.yasmin_m.workoutlog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yasmin_m.workoutlog.R
import com.yasmin_m.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtomNav()
    }

    fun setupButtomNav(){
        binding.bottonNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, PlanFragment()).commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, TrackFragment()).commit()
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