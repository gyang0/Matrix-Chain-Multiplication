public class MatrixChain {
    // returns minimum number of operations
    // https://en.wikipedia.org/wiki/Matrix_chain_multiplication
    public int findOptimal(int[][][] chain){
        int N = chain.length;
        
        int cost[][] = new int[N][N]; // cost[i][j] = cost to calculate subsequence A_i...A_j
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j) cost[i][j] = 0;
                else cost[i][j] = Integer.MAX_VALUE;
            }
        }

        int split[][] = new int[N][N]; // split[i][j] = where the split between A_i...A_j occurs
        for(int i = 0; i < N; i++){
            split[i][i] = i;
        }

        for(int l = 2; l <= N; l++){
            for(int i = 0; i < N - l + 1; i++){
                int j = i + l - 1;

                // Splitting within i...j
                for(int k = i; k < j; k++){
                    int cur = cost[i][k] + cost[k + 1][j] + (chain[i].length*chain[k][0].length*chain[j][0].length);
                    
                    if(cur < cost[i][j]){
                        cost[i][j] = cur;
                        split[i][j] = k;
                    }
                }
            }
        }

        //System.out.println("Number of operations when going in order: " + naiveCost(N));
        //System.out.println("Min number of operations: " + cost[0][N - 1]);
        //displaySeq(split, N);

        return cost[0][N - 1];
    }

    public int naiveCost(int[][][] chain){
        int N = chain.length;

        int res = 0;
        int p = chain[0].length;
        int q = chain[0][0].length;

        for(int i = 1; i < N; i++){
            res += p*q*chain[i][0].length;
            q = chain[i][0].length;
        }
        return res;
    }

    // Displays the parentheses
    public void displaySeq(int[][] split, int N){
        // pars[i][0] = parentheses before A_i
        // pars[i][1] = parentheses after A_i
        int pars[][] = new int[N][2]; 

        addSplit(split, pars, 0, N - 1);

        for(int i = 0; i < N; i++){
            System.out.print("(".repeat(pars[i][0]) + "A_" + i + ")".repeat(pars[i][1]));
        }
        System.out.println();
    }

    public void addSplit(int[][] split, int[][] pars, int i, int j){
        if(i == j) return;
        
        int where = split[i][j];
        pars[i][0]++;
        pars[where][1]++;
        pars[where + 1][0]++;
        pars[j][1]++;

        addSplit(split, pars, i, where);
        addSplit(split, pars, where + 1, j);
    }

    public void display(int[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
