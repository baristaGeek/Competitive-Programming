import java.util.*;
import java.io.*;


 class Main{
	public static void main (String [] args){

		Scanner scanner = new Scanner (System.in);
		
		while (scanner.hasNextLine()){
			
			String kString = scanner.nextLine();
			if (!kString.equals("0")){
				int K = Integer.parseInt(kString);
				int divX = scanner.nextInt();
				int divY = scanner.nextInt();
				System.out.println("divX = "+divX+", divY = "+divY);
					int [] xCords = new int [K];
					int [] yCords = new int [K];
					for (int i=0; i<K; i++){
						xCords[i] = scanner.nextInt();
						yCords[i] = scanner.nextInt();
						System.out.println ("X = "+xCords[i]+", Y = "+yCords[i]);
						System.out.println(solve(divX, divY, xCords[i], yCords[i]));
					}
					kString  = scanner.nextLine();
			}else{
				System.exit(1);
			}
			
		}
		

}
	static String solve (int xDiv, int yDiv, int xCord, int yCord){
		String solution = "";
		
		if (xCord == xDiv || yCord == yDiv){
			solution = "divisa";
		}else if(xCord > xDiv && yCord > yDiv) {
			solution = "NE";
		}else if (xCord < xDiv && yCord > yDiv){
			solution = "NO";
		}else if (xCord < xDiv && yCord < yDiv){
			solution = "SO";
		}else{
			solution = "SE";
		}
		
		return solution;
	}
	
}
	
   