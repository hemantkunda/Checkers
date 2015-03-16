import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Checkers 
{
	private Board board;
	private Token selectedToken;
	private BoardDisplay display;
	private boolean isRedWinner;
	private boolean isGameOver;
	
	private void initializeGame()
	{
		board = new Board();
		display = new BoardDisplay(board);
		addTokens();
		display.showBoard();
	}
	
	private void restartGame()
	{
		wipeTokens();
		display.setBoard(board);
		addTokens();
		display.showBoard();
		continueRunning();
	}
	
	public void run()
	{
		initializeGame();
		while (true)
		{
			if (display.isGameOver())
			{
				break;
			}
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isGameOver = true;
		if (board.remainingBlackTokens() == 0)
		{
			isRedWinner = true;
		}
		gameOverScreen();
		restart();
	}
	
	public void continueRunning()
	{
		while (true)
		{
			if (display.isGameOver())
			{
				break;
			}
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isGameOver = true;
		if (board.remainingBlackTokens() == 0)
		{
			isRedWinner = true;
		}
		gameOverScreen();
		restart();
	}
	
	public static void main(String[] args)
	{
		Checkers game = new Checkers();
		game.run();
	}
	
	public void restart()
	{
		String[] options = {"Yes", "No"};
		JFrame frame = new JFrame();
		String answer = (String)JOptionPane.showInputDialog(frame, 
				"Would you like to play again?", "Message", JOptionPane.PLAIN_MESSAGE, null, options, null);
		if (answer.equals("Yes"))
		{
			restartGame();		
		}
	}
	
	public void gameOverScreen()
	{
		String message = "";
		if (isRedWinner)
		{
			message = "Red Wins!";
		}
		else
		{
			message = "Black Wins!";
		}
		JOptionPane.showMessageDialog(null, message, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
	}

	private void wipeTokens()
	{
		for (int r = 0; r < 8; r++)
		{
			for (int c = 0; c < 8; c++)
			{
				Location l = new Location(r, c);
				board.remove(l);
			}
		}
	}
	
	private void addTokens()
	{
		for (int i = 1; i <= 7; i += 2)
		{
			Location l1 = new Location(0, i);
			Location l2 = new Location(2, i);
			Location l3 = new Location(6, i);
			Token t1 = new Token(false, board);
			t1.place(l1);
			Token t2 = new Token(false, board);
			t2.place(l2);
			Token t3 = new Token(true, board);
			t3.place(l3);
		//	redTokens.add(t1);
		//	redTokens.add(t2);
		//	blackTokens.add(t3);
		}
		for (int i = 0; i < 7; i += 2)
		{
			Location l1 = new Location(1, i);
			Location l2 = new Location (5, i);
			Location l3 = new Location(7, i);
			Token t1 = new Token(false, board);
			t1.place(l1);
			Token t2 = new Token(true, board);
			t2.place(l2);
			Token t3 = new Token(true, board);
			t3.place(l3);
		//	redTokens.add(t1);
		//	blackTokens.add(t2);
		//	blackTokens.add(t3);
		}
	}
}
