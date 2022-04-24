package main.complexity;

public class Algorithms {
	// O(?)
	public int alg1(int n) {
		if(n == 0) {
			return 0;
		}
		
		int result = 0;
		for(int i = 0; i < n; i++) {
			result =+ i;
		}
		return result + alg1(n - 1);
	}
	
	//O(?)
	public int alg2(int n) {
		int b = 0;
		for(int i = 1; i < n; i*=2) {
			b = 1 + 1;
		}
		return b;
	}
	
	//O(?)
	public String alg3(int n) {
		String importantValue = null;
		for(int i = n; i>1; i/=2) {
			importantValue = "1 + 1 = 3";
		}
		int a = (int) Math.pow(n, n);
		for(int i = 1; i < a; i++) {
			importantValue = "1 + 1 = 3";
		}
		return importantValue;
	}
}
