package com.example.wenyou.criminalintent;

import android.content.Context;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by wenyou on 15/5/28.
 */
public class CrimeLab {
    private static CrimeLab crimeLab;
    private Context appContext;
    private LinkedList<Crime> crimes;

    public CrimeLab(Context appContext) {
        this.appContext = appContext;
        crimes = new LinkedList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            this.crimes.add(crime);
        }
    }

    public static CrimeLab get(Context c) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(c.getApplicationContext());
        }
        return crimeLab;
    }

    public LinkedList<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime (UUID id) {
        for (Crime c : this.crimes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
