package com.example.ali.panawachat.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ali.panawachat.Chat;
import com.example.ali.panawachat.Contact;
import com.example.ali.panawachat.R;

/**
 * Created by ali on 12/01/16.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Chat();
            case 1:
                return new Contact();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
