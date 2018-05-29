package attori;

import libreria.Data;

public class UtenteRegistrato extends Utente{
	
	private String username;
	private String password;
	private int ID;
	private int nLocali;
	private int nPrenotazioni;
	private String nTelefono;
	private String eMail;
	private boolean attivo;
	
	public UtenteRegistrato() {
		super();
		this.nLocali = 0;
		this.nPrenotazioni = 0;
		this.ID = -1;
		this.username = null;
		this.password = null;
		this.nTelefono = null;
		this.eMail = null;
		this.attivo = false;
	}

	public UtenteRegistrato(String nome, String cognome, String codiceFiscale, Data dataNascita, String ruolo, 
			String username, String password, String nTelefono, String eMail) {
		
		super(nome, cognome, codiceFiscale, dataNascita, ruolo);
		this.username = username;
		this.password = password;
		this.nTelefono = nTelefono;
		this.eMail = eMail;
		this.nLocali = 0;
		this.nPrenotazioni = 0;
		this.attivo = false;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the nLocali
	 */
	public int getnLocali() {
		return nLocali;
	}

	/**
	 * @param nLocali the nLocali to set
	 */
	public void setnLocali(int nLocali) {
		this.nLocali = nLocali;
	}

	/**
	 * @return the nPrenotazioni
	 */
	public int getnPrenotazioni() {
		return nPrenotazioni;
	}

	/**
	 * @param nPrenotazioni the nPrenotazioni to set
	 */
	public void setnPrenotazioni(int nPrenotazioni) {
		this.nPrenotazioni = nPrenotazioni;
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

	/**
	 * @return the attivo
	 */
	public boolean isAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
	
	
	
}
