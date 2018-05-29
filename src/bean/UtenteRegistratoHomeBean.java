package bean;

import attori.UtenteRegistrato;
import control.UtenteRegistratoHomeController;

public class UtenteRegistratoHomeBean {
	
	private UtenteRegistrato ur;
	private int errore;
	
	public UtenteRegistratoHomeBean() {
		this.errore = 0;
	}

	/**
	 * @return the errore
	 */
	public int getErrore() {
		return errore;
	}



	/**
	 * @param errore the errore to set
	 */
	public void setErrore(int errore) {
		this.errore = errore;
	}



	/**
	 * @return the nome
	 */
	public String getNome() {
		return this.ur.getNome();
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.ur.setNome(nome);
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return this.ur.getCognome();
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.ur.setCognome(cognome);
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return this.ur.getCodiceFiscale();
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.ur.setCodiceFiscale(codiceFiscale);
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return this.ur.getRuolo();
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ur.setRuolo(ruolo);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.ur.getUsername();
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.ur.setUsername(username);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.ur.getPassword();
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.ur.setPassword(password);
	}
	
	/**
	 * @return the ur
	 */
	public UtenteRegistrato getUr() {
		return ur;
	}

	/**
	 * @param ur the ur to set
	 */
	public void setUr(UtenteRegistrato ur) {
		this.ur = ur;
	}

	public void loadLocatarioHome(int ID, String nome, String ruolo) {
		UtenteRegistratoHomeController lhc = UtenteRegistratoHomeController.getInstance();
		
		UtenteRegistrato utente = lhc.setLocatarioParameter(ID, nome, ruolo);
		
		if(utente == null)
			this.errore = -1;
		else
			this.ur = utente;
	}
	
}
