package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.UtilisateurDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Login {
	
	@FXML
	private JFXTextField ztLogin;
	@FXML
	private JFXPasswordField ztPassword;
	@FXML
	private JFXButton btConnexion;
	@FXML
	private Stage dialogStage;	
	
	private Main main;
	
//--------------------------------------------------------------------------------------------------	
	
	 public void setMainApp(Main main) {
	        this.main = main;
	}

	// -------------------------------------------------------------------------------------------------------

	public void handleConnexionOk() {
		UtilisateurDAO udao = new UtilisateurDAO();
		String login = ztLogin.getText();
		String password = ztPassword.getText();
	

		if (udao.loginRequest(login, password)) {

			main.showProUserInterface(login);

		}

		else {

			Main main = new Main();
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Erreur d'authentification");
			alert.setHeaderText("Login ou mot de passe incorrect");
			alert.setContentText("Votre login ou votre mot de passe est incorrect.");

			alert.showAndWait();
		}

	}

}
