import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/***
 * Main window of the application. Please notice that this window is implementing
 * the interface MouseMotionListener
 * @author rherlt
 *
 *
 */
public class MainFrame extends JFrame implements MouseMotionListener, MouseListener{
    
    private static final long serialVersionUID = -979691148534081065L;
    private JLabel label;
    private int clickCount = 0;
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
        this.addMouseListener(this);
        
        label = new JLabel("Clicks: 0");
		this.getContentPane().add(label);
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
	
	/**
     * Override the mouseClicked method of the interface MouseListener.
     * This method is called by the framework when the mouse is clicked
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		clickCount++;
		String text = String.format("Clicks: %d", clickCount);
		label.setText(text);
	}

	/**
     * Override the mousePressed method of the interface MouseListener.
     * This method is called by the framework when the mouse button is pressed
     */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Override the mouseReleased method of the interface MouseListener.
     * This method is called by the framework when the mouse button is released
     */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Override the mouseEntered method of the interface MouseListener.
     * This method is called by the framework when the mouse entered this component
     */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Override the mouseExited method of the interface MouseListener.
     * This method is called by the framework when the mouse exited this component
     */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
  
}



 