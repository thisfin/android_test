package com.example.wenyou.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by wenyou on 15/6/1.
 */
public class CrimePagerActivity
        extends FragmentActivity {
    private ViewPager viewPager;
    private LinkedList<Crime> crimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewPager = new ViewPager(this);
        this.viewPager.setId(R.id.viewPager);
        super.setContentView(this.viewPager);

        this.crimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = super.getSupportFragmentManager();
        this.viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = crimes.get(i);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });

        UUID crimeId = (UUID)super.getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < this.crimes.size(); i++) {
            if (crimes.get(i).getId().equals(crimeId)) {
                this.viewPager.setCurrentItem(i);
                break;
            }
        }

        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                Crime crime = crimes.get(i);
                if (crime.getTitle() != null) {
                    setTitle(crime.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
