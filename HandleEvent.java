
import javax.swing.*;
import java.awt.event.*;

public class HandleEvent extends JFrame {


	// Constructor
    public HandleEvent() {

	// Create the buttons
	JButton jbtOK = new JButton( "OK" );
	JButton jbtCancel = new JButton( "Cancel" );

	// Create the panel to place the buttons on
	JPanel panel = new JPanel();

	// Add each button to the panel
	panel.add( jbtOK );
	panel.add( jbtCancel );

	// Add the panel to the frame
	add(panel);

	// Associate events to each button action - 
	// where each event is an object of a class
	//
	OKListener okl = new OKListener();
	jbtOK.addActionListener( okl );	

	CancelListener cancell = new CancelListener();
	jbtCancel.addActionListener( cancell );	
	

    } // HandleEvent

    class OKListener implements ActionListener {
	public void actionPerformed( ActionEvent e ) {
	    System.out.println( "OK button clicked" );
	}
    } // OKListener

    class CancelListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            System.out.println( "Cancel button clicked" );
        }
    } // CancelListener


    public static void main( String[] args ) {

	// Create a new frame
	JFrame frame = new HandleEvent();	

	// Initialize frame information
	frame.setTitle( "Handle Event" );
	frame.setSize( 200, 150 );
	frame.setLocation( 200, 100 );
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );

	// Turn it on
	frame.setVisible( true );
    } // main()

} // class HandleEvent
