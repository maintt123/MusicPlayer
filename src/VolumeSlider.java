import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.scene.media.MediaPlayer;
import javafx.beans.value.ChangeListener; //리스너를 생성하는 크래스


public class VolumeSlider {
	Slider volumeBar; //다른 클래스에서도 참조할 수 있게 생성자 생성자 함수 외부에 선언해주어야 함(default 범위)
	private double lastVolume = 0.5;
	private ChangeListener<Number> volumeListener;
	
	// 볼륨 슬라이더 (0.0 ~ 1.0로 조절)
    VolumeSlider(MusicController controller){
    	volumeBar = new Slider(0, 1, lastVolume);
    	volumeBar.setOrientation(Orientation.VERTICAL);
    	
    	connectMusic(controller.music);
    }
    
    public void connectMusic(MediaPlayer music) {
    	if(volumeListener != null) {
    		volumeBar.valueProperty().removeListener(volumeListener);
    	}
        
    	music.setVolume(lastVolume); // ★ 이전 곡 볼륨 그대로 적용
    	
        volumeListener = (obs, oldVal, newVal) -> {
            music.setVolume(newVal.doubleValue());
            lastVolume = newVal.doubleValue();
        };
        
        volumeBar.valueProperty().addListener(volumeListener);
    }
}
