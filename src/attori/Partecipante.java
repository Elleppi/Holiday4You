package attori;

public class Partecipante {
	
	private int iDpartecipante;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	
	public Partecipante() {
		this.iDpartecipante = 0;
		this.nome = null;
		this.cognome = null;
		this.codiceFiscale = null;
	}
	
	public Partecipante(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}
	
	/**
	 * @return the iDpartecipante
	 */
	public int getiDpartecipante() {
		return iDpartecipante;
	}

	/**
	 * @param iDpartecipante the iDpartecipante to set
	 */
	public void setiDpartecipante(int iDpartecipante) {
		this.iDpartecipante = iDpartecipante;
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
		if(nome.contains("'"))
			nome = nome.substring(0, nome.indexOf("'")) + nome.substring(nome.indexOf("'") + 1);
		
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

	/*Salva i campi di un partecipante in una stringa (campi separati da ',')*/
	public static String partecipanteToString(Partecipante p) {
		
		return p.getNome() + "," + p.getCognome() + "," + p.getCodiceFiscale() + ";";
	}
	
	/*Ritorna un oggetto partecipante salvato in una stringa*/
	public static Partecipante stringToPartecipante(String s) {
		Partecipante p = new Partecipante();
		
		s = s.substring(0, s.indexOf(','));
		p.setNome(s);
		
		s = s.substring(1, s.indexOf(','));
		p.setCognome(s);
		
		s = s.substring(1);
		p.setCodiceFiscale(s);
		
		return p;
	}
}
