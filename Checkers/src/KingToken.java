
public class KingToken extends Token 
{
	public KingToken(boolean t, Board b)
	{
		super(t, b);
	}
	
	public boolean isOppositeColor(Token t)
	{
		if (isRedPiece())
		{
			return !t.isRedPiece();
		}
		return t.isRedPiece();
	}

	public Location[] validAdjMoveLocs()
	{
		Location[] validLocs;
		int validMoves = 0;
		if (board.isValid(loc.upLeft()))
		{
			validMoves++;
		}
		if (board.isValid(loc.upRight()))
		{
			validMoves++;
		}
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
		if (board.isValid(loc.upLeft()))
		{
			validLocs[j++] = loc.upLeft();
		}
		if (board.isValid(loc.upRight()))
		{
			validLocs[j++] = loc.upRight();
		}
		if (board.isValid(loc.downLeft()))
		{
			validLocs[j++] = loc.downLeft();
		}
		if (board.isValid(loc.downRight()))
		{
			validLocs[j++] = loc.downRight();
		}
		return validLocs;
	}
	
	public Location[] validJumpLocs()
	{
		Location[] jumpLocs;
		int validJumps = 0;
		boolean b1 = board.isValid(loc.upLeft().upLeft());
		boolean b2 = board.isValidMoveSpot(loc.upLeft());
		//System.out.println(loc.upLeft().upLeft() + "" + b1);
		//System.out.println(loc.upLeft() + "" + b2);
		if (b1 && b2)
		{
			Token t = board.get(loc.upLeft());
			if (t != null && t.isRedPiece() != isRedPiece())
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
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				validJumps++;
			}
		}
		boolean b5 = board.isValid(loc.downLeft().downLeft());
		boolean b6 = board.isValidMoveSpot(loc.downLeft());
		if (b5 && b6)
		{
			Token t = board.get(loc.downLeft());
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				validJumps++;
			}
		}
		boolean b7 = board.isValid(loc.downRight().downRight());
		boolean b8 = board.isValidMoveSpot(loc.downRight());
		if (b7 && b8)
		{
			Token t = board.get(loc.downRight());
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				validJumps++;
			}
		}
		/*System.out.println("v: " + validJumps);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println(b5);
		System.out.println(b6);
		System.out.println(b7);
		System.out.println(b8);*/

		jumpLocs = new Location[validJumps];
		int j = 0;
		if (b1 && b2) 
		{
			System.out.println("a1");
			Token t = board.get(loc.upLeft());
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				jumpLocs[j++] = loc.upLeft().upLeft();
			}
			for (Location l : jumpLocs)
			{
				System.out.println(l);
			}
		}
		if (b3 && b4)
		{
			System.out.println("b1");
			Token t = board.get(loc.upRight());
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				jumpLocs[j++] = loc.upRight().upRight();
			}
			for (Location l : jumpLocs)
			{
				System.out.println(l);
			}
		}
		if (b5 && b6)
		{
			System.out.println("c1");
			Token t = board.get(loc.downLeft());
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				jumpLocs[j++] = loc.downLeft().downLeft();
			}
			for (Location l : jumpLocs)
			{
				System.out.println(l);
			}
		}
		if (b7 && b8)
		{
			System.out.println("d1");
			Token t = board.get(loc.downRight());
			if (t != null && t.isRedPiece() != isRedPiece())
			{
				jumpLocs[j++] = loc.downRight().downRight();
			}
		}
		System.out.println("e1");
		for (Location l : jumpLocs)
		{
			System.out.println(l);
		}
		return jumpLocs;
	}
	
	public String getImageFileName()
	{
		if (isRedPiece())
		{
			return "tokens/redKing.png";
		}
		return "tokens/blackKing.png";
	}
}
