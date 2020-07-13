import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MyFirstFrame {
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ignore) {}
		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Mf First Frame");

		JPanel bluePanel = new JPanel();
		JPanel redPanel = new JPanel();

		JLabel label = new JLabel("<-- pick your side -->");

		frame.getContentPane().add(label, BorderLayout.CENTER);

		bluePanel.setBackground(Color.blue);
		redPanel.setBackground(Color.red);

		frame.getContentPane().add(bluePanel, BorderLayout.LINE_START);
		frame.getContentPane().add(redPanel, BorderLayout.LINE_END);

		JButton blueButton = new JButton("PICK BLUE TEAM");
		JButton redButton = new JButton("PICK RED TEAM");

		bluePanel.add(blueButton);
		redPanel.add(redButton);

		frame.pack();
		frame.setVisible(true);
	}
}