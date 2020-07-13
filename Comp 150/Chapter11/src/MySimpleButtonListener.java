import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MySimpleButtonListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(
			null,
			"You clicked me, nice!",
			"Aw yeah!",
			JOptionPane.PLAIN_MESSAGE
		);
	}
	
}