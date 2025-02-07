
import java.util.*;

public class Solution_660194 {
    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        Solution_660194 solver = new Solution_660194();
        
        int[][] resultHeap = solver.kClosest(points, k);
        System.out.println("Max Heap:");
        printPoints(resultHeap);

        System.out.println("--------------------------");
        int[][] resultQuickSelect = solver.partition_caller(points, k);
        System.out.println("Quick Select:");
        printPoints(resultQuickSelect);
    }
    private static void printPoints(int[][] points) {
        for (int[] point : points) {
            System.out.println(Arrays.toString(point));
        }
    }
//1.1
    public int[][] kClosest(int[][] points, int k) {
        return pq(points, k);
    }

    private int[][] pq(int[][] points, int K) {
        int[][] ans = new int[K][2];
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        //my codeeee
        for (int[] point : points) {
            heap.add(point);
            if (heap.size() > K) {
                heap.poll();
            }
        }
        for (int i = 0; i < K; i++) {
            ans[i] = heap.poll();
        }
        return ans;
    }
//2.1
    private int[][] partition_caller(int[][] points, int K) {
        int left = 0, right = points.length - 1;
        while (left <= right) {
            int pivot = partition(points, left, right);
            if (pivot < K)
                left = pivot + 1;
            else if (K < pivot)
                right = pivot - 1;
            else
                break;
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    //mycode:)
    private int partition(int[][] points, int left, int right) {
        int[] pivot = points[right];
        int i = left; 
        for (int j = left; j < right; j++) {
            if (compare(points[j], pivot) <= 0) {
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
                i++;
            }
        }
        int[] temp = points[i];
        points[i] = points[right];
        points[right] = temp;
        return i;
    }

    private int compare(int[] p1, int[] p2) {
        return (p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
    }
}


