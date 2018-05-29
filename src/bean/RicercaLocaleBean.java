package bean;

import control.RicercaLocaleController;
import libreria.Locale;

public class RicercaLocaleBean {
	private Locale l;
	private int errore;
	
	public RicercaLocaleBean() {
		this.errore = 0;
		this.l = new Locale();
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
	 * @return the città
	 */
	public String getProvincia() {
		return this.l.getProvincia();
	}

	/**
	 * @param città the città to set
	 */
	public void setProvincia(String provincia) {
		this.l.setProvincia(provincia);
	}

	/**
	 * @return the mQ
	 */
	public int getmQ() {
		return this.l.getmQ();
	}

	/**
	 * @param mQ the mQ to set
	 */
	public void setmQ(int mQ) {
		this.l.setmQ(mQ);
	}

	/**
	 * @return the nBagni
	 */
	public int getnBagni() {
		return this.l.getnBagni();
	}

	/**
	 * @param nBagni the nBagni to set
	 */
	public void setnBagni(int nBagni) {
		this.l.setnBagni(nBagni);
	}

	/**
	 * @return the nPersone
	 */
	public int getnPersone() {
		return this.l.getPersoneMax();
	}

	/**
	 * @param nPersone the nPersone to set
	 */
	public void setnPersone(int nPersone) {
		this.l.setPersoneMax(nPersone);
	}

	/**
	 * @return the animali
	 */
	public boolean isAnimali() {
		return this.l.getAnimali();
	}

	/**
	 * @param animali the animali to set
	 */
	public void setAnimali(boolean animali) {
		this.l.setAnimali(animali);
	}

	/**
	 * @return the giardino
	 */
	public boolean isGiardino() {
		return this.l.getGiardino();
	}

	/**
	 * @param giardino the giardino to set
	 */
	public void setGiardino(boolean giardino) {
		this.l.setGiardino(giardino);
	}

	/**
	 * @return the postoAuto
	 */
	public boolean isPostoAuto() {
		return this.l.getPostoAuto();
	}

	/**
	 * @param postoAuto the postoAuto to set
	 */
	public void setPostoAuto(boolean postoAuto) {
		this.l.setPostoAuto(postoAuto);
	}

	/**
	 * @return the prezzoANotte
	 */
	public int getPrezzoANotte() {
		return this.l.getPrezzoANotte();
	}

	/**
	 * @param prezzoANotte the prezzoANotte to set
	 */
	public void setPrezzoANotte(int prezzoANotte) {
		this.l.setPrezzoANotte(prezzoANotte);
	}
	
	public void check(String ID, String nome, String ruolo) {
		RicercaLocaleController rlc = RicercaLocaleController.getInstance();
		
		this.errore = rlc.verificaUtente(ID, nome, ruolo);
	}
	
	public String ricercaLocale() {
		RicercaLocaleController rlc = RicercaLocaleController.getInstance();
		
		String s = rlc.ricercaLocali(this.l);
	
		return s;
	}
}