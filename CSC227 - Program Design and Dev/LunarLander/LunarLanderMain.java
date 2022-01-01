import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


// Dominic Martinez
// 9/6/02
//
// LunarLanderMain provides method main for a lunar landing simulation.

public class LunarLanderMain
{
	public static void main(String[] args)
	{
		LunarLanderFrame f = new LunarLanderFrame();
		f.show();
	}
}

// Dominic Martinez
// 9/6/02
//
// Class CloseableFrame provides a variant of JFrame that includes
// a window listener that closes the window and exits the
// current application when the user selects the close button.

class CloseableFrame extends JFrame
{
	public CloseableFrame()
	{
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}

// Dominic Martinez
// 9/6/02
//
// Class LunarInfoPanel displays a text version of the lunar lander's state:
// fuel level, velocity and altitude

class LunarInfoPanel extends JPanel
{
	public LunarInfoPanel(LunarLander lander)
	{
		myLander = lander;
		setLayout(new GridLayout(3, 1));
		JPanel p;

		// create fuel subpanel
		p = new JPanel();
		myFuel = new JTextField(5);
		myFuel.setEditable(false);
		p.add(new JLabel("Fuel"));
		p.add(myFuel);
		add(p);

		// create velocity subpanel
		p = new JPanel();
		myVelocity = new JTextField(10);
		myVelocity.setEditable(false);
		p.add(new JLabel("Velocity"));
		p.add(myVelocity);
		add(p);

		// create altitude subpanel
		p = new JPanel();
		myAltitude = new JTextField(7);
		myAltitude.setEditable(false);
		p.add(new JLabel("Altitude"));
		p.add(myAltitude);
		add(p);

		update();
	}

	public void update()
	// post: updates text fields
	{
		myFuel.setText(myLander.getFuel() + " units");
		myVelocity.setText(myLander.getVelocity() + " meters/second");
		myAltitude.setText(myLander.getAltitude() + " meters");
	}

	private LunarLander myLander;   // lander to query
	private JTextField myFuel;      // text field for fuel
	private JTextField myVelocity;  // text field for velocity
	private JTextField myAltitude;  // text field for altitude
}

// Dominic Martinez
// 9/6/02
//
// Class LunarLanderFrame provides the user interface for a lunar landing
// simulation.

class LunarLanderFrame extends CloseableFrame
{
	public LunarLanderFrame()
	{
		// create frame and lander
		setSize(300, 500);
		setTitle("CS227 Lunar Lander");
		Container contentPane = getContentPane();
		myLander = new LunarLander();
		myLander.reset();

		// create and add info panel on bottom
		myInfo = new LunarInfoPanel(myLander);
		contentPane.add(myInfo, "South");

		// create and add lander picture
		myPicture = new LunarPicture(myLander);
		contentPane.add(myPicture, "Center");

		// create and add reset/thrust buttons at the top
		JPanel buttons = createButtonPanel();
		contentPane.add(buttons, "North");

		// create a timer and start it
		addTimer();
		myTimer.start();
	}

	private JPanel createButtonPanel()
	// post: creates and returns a panel with buttons for resetting the
	//       simulation and for applying thrust
	{
		JPanel buttons = new JPanel();
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myLander.reset();
				myTimer.restart();
				myInfo.update();
				myPicture.update();
			}
		});
		buttons.add(reset);
		JButton thrust = new JButton("Thrust");
		thrust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myLander.thrust();
			}
		});
		buttons.add(thrust);
		return buttons;
	}

	private void addTimer()
	// post: creates a timer that calls the lander's tick
	//       method and updates the displays
	{
		ActionListener updater = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myLander.tick();
				myInfo.update();
				myPicture.update();
				if (myLander.getAltitude() <= 0)
					myTimer.stop();
			}
		};
		myTimer = new Timer(1000, updater);
	}

	private LunarLander myLander;   // lander to query
	private LunarInfoPanel myInfo;  // info panel
	private LunarPicture myPicture; // picture panel
	private Timer myTimer;          // timer
 }

// Dominic Martinez
// 9/6/02
//
// Class LunarPicture displays a graphical view of the lunar
// lander's current altitude

class LunarPicture extends JPanel
{
	public LunarPicture(LunarLander lander)
	// post: LunarPicture constructed with given lander object
	{
		myLander = lander;
	}

	public void update()
	// post: picture is redrawn to account for current lander state
	{
		repaint();
	}

	public void paintComponent(Graphics g)
	// post: lander is drawn
	{
		super.paintComponent(g);
		setBackground(Color.darkGray);
		g.setColor(Color.magenta);
		int altitude = myLander.getAltitude();
		if (altitude <= 0 && myLander.getVelocity() > LunarLander.SAFE_LANDING) {
			Font f = new Font("Serif", Font.BOLD, Math.min(48, getHeight()));
			g.setFont(f);
			g.drawString("CRASH!", (getWidth() - g.getFontMetrics().stringWidth("CRASH!"))/2, (getHeight() + f.getSize())/2);
		} else {
			altitude = Math.max(0, altitude);
			int verticalSpace = getHeight() - 3 * BASE_HEIGHT - 4;
			int lowerLeftX = (getWidth() - BASE_WIDTH)/2;
			int lowerLeftY = 3 * BASE_HEIGHT + (LunarLander.INITIAL_ALTITUDE - myLander.getAltitude()) * verticalSpace/LunarLander.INITIAL_ALTITUDE + 2;
			// draw legs
			g.drawLine(lowerLeftX, lowerLeftY, lowerLeftX, lowerLeftY - BASE_HEIGHT);
			g.drawLine(lowerLeftX + BASE_WIDTH - 1, lowerLeftY, lowerLeftX + BASE_WIDTH - 1, lowerLeftY - BASE_HEIGHT);
			// draw struts
			g.drawLine(lowerLeftX - 3, lowerLeftY, lowerLeftX + 3, lowerLeftY);
			g.drawLine(lowerLeftX + BASE_WIDTH - 4, lowerLeftY, lowerLeftX + BASE_WIDTH + 2, lowerLeftY);
			// draw middle
			g.fillRect(lowerLeftX, lowerLeftY - 2 * BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT);
			// draw top
			g.fillOval(lowerLeftX, lowerLeftY - 3 * BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT);
		}
	}

	private LunarLander myLander; // lander to query

	public static final int BASE_HEIGHT = 20; // base height of 3 subfigures of lander
	public static final int BASE_WIDTH = 60;  // base width of lander figure
}