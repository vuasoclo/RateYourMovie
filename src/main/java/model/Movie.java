package model;

import javafx.scene.image.ImageView;

public class Movie {
    private int id;
    private String name;
    private ImageView cover;
    private String director;
    private String genre;
    private double rating;
    private int year;
    private int numberOfRate;

    public Movie(int id, String name, ImageView cover, String director, String genre, double rating, int year, int numberOfRate){
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.numberOfRate = numberOfRate;
    }
    public Movie(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getCover() {
        return cover;
    }
    public void setCover(ImageView cover) {
        this.cover = cover;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfRate() {
        return numberOfRate;
    }

    public void setNumberOfRate(int numberOfRate) {
        this.numberOfRate = numberOfRate;
    }
}
