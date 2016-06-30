import java.util.ArrayList;

public class AsciiImage{
	private int iHeight = 0;
	private int iWidth = 0;
	private char[][] cImage;

	public AsciiImage(int iWidth, int iHeight){
		this.iHeight = iHeight;
		this.iWidth = iWidth;
		cImage = new char[iHeight][iWidth];
		this.clear();
	}

	public AsciiImage(AsciiImage img){
		for(int i = 0; i<iHeight; i++){
			for(int j = 0; j<iWidth; j++){
				this.setPixel(j,i,this.getPixel(j,i));
			}
		}
	}

	public void clear(){
		for(int i = 0; i < iWidth; i++){
			for(int j = 0; j < iHeight; j++){
				cImage[j][i]='.';
			}
		}
	}

	public String toString(){
		String sTemp = "";
		for(int i = 0; i < iHeight; i++){
			for(int j = 0; j < iWidth; j++){
				sTemp+=this.cImage[i][j];
			}
			sTemp+="\n";
		}
		return sTemp;
	}

	public char getPixel(int x, int y){
		return cImage[y][x];
	}

	public char getPixel(AsciiPoint p){
		return cImage[p.getX()][p.getY()];
	}

	public int getHeight(){
		return this.iHeight;
	}

	public int getWidth(){
		return this.iWidth;
	}

	public ArrayList<AsciiPoint> getPointList(char c){
		ArrayList<AsciiPoint> alPoint = new ArrayList<AsciiPoint>();
		for(int i = 0; i < iHeight; i++){
			for(int j = 0; j < iWidth; j++){
				if(cImage[i][j]==c){
					AsciiPoint apTemp = new AsciiPoint(j,i);
					alPoint.add(apTemp);
				}
			}
		}
		return alPoint;
	}

	public AsciiPoint getCenteroid(char c){
		ArrayList<AsciiPoint> alTemp = new ArrayList<AsciiPoint>();
		alTemp = this.getPointList(c);
		int iSumX = 0;
		int iSumY = 0;
		int iN = alTemp.size();

		for(int i = 0; i<iN; i++){
			iSumX+=alTemp.get(i).getX();
			iSumY+=alTemp.get(i).getY();
		}
		
		double dX = (double) iSumX/iN;
		double dY = (double) iSumY/iN;

		iSumX = (int) Math.round(dX);
		iSumY = (int) Math.round(dY);

		AsciiPoint apTemp = new AsciiPoint(iSumX,iSumY);
		return apTemp;		
	}

	public void setPixel(int x, int y, char c){
		cImage[y][x] = c;
	}

	public void setPixel(AsciiPoint p, char c){
		cImage[p.getX()][p.getY()] = c;
	}

	public void replace(char oldChar, char newChar){
		for(int i = 0; i < iHeight; i++){
			for(int j = 0; j < iWidth; j++){
				if(cImage[i][j]==oldChar){
					this.cImage[i][j] = newChar;
				}
			}
		}
	}

	public void drawLine(int x0, int y0, int x1, int y1, char c){
		if(Math.abs(y1-y0)<=Math.abs(x1-x0)&&(x1-x0)>=0){
			int iDeltaX = x1-x0;
			int iDeltaY = y1-y0;
			int iX = x0;
			int iY = y0;
			int iIndex = 0;
			double dSlope = (double) iDeltaY / iDeltaX;
			double dTemp = iY;
			//setPixel(x0,y0,c);
			while((iX!=x1)||(iY!=y1)){
				if(iX>=this.iWidth||iY>=this.iHeight){
					break;
				}

				if(dTemp%2==0){ 
					iY = (int) dTemp;
				}else{
					iY = (int) Math.round(dTemp);
				}
				setPixel(iX,iY,c);
				iX++;	
				dTemp += dSlope;
			}
			setPixel(x1,y1,c);
			
		}else if(Math.abs(y1-y0)>Math.abs(x1-x0)&&(y1-y0)>=0){
			int iDeltaY = x1-x0;
			int iDeltaX = y1-y0;
			int iY = x0;
			int iX = y0;
			int iIndex = 0;
			double dSlope = (double) iDeltaY / iDeltaX;
			double dTemp = iY;
			//setPixel(x0,y0,c);
			while((iY!=x1)||(iX!=y1)){
				if(iY>=this.iWidth||iX>=this.iHeight){
					break;
				}

				if(dTemp%2==0){ 
					iY = (int) dTemp;
				}else{
					iY = (int) Math.round(dTemp);
				}
				setPixel(iY,iX,c);
				iX++;	
				dTemp += dSlope;
			}
			setPixel(x1,y1,c);
		}else if(Math.abs(y1-y0)>Math.abs(x1-x0)&&(y1-y0)<0){
			drawLine(x1,y1,x0,y0,c);
		}else if(Math.abs(y1-y0)<=Math.abs(x1-x0)&&(x1-x0)<0){
			drawLine(x1,y1,x0,y0,c);
		}				
	}

	public void transpose(){
		char[][] cTemp = new char[iHeight][iWidth];

		for(int i = 0; i<iHeight; i++){
			for(int j = 0; j<iWidth;j++){				
				cTemp[i][iWidth-j-1]=this.getPixel(j,i);
			}			
		}

		for(int i = 0; i<iHeight; i++){
			for(int j = 0; j<iWidth;j++){
				this.setPixel(j,i,cTemp[i][j]);
			}			
		}			
	}

	public void growRegion(char c){
		ArrayList<AsciiPoint> alTemp = new ArrayList<AsciiPoint>();
		alTemp = this.getPointList(c);
		for(int i = 0; i<alTemp.size(); i++){
			int iX = alTemp.get(i).getX();
			int iY = alTemp.get(i).getY();

			if((iX+1)>=this.iWidth||(iX-1)<0||(iY+1)>=this.iHeight||(iY-1)<0){
				continue;
			}
			
			if(this.getPixel(iX+1,iY)=='.'){
				this.setPixel(iX+1,iY,c);
			}
			
			if(this.getPixel(iX-1,iY)=='.'){
				this.setPixel(iX-1,iY,c);
			}

			if(this.getPixel(iX,iY+1)=='.'){
				this.setPixel(iX,iY+1,c);
			}

			if(this.getPixel(iX,iY-1)=='.'){
				this.setPixel(iX,iY-1,c);
			}
		}
	}
			
}
