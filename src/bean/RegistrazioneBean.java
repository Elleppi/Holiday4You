package bean;

import control.RegistrazioneController;

public class RegistrazioneBean {
	
	private int ID;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String dataNascita;
	private String user;
	private String psw;
	private String ruolo;
	private String nTelefono;
	private String eMail;
	
	private String errCfRegistrato= "Il Codice Fiscale inserito corrisponde ad un utente già registrato";
	private String errUserRegistrato = "Username già utilizzato";

	
	
	public RegistrazioneBean() {
		
	}
	
	public RegistrazioneBean(int iD, String nome, String cognome, String codiceFiscale, String dataNascita, 
			String username, String password, String ruolo) {
		
		this.ID = iD;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataNascita = dataNascita;
		this.user = username;
		this.psw = password;
		this.ruolo = ruolo;
	}


	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
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
		this.codiceFiscale = codiceFiscale;
	}

	
	/**
	 * @return the dataNascita
	 */
	public String getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return user;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.user = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return psw;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.psw = password;
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
	
	
	
	/**
	 * @return the nTelefono
	 */
	public String getnTelefono() {
		return nTelefono;
	}

	/**
	 * @param nTelefono the nTelefono to set
	 */
	public void setnTelefono(String nTelefono) {
		this.nTelefono = nTelefono;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	
	public int convalidaRegistrazione() {

		
		RegistrazioneController rc = RegistrazioneController.getInstance();
		
		return rc.Registra(nome, cognome, codiceFiscale, dataNascita, user, psw, ruolo, nTelefono, eMail);
		
	} 
	
	public String errore(int e) {
		String errore = "";
		
		switch (e) {
		case 1:
			errore = errCfRegistrato;
			break;
		case 2:
			errore = errUserRegistrato;
			break;
		}
		
		return errore;
	}

}
