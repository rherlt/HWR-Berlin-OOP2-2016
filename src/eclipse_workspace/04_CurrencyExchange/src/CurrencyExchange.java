import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// main frame for cursor movement app
public class CurrencyExchange {

	/**
	 * 
	 * Main method as starting point of our application
	 * @author Olga Mrost
	 */
    public static void main(String[] args) {
 
    	//create a new thread for the UI.
        SwingUtilities.invokeLater(new Runnable() {
        	//run the UI tread
            public void run() {
            	
            	//create the main window as JFrame.
            	//Use our MainFrame window we created earlier
                JFrame frame = new CurExchangeFrame("Currency exchange");
                
                //customize the size
                frame.setSize(600, 300);
                
                // to center the frame
                frame.setLocationRelativeTo(null);
                //set what happens if the window is closed by the user
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //show the window
                frame.setVisible(true);                                      
            }
        });
    }
}

