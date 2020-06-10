package kz.realibi.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kz.realibi.news.fragments.NewNewsFragment;
import kz.realibi.news.fragments.NewsFragment;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new NewsFragment();
        setFragment(fragment);



        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home: {
                        Fragment fragment = new NewsFragment();
                        getSupportActionBar().show();
                        setFragment(fragment);
                        break;
                    }
                    case R.id.navigation_addNews: {
                        Fragment fragment = new NewNewsFragment();
                        getSupportActionBar().hide();
                        setFragment(fragment);
                        break;
                    }
//                    case R.id.navigation_settings: {
//                        Fragment fragment = new InfoFragment();
//                        getSupportActionBar().hide();
//                        setFragment(fragment);
//                        break;
//                    }
                    }
                return true;
                }
            });



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Все записи");
        toolbar.setSubtitleTextColor(R.color.white);
        setSupportActionBar(toolbar);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.uiFragmentHolder, fragment);
        fragmentTransaction.commit();
    }
}
