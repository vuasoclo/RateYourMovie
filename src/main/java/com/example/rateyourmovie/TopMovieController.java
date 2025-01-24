package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Movie;

import java.text.SimpleDateFormat;

public class TopMovieController {
    @FXML
    private Label movieDirector;

    @FXML
    private Label movieTopId;

    @FXML
    private ImageView movieImg;

    @FXML
    private Label movieName;

    @FXML
    private Label movieYear;

    @FXML
    private Label numberRate;

    @FXML
    private Label numberReview;

    @FXML
    private Label rateingPoint;

    private AppController appController;
    private Movie movie_review_detail;

    public void setData(Movie movie, int idx, AppController appController) {
        movie_review_detail = movie;
        this.appController = appController;

        movieImg.setImage(movie.getCover());
        movieName.setText(movie.getName());
        movieDirector.setText(movie.getDirector());
        //handle year
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(movie.getRelease_date());
        movieYear.setText(year);

        numberRate.setText(String.format("/%.1fk", (double) movie.getNumberOfRate() / 1000));
        movieTopId.setText(String.valueOf(idx));

        rateingPoint.setText(String.format("%.2f", movie.getRating()));
        movieName.setStyle("-fx-text-fill: #bf671e");
        movieDirector.setStyle("-fx-text-fill: #fe9731");
        movieYear.setStyle("-fx-text-fill: black");
        numberRate.setStyle("-fx-text-fill: black");
        numberReview.setStyle("-fx-text-fill: black");
        rateingPoint.setStyle("-fx-text-fill: black");
        movieTopId.setStyle("-fx-text-fill: black");

    }
    public void TopMovieButtonOnAction() {
        // switch page
        appController.showMovieDetail(movie_review_detail);
    }

}
