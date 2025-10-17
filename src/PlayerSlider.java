import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class PlayerSlider {
	Slider playerBar;  //재생바 클래스
	Label timeLabel; 
	Label titleLabel;
	private boolean isSeeking = false;
	
	PlayerSlider(MusicController controller){
		// **재생바(Slider)**
		playerBar = new Slider();
		playerBar.setMin(0);
		playerBar.setMax(100); // 임시 값, 미디어 길이 알 때 업데이트
		playerBar.setValue(0);
		playerBar.setStyle( //플레이어 바 스타일 추가(검은색)
			    " -fx-base: #222222;"
			);
		timeLabel = new Label("00:00");
		timeLabel.setStyle("-fx-font-family: 'Malgun Gothic'; -fx-font-size: 13px;");
		
		titleLabel = new Label();
		titleLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Malgun Gothic'; -fx-font-weight: bold;");
		
		setSongTitle(controller.filepath);
		
		connectMusic(controller.music); //음악 정보를 담은 객체를 플레이어바와 연동
		
        playerBar.setOnMousePressed(e -> isSeeking = true); //마우스 버튼 온클릭 시에 바 이동 활성화
        playerBar.setOnMouseReleased(e -> { //마우스 버튼 온클릭 해제 시에 바 이동 해제 후 해당 바 위치의 시간(초) 음악 객체에 전달
            isSeeking = false;
            controller.music.seek(Duration.seconds(playerBar.getValue()));
        });
	}
    	 
	
	public void setSongTitle(String source) {
		String path = source.replaceFirst("file:/+", "/");      //미디어의 해시코드 경로를 인자로 받아 디코딩하여 곡명으로 설정
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        
	    File file = new File(path);
	    String filename = file.getName();
	    String title = filename.substring(0, filename.lastIndexOf("."));
	    titleLabel.setText(title);
	}

	
		public void connectMusic(MediaPlayer music) {
			
			timeLabel.setText("00:00");
			
			// 음악 재생 중일 때 재생 위치를 슬라이더에 표시
	         music.currentTimeProperty().addListener((obs, oldTime, newTime) -> { 
	             if (!isSeeking) {
	             	playerBar.setValue(newTime.toSeconds());
	             }
	         });
	         
	      // 음악이 준비될 때 총 길이로 max 설정
	         music.setOnReady(() -> {
	             double totalSeconds = music.getTotalDuration().toSeconds();
	             playerBar.setMax(totalSeconds);
	         });
	          
	         music.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
	             if (!isSeeking) {
	                 playerBar.setValue(newTime.toSeconds());
	             }
	             timeLabel.setText(formatTime(newTime.toSeconds())); // 시간 표시 갱신
	         });
		 }
	
	 String formatTime(double seconds) {
        int hours = (int) seconds / 3600;
        int minutes = ((int) seconds % 3600) / 60;
        int secs = (int) seconds % 60;
        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, secs);
        } else {
            return String.format("%02d:%02d", minutes, secs);
        }
    }
	 
	 
}
