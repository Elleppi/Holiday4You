package bean;

import control.InserisciLocaleController;

public class InserisciLocaleBean {
	
	
	private int iDlocatore;
	private String nome;
	private String ruolo;
	private String indirizzo;
	private int numCivico;
	private String citta;
	private String provincia;
	private int mQ;
	private int nBagni;
	private int persone;
	private int prezzo;
	private boolean animali;
	private boolean postoAuto;
	private boolean giardino;
	private String descrizione;
	
	
	public InserisciLocaleBean() {
		
	}
	
	
	public InserisciLocaleBean(String indirizzo, String citta, String provincia, int mQ, int nBagni, int persone, int prezzo,
			boolean animali, boolean postoAuto, boolean giardino, String descrizione) {
		super();
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.provincia = provincia;
		this.mQ = mQ;
		this.nBagni = nBagni;
		this.persone = persone;
		this.prezzo = prezzo;
		this.animali = animali;
		this.postoAuto = postoAuto;
		this.giardino = giardino;
		this.descrizione = descrizione;
	}


	
	/**
	 * @return the iDlocatore
	 */
	public int getiDlocatore() {
		return iDlocatore;
	}


	/**
	 * @param iDlocatore the iDlocatore to set
	 */
	public void setiDlocatore(int iDlocatore) {
		this.iDlocatore = iDlocatore;
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
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}


	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	/**
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}


	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}


	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}


	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	/**
	 * @return the mQ
	 */
	public int getmQ() {
		return mQ;
	}


	/**
	 * @param mQ the mQ to set
	 */
	public void setmQ(int mQ) {
		this.mQ = mQ;
	}


	/**
	 * @return the nBagni
	 */
	public int getnBagni() {
		return nBagni;
	}


	/**
	 * @param nBagni the nBagni to set
	 */
	public void setnBagni(int nBagni) {
		this.nBagni = nBagni;
	}


	/**
	 * @return the persone
	 */
	public int getPersone() {
		return persone;
	}


	/**
	 * @param persone the persone to set
	 */
	public void setPersone(int persone) {
		this.persone = persone;
	}


	/**
	 * @return the prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}


	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}


	/**
	 * @return the animali
	 */
	public boolean isAnimali() {
		return animali;
	}


	/**
	 * @param animali the animali to set
	 */
	public void setAnimali(boolean animali) {
		this.animali = animali;
	}


	/**
	 * @return the postoAuto
	 */
	public boolean isPostoAuto() {
		return postoAuto;
	}


	/**
	 * @param postoAuto the postoAuto to set
	 */
	public void setPostoAuto(boolean postoAuto) {
		this.postoAuto = postoAuto;
	}


	/**
	 * @return the giardino
	 */
	public boolean isGiardino() {
		return giardino;
	}


	/**
	 * @param giardino the giardino to set
	 */
	public void setGiardino(boolean giardino) {
		this.giardino = giardino;
	}


	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}


	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public int convalidaInserimento() {
		
		InserisciLocaleController ilc = InserisciLocaleController.getInstance();
		
		return ilc.aggiungiLocale(iDlocatore, indirizzo, numCivico, citta, provincia, mQ, nBagni, persone, prezzo, animali,
				postoAuto, giardino, descrizione);
		
	}
	
	public int checkParameters(String id, String nome, String ruolo) {
		
		InserisciLocaleController ilc = InserisciLocaleController.getInstance();
		
		return ilc.checkParameters(id, nome, ruolo);
		
	}
	

}
