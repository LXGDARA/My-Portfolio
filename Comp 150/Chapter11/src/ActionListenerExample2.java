import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActionListenerExample2 extends JFrame implements ActionListener {
	
	public ActionListenerExample2() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("Click Me!");
		
		// The JFrame is now also the listener object
		btn.addActionListener(this);
		
		this.add(btn);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(
			null,
			"You clicked me, nice!",
			"Aw yeah!",
			JOptionPane.PLAIN_MESSAGE
		);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ActionListenerExample2();
			}
		});
	}
}