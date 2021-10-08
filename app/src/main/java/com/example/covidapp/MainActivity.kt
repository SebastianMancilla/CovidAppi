package com.example.covidapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.fragments.CallsNumbersFragment
import com.example.covidapp.fragments.MapFragment
import com.example.covidapp.fragments.NewsFragment
import com.example.covidapp.fragments.StatsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  lateinit var  binding:ActivityMainBinding

    private val statsFragment = StatsFragment()
    private val mapFragment = MapFragment()
    private val newsFragment = NewsFragment()
    private val callsNumbersFragment = CallsNumbersFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(statsFragment)        //replace fragment and initialized in the item set

        //btn navigation bar
        btNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_stats -> replaceFragment(statsFragment)
                R.id.ic_map -> replaceFragment(mapFragment)
                R.id.ic_news -> replaceFragment(newsFragment)
                R.id.ic_callNumbers -> replaceFragment(callsNumbersFragment)
            }
            true
        }

    }

    //replace fragment in window when pushed button
    private  fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContainer, fragment)
        transaction.commit()
    }


}