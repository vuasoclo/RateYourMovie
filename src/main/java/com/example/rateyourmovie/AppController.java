package com.example.rateyourmovie;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Movie;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class AppController implements Initializable {
    @FXML
    private AnchorPane main_stage;
    @FXML
    private Button logoutButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private VBox reviewFeatureBox;

    @FXML
    private VBox topMovieBox;

    @FXML
    private VBox topResultBox;

    @FXML
    private VBox trendingBox;
//add
    @FXML
    private ImageView movieImg;

    @FXML
    private TextField add_NORTextField;

    @FXML
    private TextField add_RuntimeTextField;

    @FXML
    private ComboBox<Double> add_comboBoxRate;

    @FXML
    private ComboBox<String> add_comboBoxSelectTag;

    @FXML
    private TextField add_directedTextField;

    @FXML
    private VBox add_genreBox;

    @FXML
    private TextField add_nameTextField;

    @FXML
    private TextField add_newGenreTextField;

    @FXML
    private DatePicker add_realease_dateTextField;

    @FXML
    private Label add_messageLabel;

    @FXML
    private Label add_messageLabel1;

    @FXML
    private Button add_addButton;

    @FXML
    private TextArea descriptionTextField;
//
//    search
    @FXML
    private ComboBox<String> search_ComboBoxSelectTag;

    @FXML
    private ComboBox<String> search_ComboBoxSelectTag2;

    @FXML
    private HBox search_excludeTagBox;

    @FXML
    private HBox search_includeTagBox;
    @FXML
    private Button search_FromButton;
    @FXML
    private Button search_ToButton;
    @FXML
    private Slider search_slider;
    @FXML
    private Label search_yearLabel;
    @FXML
    private Label search_messegeLabel;

//
//    chart
    @FXML
    private ComboBox<String> chart_ComboBoxSelectTag;

    @FXML
    private ComboBox<String> chart_ComboBoxSelectTag2;

    @FXML
    private VBox chart_excludeBox;

    @FXML
    private VBox chart_includeBox;

    @FXML
    private Label chart_messageLabel;
//
    private double x = 0;
    private double y = 0;
    private String add_ImagePath;
    private Movie add_movie = new Movie();
    private Set<String> add_genres = new HashSet<>();
    private List<String> search_IncludeGenres = new ArrayList<>();
    private List<String> search_ExcludeGenres = new ArrayList<>();
    private int yearTemp;
    private int search_includeOverload;
    private int search_excludeOverload;
    private List<String> chart_IncludeGenres = new ArrayList<>();
    private List<String> chart_ExcludeGenres = new ArrayList<>();

    private List<Movie> trendingMovies;
    private List<Movie> reviewFeatureMovies; // change to child of movie and acc TO-DO or combine list so dont need to change
    private List<Movie> topMovies;
    private List<Movie> topResult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getScrollPaneTrendingMovie();
        getScrollPaneReviewFeatureMovie();
        getScrollPaneTopMovie();
        getScrollPaneTopResult();
        add_initComboBoxSelectTag();
        add_initComboBoxRate();
        init3SearchComboBox();
        chart_init();
    }

    public void getScrollPaneTrendingMovie(){
        trendingMovies = new ArrayList<>();
        try {
            trendingMovies.addAll(getMovieFromDB());
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
            reviewFeatureMovies.addAll(getMovieFromDB());
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
            topMovies.addAll(getMovieFromDB());
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

    public void getScrollPaneTopResult(){
        //generate top result as above but change name
        topResult = new ArrayList<>();
        try {
            topResult.addAll(getMovieFromDB());
            for (Movie movie : topResult) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("topresult.fxml"));
                AnchorPane topResultModel = loader.load();
                TopResultController topResultController = loader.getController();
                topResultController.setData(movie);
                topResultBox.getChildren().add(topResultModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void minimize(){
        Stage minimizeStage = (Stage) main_stage.getScene().getWindow();
        minimizeStage.setIconified(true);
    }
    public void cancelButtonAction() {
        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        primaryStage.close();
        Platform.exit();
    }

    //switch page
    public void logoutButtonOnAction(){
        //generate code
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                // HIDE THE DASHBOARD FORM
                logoutButton.getScene().getWindow().hide();
                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage loginStage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    loginStage.setX(event.getScreenX() - x);
                    loginStage.setY(event.getScreenY() - y);

                    loginStage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    loginStage.setOpacity(1);
                });

                loginStage.initStyle(StageStyle.TRANSPARENT);

                loginStage.setScene(scene);
                loginStage.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //home page
    private List<Movie> getMovieFromDB() throws SQLException {
        List<Movie> lsmovies = new ArrayList<>();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String query = "SELECT movie_id, name, cover, release_date, director, runtime, rating, numberOfRate FROM movie";
        String sqlGenre = """
        SELECT g.name
        FROM genre g
        JOIN movie_genre mg ON g.genre_id = mg.genre_id
        WHERE mg.movie_id = ?;
    """;

        // Fetch movies
        try (PreparedStatement preparedStatement = connectionDB.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("movie_id"));
                movie.setName(resultSet.getString("name"));

                // Handle cover image
                Blob coverBlob = resultSet.getBlob("cover");
                if (coverBlob != null) {
                    Image image = new Image(coverBlob.getBinaryStream());
                    movie.setCover(image);
                }

                movie.setRuntime(resultSet.getInt("runtime"));
                movie.setDirector(resultSet.getString("director"));
                movie.setRating(resultSet.getDouble("rating"));
                // Convert release_date (DATE) to String
                movie.setRelease_date(resultSet.getDate("release_date"));
                movie.setNumberOfRate(resultSet.getInt("numberOfRate"));

                movie.setGenres(new ArrayList<>()); // Initialize genres list
                lsmovies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fetch genres for each movie
        try (PreparedStatement preparedStatementGenre = connectionDB.prepareStatement(sqlGenre)) {
            for (Movie movie : lsmovies) {
                preparedStatementGenre.setInt(1, movie.getId());
                try (ResultSet resultSet = preparedStatementGenre.executeQuery()) {
                    while (resultSet.next()) {
                        String genretmp = resultSet.getString("name");
                        movie.getGenres().add(genretmp);
                        add_genres.add(genretmp);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lsmovies;
    }

    //add movie
    public void add_importButtonOnAction() {
        FileChooser open = new FileChooser();
        open.setTitle("Import Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_stage.getScene().getWindow());

        if(file != null){
            Image image = new Image(file.toURI().toString());

            movieImg.setImage(image);
            add_movie.setCover(image);

            add_ImagePath = file.getAbsolutePath();
        }
        else{
            add_messageLabel.setText("invalid file");
        }
    }

    public void add_resetButtonOnAction() {
        add_messageLabel.setText(null);
        add_movie = new Movie();
        add_ImagePath = "";
        add_addButton.setDisable(true);
        movieImg.setImage(null);
        add_nameTextField.clear();
        add_realease_dateTextField.getEditor().clear();
        add_directedTextField.clear();
        add_RuntimeTextField.clear();
        add_newGenreTextField.clear();
        descriptionTextField.clear();
        add_comboBoxRate.getSelectionModel().clearSelection();
        add_comboBoxSelectTag.getSelectionModel().clearSelection();
        add_genreBox.getChildren().clear();
    }

    public void add_AddNewGenreButtonOnAction() {
        if(add_newGenreTextField.getText().isEmpty()){
            add_messageLabel1.setText("Please enter new genre");
        }
        else{
            add_messageLabel1.setText("added on select tag");
            add_comboBoxSelectTag.getItems().add(add_newGenreTextField.getText());
        }
    }

    public void add_initComboBoxSelectTag() {
        add_comboBoxSelectTag.setItems(FXCollections.observableArrayList(add_genres));
    }
    public void add_initComboBoxRate() {
        add_comboBoxRate.setItems(FXCollections.observableArrayList(0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0));
    }
    public void add_comboBoxRateOnAction(){
        add_movie.setRating(add_comboBoxRate.getSelectionModel().getSelectedItem());
    }

    public void add_comboBoxSelectTagOnAction(){
        String selectedItem = add_comboBoxSelectTag.getSelectionModel().getSelectedItem();
        for(String x : add_movie.getGenres()){
            if(x.equals(selectedItem)){
                add_messageLabel.setText("Genre already added");
                return;
            }
        }
        add_movie.getGenres().add(selectedItem);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genre.fxml"));
            Label genreModel = loader.load();
            GenreController genreController = loader.getController();
            genreController.setData(selectedItem);
            add_genreBox.getChildren().add(genreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_saveButtonOnAction() {
        try {
            if (add_nameTextField.getText().isEmpty() ||
                    add_realease_dateTextField.getValue() == null ||
                    add_directedTextField.getText().isEmpty() ||
                    add_RuntimeTextField.getText().isEmpty() ||
                    add_comboBoxRate.getSelectionModel().isEmpty() ||
                    movieImg.getImage() == null){

                add_messageLabel.setText("Please fill all the fields");
                return;
            }

            int runtime;
            try {
                runtime = Integer.parseInt(add_RuntimeTextField.getText());
            } catch (NumberFormatException e) {
                add_messageLabel.setText("Runtime must be an INT");
                return;
            }
            int numberOfRate;
            try {
                numberOfRate = Integer.parseInt(add_NORTextField.getText());
            } catch (NumberFormatException e) {
                add_messageLabel.setText("ratings must be an INT");
                return;
            }

            // Kiểm tra định dạng số thực cho Rating
            double rating = add_comboBoxRate.getSelectionModel().getSelectedItem();


            add_movie.setName(add_nameTextField.getText());
            add_movie.setRelease_date(Date.valueOf(add_realease_dateTextField.getValue()));
            add_movie.setDirector(add_directedTextField.getText());
            add_movie.setRuntime(runtime);
            add_movie.setRating(rating);
            add_movie.setNumberOfRate(numberOfRate);
            add_messageLabel.setText("Saved successfully");
            add_addButton.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_addButtonOnAction() {
        add_messageLabel.setText("Movie added successfully");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            add_resetButtonOnAction();
        });
        pause.play();

    }

//    search feature


    public void search_ApplyButtonOnAction(){
        if(Integer.valueOf(search_FromButton.getText()) > Integer.valueOf(search_ToButton.getText())){
            search_messegeLabel.setText("From year must be smaller than To year");
            return;
        }
    }
    public void search_ResetButtonOnAction(){
        search_IncludeGenres.clear();
        search_ExcludeGenres.clear();
        search_includeTagBox.getChildren().clear();
        search_excludeTagBox.getChildren().clear();
        search_includeOverload = 0;
        search_excludeOverload = 0;
        yearTemp = 1960;
        search_yearLabel.setText(String.valueOf(yearTemp));
        //button
        search_FromButton.setText("From");
        search_ToButton.setText("To");
    }
    public void init3SearchComboBox(){
        search_includeOverload = 0;
        search_excludeOverload = 0;
        yearTemp = (int) search_slider.getValue();
        search_yearLabel.setText(String.valueOf(yearTemp));
        search_ComboBoxSelectTag.setItems(FXCollections.observableArrayList(add_genres));
        search_ComboBoxSelectTag2.setItems(FXCollections.observableArrayList(add_genres));
        search_slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                yearTemp = (int) search_slider.getValue();
                search_yearLabel.setText(String.valueOf(yearTemp));
            }
        });
    }
    public void search_ComboBoxSelectTagOnAction() {
        search_messegeLabel.setText(null);
        String selectedItem = search_ComboBoxSelectTag.getSelectionModel().getSelectedItem();

        for (String x : search_IncludeGenres) {
            if (x.equals(selectedItem)) {
                search_messegeLabel.setText("Genre already selected");
                return;
            }
        }

        search_IncludeGenres.add(selectedItem);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genre.fxml"));
            Label genreModel = loader.load();
            GenreController genreController = loader.getController();
            genreController.setData(selectedItem);

            if (search_IncludeGenres.size() > 3) {
                if (search_includeOverload == 1) {
                    // update overload component
                    Label overloadLabel = (Label) search_includeTagBox.getChildren()
                            .get(search_includeTagBox.getChildren().size() - 1);
                    overloadLabel.setText("+" + (search_IncludeGenres.size() - 3));
                } else {
                    // add new overload component
                    genreModel.setText("+" + (search_IncludeGenres.size() - 3));
                    search_includeOverload = 1;
                    search_includeTagBox.getChildren().add(genreModel);
                }
            } else {
                // add normal component
                search_includeTagBox.getChildren().add(genreModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Xóa tag đã chọn khỏi ComboBox
        search_ComboBoxSelectTag2.getItems().remove(selectedItem);
    }

    public void search_ComboBoxSelectTagOnAction2() {
        search_messegeLabel.setText(null);
        String selectedItem = search_ComboBoxSelectTag2.getSelectionModel().getSelectedItem();
        for(String x : search_ExcludeGenres){
            if(x.equals(selectedItem)){
                search_messegeLabel.setText("Genre already selected");
                return;
            }
        }
        search_ExcludeGenres.add(selectedItem);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genre.fxml"));
            Label genreModel = loader.load();
            GenreController genreController = loader.getController();
            genreController.setData(selectedItem);

            if (search_ExcludeGenres.size() > 3) {
                if (search_excludeOverload == 1) {
                    // update overload component
                    Label overloadLabel = (Label) search_excludeTagBox.getChildren()
                            .get(search_excludeTagBox.getChildren().size() - 1);
                    overloadLabel.setText("+" + (search_ExcludeGenres.size() - 3));
                } else {
                    // add new overload component
                    genreModel.setText("+" + (search_ExcludeGenres.size() - 3));
                    search_excludeOverload = 1;
                    search_excludeTagBox.getChildren().add(genreModel);
                }
            } else {
                // add normal component
                search_excludeTagBox.getChildren().add(genreModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        search_ComboBoxSelectTag.getItems().remove(selectedItem);
    }

    public void search_FromButtonOnAction(){
        search_FromButton.setText(String.valueOf(yearTemp));
    }
    public void search_ToButtonOnAction(){
        search_ToButton.setText(String.valueOf(yearTemp));
    }

//chart feature


    public void chart_init(){
        chart_ComboBoxSelectTag.setItems(FXCollections.observableArrayList(add_genres));
        chart_ComboBoxSelectTag2.setItems(FXCollections.observableArrayList(add_genres));
    }
    public void chart_ComboBoxSelectTagOnAction() {

        String selectedItem = chart_ComboBoxSelectTag.getSelectionModel().getSelectedItem();
        for(String x : chart_IncludeGenres){
            if(x.equals(selectedItem)){
                chart_messageLabel.setText("Genre already selected");
                return;
            }
        }
        chart_IncludeGenres.add(selectedItem);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genre.fxml"));
            Label genreModel = loader.load();
            GenreController genreController = loader.getController();
            genreController.setData(selectedItem);
            chart_includeBox.getChildren().add(genreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chart_ComboBoxSelectTag2.getItems().remove(selectedItem);
    }

    public void chart_ComboBoxSelectTagOnAction2() {
        String selectedItem = chart_ComboBoxSelectTag2.getSelectionModel().getSelectedItem();
        for(String x : chart_ExcludeGenres){
            if(x.equals(selectedItem)){
                chart_messageLabel.setText("Genre already selected");
                return;
            }
        }
        chart_ExcludeGenres.add(selectedItem);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genre.fxml"));
            Label genreModel = loader.load();
            GenreController genreController = loader.getController();
            genreController.setData(selectedItem);
            chart_excludeBox.getChildren().add(genreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chart_ComboBoxSelectTag.getItems().remove(selectedItem);
    }

    public void chart_ApplyButtonOnAction() {

    }

    public void chart_ResetButtonOnAction() {
        chart_IncludeGenres.clear();
        chart_ExcludeGenres.clear();
        chart_includeBox.getChildren().clear();
        chart_excludeBox.getChildren().clear();
    }

    //
//    private void addManully(){
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectionDB = connectNow.getConnection();

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
}
