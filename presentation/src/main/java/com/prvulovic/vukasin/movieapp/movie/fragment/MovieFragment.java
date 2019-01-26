package com.prvulovic.vukasin.movieapp.movie.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.prvulovic.vukasin.domain.entity.cast.CastMember;
import com.prvulovic.vukasin.domain.entity.genre.Genre;
import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.movieapp.R;
import com.prvulovic.vukasin.movieapp.application.base.BaseFragment;
import com.prvulovic.vukasin.movieapp.configuration.glide.GlideApp;
import com.prvulovic.vukasin.movieapp.movie.viewmodel.MovieViewModel;
import com.prvulovic.vukasin.movieapp.utils.StringUtils;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

import static com.prvulovic.vukasin.movieapp.utils.Constants.IMAGE_BASE_URL;

public class MovieFragment extends BaseFragment {

    @Inject
    MovieViewModel viewModel;

    public static MovieFragment newInstance(long movieId){
        MovieFragment movieFragment=new MovieFragment();
        Bundle bundle=new Bundle();
        bundle.putString("instance_id",UUID.randomUUID().toString());
        bundle.putLong("movieId",movieId);
        movieFragment.setArguments(bundle);
        return movieFragment;
    }

    @BindView(R.id.fragment_movie_image)ImageView image;
    @BindView(R.id.fragment_movie_title)TextView title;
    @BindView(R.id.fragment_movie_rating)TextView rating;
    @BindView(R.id.fragment_movie_genres)TextView genres;
    @BindView(R.id.fragment_movie_description)TextView description;
    @BindView(R.id.fragment_movie_cast)TextView cast;
    @BindView(R.id.fragment_movie_progress_bar)FrameLayout loader;
    @BindView(R.id.fragment_movie_favourite)ImageView favourite;

    @Override
    protected void onViewBound(View view) {
        viewModel.setMovieId(getArguments().getLong("movieId"));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.getMoviePublisher()
                .subscribe(this::setMovie)
        };
    }

    @OnClick(R.id.fragment_movie_favourite) void onFavouriteClick(){
        favourite.setImageResource(R.drawable.ic_favourite_on);
        viewModel.favouriteClicked();
    }

    private void setMovie(Movie movie){
        GlideApp.with(this).load(IMAGE_BASE_URL+movie.getBackdropPath()).placeholder(R.drawable.image_placeholder).into(image);
        title.setText(movie.getTitle());
        rating.setText(StringUtils.floatToString(movie.getVoteAverage()));
        genres.setText(getGenresArray(movie.getGenreList()));
        description.setText(getDescription(movie.getDescription()));
        cast.setText(getCast(movie.getCast()));
        loader.setVisibility(View.GONE);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_movie;
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

    private String getDescription(String description){
        return "Description:" + "\n\n" + description;
    }

    private String getCast(List<CastMember> cast){
        StringBuilder builder=new StringBuilder();
        builder.append("Cast:").append("\n\n");
        if (cast!=null && !cast.isEmpty()){
            for (CastMember castMember : cast) {
                builder.append(castMember.getCharacter()).append(" - ").append(castMember.getName()).append("\n");
            }
        }
        return builder.toString();
    }

}
