import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField txtSearchkey;
	private JList listBeers;
	DefaultListModel listModel = new DefaultListModel();
	
	private TokenCache tokenCache = new TokenCache();
	private HttpUtil httpHelper = new HttpUtil();
	
	protected void ButtonPressed() {
		
		//TODO implement magic code
		String token = tokenCache.getApiToken();
		String searchKey = txtSearchkey.getText();
		String encodedSearchKey = null;		
		try {
			encodedSearchKey = URLEncoder.encode(searchKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "http://api.brewerydb.com/v2/beers?key=" + token+"&name="+encodedSearchKey;
		
		String httpResponseString = httpHelper.GetUrlContentAsString(url);
	
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		JsonRoot jsonRoot = gson.fromJson(httpResponseString, JsonRoot.class);
		
		listModel.clear();
		for(Beer currentBeer:jsonRoot.getData())
		{
			String listItemText = "• "+currentBeer.getName() + " ("+currentBeer.getStyle().getName()+")";
			listModel.addElement(listItemText);
		}
		
		
	}
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
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
		
		JLabel lblSearchKey = new JLabel("Search Key:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSearchKey, 40, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSearchKey, 38, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblSearchKey);
		
		txtSearchkey = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtSearchkey, 34, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtSearchkey, 6, SpringLayout.EAST, lblSearchKey);
		springLayout.putConstraint(SpringLayout.EAST, txtSearchkey, 204, SpringLayout.EAST, lblSearchKey);
		txtSearchkey.setText("Bud");
		frame.getContentPane().add(txtSearchkey);
		txtSearchkey.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonPressed();
			}			
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnGo, 0, SpringLayout.NORTH, txtSearchkey);
		springLayout.putConstraint(SpringLayout.WEST, btnGo, 6, SpringLayout.EAST, txtSearchkey);
		frame.getContentPane().add(btnGo);		
		
		listBeers = new JList(listModel);
		springLayout.putConstraint(SpringLayout.NORTH, listBeers, 6, SpringLayout.SOUTH, txtSearchkey);
		springLayout.putConstraint(SpringLayout.WEST, listBeers, 0, SpringLayout.WEST, lblSearchKey);
		springLayout.putConstraint(SpringLayout.SOUTH, listBeers, -23, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, listBeers, 0, SpringLayout.EAST, btnGo);
		frame.getContentPane().add(listBeers);
				
	}	
}
