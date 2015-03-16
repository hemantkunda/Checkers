
public class Location 
{
	private int x;
	private int y;
	
	public static void main(String[] args)
	{
		String one = "one";
		String two = null;
		System.out.println(two.equals(one));
	}
	
	public static void testBetween()
	{
		Location l1 = new Location(2, 0);
		Location l2 = new Location(2, 2);
		System.out.println(l1.between(l2));
	}
	public Location(int r, int c)
	{
		x = r;
		y = c;
	}
	
	// Returns the x coordinate of the Location.
	public int x()
	{
		return x;
	}
	
	// Returns the y coordinate of the Location.
	public int y()
	{
		return y;
	}
	
	// Returns a Location object that is visually located one space down and one space to the left of this Location.
	public Location downLeft()
	{
		return new Location(x+1, y-1);
		
	}
	// Returns a Location object that is visually located one space down and one space to the right of this Location.
	public Location downRight()
	{
		return new Location(x+1, y+1);
	}
	
	// Returns a Location object that is visually located one space up and one space to the left of this Location.
	public Location upLeft()
	{
		return new Location(x-1,y-1);
	}
	
	// Returns a Location object that is visually located one space up and one space to the right of this Location.
	public Location upRight()
	{
		return new Location(x-1, y+1);
	}
	
	// Returns true if this Location and other have the same x and y coordinates.
	public boolean equals(Location other)
	{
		return (other.x() == x() && other.y() == y());
	}
	
	// Returns a Location object located between this Location and the specified Location by averaging the x and y coordinates.
	public Location between(Location l)
	{
		return new Location((this.x() + l.x())/2, (this.y() + l.y())/2);
	}
	
	// Returns a String containing the Location's information
	public String toString()
	{
		return "Location(" + x() + ", " + y() + ")" ;
	}
}
