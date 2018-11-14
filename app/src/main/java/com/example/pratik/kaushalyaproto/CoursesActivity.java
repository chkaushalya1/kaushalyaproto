package com.example.pratik.kaushalyaproto;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.pratik.kaushalyaproto.CoursesFragments.AllCoursesFragment;
import com.example.pratik.kaushalyaproto.CoursesFragments.MyCoursesFragment;
import com.example.pratik.kaushalyaproto.adapters.TabAdapter;

public class CoursesActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyCoursesFragment(), "My Courses");
        adapter.addFragment(new AllCoursesFragment(), "All Courses");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
