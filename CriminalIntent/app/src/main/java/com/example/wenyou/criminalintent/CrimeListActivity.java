package com.example.wenyou.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by wenyou on 15/5/28.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment crateFragment() {
        return new CrimeListFragment();
    }
}
