package bean;

import java.util.List;

import control.InserisciLocaleController;
import control.ListaLocaliController;
import libreria.Locale;

public class ListaLocaliBean {
	
	private int IDutente_registrato;
	private String nome;
	private String ruolo;
	private List<Locale> listaLocali;

	
	public ListaLocaliBean() {
		
	}

	/**
	 * @return the iDLocatore
	 */
	public int getIDutente_registrato() {
		return IDutente_registrato;
	}

	/**
	 * @param iDLocatore the iDLocatore to set
	 */
	public void setIDutente_registrato(int IDutente_registrato) {
		this.IDutente_registrato = IDutente_registrato;
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
	 * @return the listaLocali
	 */
	public List<Locale> getListaLocali() {
		return listaLocali;
	}

	/**
	 * @param listaLocali the listaLocali to set
	 */
	public void setListaLocali(List<Locale> listaLocali) {
		this.listaLocali = listaLocali;
	}
	
	
	public void setListaLocali(String IDutente_registrato, String ruolo) {
		
		ListaLocaliController llc = ListaLocaliController.getInstance();
		
		this.IDutente_registrato = Integer.parseInt(IDutente_registrato);
		this.ruolo = ruolo;
		
		this.listaLocali = llc.getLocali(this.IDutente_registrato, this.ruolo);
		
	}
	
	public String getStringaLocale(int i) {
		
		String sLocale;
		String attivo = "Non convalidato";
		
		if(listaLocali.get(i).isAttivo())
			attivo = "Convalidato";
		sLocale = "Indirizzo locale: " + String.valueOf(listaLocali.get(i).getIndirizzo()) 
				+ "; Città: " + listaLocali.get(i).getCittà() + "; " + attivo	+ ";";
		
		return sLocale;
	}
	
	public int checkParameters(String id, String nome, String ruolo) {
		
		ListaLocaliController ilc = ListaLocaliController.getInstance();
		
		return ilc.checkParameters(id, nome, ruolo);
		
	}	
	
}
