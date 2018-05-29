package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import attori.Partecipante;
import bean.DettagliPrenotazioneBean;
import bean.PrenotaLocaleBean;
import junit.framework.Assert;
import libreria.Data;
import libreria.Prenotazione;

public class PrenotaLocaleTest {

	@Test
	public void testPrenotazione() {
		int IDlocatario = 1;
		int IDlocale = 5;
		Data start = new Data();
		start.setGiorno(30);
		start.setMese(1);
		start.setAnno(2017);
		
		Data end = new Data();
		end.setGiorno(15);
		end.setMese(2);
		end.setAnno(2017);
		
		String note = "ciao";
		
		List<Partecipante> l = new ArrayList<>();
		Partecipante p1 = new Partecipante();
		p1.setNome("Giovanni");
		p1.setCognome("DAgostino");
		p1.setCodiceFiscale("NNCONLE");
		
		l.add(p1);
		
		PrenotaLocaleBean plb = new PrenotaLocaleBean(IDlocatario, IDlocale, start, end, note, l);
		
		int IDprenotazione = plb.convalidaPrenotazione();
		
		if(IDprenotazione > 0) {
			DettagliPrenotazioneBean dpb = new DettagliPrenotazioneBean();
			
			dpb.loadPrenotazione(String.valueOf(IDprenotazione), String.valueOf(IDlocatario), "Lorenzo", "Locatario");
			
			Prenotazione p2 = new Prenotazione();
			p2 = dpb.getPrenotazione();
			
			Assert.assertEquals("OK", IDlocatario, p2.getIdLocatario());
		}
		else {
			System.out.println("errore: " + IDprenotazione);
			Assert.assertTrue(false);
		}
	}

}
