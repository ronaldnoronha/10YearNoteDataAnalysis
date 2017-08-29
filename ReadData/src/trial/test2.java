package trial;

public class test2 {
	public static void main(String[] args){
		int[] A = {1 ,1, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		int K = 8;
		System.out.println(solution(A,K));
	}
	public static int solution(int[] A, int K) {
        int n = A.length;
        int best = 0;
        int count = 0;
        for (int i = 0; i < n - K-1; i++) {
        	//System.out.println(i);
            if (A[i] == A[i + 1])
                count = count + 1;
            else
                count = 0;
            if (count > best)
                best = count;
        }
        int result = best + 1 + K;
    
        return result;
    }
}
