import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlowLayoutFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("FlowLayout Frame");
		
		// some JFrame methods that need the content pane instance
		// are simplified and forwarded without having to call getContentPane
		frame.setLayout(new FlowLayout());
		
		frame.add(makePanel(Color.red));
		frame.add(makePanel(Color.orange));
		frame.add(makePanel(Color.green));
		frame.add(makePanel(Color.blue));
		frame.add(makePanel(new Color(75, 0, 130)));
		frame.add(makePanel(new Color(138, 43, 226)));
		frame.setSize(new Dimension(300, 200));
		frame.setVisible(true);
	}

	private static JPanel makePanel(Color color) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(100, 100));
		return panel;
	}
}