import java.util.*;
import java.io.*;
import java.math.*;

public class counting{
	    private final static BigInteger ZERO = new BigInteger("0");
	    private final static BigInteger ONE  = new BigInteger("1");
	    private final static BigInteger TWO  = new BigInteger("2");
	    private final static Random random = new Random();
		public static ArrayList<Long> factors;
		
		    public static BigInteger rho(BigInteger N) {
		        BigInteger divisor;
		        BigInteger c  = new BigInteger(N.bitLength(), random);
		        BigInteger x  = new BigInteger(N.bitLength(), random);
		        BigInteger xx = x;

		        // check divisibility by 2
		        if (N.mod(TWO).compareTo(ZERO) == 0) return TWO;

		        do {
		            x  =  x.multiply(x).mod(N).add(c).mod(N);
		            xx = xx.multiply(xx).mod(N).add(c).mod(N);
		            xx = xx.multiply(xx).mod(N).add(c).mod(N);
		            divisor = x.subtract(xx).gcd(N);
		        } while((divisor.compareTo(ONE)) == 0);

		        return divisor;
		    }
		
		    public static void factor(BigInteger N) {
		        if (N.compareTo(ONE) == 0) return;
		        if (N.isProbablePrime(20)) { factors.add(new Long(N.toString())); return; }
		        BigInteger divisor = rho(N);
		        factor(divisor);
		        factor(N.divide(divisor));
		    }
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String input = br.readLine();
		int T = Integer.parseInt(input);
		for (int t=0; t<T; t++){
			input = br.readLine();
			String [] split = input.split(" ");
			int n = Integer.parseInt(split[0]);
			int k = Integer.parseInt(split[1]);
			
		 	long [] arcs = new long [n+1]; 
			arcs[1] = 0; //node 1 has 0 arcs going outward. Becaureful with INDEXATION
			
			//int ans = 0;
			int possibilities = 1;
			int exceedsDivisorsCounter = 1;
			
			for (int i=2; i<=n; i++){
				//get arcos(n-1, k)
				long divisors = getDivisors(Integer.toString(i));
				if (k>divisors){
					arcs[i] = arcs[i-1]+divisors;
					//System.out.println("arcs["+i+"]: " + arcs[i]);
					//ans += arcs[i];
				}else{
					arcs[i]= arcs[i-1] + k;
					//System.out.println("arcs["+i+"]: " + arcs[i]);
					//ans += arcs[i];
				}
				//ans = arcs[n];
			}
			pr.println(arcs[n] + " " + possibilities);
		}
		pr.close();
	}

	static long getDivisors(String input){
		BigInteger n = new BigInteger(input);
					factors = new ArrayList<Long>();
					factor(n);
					Collections.sort(factors);
					//System.out.println(factors.toString());
					Iterator<Long> it = factors.iterator();
					long mult = 1;
					long sum = 1;
					Long last = it.next();
					sum++;
					while (it.hasNext()) {
						Long curr = it.next();
						if (curr.equals(last)) {
							sum ++;
						} else {
							mult *= sum;
							//System.out.println("ddd"+sum);
							last = curr;
							sum = 2;
						}
					}
					
					mult *= sum;
					//System.out.println("ddd"+sum);
					//System.out.println("mult: "+mult);
					mult -= 1;
					//System.out.println(nn);
					return mult;
	}
}