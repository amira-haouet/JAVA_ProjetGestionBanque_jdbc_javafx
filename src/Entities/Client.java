package Entities;

import java.sql.Date;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;
public class Client {
	private int idClient;
	private String nom;
	private String prenom;
	
	private Date datenaissance;
	private String adresse;
	private String email;

	// private ArrayList<Compte> listeCompte=new ArrayList<>();

	public Client() {
		super();
	}

	public Client(int idClient, String nom, String prenom, Date datenaissance, String adresse, String email) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
		this.adresse = adresse;
		this.email = email;
	}

	public Client(int idClient, String nom, String prenom, String adresse, String email) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", datanaissance="
				+ datenaissance + ", adresse=" + adresse + ", email=" + email + "]";
	}
	public static boolean isEmailAdress(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$" );
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
		}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

}