import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActionListenerExample5 extends JFrame implements ActionListener {
	
	public ActionListenerExample5() {
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
	public void actionPerformed(ActionEvent ev) {
		String message;
		if (((JButton)ev.getSource()).getText().equals("Click Me!"))
			message = "First button was clicked";
		else
			message = "Second button was clicked";
		JOptionPane.showMessageDialog(
			null,
			message,
			"Aw yeah!",
			JOptionPane.PLAIN_MESSAGE
		);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ActionListenerExample5();
			}
		});
	}
}