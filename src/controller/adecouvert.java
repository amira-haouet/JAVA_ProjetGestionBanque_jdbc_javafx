package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class adecouvert   implements Initializable{
	@FXML
    private TableColumn<Compte, String>  clientcol;

    @FXML
    private TableColumn<Compte, String>  soldecol;

    @FXML
    private TableColumn<Compte, String> numcomptecol;
    @FXML
    private TableColumn<Compte, String> etatcol;
    @FXML
    private Button close;
    @FXML
    private TableView<Compte> tableadecouvert;
	String query = null;

	Connection connection = null;

	PreparedStatement preparedStatement = null;

	ResultSet resultSet = null;
	ObservableList<Compte> compteadecouvert = FXCollections.observableArrayList();
	@FXML
	
	public void getAdecouvert() {
	try {
		
		String query="SELECT * FROM `compte` WHERE solde<0 ";
		PreparedStatement pt = connection.prepareStatement(query);
		ResultSet res = pt.executeQuery();
		while (res.next()) {
			compteadecouvert.add(new Compte(res.getInt("idCompte"),res.getDouble("solde"),res.getInt("idClient"),
					res.getString("etat")));

			tableadecouvert.setItems(compteadecouvert);

		}
	} catch (SQLException ex) {
		Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
	}
	
}
	

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	try {
		connection = application.DbConnect.getConnect();
		getAdecouvert() ;

		soldecol.setCellValueFactory(new PropertyValueFactory<>("solde"));
		clientcol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		numcomptecol.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
		etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
		

}}
