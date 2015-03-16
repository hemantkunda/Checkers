
public class Board 
{
	private Token[][] grid;
	private int row;
	private int col;
	private int redTokens;
	private int blackTokens;
	
	public Board()
	{
		grid = new Token[8][8];
		this.row = 8;
		this.col = 8;
		redTokens = 12;
		blackTokens = 12;
	}
	
	public Token get(Location l)
	{
		return (grid[l.x()][l.y()]);
	}
	
	public Token remove(Location l)
	{
		return put(null, l);
	}
	
	public Token put(Token obj, Location l)
	{
		Token current = grid[l.x()][l.y()];
		grid[l.x()][l.y()] = obj;
		return current;
	}
	
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
	
	public void updateBlackTokens()
	{
		blackTokens--;
	}
	
	public void updateRedTokens()
	{
		redTokens--;
	}
	
	public int remainingRedTokens()
	{
		return redTokens;
	}
	
	public int remainingBlackTokens()
	{
		return blackTokens;
	}
	
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
