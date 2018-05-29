package jSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.sun.webkit.ContextMenu.ShowContext;

import attori.UtenteRegistrato;
import bean.DettagliPrenotazioneBean;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;

public class DettagliPrenotazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DettagliPrenotazioneBean dpb;

	public DettagliPrenotazione(int IDprenotazione, UtenteRegistrato ur) {
		
		this.setVisible(true);
		dpb = new DettagliPrenotazioneBean();
		dpb.loadPrenotazione(String.valueOf(IDprenotazione), String.valueOf(ur.getID()), ur.getNome(), ur.getRuolo());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBentornato = new JLabel("Bentornato " + ur.getNome());
		lblBentornato.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblBentornato.setForeground(Color.ORANGE);
		lblBentornato.setHorizontalAlignment(SwingConstants.CENTER);
		lblBentornato.setBounds(118, 8, 680, 52);
		contentPane.add(lblBentornato);
		
		JLabel lblNewLabel = new JLabel("Universit\u00E0 degli Studi di Roma Tor Vergata - Ingegneria del Software e Progettazione Web - Lorenzo Paris, Giovanni D'Agostino");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 428, 788, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnHolidayyou = new JButton("Holiday4You");
		btnHolidayyou.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Login();
				dispose();
			}
		});
		btnHolidayyou.setBounds(10, 11, 110, 49);
		btnHolidayyou.setOpaque(false);
		contentPane.add(btnHolidayyou);
		
		JScrollPane dettagliPrenotazione = new JScrollPane();
		dettagliPrenotazione.setToolTipText("");
		dettagliPrenotazione.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		dettagliPrenotazione.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dettagliPrenotazione.setOpaque(false);
		dettagliPrenotazione.setBounds(118, 71, 680, 301);
		contentPane.add(dettagliPrenotazione);
		dettagliPrenotazione.setLayout(null);
		
		JLabel lblIndirizzo = new JLabel("INDIRIZZO: " + dpb.getIndirizzoLocale());
		lblIndirizzo.setBounds(10, 1, 660, 19);
		lblIndirizzo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblIndirizzo.setForeground(Color.blue);
		lblIndirizzo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIndirizzo.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliPrenotazione.add(lblIndirizzo);
		
		JLabel lblPrezzo = new JLabel("PREZZO A NOTTE: " + dpb.getPrezzoANotteLocale());
		lblPrezzo.setBounds(10, 31, 660, 19);
		lblPrezzo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblPrezzo.setForeground(Color.blue);
		lblPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliPrenotazione.add(lblPrezzo);
		
		JLabel lblDataPrenotazione = new JLabel("DATA PRENOTAZIONE: " + dpb.getDataPrenotazione());
		lblDataPrenotazione.setBounds(10, 61, 660, 19);
		lblDataPrenotazione.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblDataPrenotazione.setForeground(Color.blue);
		lblDataPrenotazione.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDataPrenotazione.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliPrenotazione.add(lblDataPrenotazione);
		
		JLabel lblDataStart = new JLabel("DATA INIZIO AFFITTO: " + dpb.getDataStart());
		lblDataStart.setBounds(10, 91, 660, 19);
		lblDataStart.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblDataStart.setForeground(Color.blue);
		lblDataStart.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDataStart.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliPrenotazione.add(lblDataStart);
		
		JLabel lblDataEnd = new JLabel("DATA FINE AFFITTO: " + dpb.getDataEnd());
		lblDataEnd.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDataEnd.setForeground(Color.blue);
		lblDataEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataEnd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblDataEnd.setBounds(10, 121, 660, 19);
		dettagliPrenotazione.add(lblDataEnd);
		
		String note = dpb.getNote();
		
		if(dpb.getNote() == null || dpb.getNote().equals("NULL"))
			note = "";
		
		JLabel lblNote = new JLabel("Note: " + note);
		lblNote.setBounds(10, 151, 660, 19);
		lblNote.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNote.setForeground(Color.blue);
		lblNote.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliPrenotazione.add(lblNote);
		
		JLabel lblConvalidaLocatore = new JLabel("CONVALIDA LOCATORE: " + dpb.getConvalidaLocatore());
		lblConvalidaLocatore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConvalidaLocatore.setForeground(Color.blue);
		lblConvalidaLocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvalidaLocatore.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblConvalidaLocatore.setBounds(10, 181, 660, 19);
		dettagliPrenotazione.add(lblConvalidaLocatore);
		
		JLabel lblConvalidaLegale = new JLabel("CONVALIDA LEGALE: " + dpb.getConvalidaLegale());
		lblConvalidaLegale.setBounds(10, 211, 660, 19);
		lblConvalidaLegale.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblConvalidaLegale.setForeground(Color.blue);
		lblConvalidaLegale.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConvalidaLegale.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliPrenotazione.add(lblConvalidaLegale);
		
		JLabel lblPagata = new JLabel("PAGATA: " + dpb.getPagata());
		lblPagata.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPagata.setForeground(Color.blue);
		lblPagata.setHorizontalAlignment(SwingConstants.CENTER);
		lblPagata.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblPagata.setBounds(10, 241, 660, 19);
		dettagliPrenotazione.add(lblPagata);
		
		String s = dpb.getDatiPartecipanti();
		s = s.replace("<br><br>", "-");
		s = s.replace("<br>", ",");
		s = s.replace("Nome: ", "");
		s = s.replace("Cognome: ", "");
		s = s.replace("Codice Fiscale: ", "");
		s = s.substring(0, s.length()-1);
		
		JLabel lblPartecipanti = new JLabel("PARTECIPANTI: " + s);
		lblPartecipanti.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPartecipanti.setForeground(Color.blue);
		lblPartecipanti.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartecipanti.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblPartecipanti.setBounds(10, 271, 660, 19);
		dettagliPrenotazione.add(lblPartecipanti);
		
	/*	JLabel partecipanti[] = new JLabel[dpb.getP().getnPartecipanti()*3];
		
		int j=0;
		
		for(int i=0; i<dpb.getP().getnPartecipanti(); i++) {
			partecipanti[i*3] = new JLabel(dpb.getP().getPartecipanti().get(j).getNome());
			partecipanti[(i*3)+1] = new JLabel(dpb.getP().getPartecipanti().get(j).getCognome());
			partecipanti[(i*3)+2] = new JLabel(dpb.getP().getPartecipanti().get(j).getCodiceFiscale());
			
			j++;
		}
		
		for(int i=0; i<dpb.getP().getnPartecipanti()*3; i++) {
			partecipanti[i].setBounds(10, 301+(i*30), 660, 19);
			partecipanti[i].setHorizontalAlignment(SwingConstants.CENTER);
			partecipanti[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			partecipanti[i].setHorizontalTextPosition(SwingConstants.CENTER);
			System.out.println(partecipanti[i].getText());
			dettagliPrenotazione.add(partecipanti[i]);
			
		} */
		
		JButton btnModifica = new JButton("MODIFICA");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int risposta = JOptionPane.showConfirmDialog(null, "Sei sicuro di "
						+ "voler modificare la prenotazione ?", 
						"Conferma modifica", JOptionPane.YES_NO_OPTION);
				if(risposta == JOptionPane.YES_OPTION) {
					if(dpb.isTimer()) {
						new ModificaPrenotazione(ur, dpb.getPrenotazione());
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "La prenotazione non può più essere modificata", 
								"Timer Scaduto", JOptionPane.ERROR_MESSAGE);
				}
				else
					return;
					
			}
		});
		
		JPanel menu = new JPanel();
		menu.setOpaque(false);
		menu.setBounds(10, 183, 98, 62);
		contentPane.add(menu);
		
		JButton btnHome = new JButton("Home");
		btnHome.setPreferredSize(new Dimension(91, 23));
		btnHome.setMinimumSize(new Dimension(91, 23));
		btnHome.setMaximumSize(new Dimension(91, 23));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new HomePage(ur);
				dispose();
			}
		});
		menu.add(btnHome);
		
		JButton btnPrenotazioni = new JButton("Prenotazioni");
		btnPrenotazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ListaPrenotazioni(ur);
				dispose();
			}
		});
		menu.add(btnPrenotazioni);
		
		btnModifica.setBounds(332, 383, 89, 23);
		contentPane.add(btnModifica);
		
		JButton btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int risposta = JOptionPane.showConfirmDialog(null, "Sei sicuro di "
						+ "voler annullare definitivamente la prenotazione ?", 
						"Conferma eliminazione", JOptionPane.YES_NO_OPTION);
				if(risposta == JOptionPane.YES_OPTION) {
					if(dpb.isTimer()) {
						dpb.eliminaPrenotazione();
						JOptionPane.showMessageDialog(null, "La prenotazione è stata annullata", 
								"Prenotazione eliminata", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "La prenotazione non può più essere annullata", 
								"Timer Scaduto", JOptionPane.INFORMATION_MESSAGE);
				}
				else
					return;
					
			}
		});
		btnAnnulla.setBounds(483, 383, 89, 23);
		contentPane.add(btnAnnulla);
		
		if(!dpb.isTimer()) {
			btnModifica.setEnabled(false);
			btnAnnulla.setEnabled(false);
		}
		
		JLabel sfondo = new JLabel(new ImageIcon(this.getClass().getResource("/jSwing/image/sfondo2.jpg")));
		sfondo.setBounds(0, 0, 808, 453);
		contentPane.add(sfondo);
		sfondo.setOpaque(true);
		sfondo.setBackground(SystemColor.inactiveCaptionBorder);
		
	}
}
