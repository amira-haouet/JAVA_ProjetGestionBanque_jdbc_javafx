package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Client;
import Entities.Compte;
import application.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class listercomptes implements Initializable {

	@FXML
	private TableView<Compte> tablecomptes;

	@FXML
	private TableColumn<Compte, String> idClientcol;

	@FXML
	private TableColumn<Compte, String> datecol;

	@FXML
	private ComboBox<String> accountOP;

	@FXML
	private TableColumn<Compte, String> numcomptecol;

	@FXML
	private Button listerbtn;

	String query = null;

	Connection connection = null;

	PreparedStatement preparedStatement = null;

	ResultSet resultSet = null;

	Client client = null;
	Compte compte = null;
// declarer liste 
	ObservableList<Client> ClientList = FXCollections.observableArrayList();
	ObservableList<Compte> CompteList = FXCollections.observableArrayList();

	public void getAllidClient() {
		try {
			ObservableList<String> allId = FXCollections.observableArrayList();
			resultSet = connection.createStatement().executeQuery("select distinct idClient  from compte ");
			List<Client> listClient = new ArrayList<Client>();
			while (resultSet.next()) {
				Client c = new Client();
				c.setIdClient(resultSet.getInt("idClient"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// this.getAllidClient();
		try {
			connection = DbConnect.getConnect();
			this.getAllidClient();
			datecol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
			idClientcol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
			numcomptecol.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
			// idcomptefid.setDisable(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listercomptes() {
		try {
			// String idClient = accountOP.getValue();
			String query = "SELECT * FROM `compte` WHERE `idClient` =" + accountOP.getValue();
			PreparedStatement pt = connection.prepareStatement(query);
			// preparedStatement.setString(1, String.valueOf(accountOP.getValue()));
			ResultSet res = pt.executeQuery();
			while (res.next()) {
				CompteList.add(new Compte(res.getInt("idCompte"), res.getInt("idClient"), res.getDate("dateCreation")));

				tablecomptes.setItems(CompteList);

			}
		} catch (SQLException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	
	@FXML
	private void clear() {
		//datecol.
	}

}
