package bean;

import attori.UtenteRegistrato;
import control.DettagliStaffController;
import control.ListaStaffController;

public class DettagliStaffBean {
	
	private UtenteRegistrato staff;

	public DettagliStaffBean() {

	}

	/**
	 * @return the staff
	 */
	public UtenteRegistrato getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
	 */
	public void setStaff(UtenteRegistrato staff) {
		this.staff = staff;
	}


	public void rimuoviUtente() {
		
		DettagliStaffController dlc = DettagliStaffController.getInstance();
		
		dlc.rimuoviStaff(this.getStaff());
		
	}

	public void assumiUtente() {
		
		DettagliStaffController dlc = DettagliStaffController.getInstance();
		
		dlc.assumiStaff(this.getStaff());
		
	}

}
