
import javax.swing.*;

public class TestFrame extends JFrame {
	// No-arg constructor to construct an instance o JFrame
	public TestFrame() {
		setTitle("Window 1");
		setSize(350, 350);
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
// Create an instance of TestFrame
		TestFrame tf = new TestFrame();
// Create an instance of JFrame() explicitly
		JFrame frame = new JFrame();
		frame.setTitle("Window 2");
		frame.setSize(500, 500);
		frame.setLocation(750, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}