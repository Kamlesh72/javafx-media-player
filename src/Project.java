import java.io.File;
// import java.time.Duration;

// import javax.swing.text.html.ImageView;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Project extends Application { // implements Initializable {

    boolean b;
    double volume = 1;
    double rate;
    Slider volumeSlider = new Slider(0, 1, volume);

    private MediaPlayer player;
    private MediaView mediaview = new MediaView(player);
    BorderPane bPane = new BorderPane();
    Button btnPlay;
    Slider slider;
    TextField txtSearch;
    Label timeLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {

        boolean Mute = false;
        txtSearch = new TextField("Search");
        txtSearch.setPrefWidth(350);
        txtSearch.setMaxWidth(350);
        // txtSearch.setStyle("-fx-background image: url("icons8-search-25.png");");
        Button btnHome = new Button("Home");
        Button btnVideo = new Button("Video Library");
        Button btnAudio = new Button("Music Library");
        Button btnQueue = new Button("Play Queue");
        Button btnPlaylist = new Button("Playlist");
        Button btnSetting = new Button("Settings");
        Button findVideo = new Button("Choose Video");
        Button findAudio = new Button("Choose Music");

        MenuBar mb = new MenuBar();
        Menu m = new Menu("Speed");
        MenuItem mi1 = new MenuItem("0.25");
        MenuItem mi2 = new MenuItem("0.5");
        MenuItem mi3 = new MenuItem("0.75");
        MenuItem mi4 = new MenuItem("Normal");
        MenuItem mi5 = new MenuItem("1.25");
        MenuItem mi6 = new MenuItem("1.5");
        MenuItem mi7 = new MenuItem("2.0");
        m.getItems().addAll(mi1, mi2, mi3, mi4, mi5, mi6, mi7);
        mb.getMenus().addAll(m);

        Image imgHome = new Image(getClass().getResourceAsStream("icons8-home-24 (1).png"));
        ImageView imgViewHome = new ImageView(imgHome);
        btnHome.setGraphic(imgViewHome);
        btnHome.setGraphicTextGap(20);
        btnHome.setAlignment(Pos.BASELINE_LEFT);

        Image imgVideo = new Image(getClass().getResourceAsStream("icons8-video-25.png"));
        ImageView imgViewVideo = new ImageView(imgVideo);
        btnVideo.setGraphic(imgViewVideo);
        btnVideo.setGraphicTextGap(20);
        btnVideo.setAlignment(Pos.BASELINE_LEFT);

        Image imageAudio = new Image(getClass().getResourceAsStream("icons8-music-library-25 (1).png"));
        ImageView imgViewMusic = new ImageView(imageAudio);
        btnAudio.setGraphic(imgViewMusic);
        btnAudio.setGraphicTextGap(20);
        btnAudio.setAlignment(Pos.BASELINE_LEFT);

        Image imgQueue = new Image(getClass().getResourceAsStream("icons8-playlist-25 (1).png"));
        ImageView imgViewQueue = new ImageView(imgQueue);
        btnQueue.setGraphic(imgViewQueue);
        btnQueue.setGraphicTextGap(20);
        btnQueue.setAlignment(Pos.BASELINE_LEFT);

        Image imgPlaylist = new Image(getClass().getResourceAsStream("icons8-playlist-25 (2).png"));
        ImageView imgViewPlaylist = new ImageView(imgPlaylist);
        btnPlaylist.setGraphic(imgViewPlaylist);
        btnPlaylist.setGraphicTextGap(20);
        btnPlaylist.setAlignment(Pos.BASELINE_LEFT);

        Image imgSetting = new Image(getClass().getResourceAsStream("icons8-settings-25.png"));
        ImageView imgViewSetting = new ImageView(imgSetting);
        btnSetting.setGraphic(imgViewSetting);
        btnSetting.setGraphicTextGap(20);
        btnSetting.setAlignment(Pos.BASELINE_LEFT);

        Image imgPlay = new Image(getClass().getResourceAsStream("icons8-play-button-50.png"));
        ImageView imgViewPlay = new ImageView(imgPlay);
        Image imgPause = new Image(getClass().getResourceAsStream("icons8-Pause-button-50.png"));
        ImageView imgViewPause = new ImageView(imgPause);
        Image imgForward = new Image(getClass().getResourceAsStream("icons8-forward-10-50.png"));
        ImageView imgViewForward = new ImageView(imgForward);
        Image imgRepeat = new Image(getClass().getResourceAsStream("icons8-replay-10-50.png"));
        ImageView imgViewRepeat = new ImageView(imgRepeat);

        btnPlay = new Button(null, imgViewPlay);
        Button btnForward = new Button(null, imgViewForward);
        Button btnrepeat = new Button(null, imgViewRepeat);

        btnAudio.setPrefSize(350, 40);
        btnHome.setPrefSize(350, 40);
        btnVideo.setPrefSize(350, 40);
        // btnQueue.setPrefSize(350, 20);
        // btnPlaylist.setPrefSize(350, 20);
        btnSetting.setPrefSize(350, 40);
        btnPlay.setPrefSize(30, 10);
        btnForward.setPrefSize(30, 10);
        btnrepeat.setPrefSize(30, 10);
        findAudio.setPrefSize(350, 50);
        findVideo.setPrefSize(350, 50);
        // mb.setPrefSize(350, 40);

        findAudio.setId("findButton");
        findVideo.setId("findButton");
        btnPlay.setId("play");
        btnrepeat.setId("play");
        btnForward.setId("play");

        // timeLabel = new Label();
        // // timeLabel.setLayoutX(200);
        // // timeLabel.setLayoutY(5);
        // timeLabel.setPrefSize(1600.0, 20);

        // BorderPane bPane = new BorderPane();
        HBox hb = new HBox();
        hb.getStyleClass().add("hbox");

        VBox vb = new VBox();
        vb.getStyleClass().add("vbox");
        VBox menu = new VBox(20.0);
        menu.getStyleClass().add("vbox");

        slider = new Slider();
        slider.setPrefSize(1500.0, 20.0);
        slider.setMaxWidth(1500.0);
        // slider.setAlignment(Pos.CENTER);
        // vb.getChildren().add(mb);
        // mb.getStyleClass().add("Menubar");

        // volumeSlider.setLayoutX(1100);
        // volumeSlider.setLayoutY(500);
        hb.getChildren().addAll(mb, btnrepeat, btnPlay, btnForward, volumeSlider);
        hb.setAlignment(Pos.CENTER);
        HBox.setHgrow(hb, Priority.ALWAYS);

        menu.getChildren().addAll(btnHome, btnAudio, btnVideo);// , btnQueue, btnPlaylist);
        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().addAll(vb, menu);
        AnchorPane.setTopAnchor(menu, 20.0);
        AnchorPane.setBottomAnchor(vb, 20.0);
        // anchor.setId("customButton");
        anchor.getStyleClass().add("anchor-pane");
        VBox playArea = new VBox();
        playArea.getStyleClass().add("vb");
        playArea.getStyleClass().add("anchor-pane");
        playArea.getChildren().addAll(slider, hb);
        // playArea.setAlignment(Pos.CENTER);

        bPane.setBottom(playArea);
        bPane.setLeft(anchor);
        // bPane.setCenter(mediaview);

        bPane.getStyleClass().add("border-pane");

        btnAudio.setOnAction(e -> {
            bPane.setCenter(findAudio);
            btnVideo.setStyle(null);
            btnHome.setStyle(null);
            btnAudio.setStyle("-fx-border: 0, 0, 0, 2");
            btnAudio.setStyle("-fx-border-color: black");
        });
        btnVideo.setOnAction(e -> {
            bPane.setCenter(findVideo);
            btnAudio.setStyle(null);
            btnHome.setStyle(null);
            btnVideo.setStyle("-fx-border: 0, 0, 0, 2");
            btnVideo.setStyle("-fx-border-color: black");
        });

        findAudio.setOnAction(e -> {
            openMedia();
        });
        findVideo.setOnAction(e -> {
            openMedia();
        });
        btnPlay.setOnAction(e -> {
            Play();
        });
        btnHome.setOnAction(e -> {
            Label lb = new Label("Welcome To Media Player");
            Image image = new Image(getClass().getResourceAsStream("background.jpg"));
            ImageView view = new ImageView(image);
            view.setPreserveRatio(false);
            view.setFitWidth(1130);
            view.setFitHeight(750);
            StackPane sPane = new StackPane(view, lb);
            bPane.setPrefSize(1130, 750);
            bPane.setCenter(sPane);
            btnAudio.setStyle(null);
            btnVideo.setStyle(null);
            btnHome.setStyle("-fx-border: 0, 0, 0, 2");
            btnHome.setStyle("-fx-border-color: black");
        });

        btnForward.setOnAction(e -> {
            double value = player.getCurrentTime().toSeconds();
            value = value + 10;
            player.seek(new Duration(value * 1000));
        });
        btnrepeat.setOnAction(e -> {
            double value = player.getCurrentTime().toSeconds();
            value = value - 10;
            player.seek(new Duration(value * 1000));
        });

        mi1.setOnAction(e -> {
            rate = 0.25;
            player.setRate(rate);
        });
        mi2.setOnAction(e -> {
            rate = 0.50;
            player.setRate(rate);
        });
        mi3.setOnAction(e -> {
            rate = 0.75;
            player.setRate(rate);
        });
        mi4.setOnAction(e -> {
            rate = 1;
            player.setRate(rate);
        });
        mi5.setOnAction(e -> {
            rate = 1.25;
            player.setRate(rate);
        });
        mi6.setOnAction(e -> {
            rate = 1.50;
            player.setRate(rate);
        });
        mi7.setOnAction(e -> {
            rate = 2;
            player.setRate(rate);
        });
        // player.setOnEndOfMedia(new Runnable() {
        // public void run() {
        // player.seek(Duration.ZERO);
        // player.play();
        // }
        // });
        bPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                MediaPlayer.Status status = player.getStatus();
                if (status == MediaPlayer.Status.PLAYING) {
                    player.pause();
                    b = false;
                    btnPlay.setGraphic(
                            new ImageView(new Image(getClass().getResourceAsStream("icons8-play-button-50.png"))));

                } else {
                    player.play();
                    b = true;
                    btnPlay.setGraphic(new ImageView(new Image("icons8-pause-button-50.png")));
                }
            } else if (e.getCode() == KeyCode.M) {
                System.out.println("The 'M' key was pressed");
                if (Mute) {
                    player.setVolume(volume); // Unmute (set volume to full)
                    // Mute = false;
                } else {
                    player.setVolume(0.0); // Mute (set volume to 0)
                    // Mute = true;
                }
            } else if (e.getCode() == KeyCode.RIGHT) {
                System.out.println("The 'RIGHT' key was pressed");
                //
                player.seek(player.getCurrentTime().add(javafx.util.Duration.seconds(10)));
                double value = player.getCurrentTime().toSeconds();
                value = value + 10;
                player.seek(new Duration(value * 1000));
            } else if (e.getCode() == KeyCode.LEFT) {
                System.out.println("The 'LEFT' key was pressed");
                //
                player.seek(player.getCurrentTime().subtract(javafx.util.Duration.seconds(10)));
                double value = player.getCurrentTime().toSeconds();
                value = value - 10;
                player.seek(new Duration(value * 1000));
            } else if (e.getCode() == KeyCode.DOWN) {
                System.out.println("The 'DOWN' key was pressed");
                volume = volume - 0.1;
                player.setVolume(volume);
                if (volume < 0) {
                    volume = 0;
                }
            } else if (e.getCode() == KeyCode.UP) {
                System.out.println("The 'UP' key was pressed");
                // volume = volume + 0.1;
                player.setVolume(volume);
                if (volume > 1) {
                    // volume = 1;
                }
            }
        });

        VBox.setMargin(txtSearch, new Insets(10, 10, 0, 10));
        VBox.setMargin(btnPlaylist, new Insets(0, 10, 0, 10));
        VBox.setMargin(btnHome, new Insets(0, 10, 0, 10));
        VBox.setMargin(btnAudio, new Insets(0, 10, 0, 10));
        VBox.setMargin(btnQueue, new Insets(0, 10, 0, 10));
        VBox.setMargin(btnVideo, new Insets(0, 10, 0, 10));
        VBox.setMargin(btnSetting, new Insets(0, 10, 0, 10));
        HBox.setMargin(slider, new Insets(0, 10, 10, 10));

        Scene scene = new Scene(bPane);
        primaryStage.setTitle("Media Player");
        scene.getStylesheets().add("Style.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void openMedia() {

        try {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);
            Media m = new Media(file.toURI().toURL().toString());
            if (player != null) {
                player.dispose();
            }
            player = new MediaPlayer(m);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            mediaview.setMediaPlayer(player);
            bPane.setCenter(mediaview);

            player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                Duration value = player.getCurrentTime();
                slider.setValue(value.toSeconds());
            });

            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (slider.isValueChanging()) {
                    player.seek(player.getTotalDuration().multiply(newValue.doubleValue() /
                            100.0));
                }
                // double val = slider.getValue();
                // player.seek(new Duration(val * 1000));

            });
            // slider.valueProperty().addListener(new ChangeListener<Number>() {
            // // @Override
            // // public void chnaged(ObservableValue<? extends Number> observable, Number
            // oldValue, number newValue) {
            // // double val = slider.getValue();
            // // player.seek(new Duration(val * 1000));
            // // }

            // @Override
            // public void changed(ObservableValue<? extends Number> observable, Number
            // oldValue, Number newValue) {
            // double val = slider.getValue();
            // player.seek(new Duration(val * 1000));
            // }

            // });
            player.volumeProperty().addListener((observable, oldValue, newValue) -> {
                volumeSlider.setValue(newValue.doubleValue());
            });
            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                player.setVolume(newValue.doubleValue());
            });
            // slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // player.seek(player.getTotalDuration().multiply(newValue.doubleValue() /
            // 100.0));
            // });

            player.setOnReady(() -> {
                slider.setMin(0);
                slider.setMax(player.getMedia().getDuration().toSeconds());
                slider.setValue(0);
                btnPlay.setGraphic(
                        new ImageView(new Image(getClass().getResourceAsStream("icons8-play-button-50.png"))));

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Play() {
        try {
            MediaPlayer.Status status = player.getStatus();
            System.out.println(status);
            if (status == MediaPlayer.Status.PLAYING) {
                player.pause();
                btnPlay.setGraphic(new ImageView(new Image("icons8-play-button-50.png)")));
            } else {
                player.play();

                btnPlay.setGraphic(new ImageView(new Image("icons8-pause-button-50.png")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatDuration(javafx.util.Duration duration) {
        int intDuration = (int) Math.floor(duration.toSeconds());
        int hours = intDuration / 3600;
        int minutes = (intDuration % 3600) / 60;
        int seconds = intDuration % 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
