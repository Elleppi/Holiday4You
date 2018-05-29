package attori;

import libreria.Data;

public abstract class Utente {
	
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Data dataNascita;
	private String ruolo;

	public Utente() {
		this.nome = null;
		this.cognome = null;
		this.codiceFiscale = null;
		this.ruolo = null;
	}
	
	public Utente(String nome, String cognome, String codiceFicale, Data dataNascita, String ruolo) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFicale;
		this.dataNascita = dataNascita;
		this.ruolo = ruolo;
	}
	

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		codiceFiscale.toUpperCase();
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the dataNascita
	 */
	public Data getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(Data dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
}
