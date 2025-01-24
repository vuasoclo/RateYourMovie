package com.example.rateyourmovie;

import model.Movie;
import model.ReviewFilm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerMovie {
    private List<Movie> movies;
    private List<ReviewFilm> reviewFilms;

    public ManagerMovie() {
        this.movies = new ArrayList<>();
        this.reviewFilms = new ArrayList<>();
    }

    public void addMovieList(List<Movie> movies){
        this.movies.addAll(movies);
    }
    public void addReviewFilmList(List<ReviewFilm> reviewFilms){
        this.reviewFilms.addAll(reviewFilms);
    }

    public List<Movie> searchMovieByName(String name){
        return this.movies.stream()
                .filter(movie -> movie.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMovieByGenre(List<Movie> preList, List<String> includeGenre, List<String> excludeGenre) {
        return preList.stream()
                .filter(movie -> includeGenre.isEmpty() || movie.getGenres().containsAll(includeGenre)) // Include movies with all genre in includeGenre
                .filter(movie -> excludeGenre.isEmpty() || excludeGenre.stream().noneMatch(movie.getGenres()::contains)) // Exclude movies with any genre in excludeGenre
                .collect(Collectors.toList());
    }

    public List<Movie> rangeMovieByYear(List<Movie> preList, int start, int end) {
        return preList.stream()
                .filter(movie -> {
                    Date releaseDate = movie.getRelease_date(); // Lấy ngày phát hành (Date)
                    int year = releaseDate.getYear() + 1900; // getYear() trả về năm từ 1900, nên cần cộng thêm 1900
                    return year >= start && year <= end;
                })
                .collect(Collectors.toList());
    }
    public List<Movie> trendingMovie(){
        // get 10 movie recently added
        return this.movies.stream()
                .sorted((m1, m2) -> m2.getRelease_date().compareTo(m1.getRelease_date()))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Movie> chartMovie(){
        return this.movies.stream()
                .sorted((m1, m2) -> Double.compare((m2.getNumberOfRate()/1000) / m2.getRating(),
                        (m1.getNumberOfRate()/1000) / m1.getRating()))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<ReviewFilm> featureReviewMoivie(){
        return this.reviewFilms.stream()
            .filter(reviewFilm -> reviewFilm.getReview().length() >= 1000)
            .sorted((m1, m2) -> Double.compare(Math.random(), Math.random())) // random sorting
            .limit(10)
            .collect(Collectors.toList());
    }
}
