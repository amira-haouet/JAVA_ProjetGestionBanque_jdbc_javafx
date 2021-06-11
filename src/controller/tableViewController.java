package controller;

import java.net.URL;

import java.util.ResourceBundle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Client;
import Entities.Compte;
import application.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

//import jdk.nashorn.internal.objects.annotations.Property;
public class tableViewController implements Initializable {

	@FXML
	private TableView<Client> tableclient;
	@FXML
	private TableColumn<Client, String> idcol;
	@FXML
	private TableColumn<Client, String> nomcol;
	@FXML
	private TableColumn<Client, String> prenomcol;
	@FXML
	private TableColumn<Client, String> datenaissancecol;
	@FXML
	private TableColumn<Client, String> adrscol;
	@FXML
	private TableColumn<Client, String> emailcol;
	@FXML
	private TableColumn<Compte, String> idcomptecol;
	@FXML
	private TableColumn<Compte, String> idccol;
	@FXML
	private TableColumn<Compte, String> etatcol;
	@FXML
	private TableColumn<Compte, String> soldecol;
	@FXML
	private TableView<Compte> tablecompte;
	@FXML
	private Button refreshTable;
	@FXML
	private Button addClient;
	@FXML
	private Button updateClient;
	@FXML
	private Button addCompte;
	@FXML
	private Button	adecouvert;
	@FXML
	private TableColumn<Compte, String> datecreationcol;
	@FXML
	private TableColumn<Compte, String> debitmaxcol;

	@FXML
	Label title;

	@FXML
	private TableColumn<Compte, String> decouvertmaxcol;

	private Button refreshTablecompte;
	@FXML
	private TableColumn<Client, String> editCol;
	@FXML
	private TableColumn<Compte, String> editColcompte;
	// declaration
	String query = null;

	Connection connection = null;

	PreparedStatement preparedStatement = null;

	ResultSet resultSet = null;

	Client client = null;
	Compte compte = null;
// declarer liste 
	ObservableList<Client> ClientList = FXCollections.observableArrayList();
	ObservableList<Compte> CompteList = FXCollections.observableArrayList();

	/**
	 * Initializes the controller class.
	 */
	/*
	 * @Override public void initialize(URL url, ResourceBundle rb) { // TODO
	 * loadDate(); }
	 */

	@FXML
	private void getAddView(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/controller/addClient.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	@FXML
	private void getAdecouvert(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/controller/adecouvert.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	@FXML
	private void getVirement(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/controller/virement.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	@FXML
	private void getAddViewCompte(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/controller/addCompte.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);

			System.out.print(" \n erreur de load addCompte.fxml");
		}

	}

	@FXML
	private void getUpdateView(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/controller/Update.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void getMaxSolde(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/controller/maxsolde.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void ListerComptes(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/controller/listercomptes.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	public void refreshTable() {

		try {
			ClientList.clear();
			String query = "select * from client";
			PreparedStatement pt = connection.prepareStatement("select * from client");
			ResultSet res = pt.executeQuery();
			while (res.next()) {
				
				ClientList.add(
						new Client(res.getInt("idClient"), 
								res.getString("nom"),
								res.getString("prenom"),
						res.getDate("datenaissance"),
						res.getString("adresse"), 
						res.getString("email")));

				tableclient.setItems(ClientList);

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

	@FXML
	public void refreshTableCompte() {

		try {
			CompteList.clear();
			PreparedStatement  soldenegatif = connection
					.prepareStatement("Update `compte` set `etat` = 'oui'" + " WHERE solde<0");
			soldenegatif.execute();
			
			// (int idCompte, double solde, int idClient , Date dateCreation
			String query = "select * from compte";
			PreparedStatement pt = connection.prepareStatement("select * from compte");
			ResultSet res = pt.executeQuery();
			
			while (res.next()) {
				
				// int idCompte, Date dateCreation, double solde, int idClient, double debitMax,
				// String etat,double decouvertmax
				CompteList.add(new Compte(res.getInt("idCompte"),res.getInt("idClient"), res.getDouble("solde"),
						res.getDouble("decouvertmax")	, res.getDouble("debitMax"), res.getDate("dateCreation"), res.getString("etat")
						));

				tablecompte.setItems(CompteList);

			}
			
			
		} catch (SQLException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	@FXML
	private void getOperation(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/controller/operation.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);

			System.out.print(" \n erreur de load operation.fxml");
		}

	}
	
	@FXML
	private void getDebiter(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/controller/debiter.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);

			System.out.print(" \n erreur de load operation.fxml");
		}

	}
	public void loadDateCompte() throws SQLException

	{
		refreshTableCompte();
		connection = application.DbConnect.getConnect();
		//

		idcomptecol.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
		soldecol.setCellValueFactory(new PropertyValueFactory<>("solde"));
		idccol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
		decouvertmaxcol.setCellValueFactory(new PropertyValueFactory<>("decouvertmax"));
		datecreationcol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
       debitmaxcol.setCellValueFactory(new PropertyValueFactory<>("debitMax"));
		Callback<TableColumn<Compte, String>, TableCell<Compte, String>> cellFactory = (
				TableColumn<Compte, String> param) -> {
			// make cell containing buttons
			final TableCell<Compte, String> cell = new TableCell<Compte, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					{

						Button deleteIconcompte = new Button("delete");
						Button editIconcompte = new Button("edit");
						Font font = Font.font("Arial", FontWeight.BOLD, 15);
						editIconcompte.setFont(font);
						editIconcompte.setStyle("-fx-background-color: #00ff00");
						deleteIconcompte.setStyle("-fx-background-color: #FF3333");
						//editIconcompte.setStyle("");
						//deleteIconcompte.setStyle("-fx-text-fill: #FFFFFF");
						deleteIconcompte.setFont(font);
					
						Button creditIconcompte = new Button("crediter");
						creditIconcompte.setFont(font);
						creditIconcompte.setStyle("-fx-background-color: #F933FF");
						// delete
						deleteIconcompte.setOnMouseClicked((MouseEvent event) -> {

							try {
								compte = tablecompte.getSelectionModel().getSelectedItem();
								query = "DELETE FROM `compte` WHERE idCompte  =" + compte.getCodeCompte();
								connection = DbConnect.getConnect();
								preparedStatement = connection.prepareStatement(query);
								preparedStatement.execute();
								refreshTableCompte();

							} catch (SQLException ex) {
								Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
							}

						});
						// update
						editIconcompte.setOnMouseClicked((MouseEvent event) -> {

							compte = tablecompte.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("addCompte.fxml"));
							try {
								loader.load();

								refreshTableCompte();
							} catch (IOException ex) {
								Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
							}

							addCompte addcompte = loader.getController();
							addcompte.setUpdate(true);

							addcompte.setTextField(compte.getCodeCompte(),  compte.getIdClient(),compte.getSolde(),
									compte.getDecouvertmax(),compte.getDebitMax() ,compte.getDateCreation().toLocalDate(), compte.getEtat());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();

							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();

						});

						/*creditIconcompte.setOnMouseClicked((MouseEvent event) -> {
							compte = tablecompte.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("operation.fxml"));
							try {
								loader.load();
								operation op = loader.getController();

								refreshTableCompte();
								//op.setCrediter(false);
							} catch (IOException ex) {
								Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
							}
							
							operation addcompte = loader.getController();
							
						
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							// title.setText("crediter");
							// op.insert();
							// title.setText("crediter");
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();
						});
					/*	creditIconcompte.setOnMouseClicked((MouseEvent event) -> {
							compte = tablecompte.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("operation.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
							}
							operation op = loader.getController();
							op.setCrediter(true);
							op.setTextField(compte.getCodeCompte(), compte.getSolde(), compte.getidClient(),
									compte.getDecouvertmax(), compte.getDateCreation().toLocalDate(), compte.getEtat());
							// op.test();
							// op.insert();
							// title.setText("crediter");
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							// title.setText("crediter");

							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();
						});*/
						HBox managebtn = new HBox(editIconcompte, deleteIconcompte);
						managebtn.setStyle("-fx-alignment:center");

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};

		editColcompte.setCellFactory(cellFactory);
		// creditIconcompte.setCellFactory(cellFoctorycompte);
		tablecompte.setItems(CompteList);
		/*
		 * editCol.setCellFactory(cellFoctory); tableclient.setItems(ClientList);
		 */
	}

	public void loadDate() throws SQLException

	{

		connection = application.DbConnect.getConnect();
		refreshTable();

		idcol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		datenaissancecol.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
		adrscol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
//System.out.print(idcol);
		Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFoctory = (
				TableColumn<Client, String> param) -> {
			// make cell containing buttons
			final TableCell<Client, String> cell = new TableCell<Client, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					{

						Button deleteIcon = new Button("delete");
						Button editIcon = new Button("edit");
						Font font = Font.font("Arial", FontWeight.BOLD, 15);
						deleteIcon.setStyle("-fx-background-color: #00ff00");
						editIcon.setStyle("-fx-background-color: #FF3333");
						deleteIcon.setFont(font);
						editIcon.setFont(font);
						// Button creditIcon= new Button("crediter");
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {

							try {
								client = tableclient.getSelectionModel().getSelectedItem();
								query = "DELETE FROM `client` WHERE idClient  =" + client.getIdClient();
								connection = DbConnect.getConnect();
								preparedStatement = connection.prepareStatement(query);
								preparedStatement.execute();
								refreshTable();

							} catch (SQLException ex) {
								Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
							}

						});
						editIcon.setOnMouseClicked((MouseEvent event) -> {

							client = tableclient.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("addClient.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(tableViewController.class.getName()).log(Level.SEVERE, null, ex);
							}

							addClient addclient = loader.getController();
							addclient.setUpdate(true);
							addclient.setTextField(client.getIdClient(), client.getNom(), client.getPrenom(),
									client.getDatenaissance().toLocalDate(),

									client.getAdresse(), client.getEmail());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();

							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();

						});

						HBox managebtn = new HBox(editIcon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};

		editCol.setCellFactory(cellFoctory);
		tableclient.setItems(ClientList);

	}

	/************************************/

	/*
	 *****************************************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadDate();
			loadDateCompte();
			
			
		
				PreparedStatement  soldenegatif = connection
						.prepareStatement("Update `compte` set `etat` = 'oui'" + " WHERE solde<0");
				soldenegatif.execute();
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * @FXML public void ajouetClient(Client c) { // TODO Auto-generated method stub
	 * try { PreparedStatement pt =
	 * connection.prepareStatement("insert into client values (?,?,?,?,?,?)");
	 * pt.setInt(1, c.getidClient()); pt.setString(2, c.getNom()); pt.setString(3,
	 * c.getPrenom()); pt.setDate(4, c.getDatenaissance()); pt.setString(4,
	 * c.getAdresse()); pt.setString(5, c.getEmail()); int res = pt.executeUpdate();
	 * if (res == 1) { System.out.print("insert"); } else {
	 * System.out.print("no insert"); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */
}