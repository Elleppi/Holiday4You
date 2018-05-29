package bean;

import java.util.List;

import control.RisultatiRicercaLocaleController;

public class RisultatiRicercaLocaleBean {
	
	List<String> l;
	
	public RisultatiRicercaLocaleBean() {
	}
	
	public String getLocale(int i) {
		return this.l.get(i);
	}
	
	public int sizeList() {
		return this.l.size();
	}
	
	public void decodificaStringa(String s) {
		RisultatiRicercaLocaleController rrlc = RisultatiRicercaLocaleController.getInstance();
		
		this.l = rrlc.decodificaStringa(s);
	}

}
