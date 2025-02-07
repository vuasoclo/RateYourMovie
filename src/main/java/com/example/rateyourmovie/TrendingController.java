package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Movie;

public class TrendingController {
    @FXML
    private ImageView coverImage;

    @FXML
    private Label movieDirector;

    @FXML
    private Label movieName;

    @FXML
    private Label numberRate;

    @FXML
    private Label rateingPoint;

    @FXML
    private AnchorPane trendingModel;

    private AppController appController;
    private Movie movie_review_detail;

    private final String [] colors = {"#FEBA54", "#B9E5FF", "#BDB2FE", "#FB9AA8"};
    //set data
    public void setData(Movie movie, AppController appController) {
        movie_review_detail = movie;
        this.appController = appController;
        coverImage.setImage(movie.getCover());
        movieName.setText(movie.getName());
        movieDirector.setText(movie.getDirector());
        numberRate.setText(String.format("/%.1fk", (double) movie.getNumberOfRate() / 1000));
        rateingPoint.setText(String.format("%.2f", movie.getRating()));


        trendingModel.setStyle("-fx-background-color: " + colors[(int) (Math.random() * colors.length)]);
        movieName.setStyle("-fx-text-fill: black");
        movieDirector.setStyle("-fx-text-fill: black");
        numberRate.setStyle("-fx-text-fill: black");
        rateingPoint.setStyle("-fx-text-fill: black");
    }

    public void trendingButtonOnAction() {
        appController.showMovieDetail(movie_review_detail);
    }
}
