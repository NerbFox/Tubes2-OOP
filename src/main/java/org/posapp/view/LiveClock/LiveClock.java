package org.posapp.view.LiveClock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LiveClock extends VBox {

    private Label lblDate;
    private Label lblTime;

    public LiveClock(String nama) {
//        super(20); // set spacing antar element dalam VBox

        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));


        lblDate = new Label();
        lblDate.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        lblTime = new Label();
        lblTime.setStyle("-fx-font-size: 64px; -fx-font-weight: bold;");


        getChildren().addAll(lblDate, lblTime); // menambahkan Label ke dalam VBox
        setAlignment(Pos.TOP_CENTER);
        setMargin(lblDate, new Insets(150,0,0,0));

        updateClock(); // memanggil method untuk mengupdate waktu saat pertama kali diinisialisasi

        // membuat sebuah Timeline dengan durasi 1 detik
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateClock() {
        // mendapatkan waktu saat ini
        LocalDateTime now = LocalDateTime.now();

        // mendapatkan hari saat ini
        String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

        // membuat sebuah formatter untuk mengubah waktu menjadi string
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

        // mengubah waktu menjadi string dan menampilkan di Label
        String formattedDate = now.format(formatterDate);
        String formattedTime = now.format(formatterTime);

        lblDate.setText(formattedDate);
        lblTime.setText(formattedTime);
    }
}