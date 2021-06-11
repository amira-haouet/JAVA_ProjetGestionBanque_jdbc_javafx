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

public class debiter implements Initializable {
	private boolean crediter;
	private String query;
	private int idcompte;
	private double montant;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	@FXML
	private Label operation;
	@FXML
	private TextField montantfid;

	@FXML
	private ComboBox<String> combocompte;
	@FXML
	private Label title;

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
			/// nkharej decouvert max mte3 un tel compte(le compte selectionne de la liste deroulante) o nhoto f resultat
			PreparedStatement pdecouvertmax = connection
					.prepareStatement("select decouvertmax from compte where idCompte=" + combocompte.getValue());
			ResultSet resultat = pdecouvertmax.executeQuery();
			resultat.next();
			double decouvertmax = resultat.getDouble(1);

			// nkharej debit max de ce meme compte o nhoto f resultat
			PreparedStatement pdebitmax = connection
					.prepareStatement("select debitmax from compte where idCompte=" + combocompte.getValue());
			ResultSet resultat1 = pdebitmax.executeQuery();
			resultat1.next();
			double debitmax = resultat1.getDouble(1);

			/* tjbed solde yezmo mayfoutech -decouvertmax et si solde<0 etat=a decouvert=oui */
			PreparedStatement psolde = connection
					.prepareStatement("select solde from compte where idCompte=" + combocompte.getValue());
			ResultSet resultatsolde = psolde.executeQuery();
			resultatsolde.next();
			double solderes = resultatsolde.getDouble(1);
			PreparedStatement  soldenegatif = connection
					.prepareStatement("Update `compte` set `etat` = 'oui'" + " WHERE solde<0");
			soldenegatif.execute();
			/****/
			connection = DbConnect.getConnect();
			String montant = montantfid.getText();
			String idCompte = combocompte.getValue();

			if ((Double.parseDouble(montant) <= debitmax) && (Double.parseDouble(montant)) > 0) {
				/*
				 * if((solderes-Double.parseDouble(montant))>0 &&
				 * (solderes-Double.parseDouble(montant))>(-decouvertmax))
				 * 
				 * {
				 */
				String mnt = montantfid.getText();
				if(((solderes-Double.parseDouble(mnt))>(-decouvertmax)) && (solderes>0) )
				{
					
					preparedStatement = connection
						.prepareStatement("Update `compte` set `solde` = solde-?" + " WHERE idCompte = ?");
				preparedStatement.setString(1, montantfid.getText());
				preparedStatement.setString(2, String.valueOf(combocompte.getValue()));
				preparedStatement.execute();

				title.setText("C'est bon debiter");
				Font font = Font.font("Arial", FontWeight.BOLD, 15);
				title.setFont(font);
				// clear();

			}
				else if((solderes<0) && (solderes-Double.parseDouble(montant))>(-decouvertmax) )
				{
					preparedStatement = connection
							.prepareStatement("Update `compte` set `solde` = solde-?" + " WHERE idCompte = ?");
					preparedStatement.setString(1, montantfid.getText());
					preparedStatement.setString(2, String.valueOf(combocompte.getValue()));
					preparedStatement.execute();
					PreparedStatement  pupd = connection
							.prepareStatement("Update `compte` set `etat` = 'oui'" + " WHERE idCompte = ?");
					pupd.setString(1, String.valueOf(combocompte.getValue()));
					pupd.execute();

					title.setText("C'est bon debiter");
					Font font = Font.font("Arial", FontWeight.BOLD, 15);
					title.setFont(font);
				}		
				
				else if((solderes-Double.parseDouble(montant))<(-decouvertmax) && (solderes<0))
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("sorry le montant depasse le decouvertmax veuillez verifiez el montant!!");
				alert.showAndWait();
				

			}
				/*if(solderes<0)
				{
					PreparedStatement  soldenegatif = connection
							.prepareStatement("Update `compte` set `etat` = 'oui'" + " WHERE idCompte = ?");
					soldenegatif.setString(1, String.valueOf(combocompte.getValue()));
					soldenegatif.execute();
				}
			/*
			 * else if (((solderes-Double.parseDouble(montant))<0)&&(solderes<0) &&
			 * (solderes-Double.parseDouble(montant))>(-decouvertmax) ) { preparedStatement
			 * = connection
			 * .prepareStatement("Update `compte` set etat='oui' WHERE idCompte = "
			 * +combocompte.getValue()); preparedStatement.execute();
			 * 
			 * 
			 * preparedStatement = connection
			 * .prepareStatement("Update `compte` set `solde` = solde-?" +
			 * " WHERE idCompte = ?"); preparedStatement.setString(1, montantfid.getText());
			 * preparedStatement.setString(2, String.valueOf(combocompte.getValue()));
			 * preparedStatement.execute();
			 * 
			 * } else if (((solderes-Double.parseDouble(montant))<0)&&(solderes<0) &&
			 * (solderes-Double.parseDouble(montant))<(-decouvertmax) )
			 * 
			 * { Alert alert = new Alert(Alert.AlertType.ERROR); alert.setHeaderText(null);
			 * alert.setContentText(
			 * "sorry le montant depasse le decouvertmax veuillez verifiez el montant!!");
			 * alert.showAndWait(); } }
			 * 
			 * 
			 * 
			 * 
			 */
			}
			else {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("sorry le montant depasse le debitmax ou le montant est negatif veuillez verifiez el montant!!");
				alert.showAndWait();

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

		/*	PreparedStatement  soldenegatif = connection
					.prepareStatement("Update `compte` set `etat` = 'oui'  WHERE solde<0");
			soldenegatif.execute();*/
			// idcomptefid.setDisable(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
