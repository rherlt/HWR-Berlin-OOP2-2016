import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HelloTextfile {

	private JFrame frame;
	private JTextField txtDollar;
	private JTextField txtEuro;
	private final String filename = "ExchangeRate.txt";
	
	private double exchangeRate = 0.87;
	private JTextField txtExchangerate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloTextfile window = new HelloTextfile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelloTextfile() {
		initialize();
		exchangeRate = loadFromFile();
		txtExchangerate.setText(String.format("%.4f", exchangeRate));
		txtDollar.setText("0");
		txtEuro.setText("0");
	}

	private double loadFromFile() {
		
		double result = 0;
		BufferedReader br = null;
		
		try {

			String sCurrentLine;
			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) {
				result = Double.parseDouble(sCurrentLine);
			}

		} catch (FileNotFoundException e) {
			result = 0;
		} catch (NumberFormatException e) {
			result = 0;
		} catch (IOException e) {
			result = 0;
		} 
		finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}

	private void ToDollar(String euroString)
	{
		if (euroString.length() > 0)
		{
			double euro = Double.valueOf(euroString);
			double dollar = euro*exchangeRate;
			txtDollar.setText(String.format("%.2f", dollar));
		}
		else
		{
			txtDollar.setText("");
		}
	}
	
	private void ToEuro(String dollarString)
	{
		if (dollarString.length() > 0)
		{
			double dollar = Double.valueOf(dollarString);
			double euro = dollar/exchangeRate;
			txtEuro.setText(String.format("%.2f", euro));
		}
		else
		{
			txtEuro.setText("");
		}
	}	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblEuro = new JLabel("Euro");
		frame.getContentPane().add(lblEuro);
		
		JLabel lblKurs = new JLabel("Exchange Rate");
		springLayout.putConstraint(SpringLayout.NORTH, lblKurs, 64, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblKurs, 0, SpringLayout.WEST, lblEuro);
		springLayout.putConstraint(SpringLayout.SOUTH, lblKurs, -18, SpringLayout.NORTH, lblEuro);
		frame.getContentPane().add(lblKurs);
		
		JLabel lblDollar = new JLabel("Dollar");
		springLayout.putConstraint(SpringLayout.SOUTH, lblEuro, -18, SpringLayout.NORTH, lblDollar);
		springLayout.putConstraint(SpringLayout.NORTH, lblDollar, 132, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDollar, 26, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEuro, 0, SpringLayout.WEST, lblDollar);
		frame.getContentPane().add(lblDollar);
		
		txtDollar = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtDollar, -6, SpringLayout.NORTH, lblDollar);
		txtDollar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String dollarString = txtDollar.getText();
				ToEuro(dollarString);
			}
		});
		frame.getContentPane().add(txtDollar);
		txtDollar.setColumns(10);
		
		txtEuro = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtDollar, 0, SpringLayout.WEST, txtEuro);
		springLayout.putConstraint(SpringLayout.NORTH, txtEuro, -6, SpringLayout.NORTH, lblEuro);
		springLayout.putConstraint(SpringLayout.WEST, txtEuro, 85, SpringLayout.EAST, lblEuro);
		txtEuro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String euroString = txtEuro.getText();
				ToDollar(euroString);
			}
		});
		frame.getContentPane().add(txtEuro);
		txtEuro.setColumns(10);
		
		txtExchangerate = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtExchangerate, -6, SpringLayout.NORTH, lblKurs);
		springLayout.putConstraint(SpringLayout.WEST, txtExchangerate, 0, SpringLayout.WEST, txtEuro);
		txtExchangerate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				exchangeRate = Double.valueOf(txtExchangerate.getText());
				
				txtDollar.setText("0");
				txtEuro.setText("0");
				
				SaveExchangerateToFile(exchangeRate);
			}

			private void SaveExchangerateToFile(double exchangeRate) {
				try {

					
					File file = new File(filename);

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(String.valueOf(exchangeRate));
					bw.close();

				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		});
		frame.getContentPane().add(txtExchangerate);
		txtExchangerate.setColumns(10);
	}
}
