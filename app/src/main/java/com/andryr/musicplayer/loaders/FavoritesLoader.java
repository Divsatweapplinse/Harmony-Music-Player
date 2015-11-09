package com.andryr.musicplayer.loaders;

import android.content.Context;
import android.database.Cursor;

import com.andryr.musicplayer.favorites.FavoritesDbHelper;
import com.andryr.musicplayer.model.Song;

import java.util.List;

/**
 * Created by Andry on 09/11/15.
 */
public class FavoritesLoader extends BaseLoader<List<Song>> {
    public FavoritesLoader(Context context) {
        super(context);


    }

    @Override
    public List<Song> loadInBackground() {
        FavoritesDbHelper dbHelper = new FavoritesDbHelper(getContext());
        List<Song> favorites = dbHelper.read();
        dbHelper.close();
        return favorites;
    }


}
