package com.example.rateyourmovie;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private Button minimizeButton, closeButton;
    @FXML
    private VBox trendingBox;
    @FXML
    private VBox reviewFeatureBox;
    @FXML
    private VBox topMovieBox;

    private List<Movie> trendingMovies;
    private List<Movie> reviewFeatureMovies; // change to child of movie and acc TO-DO or combine list so dont need to change
    private List<Movie> topMovies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getScrollPaneTrendingMovie();
        getScrollPaneReviewFeatureMovie();
        getScrollPaneTopMovie();
    }

    public void getScrollPaneTrendingMovie(){
        trendingMovies = new ArrayList<>();
        try {
            trendingMovies.addAll(getTrendingMovies());
            for (Movie movie : trendingMovies) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("trending.fxml"));
                AnchorPane trendingModel = loader.load();
                TrendingController trendingController = loader.getController();
                trendingController.setData(movie);
                trendingBox.getChildren().add(trendingModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getScrollPaneReviewFeatureMovie(){
        reviewFeatureMovies = new ArrayList<>();
        try {
            reviewFeatureMovies.addAll(getTrendingMovies());
            for (Movie movie : reviewFeatureMovies) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("reviewfeature.fxml"));
                AnchorPane reviewFeatureModel = loader.load();
                ReviewFeatureController reviewFeatureController = loader.getController();
                reviewFeatureController.setData(movie);
                reviewFeatureBox.getChildren().add(reviewFeatureModel);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getScrollPaneTopMovie(){
        topMovies = new ArrayList<>();
        try {
            topMovies.addAll(getTrendingMovies());
            for (Movie movie : topMovies) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("topmovie.fxml"));
                AnchorPane topMovieModel = loader.load();
                TopMovieController topMovieController = loader.getController();
                topMovieController.setData(movie);
                topMovieBox.getChildren().add(topMovieModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void minimize(){
        Stage minimizeStage = (Stage) minimizeButton.getScene().getWindow();
        minimizeStage.setIconified(true);
    }
    public void cancelButtonAction() {
        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        primaryStage.close();
        Platform.exit();
    }

    //switch page
    // logout
    public void logoutButtonOnAction(){

    }



    //home page
    // trending
    private List<Movie> getTrendingMovies() throws SQLException {
        List<Movie> lsmovies = new ArrayList<>();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String query = "SELECT name, cover, director, genre, rating, year, numberOfRate FROM movie WHERE movie_id = ?";
        try (PreparedStatement preparedStatement = connectionDB.prepareStatement(query)) {
            int idx = 1;
            while (true) {
                preparedStatement.setInt(1, idx);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        break;
                    }
                    Movie movie = new Movie();
                    movie.setName(resultSet.getString("name"));
                    ImageView image = new ImageView(new Image(resultSet.getBlob("cover").getBinaryStream()));
                    movie.setCover(image);
                    movie.setDirector(resultSet.getString("director"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setRating(resultSet.getDouble("rating"));
                    movie.setYear(resultSet.getInt("year"));
                    movie.setNumberOfRate(resultSet.getInt("numberOfRate"));

                    lsmovies.add(movie);
                }

                idx++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lsmovies;
    }


//    private void addManully(){
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectionDB = connectNow.getConnection();
//
//        // choose image
////        FileChooser open = new FileChooser();
////        open.setTitle("Import Image File");
////        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));
////
////        File file = open.showOpenDialog(main_form.getScene().getWindow());
////
////        if(file != null){
////            Image image = new Image(file.toURI().toString(), 118, 147, false, true);
////
////            String path = getData().path = file.getAbsolutePath();
////        }
//
//
//        String sql = "INSERT INTO movie (movie_id, name, cover, director, genre, rating, year, numberOfRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement pstmt = connectionDB.prepareStatement(sql)) {
//            pstmt.setInt(1, 1);
//            pstmt.setString(2, "The Good, the Bad, and the Ugly2");              // name
//            pstmt.setBlob(3, new FileInputStream("D:\\Intellij\\Projects\\MyFirstProjectEver\\RateYourMovie\\src\\main\\resources\\images\\co.png"));
//            pstmt.setString(4, "Sergio Leone");      // director
//            pstmt.setString(5, "Spaghetti Western");                    // genre
//            pstmt.setDouble(6, 4.35 );                      // rating
//            pstmt.setInt(7, 1966);                        // year
//            pstmt.setInt(8, 8708);                     // numberOfRate
//
//            int rowsInserted = pstmt.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("A new movie was inserted successfully!");
//            }
//        } catch (SQLException | FileNotFoundException e) {
//            System.out.println("error somewhere");
//            e.printStackTrace();
//        }
//    }
    //search feature

}
