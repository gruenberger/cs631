package homework4;



public class Polynomial {
	
	private Node head;
	
	private class Node{
		private int coef;
		private int power;
		private Node next= null;
		
		public Node(int coef, int power){
			this.coef = coef;
			this.power = power;
		}
		
		public int getCoef(){
			return coef;
		}
		
		public int getPower(){
			return power;
		}
		
		public void setCoef(int coef){
			this.coef = coef;
		}
	}
	
	public Polynomial(){
		head = new Node(0,0);
	}
	
	public int getCoefficient(int power){
		Node temp = head;
		
		while(temp.next != null){
			if(temp.getPower() == power){
				return temp.getCoef();
			}else{
				temp = temp.next;
			}
		}
		
		return 0;
	}
	
	public void setCoefficient(int coef, int power){
		Node temp = head;
		
		while(temp.next != null){
			if(temp.getPower() == power){
				temp.setCoef(coef);
			}else{
				temp = temp.next;
			}
		}
	}
	
	public double evaluate(double x){
		double total =0;
		Node temp = head;
		
		while(temp.next != null){
			total+= ((Math.pow(x, temp.getPower()))*temp.getCoef());
		}
		
		return total;
	}
	
	public Polynomial add(Polynomial other){
		Node tempThis = head;
		Node tempThat = other.head;
		Polynomial newPoly = new Polynomial();
		int thisDeg = 0, thatDeg = 0;
		
		while(tempThis.next != null){
			tempThis = tempThis.next;
			thisDeg++;
		}
		
		while(tempThat.next != null){
			tempThat = tempThat.next;
			thatDeg++;
		}
		
		while(thisDeg > thatDeg){
			//To be finished
		}
		
		return newPoly;
	}
	
	public String toString(){
		Node temp = head;
		String polyString="";
		
		while(temp != null){
			if(temp.getPower()!= 0)
				polyString+=(temp.getCoef()+"x^"+temp.getPower()+" ");
			
			if(temp.getPower()==0)
				polyString+=(temp.getCoef()+"x ");
			
			if(temp.next != null){
				polyString+="+";
			}
			temp = temp.next;
		}
		
		return polyString;
		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
