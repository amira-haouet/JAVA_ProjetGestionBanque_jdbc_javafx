package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Entities.Client;
import Entities.Compte;
import application.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addCompte implements Initializable {

	@FXML
	private TextField idcomptefid;

	@FXML
	private TextField soldefid;
	@FXML
	private TextField etatfid;
	@FXML
	private DatePicker datefid;
    @FXML
    private TextField debitmaxfid;
	@FXML
	private Button save;
	@FXML
	private Button clearajout;
	@FXML
	TextField decouvertmaxfid;

	@FXML
	private ComboBox<String> accountOP;
	@FXML
	private Label label;
	String query = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	Client client = null;
	private boolean update;
	int idcompte;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// this.getAllidClient();
		try {
			connection = DbConnect.getConnect();
			this.getAllidClient();
			idcomptefid.setDisable(true);
			etatfid.setDisable(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getAllidClient() {
		try {
			ObservableList<String> allId = FXCollections.observableArrayList();
			resultSet = connection.createStatement().executeQuery("select  idClient  from client");
			List<Client> listClient = new ArrayList<Client>();
			while (resultSet.next()) {
				Client c = new Client();
				c.setIdClient((resultSet.getInt("idClient")));
				listClient.add(c);
			}
			Iterator<Client> it = listClient.iterator();
			while (it.hasNext()) {
				allId.add(String.valueOf(it.next().getIdClient()));
			}
			accountOP.setItems(allId);
			// oprationOp.setItems(Operation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void save(MouseEvent event) throws SQLException {

		connection = DbConnect.getConnect();
		String idCompte = idcomptefid.getText();
		String solde = soldefid.getText();
		String idClient = accountOP.getValue();
		String decouvertmax = decouvertmaxfid.getText();
		String dateCreation = String.valueOf(datefid.getValue());
		String etat = etatfid.getText();
		String debitmax=debitmaxfid.getText();
		// System.out.print(idClient);

		if (idClient.isEmpty() ||dateCreation.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please 3abi les données");
			alert.showAndWait();
			label.setText("Please 3abi les données");
		} 
		else if (solde.isEmpty())
			
		{
			getQuery();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, String.valueOf(accountOP.getValue()));
			preparedStatement.setDouble(2, 0);
			preparedStatement.setString(3, decouvertmaxfid.getText());
			preparedStatement.setString(4, debitmaxfid.getText());
			preparedStatement.setString(5, String.valueOf(datefid.getValue()));
			
			preparedStatement.setString(6, "non");
			preparedStatement.execute();
			label.setText("good insert");
		}
		else  {
			
			getQuery();
			insert();
			label.setText("good insert");
			clear();

		}
		
	}

	private void getQuery() {

		if (update == false) {

			query = "INSERT INTO  `compte` (  `idClient`, `solde`, `decouvertmax`,`debitMax`,`datecreation`, `etat`) VALUES (?,?,?,?,?,?)";

		} else {
			query = "UPDATE `compte` SET " + "`idClient`=?," + "`solde`=?," + "`decouvertmax`=?," + "debitMax=? ,"+"`datecreation`=? ,"+"`etat`=?" +

					  " WHERE idCompte = '" + idcompte + "'";
		}

	}

	private void insert() {

		try {

			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, idcomptefid.getText());
		
		
			if(Double.parseDouble(soldefid.getText())<0)
			{
			/* if(Double.parseDouble( decouvertmaxfid.getText())>0)
			 {*/
			preparedStatement.setString(1, String.valueOf(accountOP.getValue()));
			preparedStatement.setString(2, soldefid.getText());
			preparedStatement.setDouble(3, (-Double.parseDouble(soldefid.getText())));
			preparedStatement.setString(4, debitmaxfid.getText());
			preparedStatement.setString(5, String.valueOf(datefid.getValue()));
			
			preparedStatement.setString(6, "oui");
			preparedStatement.execute();

			}
			/* else {
				 Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("decouvert ne pas etre negatif");
					alert.showAndWait();
				
			 }*/
			//}
			else
			{
				 if(Double.parseDouble( decouvertmaxfid.getText())>0)
				 {
				preparedStatement.setString(1, String.valueOf(accountOP.getValue()));
				preparedStatement.setString(2, soldefid.getText());
				preparedStatement.setString(3, decouvertmaxfid.getText());
				preparedStatement.setString(4, debitmaxfid.getText());
				preparedStatement.setString(5, String.valueOf(datefid.getValue()));
				preparedStatement.setString(6, "non");
				preparedStatement.execute();
			}
				 
			 else {
				 Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("decouvert ne pas etre negatif");
					alert.showAndWait();
				
			 }
			
			
			

			}} catch (SQLException ex) {
			Logger.getLogger(addClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void clear() {
		idcomptefid.setText(null);
		soldefid.setText(null);
		accountOP.setPromptText(null);
		decouvertmaxfid.setText(null);
		datefid.setValue(null);
		etatfid.setText(null);
		debitmaxfid.setText(null);
	}

	void setUpdate(boolean b) {
		this.update = b;

	}

	@FXML
	private void close(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	void setTextField(int id,int accountOP, double d, Double Decouvertmax,double debitmax, LocalDate date, String etat) {

		idcompte = id;
		soldefid.setText(String.valueOf(d));
		this.accountOP.setValue(String.valueOf(accountOP));
		decouvertmaxfid.setText(String.valueOf(Decouvertmax));
		datefid.setValue(date);
		debitmaxfid.setText(String.valueOf(debitmax));
		etatfid.setText(etat);

	}
	void setTextField( Double solde) {
		soldefid.setText(String.valueOf(solde));
	}
}