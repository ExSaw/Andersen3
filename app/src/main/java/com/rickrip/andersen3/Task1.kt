package com.rickrip.andersen3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rickrip.andersen3.fragments.*


class Task1 : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //disable night theme

        toolbar = findViewById(R.id.toolbar)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val buttonFab = findViewById<FloatingActionButton>(R.id.button_fab)

        viewPager.adapter = FragmentAdapter(this)

        TabLayoutMediator(tabLayout,viewPager){tab,position->

            when(position){
                0-> tab.text = "Amsterdam"
                1-> tab.text = "Poland"
                2-> tab.text = "Italy"
                3-> tab.text = "Colombia"
                4-> tab.text = "Madagascar"
                5-> tab.text = "Thailand"
                6-> tab.text = "Denmark"
                7-> tab.text = "Switzerland"
                else -> tab.text = "Tab ${position}"
            }
        }.attach()

      //  val drawable = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
      //  DrawableCompat.setTint(drawable!!, ContextCompat.getColor(this, R.color.white))

        buttonFab.setOnClickListener{view->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    class FragmentAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity){

        override fun getItemCount(): Int {
            return 8
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0-> AustriaFragment.newInstance("","")
                1-> PolandFragment.newInstance("","")
                2-> ItalyFragment.newInstance("","")
                3-> ColombiaFragment.newInstance("","")
                4-> MadagascarFragment.newInstance("","")
                5-> ThailandFragment.newInstance("","")
                6-> DenmarkFragment.newInstance("","")
                7-> SwitzerlandFragment.newInstance("","")
                else -> AustriaFragment.newInstance("","")
            }
        }

    }

}