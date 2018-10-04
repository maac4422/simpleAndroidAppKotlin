package co.com.ceiba.libraryexample.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.transition.Slide
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import co.com.ceiba.libraryexample.Fragments.AboutFragment
import co.com.ceiba.libraryexample.Fragments.EventsFragment
import co.com.ceiba.libraryexample.Helpers.ActionBarDrawerToogleHelper
import co.com.ceiba.libraryexample.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var navigationView: NavigationView
    private  var actionBar: ActionBar? = null
    private lateinit var fragmentManager: FragmentManager
    private lateinit var actionBarDrawerToggleHelper: ActionBarDrawerToogleHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeVariables()
        setMainFragment()
        setIconMenu()

        mDrawerLayout.addDrawerListener(actionBarDrawerToggleHelper)
        actionBarDrawerToggleHelper.syncState()

        setTitleActionBar(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionBarDrawerToggleHelper.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        if(outState != null) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                outState.putString("DrawerState", "Opened")
            } else {
                outState.putString("DrawerState", "Closed")
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggleHelper.syncState()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var id : Int = menuItem.itemId
        if(id == R.id.nav_main)
        {
            navigateToFragment(EventsFragment(), "Noticias")
        }else if (id == R.id.nav_about){
            navigateToFragment(AboutFragment(), "Principal")
        }
        mDrawerLayout.closeDrawers()
        return true
    }


    private fun initializeVariables() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        fragmentManager = supportFragmentManager
        actionBar = supportActionBar
        navigationView = findViewById(R.id.nav_view)
        actionBarDrawerToggleHelper = ActionBarDrawerToogleHelper(this,mDrawerLayout,R.string.openDrawer,R.string.closeDrawer)
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame,AboutFragment(),"Principal")
        fragmentTransaction.commit()
    }

    private fun setTitleActionBar(bundle: Bundle?) {
        if(bundle != null){
            if(bundle.get("DrawerState") == "Opened"){
                supportActionBar?.setTitle(R.string.openDrawer)
            }else{
                supportActionBar?.setTitle(R.string.closeDrawer)
            }
        }else{
            supportActionBar?.setTitle(R.string.closeDrawer)
        }
    }

    private fun navigateToFragment (fragment: Fragment, tag: String) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame,fragment,tag)
        fragmentTransaction.replace(R.id.content_frame,fragment,tag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun setIconMenu() {
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }
}
