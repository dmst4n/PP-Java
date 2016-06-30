import java.util.Scanner;
import java.util.ArrayList;


public class AsciiShop{
	public static void main(String[] args){
		Scanner scanFill = new Scanner(System.in);
		boolean bErr = false;
		boolean bErrOp = false;
		boolean bUnique = false;
		boolean bLoad = false;
		String sTemp = "";
		String sEnd = "";
		int iHeight = 0;
		int iTemp = 0;
		int iWidth = 0;
		int iPosX = 0;
		int iPosY = 0;
		int iPosX2 = 0;
		int iPosY2 = 0;
		char cSym1 = 'Q';
		char cSym2 = 'X';
		AsciiStack asStack = new AsciiStack(3);

		if(scanFill.hasNext("create")){
			sTemp = scanFill.next();
			
			if(scanFill.hasNextInt()){
				iWidth = scanFill.nextInt();

				if(scanFill.hasNextInt()){
					iHeight = scanFill.nextInt();
				}else{
					bErr = true;
				}
			}else{
				bErr = true;
			}
		}else{
			bErr = true;
		}


		AsciiImage aiImage = new AsciiImage(iWidth,iHeight);


		if(scanFill.hasNext("load")){
			sTemp = scanFill.next();
			sEnd = scanFill.nextLine();
			sEnd = sEnd.substring(1);
			bLoad = true;

			for(int i = 0; i < aiImage.getHeight(); i++){
				if(!scanFill.hasNextLine()||bErr){
					bErr = true;
					break;
				}else{
					sTemp = scanFill.nextLine();
					for(int j = 0; j < aiImage.getWidth(); j++){
						aiImage.setPixel(j,i,sTemp.charAt(j));					
					}
				}
			}	
			
			asStack.push(aiImage);
		
			if(scanFill.hasNext(sEnd)){
				sTemp = scanFill.nextLine();
			}else{
				bErr=true;
			}
		}		
		
		while(scanFill.hasNext()){
			if(bErr){
				break;

			/*}else if(scanFill.hasNext("fill")){
				sTemp = scanFill.next();
				
				if(scanFill.hasNextInt()){
					iPosX = scanFill.nextInt();

					if(iPosX > iWidth || iPosX < 0){
						bErrOp = true;
						break;
					}

					if(scanFill.hasNextInt()){
						iPosY = scanFill.nextInt();

						if(iPosY > iHeight || iPosY < 0){
						bErrOp = true;
						break;
						}

						if(scanFill.hasNext()){
							sTemp = scanFill.next();
							cSym = sTemp.charAt(0);
							aiImage.fill(iPosX,iPosY,cSym);

						}else{
							bErr = true;
							break;
						}
							
					}else{
						bErr = true;
						break;
					}
				}else{
					bErr = true;
					break;
				}

			*/}else if(scanFill.hasNext("transpose")){
				sTemp = scanFill.next();
				aiImage.transpose();
				asStack.push(aiImage);

			/*}else if(scanFill.hasNext("uniqueChars")){
				sTemp = scanFill.next();
				System.out.println("Unique Characters: " + aiImage.getUniqueChars());

			}else if(scanFill.hasNext("flip-v")){
				sTemp = scanFill.next();
				aiImage.flipV();

			*/}else if(scanFill.hasNext("print")){
				sTemp = scanFill.next();
				System.out.println(asStack.peek().toString());

			}else if(scanFill.hasNext("clear")){
				sTemp = scanFill.next();
				aiImage.clear();
				asStack.push(aiImage);

			}else if(scanFill.hasNext("centroid")){
				sTemp = scanFill.next();
				if(scanFill.hasNext()){
					cSym1 = scanFill.nextLine().charAt(1);
					System.out.println(aiImage.getCenteroid(cSym1).toString());			
				}else{
					bErr = true;
					break;
				}	
									
			}else if(scanFill.hasNext("replace")){
				sTemp = scanFill.next();
				if(scanFill.hasNext()){
					cSym1 = scanFill.next().charAt(0);

					if(scanFill.hasNext()){
						cSym2 = scanFill.next().charAt(0);
						aiImage.replace(cSym1,cSym2);
					}else{
						bErr = true;
						break;
					}
				}else{
					bErr = true;
					break;
				}
				asStack.push(aiImage);

			}else if(scanFill.hasNext("line")){
				sTemp = scanFill.next();
				int x0 = scanFill.nextInt();
				int y0 = scanFill.nextInt();
				int x1 = scanFill.nextInt();
				int y1 = scanFill.nextInt();
				char c = scanFill.next().charAt(0);
				aiImage.drawLine(x0, y0, x1, y1, c);
				asStack.push(aiImage);

			}else if(scanFill.hasNext("undo")){
				sTemp = scanFill.next();
				aiImage = asStack.pop();

			}else if(scanFill.hasNext("grow")){
				sTemp = scanFill.next();
				cSym1 = scanFill.next().charAt(0);
				aiImage.growRegion(cSym1);
				asStack.push(aiImage);

			}else if(scanFill.hasNext("show")){
				sTemp = scanFill.next();
				asStack.showAll();

			}else{	
				bErr = true;			
				break;
			}
		}


		if(bErr){
			System.out.println("INPUT MISMATCH");
		}else if(bErrOp){
			System.out.println("OPERATION FAILURE");
		}			
	}
}
		
