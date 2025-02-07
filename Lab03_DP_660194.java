public class Lab03_DP_660194 {
    static int minOperationsMatrixChain(int[] p, int n) {
          int[][] cost = new int[n][n];

          for (int i = 0; i < n; i++) {
                cost[i][i] = 0;
          }

          for (int L = 2; L < n; L++) {
                for (int i = 1; i < n - L + 1; i++) {
                      int j = i + L - 1;
                      cost[i][j] = Integer.MAX_VALUE;

                      for (int k = i; k <= j - 1; k++) {
                            int currentCost = cost[i][k] + cost[k + 1][j] + p[i - 1] * p[k] * p[j];
                            cost[i][j] = Math.min(cost[i][j], currentCost);
                      }
                }
                System.out.println("cache content = ");
                for (int r = 1; r < n; r++) {
                for (int c = 1; c < n; c++)
                System.out.print(cost[r][c] + "\t");
                System.out.println();
                }
          }
          System.out.println("------------------------------");
          return cost[1][n - 1];
    }

    static void sub1() {
          int[] P = { 4, 10, 3, 12, 20 };
          System.out.println(minOperationsMatrixChain(P, P.length));
    }
      public static void main(String[] args) {
          sub1();
    }
}