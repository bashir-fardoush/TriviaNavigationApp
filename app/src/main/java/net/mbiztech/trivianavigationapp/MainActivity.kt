package net.mbiztech.trivianavigationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import net.mbiztech.trivianavigationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var homeDrawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
     //   binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        homeDrawerLayout = binding.homeDrawerLayout


        val navController = this.findNavController(R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this,navController,homeDrawerLayout)
        NavigationUI.setupWithNavController(binding.homeNavView, navController)

        navController.addOnDestinationChangedListener{ nc: NavController, nd: NavDestination, args :Bundle? ->

            if (nd.id == nc.graph.startDestination){
                homeDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }else{
                homeDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController,homeDrawerLayout)
    }
}
