package co.com.ceiba.libraryexample.Helpers

import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View

class ActionBarDrawerToogleHelper(private var hostActivity: AppCompatActivity,
                                  drawerLayout: DrawerLayout,
                                  private var openedResource: Int,
                                  private var closedResource: Int) :
        ActionBarDrawerToggle(hostActivity,drawerLayout,openedResource,closedResource) {


    override fun onDrawerOpened(drawerView: View) {
        super.onDrawerOpened(drawerView)
        hostActivity.supportActionBar!!.setTitle(openedResource)
    }

    override fun onDrawerClosed(drawerView: View) {
        super.onDrawerClosed(drawerView)
        hostActivity.supportActionBar!!.setTitle(closedResource)
    }

}

