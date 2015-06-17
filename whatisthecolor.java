import java.util.*;
public class whatisthecolor {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		int T = scanner.nextInt();
		
		for (int i=0; i<T; i++){
			ArrayList<String> cards = new ArrayList<String>();
			for (int j=0; j<52; j++){
				cards.add(scanner.next());
			}
			System.out.println(solve(cards));
		}
	}
	static String solve(ArrayList<String>cards){
		String result = "";
		ArrayList<String>pileOf25 = new ArrayList<String>();
		for (int i=0; i<25; i++){
			pileOf25.add(cards.get(i));
			cards.remove(i);
		}
		return result;
	}
	static int getCardValue(String card){
		int value = 0;
		String regularValue = card.substring(0,1);
		if (regularValue.equals("10") || regularValue.equals("J") || regularValue.equals("Q") || regularValue.equals("K") || regularValue.equals("A") || regularValue.equals("T")){
			value = 10;
		}else{
			value = Integer.parseInt(regularValue);
		}
		return value;
	}
}
