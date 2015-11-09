package com.andryr.musicplayer.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andry on 09/11/15.
 */
public class RecyclerViewUtils {

    public static View inflateChild(LayoutInflater inflater, int layoutId, RecyclerView recyclerView) {
        View view = inflater.inflate(layoutId, recyclerView,
                false);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        view.setLayoutParams(recyclerView.getLayoutManager().generateLayoutParams(lp));
        return view;
    }
}
