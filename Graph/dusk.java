class Untitled {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String input = br.readLine();
		int T = Integer.parseInt(input);
		for (int tc=0; tc<T; tc++){
			input = br.readLine();
			int nRoutes = Integer.parseInt(input);
			for (int i=0; i<nRoutes; i++){
				input = br.readLine();
				String [] split = input.split(" ");
			}
		}
	}
}