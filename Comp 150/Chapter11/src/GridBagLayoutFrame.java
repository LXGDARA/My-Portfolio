import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GridBagLayoutFrame {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GridBagLayout frame");
		frame.setLayout(new GridBagLayout());
		
		// 7 8 9 0
		// 4 5 6 C
		// 1 2 3 =
		// + - * /
		String[][] labels = {
			{"7", "8", "9", "0"},
			{"4", "5", "6", "C"},
			{"1", "2", "3", "="},
			{"+", "-", "*", "/"}
		};
		
		Map<String, JButton> buttons = makeButtons(
			"7", "8", "9", "0",
			"4", "5", "6", "C",
			"1", "2", "3", "=",
			"+", "-", "*", "/"
		);
		
		JTextField resultBox = new JTextField("*** BATTERY EMPTY ***");
		resultBox.setEditable(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		for (int j = 1; j < labels.length; j++) {
			gbc.gridy = j;
			for (int i = 0; i < labels[0].length-1; i++) {
				gbc.gridx = i;
				frame.add(buttons.get(labels[j-1][i]), gbc);
			}
		}
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10, 4, 10, 4);
		frame.add(resultBox, gbc);
		
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(10, 0, 0, 0);
		for (int i = 0; i < labels[0].length; i++) {
			gbc.gridx = i;
			frame.add(buttons.get(labels[3][i]), gbc);
		}
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(0, 0, 0, 0);
		frame.add(buttons.get(labels[0][3]), gbc);
		gbc.insets = new Insets(0, 10, 0, 0);
		gbc.gridy = 2;
		frame.add(buttons.get(labels[1][3]), gbc);
		gbc.gridy = 3;
		frame.add(buttons.get(labels[2][3]), gbc);
		
		frame.pack();
		frame.setVisible(true);
	}

	private static Map<String, JButton> makeButtons(String... strings) {
		Map<String, JButton> buttons = new HashMap<>();
		for (String label : strings) {
			buttons.put(label, new JButton(label));
		}
		return buttons;
	}
}