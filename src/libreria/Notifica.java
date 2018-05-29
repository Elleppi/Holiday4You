package libreria;

public class Notifica {
	
	private int IDnotifica;
	private int IDoggetto;
	private String categoria;
	private int IDmittente;
	private int IDdestinatario;
	private String testo;
	private Data dataInvioNotifica;
	private boolean letta;

	public Notifica() {
		this.IDnotifica = 0;
		this.IDoggetto = 0;
		this.categoria = null;
		this.IDmittente = 0;
		this.IDdestinatario = 0;
		this.testo = "";
		this.dataInvioNotifica = null;
		this.letta = false;
	}

	
	public Notifica(int iDoggetto, String categoria, int iDmittente, int iDdestinatario,
			String testo, Data dataInvioNotifica, boolean letta) {
		super();
		IDoggetto = iDoggetto;
		this.categoria = categoria;
		IDmittente = iDmittente;
		IDdestinatario = iDdestinatario;
		this.testo = testo;
		this.dataInvioNotifica = dataInvioNotifica;
		this.letta = letta;
	}

	/**
	 * @return the iDnotifica
	 */
	public int getIDnotifica() {
		return IDnotifica;
	}

	/**
	 * @param iDnotifica the iDnotifica to set
	 */
	public void setIDnotifica(int iDnotifica) {
		IDnotifica = iDnotifica;
	}

	/**
	 * @return the iDoggetto
	 */
	public int getIDoggetto() {
		return IDoggetto;
	}

	/**
	 * @param iDoggetto the iDoggetto to set
	 */
	public void setIDoggetto(int iDoggetto) {
		IDoggetto = iDoggetto;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the iDmittente
	 */
	public int getIDmittente() {
		return IDmittente;
	}

	/**
	 * @param iDmittente the iDmittente to set
	 */
	public void setIDmittente(int iDmittente) {
		IDmittente = iDmittente;
	}

	/**
	 * @return the iDdestinatario
	 */
	public int getIDdestinatario() {
		return IDdestinatario;
	}

	/**
	 * @param iDdestinatario the iDdestinatario to set
	 */
	public void setIDdestinatario(int iDdestinatario) {
		IDdestinatario = iDdestinatario;
	}

	/**
	 * @return the messaggio
	 */
	public String getTesto() {
		return testo;
	}

	/**
	 * @param messaggio the messaggio to set
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	/**
	 * @return the dataInvioNotifica
	 */
	public Data getDataInvioNotifica() {
		return dataInvioNotifica;
	}

	/**
	 * @param dataInvioNotifica the dataInvioNotifica to set
	 */
	public void setDataInvioNotifica(Data dataInvioNotifica) {
		this.dataInvioNotifica = dataInvioNotifica;
	}

	/**
	 * @return the letta
	 */
	public boolean isLetta() {
		return letta;
	}

	/**
	 * @param letta the letta to set
	 */
	public void setLetta(boolean letta) {
		this.letta = letta;
	}

	
}




