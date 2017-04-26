package com.sshabalin.sudoku

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.view.*
import android.widget.*
import layout.Options



class MainActivity : AppCompatActivity() {

    internal inner class NavItem(var mTitle: String, var mSubtitle: String, var mIcon: Int)


    internal inner class DrawerListAdapter(var mContext: Context, var mNavItems: ArrayList<NavItem>) : BaseAdapter() {

        override fun getCount(): Int {
            return mNavItems.size
        }

        override fun getItem(position: Int): Any {
            return mNavItems[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View

            if (convertView == null) {
                view = (mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.drawer_item, null)
            } else {
                view = convertView
            }

            val iconView = view.findViewById(R.id.icon) as ImageView

            (view.findViewById(R.id.title) as TextView).text = mNavItems[position].mTitle
            (view.findViewById(R.id.subTitle) as TextView).text = mNavItems[position].mSubtitle
            iconView.setImageResource(mNavItems[position].mIcon)

            return view
        }
    }

    var mDrawerList: ListView? = null
    var mDrawerPane: RelativeLayout? = null
    private var mDrawerLayout: DrawerLayout? = null

    private var mNavItems = ArrayList<NavItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavItems.add(NavItem("Home", "Meetup destination", R.mipmap.ic_launcher))
        mNavItems.add(NavItem("Preferences", "Change your preferences", R.mipmap.ic_launcher))
        mNavItems.add(NavItem("About", "Get to know about us", R.mipmap.ic_launcher))

        // DrawerLayout
        mDrawerLayout = findViewById(R.id.drawerLayout) as DrawerLayout

        // Populate the Navigation Drawer with options
        mDrawerPane = findViewById(R.id.drawerPane) as RelativeLayout
        mDrawerList = findViewById(R.id.navList) as ListView
        val adapter = DrawerListAdapter(this, mNavItems)
        mDrawerList!!.adapter = adapter

        // Drawer Item click listeners
        mDrawerList!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> selectItemFromDrawer(position) }
    }

    private fun selectItemFromDrawer(position: Int) {
        val fragment = Options();

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main, fragment)
                .commit()

        mDrawerList!!.setItemChecked(position, true)
        title = mNavItems[position].mTitle

        // Close the drawer
        mDrawerLayout!!.closeDrawer(mDrawerPane)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId ?: R.id.fragment
        when (id) {
            R.id.option -> setContentView(R.layout.fragment_options)
            R.id.fragment -> setContentView(R.layout.fragment_field)

            else -> {
                setContentView(R.layout.fragment_field)
            }
        }


        return true
    }
}
