package lcs;


public class Memoization {
    public static String memoization(String s1, String s2){
        String res = "";
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] matrix = new int [l1+1][l2+1];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = -1;
            }
        }
        
        int res_len = aux(l1, l2, s1, s2, matrix);
        
        int i = l1;
        int k = l2;
        while(i > 0 && k > 0){
            if(matrix[i][k] != 0 && matrix[i][k] != matrix[i-1][k]){
                res = s1.charAt(i-1) + res;
                k = k - 1;
            }
            i = i - 1;
        }
        return res;
    }

    private static int aux(int l1, int l2, String s1, String s2, int[][] matrix) {
        if(l1 == 0 || l2 == 0){
            return 0;
        }
        if (matrix[l1][l2] == -1){
            if(s1.charAt(l1-1) == s2.charAt(l2-1)){
                matrix[l1][l2] = aux(l1-1, l2-1, s1, s2, matrix) + 1;
            }else{
                matrix[l1][l2] = Math.max(aux(l1, l2-1, s1, s2, matrix), aux(l1-1, l2, s1, s2, matrix));
            }
        }
        return matrix[l1][l2];
    }
}
