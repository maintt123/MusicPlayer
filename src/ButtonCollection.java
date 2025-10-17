import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

public class ButtonCollection {
	Button playOrpauseBtn; //버튼클릭 시에 재생/일시정지를 유동적 변경을 버튼 상태 변화를 위함
	Button nextSongBtn;
	Button previousSongBtn;
	PlayerSlider playerSlider;
    MusicController controller;
    VolumeSlider volumeSlider;
    MediaPlayer newMusic;
	boolean isPlaying = false;
	
	ButtonCollection(MusicController controller,  PlayerSlider playerSlider, VolumeSlider volumeSlider){
		this.controller = controller;
        this.playerSlider = playerSlider;
        this.volumeSlider = volumeSlider;
		// 버튼 생성
		playOrpauseBtn = new Button();
		nextSongBtn = new Button();
		previousSongBtn = new Button();
		
		Image playImg = new Image("file:C:\\자바학습\\MusicPlayerIconSource\\play.png"); //재생, 정지 이미지를 자바 상의 아이콘으로 등록 24 by 24
		ImageView playIcon = new ImageView(playImg);
		playIcon.setFitWidth(24);
		playIcon.setFitHeight(24);
		Image pauseImg = new Image("file:C:\\자바학습\\MusicPlayerIconSource\\pause.png");
		ImageView pauseIcon = new ImageView(pauseImg);
		pauseIcon.setFitWidth(24);
		pauseIcon.setFitHeight(24);

		// 최초엔 "재생" 아이콘
		playOrpauseBtn.setGraphic(playIcon); //재생 아이콘으로 버튼의 그래픽 대입
    	playOrpauseBtn.setStyle("-fx-background-color: transparent; -fx-padding: 1;");
    	 
        // 버튼 이벤트
    	playOrpauseBtn.setOnAction(e -> {
    	    if (isPlaying) {
    	    	playOrpauseBtn.setGraphic(playIcon); // 일시정지→재생 아이콘으로
    	        controller.pause(); 
    	        isPlaying = false;
    	    } else {
    	    	playOrpauseBtn.setGraphic(pauseIcon); // 재생→일시정지 아이콘으로
    	        controller.play(); 
    	        isPlaying = true;
    	    }
    	    
    	    controller.music.setOnEndOfMedia(() -> { //곡이 끝나면 다음 곡을 실행하는 실행함수
    	    	controller.music = controller.nextSong();
        	    playerSlider.connectMusic(controller.music);
        	    volumeSlider.connectMusic(controller.music);
        	    playerSlider.setSongTitle(controller.music.getMedia().getSource()); // 또는 파일명 소스
        	});
    	});
    	
    	
    	
    	Image forwardImg = new Image("file:C:\\자바학습\\MusicPlayerIconSource\\step-forward.png"); //다음곡 이미지의 아이콘 생성 24 by 24
    	ImageView forwardIcon = new ImageView(forwardImg);
    	forwardIcon.setFitWidth(24);
    	forwardIcon.setFitHeight(24);
    	
    	nextSongBtn.setGraphic(forwardIcon);
    	nextSongBtn.setStyle("-fx-background-color: transparent; -fx-padding: 1;");
    	nextSongBtn.setOnAction(e -> {
    		newMusic = controller.nextSong();
    		playerSlider.connectMusic(newMusic);
    		volumeSlider.connectMusic(newMusic);
    		playerSlider.setSongTitle(newMusic.getMedia().getSource());
    		
    		newMusic.setOnEndOfMedia(() -> { //곡이 끝나면 다음 곡을 실행하는 실행함수
        	    newMusic = controller.nextSong();
        	    playerSlider.connectMusic(newMusic);
        	    volumeSlider.connectMusic(newMusic);
        	    playerSlider.setSongTitle(newMusic.getMedia().getSource()); // 또는 파일명 소스
        	});
    	});
    	
    	
    	Image BackwardImg = new Image("file:C:\\자바학습\\MusicPlayerIconSource\\step-Backward.png"); //다음곡 이미지의 아이콘 생성 24 by 24
    	ImageView BackwardIcon = new ImageView(BackwardImg);
    	BackwardIcon.setFitWidth(24);
    	BackwardIcon.setFitHeight(24);
    	
    	previousSongBtn.setGraphic(BackwardIcon);
    	previousSongBtn.setStyle("-fx-background-color: transparent; -fx-padding: 1;");
    	previousSongBtn.setOnAction(e -> {
    		newMusic = controller.previousSong();
    		playerSlider.connectMusic(newMusic);
    		volumeSlider.connectMusic(newMusic);
    		playerSlider.setSongTitle(newMusic.getMedia().getSource());
    		
    		newMusic.setOnEndOfMedia(() -> { //곡이 끝나면 다음 곡을 실행하는 실행함수
        	    newMusic = controller.nextSong();
        	    playerSlider.connectMusic(newMusic);
        	    volumeSlider.connectMusic(newMusic);
        	    playerSlider.setSongTitle(newMusic.getMedia().getSource()); // 또는 파일명 소스
        	});
    	});
    	
	} //생성자 함수 끝
	
	
}
