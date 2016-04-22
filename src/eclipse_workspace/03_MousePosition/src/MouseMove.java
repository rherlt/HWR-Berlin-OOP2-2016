import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MouseMove {

	// TODO Auto-generated method stub
	/**
	 * Main mehtod as starting point of our application
	 * @param args
	 */
    public static void main(String[] args) {
 
    	//create a new thread for the UI.
        SwingUtilities.invokeLater(new Runnable() {
        	//run the UI tread
            public void run() {
            	
            	//create the main window as JFrame.
            	//Use our MainFrame window we created earlier
                JFrame frame = new MainFrame("Mäuschen mäuschen");
                
                //customize the size
                frame.setSize(300, 300);
                //set what happens if the window is closed by the user
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //show the window
                frame.setVisible(true);                                      
            }
        });
    }
}
