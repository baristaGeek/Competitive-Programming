import java.util.*;

class requestForProposal {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		while (scanner.hasNextLine()){
			String firstLine = scanner.nextLine();
			if (!firstLine.equals("0 0")){
				
			
			
			int nRequirements = scanner.nextInt();
			String [] requirements = new String [nRequirements];
			int nProposals = scanner.nextInt();
			
			double bestCompliance = 0;
			
			int RFP = 0;
			String answerName = "";
			
			for (int i=0; i<nRequirements; i++){
				String input = scanner.next();
				System.out.println(input);
				requirements[i] = input;
			}
			for (int i=0; i<nProposals; i++){
				String proposalName = scanner.next();
				float price = scanner.nextFloat();
				int metRequirements = scanner.nextInt();
			    double compliance = metRequirements/nRequirements;
				String [] metList = new String [metRequirements];
				for (int j=0; j<metRequirements; j++){
					metList[i] = scanner.next();
				}
				if (compliance > bestCompliance){
					bestCompliance = compliance;
					answerName = proposalName;
					RFP = i+1;
				}
			}
			System.out.println("RFP # " + RFP);
			System.out.println(answerName);
		}
		}

	}

}
