package com.example.wenyou.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by wenyou on 15/5/28.
 */
public class CrimeListFragment
        extends ListFragment {
    private static final String TAG = "CrimeListFragment";
    private static final int REQUEST_CRIME = 1;

    private LinkedList<Crime> crimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.getActivity().setTitle(R.string.crimes_title);
        this.crimes = CrimeLab.get(super.getActivity()).getCrimes();

        ArrayAdapter<Crime> adapter = new CrimeAdapter(crimes);
        super.setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)super.getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = ((CrimeAdapter)super.getListAdapter()).getItem(position);

        Intent intent = new Intent(super.getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
        super.startActivity(intent);
    }

    private class CrimeAdapter
            extends ArrayAdapter<Crime> {
        public CrimeAdapter(LinkedList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }
            Crime crime = getItem(position);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(crime.getTitle());
            TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextVie);
            dateTextView.setText(crime.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(crime.isSolved());
            return convertView;
        }
    }
}
