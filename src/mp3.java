import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class mp3 extends Application {
    MusicController controller; //재생, 정지, 초기화 등의 기능 구현 클래스
    public VolumeSlider volumeSlider;
    public PlayerSlider playerSlider;
    public ButtonCollection buttonCollection;
    private PlayList musiclist;

    @Override
    public void start(Stage primaryStage) {
    	controller = new MusicController();
    	
    	volumeSlider = new VolumeSlider(controller); //볼륨바에 음악파일 정보 연동
    	playerSlider = new PlayerSlider(controller); //플레이바에 음악파일 정보 연동
    	buttonCollection = new ButtonCollection(controller, playerSlider, volumeSlider); //버튼모음에 음악파일 정보 연동
         
    	
       // 레이아웃
         HBox buttonPane = new HBox(20, playerSlider.timeLabel, buttonCollection.previousSongBtn, buttonCollection.playOrpauseBtn, buttonCollection.nextSongBtn);
         VBox musicBar_box = new VBox(20, volumeSlider.volumeBar, playerSlider.titleLabel, playerSlider.playerBar,buttonPane);
         musicBar_box.setStyle("-fx-background-color: white;");
    	
       // 씬/윈도우 설정
         primaryStage.setScene(new Scene(musicBar_box, 300, 200));
         primaryStage.setTitle("뮤직 플레이어");
         primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}