package com.andryr.musicplayer.loaders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.andryr.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andry on 09/11/15.
 */
public class PlaylistLoader extends BaseLoader<List<Song>> {
    private static final String[] sProjection = {
            MediaStore.Audio.Playlists.Members.AUDIO_ID,
            MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.TRACK};

    private long mPlaylistId;

    public PlaylistLoader(Context context, long playlistId) {
        super(context);
        mPlaylistId = playlistId;
    }

    @Override
    public List<Song> loadInBackground() {
        List<Song> playlist = new ArrayList<>();
        Cursor cursor = getPlaylistCursor();
        if (cursor != null && cursor.moveToFirst()) {
            int idCol = cursor
                    .getColumnIndex(MediaStore.Audio.Playlists.Members.AUDIO_ID);
            if (idCol == -1) {
                idCol = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            }
            int titleCol = cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistCol = cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumCol = cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int albumIdCol = cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int trackCol = cursor
                    .getColumnIndex(MediaStore.Audio.Media.TRACK);

            do {
                long id = cursor.getLong(idCol);
                String title = cursor.getString(titleCol);

                String artist = cursor.getString(artistCol);

                String album = cursor.getString(albumCol);

                long albumId = cursor.getLong(albumIdCol);

                int track = cursor.getInt(trackCol);


                playlist.add(new Song(id, title, artist, album, albumId, track));

            } while (cursor.moveToNext());

        }

        if (cursor != null) {
            cursor.close();
        }


        return playlist;
    }

    private Cursor getPlaylistCursor() {
        Uri musicUri = MediaStore.Audio.Playlists.Members.getContentUri(
                "external", mPlaylistId);

        return getContext().getContentResolver().query(musicUri,
                sProjection, getSelectionString(), getSelectionArgs(),
                MediaStore.Audio.Playlists.Members.PLAY_ORDER);
    }
}
