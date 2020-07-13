import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActionListenerExample4 extends JFrame implements ActionListener {
	
	public ActionListenerExample4() {
		super();
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn1 = new JButton("Click Me!");
		JButton btn2 = new JButton("No, Click Me!");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		this.add(btn1);
		this.add(btn2);
		
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
				new ActionListenerExample4();
			}
		});
	}
}