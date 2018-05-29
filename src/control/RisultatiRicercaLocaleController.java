package control;

import java.util.ArrayList;
import java.util.List;

public class RisultatiRicercaLocaleController {
	
	private static RisultatiRicercaLocaleController instance;
	
	private RisultatiRicercaLocaleController() {
	}
	
	public static RisultatiRicercaLocaleController getInstance() {
		if(instance == null)
			instance = new RisultatiRicercaLocaleController();
		
		return instance;
	}
	
	public synchronized List<String> decodificaStringa(String s) {
		List<String> l = new ArrayList<>();
		
		while(s.length() > 1) {
			l.add(s.substring(0, s.indexOf("|")));
			l.add(s.substring(s.indexOf("|") + 1, s.indexOf("-")));
			s = s.substring(s.indexOf("-") + 1);
		}
		
		return l;
	}

}
