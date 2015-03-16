
public class Board 
{
	private Token[][] grid;
	private int row;
	private int col;
	private int redTokens;
	private int blackTokens;
	
	/*
	 * Makes a new Board object that holds an 8x8 grid.
	 */
	public Board()
	{
		grid = new Token[8][8];
		this.row = 8;
		this.col = 8;
		redTokens = 12;
		blackTokens = 12;
	}
	
	/**
	 * Gets whatever Token is located at that Location
	 */
	public Token get(Location l)
	{
		return (grid[l.x()][l.y()]);
	}
	
	public void reset()
	{
		redTokens = 12;
		blackTokens = 12;
	}
	
	/*
	 * Returns the Token at the specified location and replaces it with the null value.
	 */ 
	public Token remove(Location l)
	{
		return put(null, l);
	}
	
	/*
	* Puts the specified Token at the specified Location and returns whatever was originally at the Location.
	*/
	public Token put(Token obj, Location l)
	{
		Token current = grid[l.x()][l.y()];
		grid[l.x()][l.y()] = obj;
		return current;
	}
	
	/**
	 * Returns true if the Location's x and y coordinates fall within the boundaries of the grid (0 <= x,y < 8) and 
	 * if there is no Token at that location in the Board.
	 */ 
	public boolean isValid(Location l)
	{
		if (0 <= l.x() && l.x() < row)
		{
			if (0 <= l.y() && l.y() < col)
			{
				if (get(l) == null)
				{
					return true;
				}			
			}
		}
		return false;
	}
	
	/**
	 * Updates the number of black Tokens on the grid. Should only be called when a red Token jumps a black Token.
	 */ 
	public void updateBlackTokens()
	{
		blackTokens--;
	}
	
	/**
	 * Updates the number of red Tokens on the grid.  Should only be called when a black Token jumps a red Token.
	 */ 
	public void updateRedTokens()
	{
		redTokens--;
	}
	
	/**
	 * Returns the number of remaining red Tokens on the Board.  Used to figure out if the board is devoid of red Tokens.
	 */ 
	public int remainingRedTokens()
	{
		return redTokens;
	}
	
	/**
	 * Returns the number of remaining black Tokens on the Board.  Used to figure out if the board is devoid of black Tokens.
	 */ 
	public int remainingBlackTokens()
	{
		return blackTokens;
	}
	
	/**
	 * Returns true if the Location's x and y coordinates fall within the boundaries of the Board (0 <= x, y < 8)'
	 */ 
	public boolean isValidMoveSpot(Location l)
	{
		if (0 <= l.x() && l.x() < row)
		{
			if (0 <= l.y() && l.y() < col)
			{
				return true;			
			}
		}
		return false;
	}
}
