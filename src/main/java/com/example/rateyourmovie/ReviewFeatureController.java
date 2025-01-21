package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private int genreOverLoad = 0;

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
        try{

            for(int genreId = 0; genreId < movie.getGenres().size(); genreId++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("genre.fxml"));
                Label genreModel = loader.load();
                GenreController genreController = loader.getController();
                genreController.setData(movie.getGenres().get(genreId));
                if(genreId < 2){
                    hBox1.getChildren().add(genreModel);
                }
                else if (genreId < 4){
                    hBox2.getChildren().add(genreModel);
                }
                else{
                    if(genreOverLoad == 1){
                        Label overloadLabel = (Label) hBox2.getChildren()
                                .get(hBox2.getChildren().size() - 1);
                        overloadLabel.setText("+" + (genreId - 3));
                    }
                    else{
                        // add new overload component
                        genreModel.setText("+" + (genreId - 3));
                        genreOverLoad = 1;
                        hBox2.getChildren().add(genreModel);
                    }
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
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
