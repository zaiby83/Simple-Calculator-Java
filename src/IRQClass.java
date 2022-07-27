package calculator;

import java.util.Arrays;


public class IRQClass {
    private static int median(int[] arr, int s, int e) {
        int n = e - s;
        int i = s + (n >> 1);
        if ((n & 1) == 0) { // even case

            return (arr[i] + arr[i-1]) >> 1;
        } else {
            return arr[i];
        }
     }
    
    public static int findIRQ(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;

        int m = n >> 1;
        int Q1 = median(arr, 0, m);
        int Q3 = median(arr, m + (n&1), n);

        return Q3 - Q1;
    }
}
