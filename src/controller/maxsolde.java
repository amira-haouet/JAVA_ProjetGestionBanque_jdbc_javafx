package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Client;
import Entities.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class maxsolde implements Initializable {
    @FXML
    private TableColumn<Compte, String>  clientcol;

    @FXML
    private TableColumn<Compte, String>  soldecol;

    @FXML
    private TableColumn<Compte, String> numcomptecol;
    @FXML
    private Button close;
    @FXML
    private TableView<Compte> tablesoldemax;
	String query = null;

	Connection connection = null;

	PreparedStatement preparedStatement = null;

	ResultSet resultSet = null;
	ObservableList<Compte> comptemax = FXCollections.observableArrayList();
	@FXML
	public void getMax() {
	try {
		
		String query="SELECT * FROM `compte` WHERE solde = (select MAX(solde) from compte)";
		PreparedStatement pt = connection.prepareStatement(query);
		ResultSet res = pt.executeQuery();
		while (res.next()) {
			comptemax.add(new Compte(res.getInt("idCompte"),res.getDouble("solde"),res.getInt("idClient")));

			tablesoldemax.setItems(comptemax);

		}
	} catch (SQLException ex) {
		Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
	}
	
}
	
	@FXML
	private void close(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			connection = application.DbConnect.getConnect();
			getMax();

			soldecol.setCellValueFactory(new PropertyValueFactory<>("solde"));
			clientcol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
			numcomptecol.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
}
	

}
