package jSwing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import attori.UtenteRegistrato;
import bean.LoginBean;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JPasswordField password;
	private UtenteRegistrato ur;

	public Login() {
		this.setVisible(true);
		setTitle("Holiday4You");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 492);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 345, 231, 97);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(9, 11, 71, 18);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblUsername.setForeground(new Color(255, 0, 0));

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(9, 41, 70, 18);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPassword.setForeground(new Color(255, 0, 0));

		username = new JTextField();
		username.setBounds(91, 11, 126, 20);
		panel.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(89, 41, 128, 20);
		panel.add(password);

		JLabel lblDatiErrati = new JLabel("Dati Errati !!!");
		lblDatiErrati.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblDatiErrati.setForeground(Color.RED);
		lblDatiErrati.setBounds(91, 71, 126, 14);
		lblDatiErrati.setVisible(false);
		panel.add(lblDatiErrati);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoginBean lb = new LoginBean();
				lb.setUsername(username.getText());
				lb.setPassword(password.getText());

				if (lb.validate()) {
					lblDatiErrati.setVisible(false);
					ur = lb.getUr();
					new HomePage(ur);
					dispose();
				} else {
					lblDatiErrati.setVisible(true);
					username.setText("");
					password.setText("");
				}
			}
		});

		btnLogin.setBounds(9, 70, 71, 20);
		panel.add(btnLogin);

		JLabel sfondo = new JLabel(new ImageIcon(this.getClass().getResource("/jSwing/image/casa4.jpg")));
		sfondo.setOpaque(true);
		sfondo.setBackground(SystemColor.inactiveCaptionBorder);
		sfondo.setBounds(0, 0, 808, 453);
		contentPane.add(sfondo);

	}
}
