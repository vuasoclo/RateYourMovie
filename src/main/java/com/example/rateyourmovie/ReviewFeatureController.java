package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Movie;

public class ReviewFeatureController {

    @FXML
    private ImageView accImg;

    @FXML
    private Label accName;

    @FXML
    private Label accRating5;

    @FXML
    private Label accReview;

    @FXML
    private Label fromNumverOfRating;

    @FXML
    private Label movieDirector;

    @FXML
    private Label movieGenre;

    @FXML
    private ImageView movieImg;

    @FXML
    private Label movieName;

    @FXML
    private Label movieYear;

    @FXML
    private Label ratingPoint;
    @FXML
    private Label e1, e2;

    public void setData(Movie movie){
        movieImg.setImage(movie.getCover().getImage());
        movieName.setText(movie.getName());
        movieDirector.setText(movie.getDirector());
        movieYear.setText(String.valueOf(movie.getYear()));
        fromNumverOfRating.setText(String.format("from %d ratings", movie.getNumberOfRate()));
        ratingPoint.setText(String.format("%.2f", movie.getRating()));
        movieGenre.setText(movie.getGenre());

        movieName.setStyle("-fx-text-fill: #bf671e");
        movieDirector.setStyle("-fx-text-fill: #fe9731");
        movieYear.setStyle("-fx-text-fill: black");
        fromNumverOfRating.setStyle("-fx-text-fill: black");
        ratingPoint.setStyle("-fx-text-fill: black");
        movieGenre.setStyle("-fx-text-fill: black");
        accName.setStyle("-fx-text-fill: #ee7912");
        accRating5.setStyle("-fx-text-fill: black");
        accReview.setStyle("-fx-text-fill: black");
        e1.setStyle("-fx-text-fill: black");
        e2.setStyle("-fx-text-fill: black");
    }

    public void movieOnActionButton(){

    }
}
