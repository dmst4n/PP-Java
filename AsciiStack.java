public class AsciiStack{
	private AsciiImage[] aiArray;
	private int increment = 0;

	public AsciiStack(int increment){
		this.increment = increment;
		aiArray = new AsciiImage[increment];
	}

	public boolean empty(){
		if(this.size()==0){
			return true;
		}else{
			return false;
		}
	}

	public int capacity(){
		return aiArray.length;
	}

	public AsciiImage pop(){
		if(this.empty()){
			return null;
		}

		AsciiImage aiTemp = this.peek();
		aiArray[this.size()-1] = null;

		if((this.size() -1) < this.capacity() - increment && (this.capacity() - increment) > 0){
			AsciiImage[] aiTempArray = new AsciiImage[this.size()-increment];
			System.arraycopy(aiArray,0,aiTempArray,0,aiTempArray.length);
			aiArray = aiTempArray;
		}

		return aiTemp;
			
	}

	public AsciiImage peek(){
		if(this.empty()){
			System.out.println("Stack is empty");
			return null;
		}else{
			return aiArray[this.size()-1];
		}			
	}

	public int size(){
		int n = 0;
		for(int i = 0; i < this.capacity(); i++){
			if(aiArray[i]!=null){
				n++;
			}else{
				break;
			}
		}
		return n;
	}

	public void push(AsciiImage img){
		if(this.size()>=this.capacity()){
			AsciiImage[] aiTempArray = new AsciiImage[this.size()+increment];
			System.arraycopy(aiArray,0,aiTempArray,0,aiArray.length);
			aiArray = aiTempArray;
		}

		aiArray[this.size()]=img;
		System.out.println(img.toString());
	}

	public void showAll(){
		for(int i = 0; i < this.size(); i++){
			System.out.println(aiArray[i].toString());
		}
	}
}
