import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Olga Mrost
 *
 */
public class CurExchangeFrame extends JFrame {
	
	private static final long serialVersionUID = -1924458747511930425L;
	private JLabel amount;
	private JTextField sumToConvert;
	private JLabel warning;
	private JLabel resultLabel;
	private JLabel resultAmount;
	private JLabel exchangeRate;
	private JButton btnEuroToDollar;
	private JButton btnDollarToEuro;
	
	final double exchRate = 1.13;
	
	public CurExchangeFrame(String title){
		super(title);
		
		setLayout(new GridBagLayout());
		
		amount = new JLabel("Amount to convert:");
		sumToConvert = new JTextField(5);
		warning = new JLabel();
		resultLabel = new JLabel("Result: " );
		resultAmount = new JLabel();
		exchangeRate = new JLabel("Exchange rate: 1 € is " + exchRate + " $");
		btnEuroToDollar = new JButton("Convert € to $");
		btnDollarToEuro = new JButton("Convert $ to €");
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// first row
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(amount, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		add(sumToConvert, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		add(btnEuroToDollar, gc);
		
		gc.gridx = 3;
		gc.gridy = 0;
		add(btnDollarToEuro, gc);
		
		// second row
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(warning, gc);
		
		// third row
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		add(resultLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(resultAmount, gc);
		
		// fourth row
		gc.gridx = 0;
		gc.gridy = 3;
		add(exchangeRate, gc);
		
		// try to validate user input
		sumToConvert.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char input = e.getKeyChar();
				if (input < '0' || input > '9' || input == '.'){
					e.consume();
					warning.setText("Please insert a number!");
				}
				else warning.setText(" ");
			}
		});
		
		btnEuroToDollar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = sumToConvert.getText();
				double resDouble = Double.parseDouble(text) * exchRate;
				String resText = String.valueOf(resDouble);
				resText = String.format("%.2f", resDouble);
				resultAmount.setText(resText);
			}
		});
		
		btnDollarToEuro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = sumToConvert.getText();
				double resDouble = Double.parseDouble(text) / exchRate;
				String resText = String.valueOf(resDouble);
				resText = String.format("%.2f", resDouble);
				resultAmount.setText(resText);
			}
		});
	}

}
