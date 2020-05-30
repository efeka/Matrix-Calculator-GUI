import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ResultWindow {

	private JFrame frame;
	private int width, height;

	private JTextField[][] textArray;

	public ResultWindow(double[][] matrix) {
		if (matrix == null) {
			MainWindow.setCalculatorText("ERROR! Could not evaluate");
			return;
		}

		width = 5 + 62*(matrix[0].length - 1) + 65 + 14;
		height = 13 + 32*(matrix.length + 1);

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.setTitle("Result");
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.controlHighlight);
		frame.setBounds(0, 0, width, height);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		textArray = new JTextField[matrix.length][matrix[0].length];
		for (int i = 0; i < textArray.length; i++) {
			for (int j = 0; j < textArray[i].length; j++) {
				textArray[i][j] = new JTextField();
				textArray[i][j].setVisible(true);
				textArray[i][j].setEditable(true);
				textArray[i][j].setText(matrix[i][j] + "");
				textArray[i][j].setBackground(Color.WHITE);
				textArray[i][j].setFont(new Font("Myriad Web Pro", Font.PLAIN, 15));
				textArray[i][j].setHorizontalAlignment(SwingConstants.RIGHT);
				
				if (matrix[0].length == 2)
					textArray[i][j].setBounds(15 + 62*j, 5 + 32*i, 60, 30);
				else
					textArray[i][j].setBounds(5 + 62*j, 5 + 32*i, 60, 30);

				frame.add(textArray[i][j]);
			}
		}
	}
}
