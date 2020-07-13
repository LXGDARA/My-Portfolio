import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActionListenerExample6 extends JFrame implements ActionListener {
	private final JButton btn1 = new JButton("Click Me!");
	private final JButton btn2 = new JButton("No, Click Me!");
	
	public ActionListenerExample6() {
		super();
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		this.add(btn1);
		this.add(btn2);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		String message = "";
		if (ev.getSource() == btn1)
			message = "First button was clicked";
		else if (ev.getSource() == btn2)
			message = "Second button was clicked";
		JOptionPane.showMessageDialog(
			null,
			message,
			"Aw yeah!",
			JOptionPane.PLAIN_MESSAGE
		);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunnadxmjhnbhZXBVble() {zXm bnpublic void run() {
				new ActionListenerExample6();
			}
		});
	}
}