package ro.ubb.cristian.examskeletonimproved.section2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ro.ubb.cristian.examskeletonimproved.R;
import ro.ubb.cristian.examskeletonimproved.section2.subSection2_1.ItemListFragment;

public class SectionTwoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Integer lastUsedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lastUsedFragment = R.id.nav_item_list;
        navigationView.setCheckedItem(lastUsedFragment);
        switchScreen(lastUsedFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.section_two, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switchScreen(id);
        return true;
    }

    private void switchScreen(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_item_list) {
            fragment = new ItemListFragment();
            lastUsedFragment = R.id.nav_item_list;
        } else if (id == R.id.nav_item_list_2) {
            fragment = new ro.ubb.cristian.examskeletonimproved.section2.subSection2_2.ItemListFragment();
            lastUsedFragment = R.id.nav_item_list_2;
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("repository", carRepository);
//            fragment.setArguments(bundle);

//        } else if (id == R.id.nav_my_cars) {
//            fragment = new MyCarsFragment();
//            lastUsedFragment = R.id.nav_my_cars;
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("repository", myCarRepository);
//            fragment.setArguments(bundle);
//        } else if (id == R.id.nav_clear_cars){
//            AppDatabase database = DatabaseProvider.getDatabaseInstance(this);
//            CarDao carDao = database.getCarDao();
//            carDao.deleteAll();
//            myCarRepository.setCars(carDao.findAll());
//            myCarRepository.notifyObservers();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
