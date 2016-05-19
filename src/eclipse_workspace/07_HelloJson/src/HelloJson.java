import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Json.JsonProcessor;
import Json.JsonSimpleProcessor;
import Json.ExchangeRate;
import Json.GoolgeGsonProcessor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Locale;

public class HelloJson {

	private JFrame frame;
	private JTextField txtDollar;
	private JTextField txtEuro;
	private final String filename = "ExchangeRate.json";

	private JTextField txtExchangerate;
	private ExchangeRate exchangeRate;
	//private JsonProcessing jsonProcessor = new OrgJson();
	private JsonProcessor jsonProcessor = new GoolgeGsonProcessor();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloJson window = new HelloJson();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	}

	/**
	 * Create the application.
	 */
	public HelloJson() {
		initialize();
				
		exchangeRate = jsonProcessor.loadExchangeRateFromJsonFile(filename);
		
		txtExchangerate.setText(String.format(Locale.ENGLISH, "%.4f", exchangeRate.getRates().getUsd()));
		txtDollar.setText("0");
		txtEuro.setText("0");
	}

	
	private double parseJson(FileReader reader) {
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject root = (JSONObject)parser.parse(reader);
			JSONObject rates = (JSONObject)root.get("rates");
			double rate = (double) rates.get("USD");
			
			return rate;
		} catch (Exception e) {
			return 0;
		}
	}

	private void ToDollar(String euroString) {
		if (euroString.length() > 0) {
			double euro = Double.valueOf(euroString);
			double dollar = euro * exchangeRate.getRates().getUsd();
			txtDollar.setText(String.format("%.2f", dollar));
		} else {
			txtDollar.setText("");
		}
	}

	private void ToEuro(String dollarString) {
		if (dollarString.length() > 0) {
			double dollar = Double.valueOf(dollarString);
			double euro = dollar / exchangeRate.getRates().getUsd();
			txtEuro.setText(String.format("%.2f", euro));
		} else {
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
				exchangeRate.getRates().setUsd(Double.valueOf(txtExchangerate.getText()));

				txtDollar.setText("0");
				txtEuro.setText("0");
				
				jsonProcessor.saveExchangeRateToJsonFile(filename, exchangeRate);
			}
			
		});
		frame.getContentPane().add(txtExchangerate);
		txtExchangerate.setColumns(10);
	}
}
