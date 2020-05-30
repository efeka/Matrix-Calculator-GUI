import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SetMatrixWindow {
	
	private JFrame frame;

	private JLabel rowsLabel, colsLabel;
	private Choice rowChoice, colChoice;
	private JButton setSizeButton, saveMatrixButton;
	private JSeparator seperateTop;
	private JTextField[][] textArray;

	public SetMatrixWindow(int width, int height, String name) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.setTitle("Set matrix " + name);
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.controlHighlight);
		frame.setBounds(0, 0, width, height);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		rowsLabel = new JLabel("Rows:");
		rowsLabel.setVisible(true);
		rowsLabel.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
		rowsLabel.setBounds(width/2 - 120, 10, 50, 24);
		frame.add(rowsLabel);
		
		rowChoice = new Choice();
		rowChoice.setFont(new Font("Myriad Web Pro", Font.PLAIN, 12));
		rowChoice.setBounds(width/2 - 72, 13, 50, 24);
		for (int i = 2; i < 21; i++) 
			rowChoice.add(i + "");
		frame.add(rowChoice);
		
		colsLabel = new JLabel("Columns:");
		colsLabel.setVisible(true);
		colsLabel.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
		colsLabel.setBounds(width/2 - 10, 10, 70, 24);
		frame.add(colsLabel);
		
		colChoice = new Choice();
		colChoice.setFont(new Font("Myriad Web Pro", Font.PLAIN, 12));
		colChoice.setBounds(width/2 + 65, 13, 50, 24);
		for (int i = 2; i < 21; i++) 
			colChoice.add(i + "");
		frame.add(colChoice);
		
		seperateTop = new JSeparator();
		seperateTop.setVisible(true);
		seperateTop.setBounds(5, 75, width - 23, 2);
		frame.add(seperateTop);
		
		saveMatrixButton = new JButton("Save matrix");
		saveMatrixButton.setBounds(width/2, 45, 115, 20);
		saveMatrixButton.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
		saveMatrixButton.setVisible(true);
		saveMatrixButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (name.equals("A")) {
					if (setA(textArray))
						JOptionPane.showMessageDialog(null, "Successfully saved Matrix " + name, "Information", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "ERROR! Could not save Matrix " + name, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if (name.equals("B")) {
					if (setB(textArray))
						JOptionPane.showMessageDialog(null, "Successfully saved Matrix " + name, "Information", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "ERROR! Could not save Matrix " + name, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.add(saveMatrixButton);

		setSizeButton = new JButton("Set size");
		setSizeButton.setBounds(width/2 - 120, 45, 115, 20);
		setSizeButton.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
		setSizeButton.setVisible(true);
		setSizeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				frame.dispose();
				int rows = Integer.parseInt(rowChoice.getSelectedItem());
				int cols = Integer.parseInt(colChoice.getSelectedItem());
				frame = new JFrame();
				frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
				frame.setTitle("Set matrix " + name);
				frame.setResizable(false);
				frame.getContentPane().setBackground(SystemColor.controlHighlight);
				
				int width = 5 + 62*(cols - 1) + 65 + 14;
				int height = 120 + 32*rows;
				if (width < 350)
					width = 350;
				
				frame.setBounds(0, 0, width, height);
				frame.setLocationRelativeTo(null);
				frame.getContentPane().setLayout(null);
				frame.setVisible(true);
				
				saveMatrixButton.setBounds(width/2, 45, 115, 20);
				setSizeButton.setBounds(width/2 - 120, 45, 115, 20);
				seperateTop.setBounds(5, 75, width - 23, 2);
				colsLabel.setBounds(width/2 - 10, 10, 70, 24);
				rowsLabel.setBounds(width/2 - 120, 10, 50, 24);
				colChoice.setBounds(width/2 + 65, 13, 50, 24);
				rowChoice.setBounds(width/2 - 72, 13, 50, 24);
				frame.add(saveMatrixButton);
				frame.add(setSizeButton);
				frame.add(seperateTop);
				frame.add(colsLabel);
				frame.add(rowsLabel);
				frame.add(colChoice);
				frame.add(rowChoice);

				setTextAreas(rows, cols);
			}
		});
		frame.add(setSizeButton);
		
		textArray = new JTextField[2][2];
		for (int i = 0; i < textArray.length; i++) {
			for (int j = 0; j < textArray[i].length; j++) {
				textArray[i][j] = new JTextField();
				textArray[i][j].setVisible(true);
				textArray[i][j].setEditable(true);
				textArray[i][j].setBackground(Color.WHITE);
				textArray[i][j].setFont(new Font("Myriad Web Pro", Font.PLAIN, 15));
				textArray[i][j].setHorizontalAlignment(SwingConstants.LEFT);
				textArray[i][j].setBounds(112 + 62*j, 80 + 32*i, 60, 30);

				frame.add(textArray[i][j]);
			}
		}
	}
	
	private void setTextAreas(int rows, int cols) {
		
		textArray = new JTextField[rows][cols];
		for (int i = 0; i < textArray.length; i++) {
			for (int j = 0; j < textArray[i].length; j++) {
				textArray[i][j] = new JTextField();
				textArray[i][j].setVisible(true);
				textArray[i][j].setEditable(true);
				textArray[i][j].setBackground(Color.WHITE);
				textArray[i][j].setFont(new Font("Myriad Web Pro", Font.PLAIN, 15));
				textArray[i][j].setHorizontalAlignment(SwingConstants.LEFT);
				
				if (cols == 2) 
					textArray[i][j].setBounds(112 + 62*j, 80 + 32*i, 60, 30);
				else if (cols == 3)
					textArray[i][j].setBounds(110 - 30 + 62*j, 80 + 32*i, 60, 30);
				else if (cols == 4)
					textArray[i][j].setBounds(110 - 60 + 62*j, 80 + 32*i, 60, 30);
				else if (cols == 5)
					textArray[i][j].setBounds(15 + 62*j, 80 + 32*i, 60, 30);
				else
					textArray[i][j].setBounds(5 + 62*j, 80 + 32*i, 60, 30);
				
				frame.add(textArray[i][j]);
			}
		}
	}
	
	private boolean setA(JTextField[][] text) {
		double[][] tempA = new double[text.length][text[0].length];
		for (int i = 0; i < text.length; i++) {
			for (int j = 0; j < text[i].length; j++) {
				try {
					tempA[i][j] = Double.parseDouble(text[i][j].getText());
				} catch (Exception e) {
					return false;
				}
			}
		}
		MatrixA.setA(tempA);
		return true;
	}
	
	private boolean setB(JTextField[][] text) {
		double[][] tempB = new double[text.length][text[0].length];
		for (int i = 0; i < text.length; i++) {
			for (int j = 0; j < text[i].length; j++) {
				try {
					tempB[i][j] = Double.parseDouble(text[i][j].getText());
				} catch (Exception e) {
					return false;
				}
			}
		}
		MatrixB.setB(tempB);
		return true;
	}
}
