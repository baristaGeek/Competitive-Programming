import java.util.*;
import java.io.*;
import java.math.*;


class d {

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

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter (new BufferedWriter(new OutputStreamWriter(System.out)));
		String input = br.readLine();
		int testcases =  Integer.parseInt(input);
		for (int kz = 0; kz < testcases; kz++) {
			input = br.readLine();
			long nn = Long.parseLong(input);
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
			pr.println(mult);
		}
		pr.close();
	}

}
