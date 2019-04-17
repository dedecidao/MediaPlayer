package application;
//IMPORTS DO PROFESSOR
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

//IMPORTS PADRAO
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ScreenController implements Initializable {

	public static MediaPlayer mediaPlayer;
	public static File file;
	public static List<String> list = new ArrayList<String>();
	public static List<String> name = new ArrayList<String>();
	public static int musicNumber = -1;

	@FXML
	private TextField txtSource;

	@FXML
	private TextField txtArchive;

	@FXML
	private Button btnChange;
	
    @FXML
    private Label lblStatus;
    
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnPause;
    @FXML
    private Button btnPlus;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnForward;

    @FXML
    private Button btnBackward;

	// FIM DOS ATRIBUTOS

	// METODO CHANGE SONG
	public void btnChangeHandler() {
		try {

			file = new File(txtSource.getText());
			if (file.exists()) {
				list.clear();
				name.clear();
				for (File f : file.listFiles()) {
					list.add(f.getAbsoluteFile().toURI().toString());
					name.add(f.getName());
				}
				musicNumber = 0;
				mediaPlayer = new MediaPlayer(new Media(list.get(musicNumber)));
				updateStatus();
			} else {
				lblStatus.setText("SOURCE DO NOT EXISTS");
				musicNumber = -1;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtSource.setText("C:\\Users\\18114290019\\Documents\\musicas");
		btnChangeHandler();
		lblStatus.setText("SOURCE: " + file.toString());
	}
	//end change button
	
	//play
	public void btnPlayHandler() {
		 if (musicNumber!=-1){
			mediaPlayer.play();
			updateStatus();
		 	}
		 		else
		 			lblStatus.setText( "NO SOURCE" );
	}
	//end play
	
	//pause
	public void btnStopHandler() {
		 if (musicNumber!=-1){
		 mediaPlayer.pause();
		 updateStatus();
		 }
		 	else
		 		lblStatus.setText( "NO SOURCE" );
		 }
	//end pause
	
//status
	public void updateStatus(){
		 lblStatus.setText( musicNumber+"/"+list.size()+" - VOL: "+(int)(mediaPlayer.getVolume()*100)+"%" );
		 
		 txtArchive.setText( name.get( musicNumber ) );
		 }	


	
	
	
	

} //end main class
