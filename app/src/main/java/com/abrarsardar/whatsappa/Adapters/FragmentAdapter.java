package com.abrarsardar.whatsappa.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abrarsardar.whatsappa.Fragments.CallFragment;
import com.abrarsardar.whatsappa.Fragments.ChatFragment;
import com.abrarsardar.whatsappa.Fragments.StatusFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChatFragment();

            case 1:
                return new StatusFragment();

            case 2:
                return new CallFragment();

            default:
                return new ChatFragment();

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0) {
            title = "CHATS";

        } else if(position == 1) {
            title = "STATUS";

        } else if(position == 2) {
            title = "CALLS";

        }
        return title;
    }


}
