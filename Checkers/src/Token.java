import java.awt.Color;

public class Token 
{
	protected boolean redPiece; // true = red; false = black
	protected Location loc;
	protected Board board; 
	
	public Token(boolean t, Board b)
	{
		redPiece = t;
		board = b;
	}
		
	// returns true if the Token is red.
	public boolean isRedPiece()
	{
		return redPiece;
	}
	
	// places the Token at the specified Location in the board.
	public void place(Location l)
	{
		if (loc == null)
		{
			board.put(this, l);
			loc = l;
		}
	}
	
	// Promotes this token to a King.
	public void promote()
	{
		KingToken king = new KingToken(redPiece, board);
		board.put(king, loc);
		king.place(loc);
	}
	
	// Returns true if the Token lies on the row farthest from its initial location. 
	public boolean canPromote()
	{
		if (isRedPiece())
		{
			return loc.x() == 0;
		}
		return loc.x() == 7;
	}
	
	// Removes the Token from the Board. Called when it's jumped over.
	public void removeSelfFromBoard()
	{
		board.remove(loc);
		board = null;
		loc = null;
	}
	
	// Returns a list of Locations that it can move to.  Directions differ based on the color.
	public Location[] validAdjMoveLocs()
	{
		Location[] validLocs;
		int validMoves = 0;
		if (redPiece)
		{
			if (board.isValid(loc.upLeft()))
			{
				validMoves++;
			}
			if (board.isValid(loc.upRight()))
			{
				validMoves++;
			}	
			validLocs = new Location[validMoves];
			int j = 0;
			if (board.isValid(loc.upLeft()))
			{
				validLocs[j++] = loc.upLeft();
			}
			if (board.isValid(loc.upRight()))
			{
				validLocs[j++] = loc.upRight();
			}
		}
		else
		{
			if (board.isValid(loc.downLeft()))
			{
				validMoves++;
			}
			if (board.isValid(loc.downRight()))
			{
				validMoves++;
			}	
			validLocs = new Location[validMoves];
			int j = 0;
			if (board.isValid(loc.downLeft()))
			{
				validLocs[j++] = loc.downLeft();
			}
			if (board.isValid(loc.downRight()))
			{
				validLocs[j++] = loc.downRight();
			}
		}
		return validLocs;
	}
	
	// Returns a list of Locations that the Token can jump to.  Directions differ on the Token's color.
	public Location[] validJumpLocs()
	{
		Location[] jumpLocs;
		int validJumps = 0;
		if (redPiece)
		{
			boolean b1 = board.isValid(loc.upLeft().upLeft());
			boolean b2 = board.isValidMoveSpot(loc.upLeft());
			//System.out.println(loc.upLeft().upLeft() + "" + b1);
			//System.out.println(loc.upLeft() + "" + b2);
			if (b1 && b2)
			{
				Token t = board.get(loc.upLeft());
				if (t != null && !t.isRedPiece())
				{
					validJumps++;
				}
			}
			boolean b3 = board.isValid(loc.upRight().upRight());
			boolean b4 = board.isValidMoveSpot(loc.upRight());
			//System.out.println(loc.upRight().upRight() + "" + b3);
			//System.out.println(loc.upRight() + "" + b4);

			if (b3 && b4)
			{
				Token t = board.get(loc.upRight());
				if (t != null && !t.isRedPiece())
				{
					validJumps++;
				}
			}
			jumpLocs = new Location[validJumps];
			int j = 0;
			if (b1 && b2) 
			{
				Token t = board.get(loc.upLeft());
				if (t != null && !t.isRedPiece())
				{
					jumpLocs[j++] = loc.upLeft().upLeft();
				}
			}
			if (b3 && b4)
			{
				Token t = board.get(loc.upRight());
				if (t != null && !t.isRedPiece())
				{
					jumpLocs[j++] = loc.upRight().upRight();
				}
			}
		}
		else
		{
			boolean b1 = board.isValid(loc.downLeft().downLeft());
			boolean b2 = board.isValidMoveSpot(loc.downLeft());
			if (b1 && b2)
			{
				Token t = board.get(loc.downLeft());
				if (t != null && t.isRedPiece())
				{
					validJumps++;
				}
			}
			boolean b3 = board.isValid(loc.downRight().downRight());
			boolean b4 = board.isValidMoveSpot(loc.downRight());
			if (b3 && b4)
			{
				Token t = board.get(loc.downRight());
				if (t != null && t.isRedPiece())
				{
					validJumps++;
				}
			}
			jumpLocs = new Location[validJumps];
			int j = 0;
			if (b1 && b2)
			{
				Token t = board.get(loc.downLeft());
				if (t != null && t.isRedPiece())
				{
					jumpLocs[j++] = loc.downLeft().downLeft();
				}
			}
			if (b3 && b4)
			{
				Token t = board.get(loc.downRight());
				if (t != null && t.isRedPiece())
				{
					jumpLocs[j++] = loc.downRight().downRight();
				}
			}
		}
		return jumpLocs;
	}
	
	// returns true if the Token can move to the specified Location.
	public boolean canMoveTo(Location l)
	{
		for (Location o : validAdjMoveLocs())
		{
			if (o.equals(l))
			{
				return true;
			}
		}
		return false;
	}
	
	// Moves the Token to the specified Location on its board.
	public void moveTo(Location l)
	{
		board.put(board.remove(loc), l);
		loc = l;
	}
	
	// Jumps the Token to the specified Location and removes whatever lies bewteen the two locations.
	public void jumpTo(Location l)
	{
		board.remove(loc.between(l));
		moveTo(l);
	}
	
	// Returns the Location of the Token.
	public Location getLocation()
	{
		return loc;
	}
	
	// Gets the Board of the Token.
	public Board getBoard()
	{
		return board;
	}
	
	// Gets the Color of the Token.
	public Color getColor()
	{
		if (isRedPiece())
		{
			return Color.RED;
		}
		return Color.BLACK;
	}
	
	// Returns true if the Token can jump at all.
	public boolean canJump()
	{
		Location[] locs = validJumpLocs();
		for (Location l : locs)
		{
			if (canJumpTo(l))
			{
				return true;
			}
		}
		return false;
	}
	
	// Returns true if the Token can jump to the specified Location.
	public boolean canJumpTo(Location l)
	{
		for (Location o : validJumpLocs())
		{
			//System.out.println(o);
			if (o.equals(l))
			{
				return true;
			}
		}
		return false;
	}
	
	// Returns a String containing the Token's icon filepath.
	public String getImageFileName()
	{
		if (isRedPiece())
		{
			return "tokens/redToken.png";
		}
		return "tokens/blackToken.png";
	}
	
	//Returns true if this Token and other share the same color and Location.
	public boolean equals(Token other)
	{
		return redPiece == other.isRedPiece() && loc.equals(other.getLocation());
	}
}
