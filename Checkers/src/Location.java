
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
	
	public int x()
	{
		return x;
	}
	
	public int y()
	{
		return y;
	}
	
	public Location downLeft()
	{
		return new Location(x+1, y-1);
	}
	
	public Location downRight()
	{
		return new Location(x+1, y+1);
	}
	
	public Location upLeft()
	{
		return new Location(x-1,y-1);
	}
	
	public Location upRight()
	{
		return new Location(x-1, y+1);
	}
	
	public boolean equals(Location other)
	{
		return (other.x() == x() && other.y() == y());
	}
	
	public Location between(Location l)
	{
		return new Location((this.x() + l.x())/2, (this.y() + l.y())/2);
	}
	public String toString()
	{
		return "Location(" + x() + ", " + y() + ")" ;
	}
}
