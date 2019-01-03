package mainprocessing;

import abstractDataTypes.CrimeADT;

public class Heap {

	public static void sortHeap (CrimeADT[] x, int n) {
		
		for (int i = n / 2; i >= 1; i--)
			sink(x, i, n);
		
		while (n > 1){
			swap(x, 1, n--);
			sink(x, 1, n);
		}
	}
	
    private static void sink(CrimeADT[] x, int i, int n) {
    	
        while (2 * i <= n) {
            int j = 2 * i;
            
            if (j < n && (x[j-1].getBeat() < x[j].getBeat() )) 
            	j++;
            
            if (x[i-1].getBeat() >= x[j-1].getBeat()) 
            	break;
            
            swap(x, i, j);
            i = j;
        }
    }
 
	private static void swap(Object[] obj, int index1, int index2) {
		
		Object temp = obj[index1-1];
		obj[index1-1] = obj[index2-1];
		obj[index2-1] = temp;
	}
}