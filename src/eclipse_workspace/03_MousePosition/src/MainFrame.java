import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/***
 * Main window of the application. Please notice that this window is implementing
 * the interface MouseMotionListener
 * @author rherlt
 *
 *
 */
public class MainFrame extends JFrame implements MouseMotionListener{
    
    private static final long serialVersionUID = -979691148534081065L;
    
       /**
     * constructor of the window
     * @param title which is displayed as window title
     */
    public MainFrame(String title) {
        super(title);
        
        //add a listener for mouse motions. Use this class as
        //listener which is called by the framework when a mouse
        //motion is detected
        this.addMouseMotionListener(this);        
    }

    /**
     * Override the mouseDragged method of the interface MouseMotionListener.
     * This method is called by the framework when the mouse is dragged
     */
	@Override
	public void mouseDragged(MouseEvent e) {
	
		//get the x and y coordinates of the current mouse position
		double xCoord = e.getPoint().getX();
		double yCoord = e.getPoint().getY();
		
		//create a text containing the positions and write them to the current frame title
		String position = String.format("Mouse dragged: X: %.2f - Y: %.2f ", xCoord, yCoord);
		this.setTitle(position);
	}

	/**
     * Override the mouseMoved method of the interface MouseMotionListener.
     * This method is called by the framework when the mouse is moved
     */
	@Override
	public void mouseMoved(MouseEvent args) {
		
		//get the x and y coordinates of the current mouse position
		double xCoord = args.getPoint().getX();
		double yCoord = args.getPoint().getY();
		
		//create a text containing the positions and write them to the current frame title
		String position = String.format("X: %.2f - Y: %.2f ", xCoord, yCoord);
		this.setTitle(position);
	}
  
}



 