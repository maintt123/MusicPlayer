import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicController {
	    public MediaPlayer music; //중앙관리 객체 설계
	    PlayList playlist;
	    String filepath; //다른 클래스에서 파일 경로를 참조하기 클래스 필드 선언
	    int songIndex = 0;
	    
	    MusicController() {
	    	playlist = new PlayList();
	    	filepath = playlist.mp3FileLists.get(songIndex);
	        String source = new File(filepath).toURI().toString();
	        Media media = new Media(source);
	        music = new MediaPlayer(media); //음악파일 정보가 담긴 객체를 생성
	    }

	    public void play() { //음악 파일 재생 (재생 기능)
	        if (music != null) music.play();
	    }

	    public void pause() { //음악 파일 일시정지(일시정지 기능)
	        if (music != null) music.pause();
	    }

	    public void stop() {  //mp3 파일 초기화 후 재생(초기화 기능)
	        if (music != null) {
	        	music.stop();
	        	music.play();
	        	}
	    }

	    public void resume() {
	        // JavaFX의 MediaPlayer에는 resume()이 따로 없고, 
	        // play()가 곧 resume(일시정지 상태에서 play 하면 이어서 재생됨)
	        play();
	    }
	    
	    public MediaPlayer nextSong() {
	    	if(music != null && songIndex < playlist.mp3FileLists.size() - 1) {
	    		songIndex += 1;
	    		String filepath = playlist.mp3FileLists.get(songIndex);
		        String source = new File(filepath).toURI().toString();
		        Media media = new Media(source);

		        music.stop();
		        music.dispose(); //음악 파일 객체 메모리 해제ㅌ
		        music = new MediaPlayer(media); //음악파일 정보가 담긴 객체를 생성
		        music.play();
		        return music;
	    	}
	    	
	    	else {
	    		System.out.println("현재 마지막 곡입니다");
	    		return music;
	    	}
	    }
	    
	    public MediaPlayer previousSong() {
	    	if(music != null && songIndex > 0) {
	    		songIndex -= 1;
	    		String filepath = playlist.mp3FileLists.get(songIndex);
		        String source = new File(filepath).toURI().toString();
		        Media media = new Media(source);
		        
		        music.stop();
		        music.dispose(); //음악 파일 객체 메모리 해제
		        music = new MediaPlayer(media); //음악파일 정보가 담긴 객체를 생성
		        music.play();
		        return music;
	    	}
	    	
	    	else {
	    		System.out.println("처음 곡입니다.");
	    	} return music;
	    }
}
