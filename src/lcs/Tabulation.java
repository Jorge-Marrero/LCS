package lcs;

public class Tabulation {
    public static String tabulation(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] matrix = new int[l1 + 1][l2 + 1];
        
        fillTableLcs(s1, s2, l1, l2, matrix);
        return lcs(s1, s2, l1, l2, matrix);
    }

    private static String lcs(String s1, String s2, int l1, int l2, int[][] matrix) {
        if (l1 == 0 || l2 == 0) {
            return new String();
        }
        if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)){
            return lcs(s1, s2, l1 - 1, l2 - 1, matrix) + s1.charAt(l1 - 1);
        }
        if (matrix[l1 - 1][l2] > matrix[l1][l2 - 1]) {
            return lcs(s1, s2, l1 - 1, l2, matrix);
        } else {
            return lcs(s1, s2, l1, l2 - 1, matrix);
        }
    }
    
    private static void fillTableLcs(String s1, String s2, int m, int n, int[][] T) {
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    T[i][j] = T[i - 1][j - 1] + 1;
                }
                else {
                    T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
                }
            }
        }
    }
}
