package control;

import java.util.ArrayList;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Locale;

public class DettagliStaffController {

	private static DettagliStaffController instance;
	
	public static DettagliStaffController getInstance() {
        if (instance == null)
            instance = new DettagliStaffController();
        return instance;
    }
	
	public DettagliStaffController() {

	}
	


	public void rimuoviStaff(UtenteRegistrato ur) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		List<Locale> listaLocali;
		int IDuser = ur.getID();
		String ruolo = ur.getRuolo();
		
		listaLocali = gdb.getLocaliStaff(IDuser, ruolo);
		gdb.rimuoviStaff(ur.getID());

		int IDstaff = -1;
		List<UtenteRegistrato> listaStaff;
		int min;
		
		for(int i=0; i<listaLocali.size(); ++i) {
			listaStaff = gdb.caricaRuolo(ruolo);
			min = listaStaff.get(0).getnLocali();
			IDstaff = listaStaff.get(0).getID();
			
			for(int j=1; j<listaStaff.size(); j++) {
				if(listaStaff.get(j).getnLocali() < min) {
					min = listaStaff.get(j).getnLocali();
					IDstaff = listaStaff.get(j).getID();
				}
			}
			
			gdb.assegnaStaff(listaLocali.get(i).getID(), IDstaff, ruolo);

		}
		
	}

	public void assumiStaff(UtenteRegistrato ur) {
		GestoreDB gdb = GestoreDB.getInstance();
		
		gdb.assumiStaff(ur.getID());
		
	}
	

}
