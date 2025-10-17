import java.util.ArrayList; //음악 파일의 경로를 배열로 담기 위한 클래스
import java.io.File; //음악 파일을 다루기 위한 클래스

public class PlayList {
	ArrayList<String> mp3FileLists; //다른 클래스에서 참조할 수 있게 클래스 선언부에 작성;
	
	PlayList(){
		mp3FileLists = new ArrayList<>();
		File folder = new File("C://MusicList");
		
		//해당 폴더 중 .mp3 확장자만 추가
		File Allfiles [] = folder.listFiles(); //파일 명칭들을 모두 하나의 File 클래스 배열에 저장
		int i = 0;
		while(i < Allfiles.length) {
			if(Allfiles[i] != null) {
				if(Allfiles[i].isFile() && Allfiles[i].getName().toLowerCase().endsWith(".mp3")) {
					mp3FileLists.add(Allfiles[i].getAbsolutePath());  //플레이 리스트 배열에 해당 mp3 파일의 경로를 삽입
				}
			}
			
			i++;
		}
	} //생성자 함수 끝

}
