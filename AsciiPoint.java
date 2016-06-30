public class AsciiPoint{
	private int iX = 0;
	private int iY = 0;

	public AsciiPoint(int x, int y){
		iX = x;
		iY = y;
	}

	public int getX(){
		return iX;
	}
	
	public int getY(){
		return iY;
	}
	
	public String toString(){
		String sTemp = "("+iX+","+iY+")";
		return sTemp;
	}
}

	
