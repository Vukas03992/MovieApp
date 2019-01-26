package com.prvulovic.vukasin.movieapp.movie.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prvulovic.vukasin.domain.entity.genre.Genre;
import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.movieapp.R;
import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.configuration.glide.GlideApp;
import com.prvulovic.vukasin.movieapp.movie.viewmodel.MovieListViewModel;
import com.prvulovic.vukasin.movieapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.prvulovic.vukasin.movieapp.utils.Constants.IMAGE_BASE_URL;

@FragmentScope
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieHolder> {

    private static final String TAG=MovieListAdapter.class.getSimpleName();

    private List<Movie> data=new ArrayList<>();

    @Inject Context context;
    @Inject Provider<MovieListViewModel> viewModelProvider;

    @Inject
    public MovieListAdapter() { }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_movie_list,viewGroup,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        movieHolder.setMovie(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Movie> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    protected class MovieHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_movie_list_image)ImageView movieImage;
        @BindView(R.id.item_movie_list_title)TextView movieTitle;
        @BindView(R.id.item_movie_list_rating)TextView rating;
        @BindView(R.id.item_movie_list_genres)TextView genres;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            Typeface muliBold=ResourcesCompat.getFont(context,R.font.muli_bold);
            movieTitle.setTypeface(muliBold);
            Typeface muliRegular=ResourcesCompat.getFont(context,R.font.muli_regular);
            rating.setTypeface(muliRegular);
            genres.setTypeface(muliRegular);

            itemView.setOnClickListener(v->viewModelProvider.get().onMovieClick(data.get(getLayoutPosition()).getId()));
            itemView.setOnLongClickListener(v->viewModelProvider.get().removeFavourite(data.get(getLayoutPosition()).getId()));
        }

        public void setMovie(Movie movie) {
            movieTitle.setText(movie.getTitle());
            rating.setText(StringUtils.floatToString(movie.getVoteAverage()));
            genres.setText(getGenresArray(movie.getGenreList()));
            setImage(IMAGE_BASE_URL+movie.getPosterPath());
        }

        private void setImage(String url){
            GlideApp.with(context).load(url).placeholder(R.drawable.image_placeholder).into(movieImage);
        }

        private String getGenresArray(List<Genre> genreList){
            StringBuilder builder=new StringBuilder();
            if (genreList!=null && !genreList.isEmpty()){
                builder.append(genreList.get(0).getName());
                for (int i = 1; i < genreList.size(); i++) {
                    builder.append(", ");
                    builder.append(genreList.get(i).getName());
                }
            }
            return builder.toString();
        }
    }
}
