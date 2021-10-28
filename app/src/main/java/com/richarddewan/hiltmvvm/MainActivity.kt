package com.richarddewan.hiltmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import com.richarddewan.hiltmvvm.databinding.ActivityMainBinding
import com.richarddewan.hiltmvvm.ui.TaskViewModel
import com.richarddewan.hiltmvvm.ui.adaptor.TaskAdaptor
import com.richarddewan.hiltmvvm.util.ResultState
import com.richarddewan.hiltmvvm.util.event.TaskEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        bottomNav = binding.bottomNavigationView
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}