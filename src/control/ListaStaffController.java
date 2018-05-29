package control;

import java.util.ArrayList;
import java.util.List;

import attori.UtenteRegistrato;
import feature.GestoreDB;
import libreria.Locale;

public class ListaStaffController {

	private static ListaStaffController instance;
	
	public static ListaStaffController getInstance() {
        if (instance == null)
            instance = new ListaStaffController();
        return instance;
    }
	
	public ListaStaffController() {

	}
	
	
	public List<UtenteRegistrato> getStaff() {
		GestoreDB gdb = GestoreDB.getInstance();
		
		List<UtenteRegistrato> lista;

		lista = gdb.getListaStaff();

		return lista;
		
	}
	

}
