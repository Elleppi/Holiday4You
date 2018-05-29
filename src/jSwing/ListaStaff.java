package jSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import attori.UtenteRegistrato;
import bean.ListaPrenotazioniBean;
import bean.ListaStaffBean;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ListaStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ListaStaffBean lsb;

	public ListaStaff(UtenteRegistrato ur) {
		
		this.setVisible(true);
		lsb = new ListaStaffBean();
		
		lsb.setListaStaff();
		
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
		
		JPanel ListaStaff = new JPanel();
		ListaStaff.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ListaStaff.setBounds(118, 71, 680, 328);
		contentPane.add(ListaStaff);
		ListaStaff.setLayout(null);
		ListaStaff.setOpaque(false);
		
		int size = lsb.getListaStaff().size();
		String[] stringa = new String[size];
		
		for(int i=0; i<size; ++i) {
			stringa[i] = lsb.getStringaStaff(i);
		}
		
		AbstractListModel<String> almFuture = new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			public int getSize() {
				return stringa.length;
			}
			public String getElementAt(int index) {
				return stringa[index];
			}
		};
		
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(almFuture);
		list.setBounds(0, 0, 680, 284);
		list.setOpaque(false);
		ListaStaff.add(list);
		
		JButton btnVediDettagli = new JButton("Vedi Dettagli");
		btnVediDettagli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = list.getSelectedIndex();
				new DettagliStaff(lsb.getListaStaff().get(index), ur);
				dispose();
				
			}
		});
		btnVediDettagli.setBounds(260, 295, 172, 23);
		ListaStaff.add(btnVediDettagli);
		
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
		
		JLabel sfondo = new JLabel(new ImageIcon(this.getClass().getResource("/jSwing/image/sfondo2.jpg")));
		sfondo.setBounds(0, 0, 808, 453);
		contentPane.add(sfondo);
		sfondo.setOpaque(true);
		sfondo.setBackground(SystemColor.inactiveCaptionBorder);
	}
}
