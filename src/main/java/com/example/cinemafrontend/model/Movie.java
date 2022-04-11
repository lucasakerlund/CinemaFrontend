package com.example.cinemafrontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int movieId;
    private String title;
    private String description;
    private int ageRestriction;
    private String categoryCoverImage;
    private String coverImage;

    private List<String> genres;

    public Movie(@JsonProperty("movie_id") int movieId,
                 @JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("age_restriction") int ageRestriction,
                 @JsonProperty("category_cover_image") String categoryCoverImage,
                 @JsonProperty("cover_image") String coverImage) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.ageRestriction = ageRestriction;
        this.categoryCoverImage = categoryCoverImage;
        this.coverImage = coverImage;
        genres = new ArrayList<>();
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public String getCategoryCoverImage() {
        return categoryCoverImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public List<String> getGenres(){
        return genres;
    }

    public void addGenre(String genre){
        if(genres.indexOf(genre) != -1){
            return;
        }
        genres.add(genre);
    }

    public void removeGenre(String genre){
        genres.remove(genre);
    }

}
