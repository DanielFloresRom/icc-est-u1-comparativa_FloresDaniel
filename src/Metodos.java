import java.util.Arrays;
import java.util.Comparator;

public class Metodos {


    public void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int aux = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = aux;
                    swap = true;
                }
            }
            if (!swap) {
                return;
            }
        }
    }

    public void sortMerge(int[] arr) {
        int n = arr.length;
        if (n < 2) return;
        int[] tmp = new int[n];
        mergeSort(arr, tmp, 0, n - 1);
    }

    private void mergeSort(int[] arr, int[] tmp, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, tmp, lo, mid);
        mergeSort(arr, tmp, mid + 1, hi);
        merge(arr, tmp, lo, mid, hi);
    }

    private void merge(int[] arr, int[] tmp, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            tmp[k] = arr[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = tmp[j++];
            } else if (j > hi) {
                arr[k] = tmp[i++];
            } else if (tmp[j] < tmp[i]) {
                arr[k] = tmp[j++];
            } else {
                arr[k] = tmp[i++];
            }
        }
    }

    public void sortLibrary(int[] arr) {
        Arrays.sort(arr);
    }

    public void sortLibraryComparator(int[] arr) {
        Comparator<Integer> cmp = (x, y) -> Integer.compare(x, y);

        Integer[] boxed = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxed[i] = arr[i];
        }

        Arrays.sort(boxed, cmp);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = boxed[i];
        }
    }
}