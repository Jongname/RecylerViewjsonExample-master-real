package com.example.kccistc.recylerviewjsonexample;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab);
        appBarLayout = findViewById(R.id.appbar);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new Fragment_1(), "고궁");
//        adapter.AddFragment(new Fragment_2(), "박물관");
//        adapter.AddFragment(new Fragment_3(), "놀이시설");

        //adapter setup

        viewPager.setAdapter(adapter);

        TabLayout tab = findViewById(R.id.tab);
        //공간이 부족하면 스크롤 해서 넘어갈 수 있도록 해주는 기능
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setupWithViewPager(viewPager);
    }
}

