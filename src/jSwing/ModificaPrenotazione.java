package jSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import attori.Partecipante;
import attori.UtenteRegistrato;
import bean.GestionePrenotazioneBean;
import libreria.Data;
import libreria.Prenotazione;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificaPrenotazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GestionePrenotazioneBean gpb;
	private JButton btnHolidayyou;
	private JTextField nomePartecipante[], cognomePartecipante[], cfPartecipante[];
	
	public ModificaPrenotazione(UtenteRegistrato ur, Prenotazione p) {
		Data start;
		Data end;
		
		this.setVisible(true);
		
		gpb = new GestionePrenotazioneBean();
		gpb.setP(p);
		
		Partecipante partecipante[] = new Partecipante[gpb.getP().getnPartecipanti()];
		List<Partecipante> lPartecipante = new ArrayList<>();
		
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
		
		btnHolidayyou = new JButton("Holiday4You");
		btnHolidayyou.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Login();
				dispose();
			}
		});
		btnHolidayyou.setBounds(10, 11, 111, 49);
		btnHolidayyou.setOpaque(false);
		contentPane.add(btnHolidayyou);
		
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
		
		JScrollPane ModificaPrenotazione = new JScrollPane();
		ModificaPrenotazione.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ModificaPrenotazione.setBounds(118, 71, 680, 290);
		contentPane.add(ModificaPrenotazione);
		ModificaPrenotazione.setLayout(null);
		ModificaPrenotazione.setOpaque(false);
		
		lblBentornato.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblBentornato.setForeground(Color.ORANGE);
		lblBentornato.setHorizontalAlignment(SwingConstants.CENTER);
		lblBentornato.setBounds(118, 8, 680, 52);
		
		JTextField DataStart, DataEnd;
		JLabel Start, End;
		
		Start = new JLabel("Data inizio affitto");
		DataStart = new JTextField(Data.formattaData(gpb.getP().getDataStart()));
		Start.setBounds(10, 1, 100, 20);
		DataStart.setBounds(150, 1, 100, 20);
		DataStart.setHorizontalAlignment(SwingConstants.CENTER);
		
		ModificaPrenotazione.add(Start);
		ModificaPrenotazione.add(DataStart);
		
		End = new JLabel("Data fine affitto");
		DataEnd = new JTextField(Data.formattaData(gpb.getP().getDataEnd()));
		End.setBounds(10, 21, 100, 20);
		DataEnd.setBounds(150, 21, 100, 20);
		DataEnd.setHorizontalAlignment(SwingConstants.CENTER);
		
		ModificaPrenotazione.add(End);
		ModificaPrenotazione.add(DataEnd);
		
		nomePartecipante = new JTextField[gpb.getP().getnPartecipanti()];
		cognomePartecipante = new JTextField[gpb.getP().getnPartecipanti()];
		cfPartecipante = new JTextField[gpb.getP().getnPartecipanti()];
		
		for(int i=0; i<gpb.getP().getnPartecipanti(); i++) {
			String nome = gpb.getP().getPartecipanti().get(i).getNome();
			String cognome = gpb.getP().getPartecipanti().get(i).getCognome();
			String cf = gpb.getP().getPartecipanti().get(i).getCodiceFiscale();
			
			partecipante[i] = new Partecipante();
			partecipante[i].setiDpartecipante(p.getPartecipanti().get(i).getiDpartecipante());
			
			nomePartecipante[i] = new JTextField(nome);
			cognomePartecipante[i] = new JTextField(cognome);
			cfPartecipante[i] = new JTextField(cf);
			
			nomePartecipante[i].setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			nomePartecipante[i].setForeground(Color.blue);
			nomePartecipante[i].setHorizontalAlignment(SwingConstants.CENTER);
			nomePartecipante[i].setBounds(10, 61+(i*70), 660, 20);
			
			cognomePartecipante[i].setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			cognomePartecipante[i].setForeground(Color.blue);
			cognomePartecipante[i].setHorizontalAlignment(SwingConstants.CENTER);
			cognomePartecipante[i].setBounds(10, 81+(i*70), 660, 20);
			
			cfPartecipante[i].setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			cfPartecipante[i].setForeground(Color.blue);
			cfPartecipante[i].setHorizontalAlignment(SwingConstants.CENTER);
			cfPartecipante[i].setBounds(10, 101+(i*70), 660, 20);
			
			ModificaPrenotazione.add(nomePartecipante[i]);
			ModificaPrenotazione.add(cognomePartecipante[i]);
			ModificaPrenotazione.add(cfPartecipante[i]);
		}
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DettagliPrenotazione(p.getIdPrenotazione(), ur);
				dispose();
			}
		});
		btnAnnulla.setBounds(472, 372, 111, 23);
		contentPane.add(btnAnnulla);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				Data s = Data.formattaDataReverse(DataStart.getText());
				gpb.getP().setDataStart(s);
				Data e = Data.formattaDataReverse(DataEnd.getText());
				gpb.getP().setDataEnd(e);
				
				boolean vuoto = false;
				
				for(int i=0; i<gpb.getP().getnPartecipanti(); i++) {
					if(nomePartecipante[i].getText().length() < 1 ||
						cognomePartecipante[i].getText().length() < 1 ||
						cfPartecipante[i].getText().length() < 1) {
							JOptionPane.showMessageDialog(null, "Hai lasciato un campo vuoto !!!", 
								"Impossibile continuare", JOptionPane.ERROR_MESSAGE);
							vuoto = true;
					}
				}
					
				if(!vuoto) {
					for(int j=0; j<gpb.getP().getnPartecipanti(); j++) {
						partecipante[j].setNome(nomePartecipante[j].getText());
						partecipante[j].setCognome(cognomePartecipante[j].getText());
						partecipante[j].setCodiceFiscale(cfPartecipante[j].getText());
						
						lPartecipante.add(partecipante[j]);
					}
					
					gpb.getP().setPartecipanti(lPartecipante);
					int error = gpb.convalidaModifica(gpb.getP(), ur);
					
					if(error < 0) {
						JOptionPane.showMessageDialog(null, gpb.errore(error), 
								"Impossibile continuare", JOptionPane.ERROR_MESSAGE);
					}
					else if(gpb.isTimer(gpb.getP())) {
						JOptionPane.showMessageDialog(null, "Le tue modifiche sono state apportate", 
								"Modifiche Apportate", JOptionPane.INFORMATION_MESSAGE);
						new DettagliPrenotazione(gpb.getP().getIdPrenotazione(), ur);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Non puoi più apportare modifiche", 
								"Timer Scaduto", JOptionPane.INFORMATION_MESSAGE);
						new DettagliPrenotazione(gpb.getP().getIdPrenotazione(), ur);
						dispose();
					}
					
				}
				
			}
		});
		btnConferma.setBounds(284, 372, 111, 23);
		contentPane.add(btnConferma);
		
		JLabel sfondo = new JLabel(new ImageIcon(this.getClass().getResource("/jSwing/image/sfondo2.jpg")));
		sfondo.setBounds(0, 0, 808, 453);
		contentPane.add(sfondo);
		sfondo.setOpaque(true);
		sfondo.setBackground(SystemColor.inactiveCaptionBorder);
		
	}
}
