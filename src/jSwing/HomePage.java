package jSwing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import attori.UtenteRegistrato;
import bean.UtenteRegistratoHomeBean;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomePage(UtenteRegistrato ur) {
		
		UtenteRegistratoHomeBean urhb = new UtenteRegistratoHomeBean();
		urhb.loadLocatarioHome(ur.getID(), ur.getNome(), ur.getRuolo());
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel DatiUtente = new JPanel();
		DatiUtente.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		DatiUtente.setOpaque(false);
		DatiUtente.setBounds(118, 71, 661, 328);
		contentPane.add(DatiUtente);
		DatiUtente.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(227, 1, 226, 19);
		lblNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNome.setForeground(Color.red);
		lblNome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		DatiUtente.add(lblNome);
		
		JLabel lblGetNome = new JLabel(ur.getNome());
		lblGetNome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGetNome.setForeground(Color.blue);
		lblGetNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblGetNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGetNome.setBounds(227, 31, 236, 19);
		DatiUtente.add(lblGetNome);
		
		JLabel lblCognome = new JLabel("COGNOME");
		lblCognome.setBounds(227, 78, 226, 19);
		lblCognome.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCognome.setForeground(Color.red);
		lblCognome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
		DatiUtente.add(lblCognome);
		
		JLabel lblGetCognome = new JLabel(ur.getCognome());
		lblGetCognome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGetCognome.setForeground(Color.blue);
		lblGetCognome.setHorizontalAlignment(SwingConstants.CENTER);
		lblGetCognome.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGetCognome.setBounds(227, 108, 226, 19);
		DatiUtente.add(lblGetCognome);
		
		JLabel lblRuolo = new JLabel("RUOLO");
		lblRuolo.setBounds(227, 239, 226, 19);
		lblRuolo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblRuolo.setForeground(Color.red);
		lblRuolo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRuolo.setHorizontalAlignment(SwingConstants.CENTER);
		DatiUtente.add(lblRuolo);
		
		JLabel lblCodiceFiscale = new JLabel("CODICE FISCALE");
		lblCodiceFiscale.setBounds(227, 159, 226, 19);
		lblCodiceFiscale.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblCodiceFiscale.setForeground(Color.red);
		lblCodiceFiscale.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCodiceFiscale.setHorizontalAlignment(SwingConstants.CENTER);
		DatiUtente.add(lblCodiceFiscale);
		
		JLabel lblGetCF = new JLabel(ur.getCodiceFiscale());
		lblGetCF.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGetCF.setForeground(Color.blue);
		lblGetCF.setHorizontalAlignment(SwingConstants.CENTER);
		lblGetCF.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGetCF.setBounds(227, 189, 226, 19);
		DatiUtente.add(lblGetCF);
		
		JLabel lblGetRuolo = new JLabel(ur.getRuolo());
		lblGetRuolo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGetRuolo.setForeground(Color.blue);
		lblGetRuolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGetRuolo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblGetRuolo.setBounds(227, 269, 226, 19);
		DatiUtente.add(lblGetRuolo);
		
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
		
		JLabel lblBentornato = new JLabel("Bentornato " + ur.getNome());
		lblBentornato.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblBentornato.setForeground(Color.ORANGE);
		lblBentornato.setHorizontalAlignment(SwingConstants.CENTER);
		lblBentornato.setBounds(118, 8, 680, 52);
		contentPane.add(lblBentornato);
		
		JButton btnStaff = new JButton("Lista Staff");
		btnStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ListaStaff(ur);
				dispose();
			}
		});
		menu.add(btnStaff);
		
		if(ur.getRuolo().equals("Locatario")) {
			btnStaff.setVisible(false);
		}
		else if(ur.getRuolo().equals("Amministratore")) {
			btnPrenotazioni.setVisible(false);
		}
		else {
			btnStaff.setVisible(false);
			btnPrenotazioni.setVisible(false);
		}
		
		JLabel lblNewLabel = new JLabel("Universit\u00E0 degli Studi di Roma Tor Vergata - Ingegneria del Software e Progettazione Web - Lorenzo Paris, Giovanni D'Agostino");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 428, 788, 14);
		contentPane.add(lblNewLabel);
		
		JLabel sfondo = new JLabel(new ImageIcon(this.getClass().getResource("/jSwing/image/sfondo2.jpg")));
		sfondo.setBounds(0, 0, 808, 453);
		contentPane.add(sfondo);
		sfondo.setOpaque(true);
		sfondo.setBackground(SystemColor.inactiveCaptionBorder);
		
	}
}
