import java.util.*;

public class StoreTracker {

    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        int confluxNo = src.nextInt();
        int streetNo = src.nextInt();
        int houseConflux = src.nextInt();
        int storeNo = src.nextInt();
        src.nextLine();
        
        int[] storeConflux = new int[storeNo];
        for (int i = 0; i < storeNo; i++) {
            storeConflux[i] = src.nextInt();
        }
        src.nextLine();

        long[][] pathTime = new long[confluxNo][confluxNo];
        for (long[] temp : pathTime) {
            Arrays.fill(temp, Long.valueOf(Integer.MAX_VALUE));
        }
        for (int i = 0; i < streetNo; i++) {
            int beginning = src.nextInt();
            int destination = src.nextInt();
            pathTime[beginning - 1][destination - 1] = src.nextLong();
            src.nextLine();
        }
        for (int i = 0; i < confluxNo; i++) {
            pathTime[i][i] = 0;
        }
		src.close();

        for (int k = 0; k < confluxNo; k++) {
            for (int i = 0; i < confluxNo; i++) {
                for (int j = 0; j < confluxNo; j++) {
                    pathTime[i][j] = Math.min(pathTime[i][j], pathTime[i][k] + pathTime[k][j]);
                }
            }
        }
    
        long[] storePathTime = new long[storeNo];
        for (int i = 0; i < storeNo; i++) {
            storePathTime[i] = pathTime[houseConflux - 1][storeConflux[i] - 1] 
                               + pathTime[storeConflux[i] - 1][houseConflux - 1]; 
        }
        Arrays.sort(storePathTime);
        System.out.println(storePathTime[0] < Long.valueOf(Integer.MAX_VALUE) ? storePathTime[0] : "IMPOSSIBLE");
    }
}