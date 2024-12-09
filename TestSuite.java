public class TestSuite {
    // Minimum & maximum dimensions
    public static final int MIN_DIM = 1;
    public static final int MAX_DIM = 100;

    public static final int NUM_TESTS = 1000;

    public static void main(String[] args){
        MatrixChain m = new MatrixChain();

        for(int i = 5; i <= 100; i += 5){
            getRes(m, i);
        }
    }

    // Result for a particular subsequence length
    public static void getRes(MatrixChain m, int len){
        int[][][] chain = generate(MIN_DIM, MAX_DIM, len);

        // Subsequence length | naive op. | min op.
        double naive_avgOp = 0.0;
        double best_avgOp = 0.0;
        for(int i = 0; i < NUM_TESTS; i++){
            naive_avgOp += m.naiveCost(chain);
            best_avgOp += m.findOptimal(chain);
        }

        naive_avgOp /= NUM_TESTS;
        best_avgOp /= NUM_TESTS;

        System.out.println("Seq. len = " + len + "\n\tNaive: " + naive_avgOp + "\n\tBest: " + best_avgOp);
    }

    public static int[][][] generate(int minDim, int maxDim, int len){
        int[][][] res = new int[len][][];
        
        for(int i = 0; i < len; i++){
            int w = rand(minDim, maxDim);
            int h = rand(minDim, maxDim);
            int[][] res2 = new int[w][h];
            for(int j = 0; j < w; j++){
                for(int k = 0; k < h; k++){
                    res2[j][k] = rand(1, 9);
                }
            }
            
            res[i] = res2;
        }
        return res;
    }

    public static int rand(int mn, int mx){
        return (int)(Math.random() * (mx - mn)) + mn;
    }
}
