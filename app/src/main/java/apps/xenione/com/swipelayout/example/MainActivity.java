package apps.xenione.com.swipelayout.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import apps.xenione.com.swipelayout.R;
import apps.xenione.com.swipelayout.example.fragment.BothSideSwipeFragment;
import apps.xenione.com.swipelayout.example.fragment.HalfRightDragFrictionSwipeFragment;
import apps.xenione.com.swipelayout.example.fragment.HalfRightDragSwipeFragment;
import apps.xenione.com.swipelayout.example.fragment.HalfRightSwipeFragment;
import apps.xenione.com.swipelayout.example.fragment.RightSwipeFragment;
import apps.xenione.com.swipelayout.example.fragment.TwoStepRightSwipeFragment;
import apps.xenione.com.swipelayout.example.fragment.VerticalSwipeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showFragment(RightSwipeFragment.newInstance(), RightSwipeFragment.TAG);
        navigationView.setCheckedItem(R.id.nav_rightswipe);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.nav_rightswipe: {
                showFragment(RightSwipeFragment.newInstance(), RightSwipeFragment.TAG);
                break;
            }
            case R.id.nav_half_swipe: {
                showFragment(HalfRightSwipeFragment.newInstance(), HalfRightSwipeFragment.TAG);
                break;
            }
            case R.id.nav_two_step_swipe: {
                showFragment(TwoStepRightSwipeFragment.newInstance(), TwoStepRightSwipeFragment.TAG);
                break;
            }
            case R.id.nav_both_side_swipe: {
                showFragment(BothSideSwipeFragment.newInstance(), BothSideSwipeFragment.TAG);
                break;
            }
            case R.id.nav_right_drag_swipe: {
                showFragment(HalfRightDragSwipeFragment.newInstance(), HalfRightDragSwipeFragment.TAG);
                break;
            }
            case R.id.nav_right_drag_friction_swipe: {
                showFragment(HalfRightDragFrictionSwipeFragment.newInstance(), HalfRightDragFrictionSwipeFragment.TAG);
                break;
            }

            case R.id.nav_share: {
                launchSharer();
                break;
            }
            case R.id.nav_vertical_swipe: {
                showFragment(VerticalSwipeFragment.newInstance(), VerticalSwipeFragment.TAG);
                break;
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment prevFragment = fm.findFragmentByTag(tag);
        if (prevFragment != null) {
            return;
        }
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    private void launchSharer() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I found on google play that awesome swipe library at https://github.com/xenione/SwipeLayout. Have a look !!! ");
        sendIntent.setType("text/html");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
}
