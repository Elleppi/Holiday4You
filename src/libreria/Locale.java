package libreria;

import java.util.List;

public class Locale{
	
	private int ID;
	private int IDlocatore;
	private String indirizzo;
	private String città;
	private String provincia;
	private int mQ;
	private int nBagni;
	private int personeMax;
	private Boolean animali;
	private Boolean giardino;
	private Boolean postoAuto;
	private int prezzoANotte;
	private String descrizione;
	private Data dataInserimento;
	private int convalidaScout;
	private int convalidaLegale;
	private int IDScout;
	private int IDLegale;
	private boolean attivo;
	private List<Prenotazione> p;
	
	public Locale() {
		this.ID = 0;
		this.IDlocatore = 0;
		this.indirizzo = null;
		this.città = null;
		this.provincia = null;
		this.mQ = 0;
		this.nBagni = 0;
		this.personeMax = 0;
		this.animali = false;
		this.giardino = false;
		this.postoAuto = false;
		this.prezzoANotte = 0;
		this.descrizione = null;
		this.dataInserimento = null;
		this.convalidaLegale = 0;
		this.convalidaScout = 0;
		this.IDScout = 0;
		this.IDLegale = 0;
		this.attivo = false;
		this.p = null;
	}

	public Locale(int iDlocatore, String indirizzo, String città, String provincia, int mQ, int nBagni, int persone, Boolean animali, Boolean giardino, 
			Boolean postoAuto, int prezzoANotte, String descrizione, Data dataInserimento, int IDScout, int IDLegale) {
		
		this.IDlocatore = iDlocatore;
		this.indirizzo = indirizzo;
		this.città = città;
		this.provincia = provincia;
		this.mQ = mQ;
		this.nBagni = nBagni;
		this.personeMax = persone;
		this.animali = animali;
		this.giardino = giardino;
		this.postoAuto = postoAuto;
		this.prezzoANotte = prezzoANotte;
		this.descrizione = descrizione;
		this.dataInserimento = dataInserimento;
		this.IDScout = IDScout;
		this.IDLegale = IDLegale;
		this.convalidaScout = 0;
		this.convalidaLegale = 0;
		this.attivo = false;
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
	 * @return the iDlocatore
	 */
	public int getIDlocatore() {
		return IDlocatore;
	}

	/**
	 * @param iDlocatore the iDlocatore to set
	 */
	public void setIDlocatore(int iDlocatore) {
		IDlocatore = iDlocatore;
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
	 * @return the città
	 */
	public String getCittà() {
		return città;
	}

	/**
	 * @param città the città to set
	 */
	public void setCittà(String città) {
		this.città = città;
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
	 * @return the personeMax
	 */
	public int getPersoneMax() {
		return personeMax;
	}

	/**
	 * @param personeMax the personeMax to set
	 */
	public void setPersoneMax(int personeMax) {
		this.personeMax = personeMax;
	}

	/**
	 * @return the animali
	 */
	public Boolean getAnimali() {
		return animali;
	}

	/**
	 * @param animali the animali to set
	 */
	public void setAnimali(Boolean animali) {
		this.animali = animali;
	}

	/**
	 * @return the giardino
	 */
	public Boolean getGiardino() {
		return giardino;
	}

	/**
	 * @param giardino the giardino to set
	 */
	public void setGiardino(Boolean giardino) {
		this.giardino = giardino;
	}

	/**
	 * @return the postoAuto
	 */
	public Boolean getPostoAuto() {
		return postoAuto;
	}

	/**
	 * @param postoAuto the postoAuto to set
	 */
	public void setPostoAuto(Boolean postoAuto) {
		this.postoAuto = postoAuto;
	}

	/**
	 * @return the prezzoANotte
	 */
	public int getPrezzoANotte() {
		return prezzoANotte;
	}

	/**
	 * @param prezzoANotte the prezzoANotte to set
	 */
	public void setPrezzoANotte(int prezzoANotte) {
		this.prezzoANotte = prezzoANotte;
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

	/**
	 * @return the dataInserimento
	 */
	public Data getDataInserimento() {
		return dataInserimento;
	}

	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(Data dataInserimento) {
		this.dataInserimento = dataInserimento;
	}



	/**
	 * @return the convalidaScout
	 */
	public int getConvalidaScout() {
		return convalidaScout;
	}

	/**
	 * @param convalidaScout the convalidaScout to set
	 */
	public void setConvalidaScout(int convalidaScout) {
		this.convalidaScout = convalidaScout;
	}

	/**
	 * @return the convalidaLegale
	 */
	public int getConvalidaLegale() {
		return convalidaLegale;
	}

	/**
	 * @param convalidaLegale the convalidaLegale to set
	 */
	public void setConvalidaLegale(int convalidaLegale) {
		this.convalidaLegale = convalidaLegale;
	}

	/**
	 * @return the iDScout
	 */
	public int getIDScout() {
		return IDScout;
	}

	/**
	 * @param iDScout the iDScout to set
	 */
	public void setIDScout(int iDScout) {
		IDScout = iDScout;
	}

	/**
	 * @return the iDLegale
	 */
	public int getIDLegale() {
		return IDLegale;
	}

	/**
	 * @param iDLegale the iDLegale to set
	 */
	public void setIDLegale(int iDLegale) {
		IDLegale = iDLegale;
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

	/**
	 * @return the p
	 */
	public List<Prenotazione> getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(List<Prenotazione> p) {
		this.p = p;
	}
	
}
