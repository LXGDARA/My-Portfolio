import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ActionListenerExample3 extends JFrame {
	
	public ActionListenerExample3() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("Click Me!");
		
		MySimpleButtonListener listener = new MySimpleButtonListener();
		btn.addActionListener(listener);
		
		this.add(btn);
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ActionListenerExample3();
			}
		});
	}
}