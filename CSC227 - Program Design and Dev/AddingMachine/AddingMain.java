// Dominic Martinez
// 9/3/02

//
// Class AddingFrame provides the user interface for a graphical
// adding machine.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddingFrame extends CloseableFrame
{
	public AddingFrame()
	{
		// create frame and accumulator
		setSize(200, 250);
		setTitle("CS227 Adder");
		Container contentPane = getContentPane();
		myAccumulator = new AddingMachine();

		// create font for all of the buttons
		myFont = new Font("Monospaced", Font.BOLD, 24);

		// create and add text field for current display
		myText = new JTextField("0", 20);
		myText.setEditable(false);
		myText.setBackground(Color.cyan);
		myText.setFont(myFont);
		contentPane.add(myText, "North");
		myText.setHorizontalAlignment(JTextField.RIGHT);

		// create and add button panel for adding machine
		JPanel buttons = createButtonPanel();
		contentPane.add(buttons, "Center");

		// create and add clear button at the bottom
		JButton clear = new JButton("Clear");
		clear.setFont(myFont);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myAccumulator.clear();
				updateDisplay();
			}
		});
		contentPane.add(clear, "South");
	}

	private JPanel createButtonPanel()
	// post: creates a panel of buttons with digits placed as
	//       in an adding machine, with minus and plus buttons
	//       surrounding 0 in the bottom row.  Adds listeners
	//       to each button to modify accumulator and update
	//       the display.
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 3));
		digitButton(buttons, 7);
		digitButton(buttons, 8);
		digitButton(buttons, 9);
		digitButton(buttons, 4);
		digitButton(buttons, 5);
		digitButton(buttons, 6);
		digitButton(buttons, 1);
		digitButton(buttons, 2);
		digitButton(buttons, 3);
		JButton minus = new JButton("-");
		minus.setFont(myFont);
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myAccumulator.minus();
				updateDisplay();
			}
		});
		buttons.add(minus);
		digitButton(buttons, 0);
		JButton plus = new JButton("+");
		plus.setFont(myFont);
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myAccumulator.plus();
				updateDisplay();
			}
		});
		buttons.add(plus);
		return buttons;
	}

	private void digitButton(JPanel buttons, final int value)
	// post: adds a single digit button to the buttons panel
	//       and adds a listener that calls the accumulator
	//       with that digit value.
	{
		JButton b = new JButton("" + value);
		b.setFont(myFont);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myAccumulator.addDigit(value);
				updateDisplay();
			}
		});
		buttons.add(b);
	}

	private void updateDisplay()
	// post: updates the text display to the current display
	//       value returned by the accumulator
	{
		myText.setText("" + myAccumulator.getDisplay());
	}

	private AddingMachine myAccumulator; // accumulator for math
	private JTextField myText;           // text display
	private Font myFont;                 // font for buttons
 }

// Stuart Reges
// 8/27/99
// grader: self
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

// Stuart Reges
// 8/27/99
// grader: self
//
// AddingMain provides method main for a graphical adding machine.

public class AddingMain
{
	public static void main(String[] args)
	{
		AddingFrame f = new AddingFrame();
		f.show();
	}
}
