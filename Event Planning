import java.util.*;

class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		while (scanner.hasNextLine()){
			
		
		
		int N = scanner.nextInt();
		int B = scanner.nextInt();
		int H = scanner.nextInt();
		int W = scanner.nextInt();
		int [] priceArray = new int [H];
		int [] availableBeds = new int [W];
		int cheapestStay = 999999999;
		for (int i=0; i<H; i++){
			priceArray[i] = scanner.nextInt();
			
			for (int j=0; j<W; j++){
				availableBeds[j] = scanner.nextInt();
				if (availableBeds[j] >= N && priceArray[i]*N <= B && priceArray[i]*N < cheapestStay){
					cheapestStay = priceArray[i]*N;
				}

			}

		}
		
		if (cheapestStay != 999999999){
			System.out.println(cheapestStay);
		}else{
			System.out.println("stay home");
		}
		
	}
	}

}
