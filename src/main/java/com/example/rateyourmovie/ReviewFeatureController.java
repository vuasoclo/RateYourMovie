package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Movie;

import java.text.SimpleDateFormat;

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
    private HBox hBox1;

    @FXML
    private HBox hBox2;

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
        movieImg.setImage(movie.getCover());
        movieName.setText(movie.getName());
        movieDirector.setText(movie.getDirector());
        //handle year
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(movie.getRelease_date());
        movieYear.setText(year);

        fromNumverOfRating.setText(String.format("from %d ratings", movie.getNumberOfRate()));
        ratingPoint.setText(String.format("%.2f", movie.getRating()));
        //handle genre
        StringBuilder genres = new StringBuilder();
        for (String genre : movie.getGenres()) {
            genres.append(genre).append(", ");
        }
        genres.deleteCharAt(genres.length() - 2);
//        movieGenre.setText(genres.toString());

        movieName.setStyle("-fx-text-fill: #bf671e");
        movieDirector.setStyle("-fx-text-fill: #fe9731");
        movieYear.setStyle("-fx-text-fill: black");
        fromNumverOfRating.setStyle("-fx-text-fill: black");
        ratingPoint.setStyle("-fx-text-fill: black");
//        movieGenre.setStyle("-fx-text-fill: black");
        accName.setStyle("-fx-text-fill: #ee7912");
        accRating5.setStyle("-fx-text-fill: black");
        accReview.setStyle("-fx-text-fill: black");
        e1.setStyle("-fx-text-fill: black");
        e2.setStyle("-fx-text-fill: black");
    }

    public void movieOnActionButton(){

    }
}
