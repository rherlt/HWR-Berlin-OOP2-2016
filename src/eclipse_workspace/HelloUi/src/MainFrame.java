import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainFrame extends JFrame {
    
    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        setLayout(new BorderLayout());
        
        // Create Swing component
        final JTextField textfield = new JTextField("It is so nice to see you :)");
        final JTextArea textArea = new JTextArea();
        JButton button = new JButton("Click me!");
        
        // Add Swing components to content pane
        Container c = getContentPane();
        
        c.add(textfield, BorderLayout.NORTH);
        c.add(textArea, BorderLayout.CENTER);
        c.add(button, BorderLayout.SOUTH);
        
        // Add behaviour
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append(textfield.getText()+"\n");
            }
            
        });
    }
}



 