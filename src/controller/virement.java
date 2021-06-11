package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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

public class virement implements Initializable{
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
	private ComboBox<String> restecompte;
	@FXML
	private Label title;
    @FXML
    private Label label;
	public void getAllidCompte() {
		try {
			ObservableList<String> allId = FXCollections.observableArrayList();
			resultSet = connection.createStatement().executeQuery("select  idCompte  from compte");
			List<Compte> listecompte = new ArrayList<Compte>();
			while (resultSet.next()) {
				Compte c = new Compte();
				c.setCodeCompte(resultSet.getInt("idcompte"));
				listecompte.add(c);
			}
			Iterator<Compte> it = listecompte.iterator();
			while (it.hasNext()) {
				allId.add(String.valueOf(it.next().getCodeCompte()));
			}
			combocompte.setItems(allId);
			// oprationOp.setItems(Operation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getrestecompte() {
		try {
			ObservableList<String> allId = FXCollections.observableArrayList();
			resultSet = connection.createStatement().executeQuery("select  idCompte  from compte where idcompte <>"+combocompte.getValue());
			List<Compte> listecompte = new ArrayList<Compte>();
			while (resultSet.next()) {
				Compte c = new Compte();
				c.setCodeCompte(resultSet.getInt("idcompte"));
				listecompte.add(c);
				
			}
			Iterator<Compte> it = listecompte.iterator();
			while (it.hasNext()) {
				allId.add(String.valueOf(it.next().getCodeCompte()));
			}
			restecompte.setItems(allId);
			// oprationOp.setItems(Operation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			connection = DbConnect.getConnect();
			this.getAllidCompte() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	public Double getsolde()
	{
		return montant;
		
	}
	@FXML
	public void virement() throws SQLException
	{
		//String idCompte = combocompte.getValue();
		// double s=0;
		 double montant;
		//Statement statement = connection.createStatement();
			/// nkharej decouvert max mte3 un tel compte(le compte selectionne de la liste deroulante) o nhoto f resultat
			PreparedStatement pdecouvertmax = connection
					.prepareStatement("select decouvertmax from compte where idCompte=" + combocompte.getValue());
			ResultSet resultatde = pdecouvertmax.executeQuery();
			resultatde.next();
			double decouvertmax = resultatde.getDouble(1);

			// nkharej debit max de ce meme compte o nhoto f resultat
			PreparedStatement pdebitmax = connection
					.prepareStatement("select debitmax from compte where idCompte=" + combocompte.getValue());
			ResultSet resultat1 = pdebitmax.executeQuery();
			resultat1.next();
			double debitmax = resultat1.getDouble(1);
		
			/* tjbed solde yezmo mayfoutech -decouvertmax et si solde<0 etat=a decouvert=oui */
		

		PreparedStatement psolde=connection.prepareStatement("select solde from compte where idCompte="+combocompte.getValue());
		ResultSet resultat = psolde.executeQuery();
		resultat.next();
		
		double	s=resultat.getDouble(1);
		connection = DbConnect.getConnect();
		//String montant = montantfid.getText();
		String idCompte = combocompte.getValue();
		
		montant=Double.parseDouble(montantfid.getText());
		
		if( ((s-montant)>(-decouvertmax)) && (s>0)) {
			
		PreparedStatement p1 = connection.prepareStatement("update compte set solde =solde+? "+" where idCompte="+restecompte.getValue());
		PreparedStatement p2 = connection.prepareStatement("update compte set solde =solde-? "+"where idCompte="+combocompte.getValue());

		p1.setDouble(1, Double.parseDouble(montantfid.getText()));
		p1.execute();
		p2.setDouble(1, Double.parseDouble(montantfid.getText()));
		p2.execute(); 
		label.setText("Virement avec succes ! ");
		}
		else if((s<0) && (s-montant)>(-decouvertmax))
		{
			PreparedStatement p1 = connection.prepareStatement("update compte set solde =solde+? "+" where idCompte="+restecompte.getValue());
			PreparedStatement p2 = connection.prepareStatement("update compte set solde =solde-? "+"where idCompte="+combocompte.getValue());

			p1.setDouble(1, Double.parseDouble(montantfid.getText()));
			p1.execute();
			p2.setDouble(1, Double.parseDouble(montantfid.getText()));
			p2.execute(); 
			label.setText("Virement avec succes ! ");}
		
		//System.out.print(s);
		/*if(s>Double.parseDouble(montantfid.getText()))	
		{*/
			//PreparedStatement p1=connection.prepareStatement("select num, from compte where num =? ");
	//	p1.setString(1, idCompte);
			
		
	//}
		else if ((s-montant)<(-decouvertmax) && (s<0))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("montant depasse le decouvert max !!");
			alert.showAndWait();
			
		}
	}

}
