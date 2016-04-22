import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/***
 * Main window of the application
 * @author rherlt
 *
 */
public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = -979691148534081065L;
    
    private int clickCount = 0;
    private final int TIMEOUT_IN_MILLIS = 5000;
    private JButton button;
    private boolean timerIsRunnig = false;
	
    /**
     * constructor of the window
     * @param title which is displayed as window title
     */
    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        setLayout(new BorderLayout());
        
        // Create Swing component
        button = new JButton();
        
        // Add Swing components to content pane
        Container c = getContentPane();        
        c.add(button, BorderLayout.CENTER);
        
        // Add behavior of button click
        button.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//check if timer is already running
            	if (timerIsRunnig)
            	{
	            	//if yes, count each click and set the button text
	            	clickCount++;  
	            	String buttonText = "Clicks: "+clickCount;
	            	button.setText(buttonText);            	
            	}
            	else
            	{            		
            		//else start the timer
            		startTimer();
            	}
            }
            
        });
        
        //call the reset method to (re)set all controls
        reset();
    }
    
    /**
     * Method which handles the timer stuff
     */
    private void startTimer()
    {
    	Timer timer = new Timer();	  	

	  	//set the task which should be run after timer schedule occurs
	  	timer.schedule(new TimerTask(){

			@Override
			public void run() {
				//call the finish method!
				timerIsRunnig = false;
				timerFinished();				
			}	  			  		
	  	}, TIMEOUT_IN_MILLIS); //setting the timer schedule to the desired timeout
	  	timerIsRunnig = true;
    }
	
    /**
     * Method which resets the UI and the click count
     */
	private void reset()
	{
		button.setText("Click me to start!");
	  	clickCount = 0;
	} 	
	
	/**
	 * Method which handles the behavior when the timer finished
	 */
	private void timerFinished()
	{
		//show a message that time is over!
		String message = String.format("Congratulations, you got %d clicks :)", clickCount);
		String title = "Time is over!";
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
		
		//reset the UI
		reset();
	}  
}



 