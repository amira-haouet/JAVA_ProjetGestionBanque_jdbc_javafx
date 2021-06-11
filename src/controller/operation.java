package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class operation implements Initializable {
	@FXML
	private Label title;

	@FXML
	private Label operation;
	  @FXML
	    private TextField montantfid;
	  
	  @FXML
	    private ComboBox<String> combocompte;

	   

	  
	private boolean crediter;
	private String query;
	private int idcompte;
	private double montant;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	// tehot f wst label crediter ou debiter


	public void getAllidCompte() {
		try {
			ObservableList<String> allId = FXCollections.observableArrayList();
			resultSet = connection.createStatement().executeQuery("select  idCompte  from compte");
			List<Compte> listecompte = new ArrayList<Compte>();
			while (resultSet.next()) {
				Compte c = new Compte();
				c.setIdCompte(resultSet.getInt("idcompte"));
				listecompte.add(c);
			}
			Iterator<Compte> it = listecompte.iterator();
			while (it.hasNext()) {
				allId.add(String.valueOf(it.next().getIdCompte()));
			}
			combocompte.setItems(allId);
			// oprationOp.setItems(Operation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@FXML
	private void submit(MouseEvent event) {
		try {
			
			PreparedStatement psolde = connection
					.prepareStatement("select solde from compte where idCompte=" + combocompte.getValue());
			ResultSet resultatsolde = psolde.executeQuery();
			resultatsolde.next();
			double solderes = resultatsolde.getDouble(1);
			
			
			connection = DbConnect.getConnect();
			String montant = montantfid.getText();
			String idCompte = combocompte.getValue();
		
			
		
			preparedStatement = connection.prepareStatement("Update `compte` set `solde` = solde+?" +  " WHERE idCompte = ?");
			preparedStatement.setString(1, montantfid.getText());
			preparedStatement.setString(2, String.valueOf(combocompte.getValue()));
			preparedStatement.execute();
			
		
			title.setText("C'est bon crediter");
			Font font = Font.font("Arial", FontWeight.BOLD, 15);
			title.setFont(font);
			// clear();
			
			if(Double.parseDouble(montant)+solderes>0)
			{
				PreparedStatement petat = connection
						.prepareStatement("Update `compte` set etat='non' WHERE idCompte = "+combocompte.getValue());
				petat.execute();
			}
			else
			{
				PreparedStatement petat = connection
						.prepareStatement("Update `compte` set etat='oui' WHERE idCompte = "+combocompte.getValue());
				petat.execute();
			}
		} catch (SQLException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);

		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			connection = DbConnect.getConnect();
			this.getAllidCompte();
			//idcomptefid.setDisable(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}