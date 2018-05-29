package avvio;

import java.awt.EventQueue;

import jSwing.Login;

public class AvvioStandAlone {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
