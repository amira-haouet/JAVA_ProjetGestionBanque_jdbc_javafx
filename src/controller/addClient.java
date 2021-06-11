package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Entities.Client;
import application.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class addClient implements Initializable {

	@FXML
	private TextField adrsfid;

	@FXML
	private TextField nomfid;

	@FXML
	private DatePicker datefid;

	@FXML
	private TextField prenomdif;

	@FXML
	private TextField emailfid;
	@FXML
	private Button save;
	@FXML
	private Button clear;
	
	
	String query = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	Client client = null;
	private boolean update;
	int idClient;

	@FXML
	private void clear() {
		nomfid.setText(null);
		datefid.setValue(null);
		prenomdif.setText(null);
		emailfid.setText(null);
		adrsfid.setText(null);
	}

	@FXML
	private void save(MouseEvent event) throws SQLException {

		connection = DbConnect.getConnect();
		String nom = nomfid.getText();
		String prenom = prenomdif.getText();
		String datenaissance = String.valueOf(datefid.getValue());
		String adresse = adrsfid.getText();
		String email = emailfid.getText();

		if (prenom.isEmpty() || nom.isEmpty() || datenaissance.isEmpty() || adresse.isEmpty() || email.isEmpty()  ) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please 3abi les données");
			alert.showAndWait();

		} else { 
			if(Client.isEmailAdress(email) == false)
		{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("email incorrecte");
				alert.showAndWait();
		}
			else 
			{	getQuery();
			insert();
			clear();

		}

	}
	}
	private void getQuery() {

		if (update == false) {

			query = "INSERT INTO `client`(  `nom`, `prenom`, `datenaissance`, `adresse`, `email`) VALUES (?,?,?,?,?)";

		} else {
			query = "UPDATE `client` SET " 
		+ "`nom`=?," + 
		"`prenom`=?," + 
		"`datenaissance`=?," 
		+ "`adresse`= ?,"
		+ " `email`=?  WHERE idClient = '"+ idClient + "'";
		}

	}

	private void insert() {

		try {

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nomfid.getText());
			preparedStatement.setString(2, prenomdif.getText());
			preparedStatement.setString(3, String.valueOf(datefid.getValue()));
			preparedStatement.setString(4, adrsfid.getText());
			preparedStatement.setString(5, emailfid.getText());
			preparedStatement.execute();

		} catch (SQLException ex) {
			Logger.getLogger(addClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	void setTextField(int id, String firstname, String lastname, LocalDate toLocalDate, String adress, String email) {

		idClient=id;
		
		nomfid.setText(firstname);
		prenomdif.setText(lastname);
		datefid.setValue(toLocalDate);
		adrsfid.setText(adress);
		emailfid.setText(email);
		

	}
	@FXML
	private void close(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	void setUpdate(boolean b) {
		this.update = b;

	}

	public void setTextField(int idClient2, String nom, String prenom, Date valueOf, String adresse, String email) {
		// TODO Auto-generated method stub
		
	}
}