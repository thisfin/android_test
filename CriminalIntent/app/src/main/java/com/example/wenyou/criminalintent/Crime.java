package com.example.wenyou.criminalintent;

import java.util.UUID;

/**
 * Created by wenyou on 15/5/27.
 */
public class Crime {
    private UUID mId;
    private String mTitle;

    public Crime() {
        this.mId = UUID.randomUUID();
    }
}
