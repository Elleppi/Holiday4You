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
import bean.DettagliStaffBean;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;

public class DettagliStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DettagliStaffBean dsb;

	public DettagliStaff(UtenteRegistrato staff, UtenteRegistrato ur) {
		
		this.setVisible(true);
		dsb = new DettagliStaffBean();
		dsb.setStaff(staff);
		
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
		
		JScrollPane dettagliStaff = new JScrollPane();
		dettagliStaff.setToolTipText("");
		dettagliStaff.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		dettagliStaff.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dettagliStaff.setOpaque(false);
		dettagliStaff.setBounds(118, 71, 680, 301);
		contentPane.add(dettagliStaff);
		dettagliStaff.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: " + dsb.getStaff().getNome());
		lblNome.setBounds(10, 1, 660, 19);
		lblNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNome.setForeground(Color.blue);
		lblNome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliStaff.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome: " + dsb.getStaff().getCognome());
		lblCognome.setBounds(10, 31, 660, 19);
		lblCognome.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCognome.setForeground(Color.blue);
		lblCognome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliStaff.add(lblCognome);
		
		JLabel lblDataNascita = new JLabel("Data di nascita: " + dsb.getStaff().getDataNascita());
		lblDataNascita.setBounds(10, 91, 660, 19);
		lblDataNascita.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblDataNascita.setForeground(Color.blue);
		lblDataNascita.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDataNascita.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliStaff.add(lblDataNascita);
		
		JLabel lblCF = new JLabel("Codica Fiscale: " + dsb.getStaff().getCodiceFiscale());
		lblCF.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCF.setForeground(Color.blue);
		lblCF.setHorizontalAlignment(SwingConstants.CENTER);
		lblCF.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCF.setBounds(10, 121, 660, 19);
		dettagliStaff.add(lblCF);
		
		JLabel lblRuolo = new JLabel("Ruolo: " + dsb.getStaff().getRuolo());
		lblRuolo.setBounds(10, 61, 660, 19);
		lblRuolo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblRuolo.setForeground(Color.blue);
		lblRuolo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRuolo.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliStaff.add(lblRuolo);
		
		JLabel lblTelefono = new JLabel("Telefono: " + dsb.getStaff().getnTelefono());
		lblTelefono.setBounds(10, 91, 660, 19);
		lblTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblTelefono.setForeground(Color.blue);
		lblTelefono.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		dettagliStaff.add(lblTelefono);
		
		JLabel lbleMail = new JLabel("eMail: " + dsb.getStaff().geteMail());
		lbleMail.setHorizontalTextPosition(SwingConstants.CENTER);
		lbleMail.setForeground(Color.blue);
		lbleMail.setHorizontalAlignment(SwingConstants.CENTER);
		lbleMail.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lbleMail.setBounds(10, 121, 660, 19);
		dettagliStaff.add(lbleMail);

		
		JButton btnAssumi = new JButton("ASSUMI");
		btnAssumi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int risposta = JOptionPane.showConfirmDialog(null, "Sei sicuro di "
						+ "voler assumere l'Utente selezionato?", 
						"Conferma assunzione", JOptionPane.YES_NO_OPTION);
				if(risposta == JOptionPane.YES_OPTION) {
					dsb.assumiUtente();
					new ListaStaff(ur);
					dispose();
				}
				else
					return;
					
			}
		});
		
		btnAssumi.setBounds(332, 383, 89, 23);
		contentPane.add(btnAssumi);
		

		JButton btnRimuovi = new JButton("RIMUOVI");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int risposta = JOptionPane.showConfirmDialog(null, "Sei sicuro di "
						+ "voler rimuovere l'Utente selezionato?", 
						"Conferma rimozione", JOptionPane.YES_NO_OPTION);
				if(risposta == JOptionPane.YES_OPTION) {
					dsb.rimuoviUtente();
					new ListaStaff(ur);
					dispose();
				}
				else
					return;
					
			}
		});
		
		btnRimuovi.setBounds(332, 383, 89, 23);
		contentPane.add(btnRimuovi);

		
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
		
		JButton btnStaff = new JButton("Lista Staff");
		btnStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ListaStaff(ur);
				dispose();
			}
		});
		menu.add(btnStaff);
		
		
		if(dsb.getStaff().isAttivo()) {
			btnRimuovi.setVisible(true);
			btnAssumi.setVisible(false);
		}
		else {
			btnAssumi.setVisible(true);	
			btnRimuovi.setVisible(true);
		}
		
		JLabel sfondo = new JLabel(new ImageIcon(this.getClass().getResource("/jSwing/image/sfondo2.jpg")));
		sfondo.setBounds(0, 0, 808, 453);
		contentPane.add(sfondo);
		sfondo.setOpaque(true);
		sfondo.setBackground(SystemColor.inactiveCaptionBorder);
		
	}
}
