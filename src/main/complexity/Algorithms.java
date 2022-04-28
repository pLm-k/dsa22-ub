package main.complexity;

public class Algorithms {
	// O(n^2)
	public int alg1(int n) {
		if(n == 0) {
			return 0;
		}
		
		int result = 0;
		for(int i = 0; i < n; i++) {//O(n)
			result =+ i;
		}
		return result + alg1(n - 1);
	}
	
	//O(log(n))
	public int alg2(int n) {
		int b = 0;
		for(int i = 1; i < n; i*=2) {
			b = 1 + 1;
		}
		return b;
	}
	
	//O(n^n)
	public String alg3(int n) {
		String importantValue = null;
		for(int i = n; i>1; i/=2) { //O(log(n))
			importantValue = "1 + 1 = 3";
		}
		int a = (int) Math.pow(n, n);
		for(int i = 1; i < a; i++) {//O(a), O(n^n)
			importantValue = "1 + 1 = 3";
		}
		return importantValue;
	}
	
	//O(1)
	public int alg4(int n) {
		int result = 1;
		int a = (int) Math.pow(23579, result);
		for(int i = 1; i < a; i*=2) {
			result++;
		}
		return result;
	}
}
