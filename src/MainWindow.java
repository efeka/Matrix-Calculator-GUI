import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frame;
	private int width = 350, height = 411;
	
	private static JTextField calculationScreen;
	private JButton setAButton;
	private JButton setBButton;
	private JButton addButton, substractButton, pointButton, multiplyButton, equalsButton;
	private JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonA, buttonB, buttonInverse, buttonClear, buttonDelete;

	private OperationHandler handler = new OperationHandler();
	
	public static boolean didSetA = false, didSetB = false;
	public static int windowAWidth = 350, windowAHeight = 184, windowBWidth = 350, windowBHeight = 184;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainWindow() {
		initialize();
	}
	
	//actual bounds 5, width - 22 (328)
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.setTitle("Matrix Operations");
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.controlHighlight);
		frame.setBounds(100, 100, width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		calculationScreen = new JTextField();
		calculationScreen.setVisible(true);
		calculationScreen.setEditable(false);
		calculationScreen.setBackground(Color.WHITE);
		calculationScreen.setFont(new Font("Myriad Web Pro", Font.PLAIN, 25));
		calculationScreen.setHorizontalAlignment(SwingConstants.RIGHT);
		calculationScreen.setBounds(5, 5, width - 24, 50);
		frame.add(calculationScreen);

		setAButton = new JButton("Set Matrix A ");
		setAButton.setBounds(5, 60, 120, 50);
		setAButton.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
		setAButton.setVisible(true);
		setAButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				new SetMatrixWindow(windowAWidth, windowAHeight, "A");
			}
		});
		frame.add(setAButton);
		
		setBButton = new JButton("Set Matrix B");
		setBButton.setBounds(5 + 120 + 2, 60, 120, 50);
		setBButton.setFont(new Font("Myriad Web Pro", Font.BOLD, 14));
		setBButton.setVisible(true);
		setBButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new SetMatrixWindow(windowBWidth, windowBHeight, "B");
			}
		});
		frame.add(setBButton);
		
		equalsButton = new JButton(" =");
		equalsButton.setBounds(5 + 240 + 4, 60, 83, 50);
		equalsButton.setVisible(true);
		equalsButton.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		equalsButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					handler.evaluate();
				} catch(NullPointerException ex) {
					calculationScreen.setText("ERROR! Could not evaluate");
				}
			}
		});
		frame.add(equalsButton);
		
		addButton = new JButton(" +");
		addButton.setBounds(5 + 240 + 4, 60 + 52, 83, 50);
		addButton.setVisible(true);
		addButton.setFont(new Font("Myriad Web Pro", Font.PLAIN, 25));
		addButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("+");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(addButton);
		
		substractButton = new JButton(" -");
		substractButton.setFont(new Font("Myriad Web Pro", Font.PLAIN, 30));
		substractButton.setBounds(5 + 240 + 4, 60 + 52*2, 83, 50);
		substractButton.setVisible(true);
		substractButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("-");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(substractButton);
		
		multiplyButton = new JButton(" x");
		multiplyButton.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		multiplyButton.setBounds(5 + 240 + 4, 60 + 52*3, 83, 50);
		multiplyButton.setVisible(true);
		multiplyButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("x");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(multiplyButton);
		
		pointButton = new JButton(" .");
		pointButton.setFont(new Font("Myriad Web Pro", Font.BOLD, 20));
		pointButton.setBounds(5 + 240 + 4, 60 + 52*4, 83, 50);
		pointButton.setVisible(true);
		pointButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate(".");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(pointButton);
		
		button1 = new JButton("1");
		button1.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button1.setBounds(5, 60 + 52, 79, 50);
		button1.setVisible(true);
		button1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("1");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button1);
		
		button2 = new JButton("2");
		button2.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button2.setBounds(5 + 81, 60 + 52, 79, 50);
		button2.setVisible(true);
		button2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("2");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button2);
		
		button3 = new JButton("3");
		button3.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button3.setBounds(5 + 81*2, 60 + 52, 80, 50);
		button3.setVisible(true);
		button3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("3");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button3);
		
		button4 = new JButton("4");
		button4.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button4.setBounds(5, 60 + 52*2, 79, 50);
		button4.setVisible(true);
		button4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("4");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button4);
		
		button5 = new JButton("5");
		button5.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button5.setBounds(5 + 81, 60 + 52*2, 79, 50);
		button5.setVisible(true);
		button5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("5");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button5);
		
		button6 = new JButton("6");
		button6.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button6.setBounds(5 + 81*2, 60 + 52*2, 80, 50);
		button6.setVisible(true);
		button6.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("6");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button6);
		
		button7 = new JButton("7");
		button7.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button7.setBounds(5, 60 + 52*3, 79, 50);
		button7.setVisible(true);
		button7.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("7");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button7);
		
		button8 = new JButton("8");
		button8.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button8.setBounds(5 + 81, 60 + 52*3, 79, 50);
		button8.setVisible(true);
		button8.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("8");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button8);
		
		button9 = new JButton("9");
		button9.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button9.setBounds(5 + 81*2, 60 + 52*3, 80, 50);
		button9.setVisible(true);
		button9.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("9");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button9);
		
		button0 = new JButton("0");
		button0.setFont(new Font("Myriad Web Pro", Font.PLAIN, 20));
		button0.setBounds(5 + 81, 60 + 52*4, 79, 50);
		button0.setVisible(true);
		button0.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("0");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(button0);
		
		buttonA = new JButton("A");
		buttonA.setFont(new Font("Myriad Web Pro", Font.PLAIN, 19));
		buttonA.setBounds(5, 60 + 52*4, 79, 50);
		buttonA.setVisible(true);
		buttonA.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("A");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(buttonA);
		
		buttonB = new JButton("B");
		buttonB.setFont(new Font("Myriad Web Pro", Font.PLAIN, 19));
		buttonB.setBounds(5 + 81*2, 60 + 52*4, 80, 50);
		buttonB.setVisible(true);
		buttonB.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("B");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(buttonB);
		
		buttonClear = new JButton("Clear");
		buttonClear.setBounds(5, 60 + 52*5, 120, 50);
		buttonClear.setFont(new Font("Myriad Web Pro", Font.PLAIN, 17));
		buttonClear.setVisible(true);
		buttonClear.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("clear");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(buttonClear);
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(5 + 120 + 2, 60 + 52*5, 120, 50);
		buttonDelete.setFont(new Font("Myriad Web Pro", Font.PLAIN, 17));
		buttonDelete.setVisible(true);
		buttonDelete.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("delete");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(buttonDelete);
		
		buttonInverse = new JButton("inv");
		buttonInverse.setFont(new Font("Myriad Web Pro", Font.PLAIN, 17));
		buttonInverse.setBounds(5 + 240 + 4, 60 + 52*5, 83, 50);
		buttonInverse.setVisible(true);
		buttonInverse.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handler.operate("'");
				calculationScreen.setText(handler.getOperation());
			}
		});
		frame.add(buttonInverse);	
	}
	
	public static void setCalculatorText(String text) {
		calculationScreen.setText(text);
	}

}