package json.demolr8.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private int year;
    private String director;
    private String genre;
    private double rating;
    private String description;

    public Movie() {}

    public Movie( String title, int year, String director,
                 String genre, double rating, String description) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}