import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridLayoutFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GridLayout frame");

		// 7 8 9 0
		// 4 5 6 C
		// 1 2 3 =
		// + - * /
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4));
		
		addButtons(
			buttonPanel,
			"7", "8", "9", "0",
			"4", "5", "6", "C",
			"1", "2", "3", "=",
			"+", "-", "*", "/"
		);
		
		JTextField resultBox = new JTextField("*** BATTERY EMPTY ***");
		resultBox.setEditable(false);
		
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(resultBox, BorderLayout.NORTH);
		
		frame.pack();
		frame.setVisible(true);
	}

	private static void addButtons(Container contentPane, String... strings) {
		for (String label : strings) {
			contentPane.add(new JButton(label));
		}
	}
}