package com.andryr.musicplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andryr.musicplayer.R;
import com.andryr.musicplayer.model.Genre;
import com.andryr.musicplayer.widgets.FastScroller;

import java.util.Collections;
import java.util.List;

/**
 * Created by Andry on 28/10/15.
 */
public class GenreListAdapter extends BaseAdapter<GenreListAdapter.GenreViewHolder>
        implements FastScroller.SectionIndexer {


    private List<Genre> mGenreList = Collections.emptyList();

    public void setData(List<Genre> data) {
        mGenreList = data;
    }

    @Override
    public int getItemCount() {
        return mGenreList.size();
    }

    @Override
    public void onBindViewHolder(GenreViewHolder viewHolder, int position) {
        Genre genre = mGenreList.get(position);
        viewHolder.vName.setText(genre.getName());

    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.genre_list_item, parent, false);
        return new GenreViewHolder(itemView);
    }

    @Override
    public String getSectionForPosition(int position) {
        return mGenreList.get(position).getName().substring(0, 1);

    }

    class GenreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView vName;


        public GenreViewHolder(View itemView) {
            super(itemView);
            vName = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            triggerOnItemClickListener(position, v);
        }
    }
}