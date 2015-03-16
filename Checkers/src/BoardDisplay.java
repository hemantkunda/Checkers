import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Used to display the contents of a game board
public class BoardDisplay implements ActionListener
{
	private Board board;
	private JButton[][] grid;
	private Token selectedToken;
	private Token jumpingToken;
	private JFrame frame;
	private boolean isRedTurn;
	private boolean jumping;
	public ArrayList<Token> redTokens;
	public ArrayList<Token> blackTokens;
	
	// Constructs a new display for displaying the given board
	public BoardDisplay(Board board)
	{
		isRedTurn = true;
		this.board = board;
		grid = new JButton[8][8];

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                createAndShowGUI();
            }
        });

		//Wait until display has been drawn
        try
        {
        	while (frame == null || !frame.isVisible())
        		Thread.sleep(1);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        //Create and set up the window.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(8, 8));
        frame.setTitle("Checkers");

		//Create each square as a button.
        for (int row = 0; row < grid.length; row++)
        	for (int col = 0; col < grid[row].length; col++)
        	{
        		JButton button = new JButton();
        		button.setContentAreaFilled(false);
        		button.setOpaque(true);
        		if ((row + col) % 2 == 0)
        		{
        			button.setBackground(new Color(155, 145, 115));
        		}
        		else
        		{
        			button.setBackground(new Color(110, 85, 55));
        		}
        		button.setPreferredSize(new Dimension(85, 85));
        		button.setActionCommand(row + "," + col);
        		button.addActionListener(this);
        		frame.getContentPane().add(button);
        		grid[row][col] = button;
			}
        clearColors();

		//Show the pieces
		showBoard();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	//Called when a square is clicked.
    public void actionPerformed(ActionEvent event)
    {
		//Determine location of clicked button.
		String command = event.getActionCommand();
		int comma = command.indexOf(",");
		int row = Integer.parseInt(command.substring(0, comma));
		int col = Integer.parseInt(command.substring(comma + 1));
		Location loc = new Location(row, col);

		if (selectedToken == null || (jumping && !selectedToken.equals(jumpingToken)))
		{
			System.out.println("1");
			//we have just selected a piece for the first time.
			if (board.get(loc) != null)
			{
				selectedToken = board.get(loc);
				if (jumping && !selectedToken.equals(jumpingToken))
				{
					return;
				}
				boolean validMove = isRedTurn == selectedToken.isRedPiece();
				if (validMove)
				{
					grid[loc.x()][loc.y()].setBorder(BorderFactory.createLineBorder(Color.YELLOW));			
				}
				else
				{
					selectedToken = null;
				}
			}
			
		}
		else if (loc.equals(selectedToken.getLocation()))
		{
			System.out.println("2");
			//we are deselecting the piece
			selectedToken = null;
			grid[loc.x()][loc.y()].setBorder(null);			
			if (jumping)
			{
				jumping = false;
				isRedTurn = !isRedTurn;
			}
		}
		else
		{
			System.out.println("3");
			if (selectedToken.canJumpTo(loc))
			{
				System.out.println("4");
				selectedToken.jumpTo(loc);
				if (isRedTurn)
				{
					board.updateBlackTokens();
				}
				else
				{
					board.updateRedTokens();
				}
				if (!selectedToken.canJump())
				{
					isRedTurn = !isRedTurn;
					System.out.println("Can't jump");
					clearColors();
					jumping = false;
					if (selectedToken.canPromote())
					{
						Location currentLoc = selectedToken.getLocation();
						selectedToken.promote();
					}
					selectedToken = null;
				}
				else
				{
					jumping = true;
					System.out.println("Can jump");
					jumpingToken = selectedToken;
					selectedToken = null;
					clearColors();
				}
			}
			else if (selectedToken.canMoveTo(loc))
			{
				selectedToken.moveTo(loc);
				if (selectedToken.canPromote())
				{
					Location currentLoc = selectedToken.getLocation();
					selectedToken.promote();
				}
				selectedToken = null;
				isRedTurn = !isRedTurn;
				clearColors();
			}
		}
		showBoard();
	}

	//Redraws the board to include the pieces and border colors.
	public void showBoard()
	{
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
			{
				Location loc = new Location(row, col);

				Token token = board.get(loc);

				Icon icon = null;
				if (token != null)
				{
					//System.out.println(loc);
					icon = new ImageIcon(token.getImageFileName());
				}
				grid[row][col].setIcon(icon);
			}
	}
	
	public boolean isGameOver()
	{
		return board.remainingBlackTokens() == 0 || board.remainingRedTokens() == 0;
	}
	
	public void clearColors()
	{
		for (JButton[] bb : grid)
		{
			for (JButton b : bb)
			{
				b.setBorder(BorderFactory.createLineBorder(null));
			}
		}
	}

	// Waits for the user to select a move and returns this move.

	// Sets the title of the window.
	public void setTitle(String title)
	{
		frame.setTitle(title);
	}
	
	public void setBoard(Board board)
	{
		this.board = board;
	}
}