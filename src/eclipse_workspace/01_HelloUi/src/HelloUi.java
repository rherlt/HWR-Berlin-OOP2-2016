 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
public class HelloUi {
 
	//main method - nothing new here
    public static void main(String[] args) {
 
    	//create a new thread for the UI.
        SwingUtilities.invokeLater(new Runnable() {
        	//run the UI tread
            public void run() {
            	
            	//create the main window as JFrame.
            	//Use our MainFrame window we created earlier
                JFrame frame = new MainFrame("Hello UI");
                
                //customize the size
                frame.setSize(500, 400);
                //set what happens if the window is closed by the user
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //show the window
                frame.setVisible(true);                
            }
        });
    }
}