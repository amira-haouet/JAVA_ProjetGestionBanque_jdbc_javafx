package Entities;

import java.sql.Date;

public class Compte {
	private int idCompte;
	private Date dateCreation;
	private double solde;
	private int idClient;
	public double debitMax = 500;
	public String etat;
	private double decouvertmax = 2000;

	public Compte() {
		super();
	}

	public Compte(int idCompte, double solde, int idClient, Date dateCreation) {
		// super();
		this.idCompte = idCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.idClient = idClient;
	}

	public Compte(int idCompte, Date dateCreation, double solde, int idClient, String etat, double decouvertmax) {
		super();
		this.idCompte = idCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.idClient = idClient;
		this.etat = etat;
		this.decouvertmax = decouvertmax;
	}

	public Compte(int idCompte, Date dateCreation, double solde, int idClient, String etat) {
		super();
		this.idCompte = idCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.idClient = idClient;
		this.debitMax = 500;
		this.etat = etat;
		this.decouvertmax = 2000;
	}

	public Compte(int idCompte, int idClient, double solde,	double decouvertmax, double debitMax,  Date dateCreation,String etat
		) {
		super();
		this.idCompte = idCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.idClient = idClient;
		this.debitMax = debitMax;
		this.etat = etat;
		this.decouvertmax = decouvertmax;
	}

	public Compte(int idCompte, double solde, int idClient) {
		super();
		this.idCompte = idCompte;
		this.solde = solde;
		this.idClient = idClient;
	}

	public Compte(int int1, int int2, Date date) {
		this.idCompte = int1;
		this.dateCreation = date;
		this.idClient = int2;
	}

	public Compte(int int1, double double1, int int2, String nString) {
		this.idCompte = int1;
		this.solde = double1;
		this.idClient = int2;
		this.etat=nString;
	}

	public int getCodeCompte() {
		return idCompte;
	}

	public void setCodeCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public double getDebitMax() {
		return debitMax;
	}

	public void setDebitMax(double debitMax) {
		this.debitMax = debitMax;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public double getDecouvertmax() {
		return decouvertmax;
	}

	public void setDecouvertmax(double decouvertmax) {
		this.decouvertmax = decouvertmax;
	}

	@Override
	public String toString() {
		return "Compte [idCompte=" + idCompte + ", dateCreation=" + dateCreation + ", solde=" + solde + ", idClient="
				+ idClient + ", debitMax=" + debitMax + ", etat=" + etat + ", decouvertmax=" + decouvertmax + "]";
	}

}
