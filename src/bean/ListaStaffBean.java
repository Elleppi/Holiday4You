package bean;

import java.util.List;

import attori.UtenteRegistrato;
import control.ListaLocaliController;
import control.ListaStaffController;

public class ListaStaffBean {

	private List<UtenteRegistrato> listaStaff;
	
	public ListaStaffBean() {

	}

	public void setListaStaff() {
		
		ListaStaffController llc = ListaStaffController.getInstance();
		
		this.listaStaff = llc.getStaff();
		
		this.setListaStaff(listaStaff);
		
	}

	/**
	 * @return the listaStaff
	 */
	public List<UtenteRegistrato> getListaStaff() {
		return listaStaff;
	}

	/**
	 * @param listaStaff the listaStaff to set
	 */
	public void setListaStaff(List<UtenteRegistrato> listaStaff) {
		this.listaStaff = listaStaff;
	}

	public String getStringaStaff(int i) {
		
		String sStaff = "";
		String attivo = "Non convalidato";
		
		if(listaStaff.get(i).isAttivo())
			attivo = "Convalidato";
		sStaff = "Nome: " + listaStaff.get(i).getNome() 
				+ "; Cognome: " + listaStaff.get(i).getCognome()
				+ "; Ruolo: " + listaStaff.get(i).getRuolo()
				+ "; " + attivo	+ ";";	
		
		return sStaff;
	}
	
}
