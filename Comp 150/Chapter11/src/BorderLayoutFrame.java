import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("BorderLayout Frame");

		frame.setLayout(new BorderLayout());

		frame.add(
			new JButton("NORTH"),
			BorderLayout.NORTH
		);
		frame.add(
			new JButton("WEST"),
			BorderLayout.WEST
		);
		JButton east = new JButton("EAST");
		east.setPreferredSize(new Dimension(200, 50));
		frame.add(
			east,
			BorderLayout.EAST
		);
		frame.add(
			new JButton("SOUTH"),
			BorderLayout.SOUTH
		);
		frame.add(
			new JButton("CENTER"),
			BorderLayout.CENTER
		);
		frame.pack();
		frame.setVisible(true);
	}
}