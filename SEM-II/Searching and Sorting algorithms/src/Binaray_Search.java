import java.util.ArrayList;
import java.util.Scanner;

public class Binaray_Search extends Bubble_Sort{

    public static int search(ArrayList<int[]> list,int n){

        if (n <0 ){
            System.out.println("pls enter apositive integer ");
            return -1;
        }
        else{
            int mid = (list.size()-1)/2;
            if (n == list.get(mid)[0]){
                return mid;
            }
            else if (n < list.get(mid)[0]) {
                for (int i = 0;i<mid;i++){
                    if (n == list.get(i)[0]) {
                        return i ;
                    }
                }
            } else {
                for (int i = mid + 1 ; i < list.size() -1;i++){
                    if (n == list.get(i)[0]) {
                        return i ;
                    }
                }
                
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[] {8});
        list.add(new int[] {2});
        list.add(new int[] {7});
        list.add(new int[] {4});
        list.add(new int[] {5});
        list.add(new int[] {6});
        list.add(new int[] {3});
        list.add(new int[] {9});
        list.add(new int[] {10});
        list.add(new int[] {1});
        sort(list);
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter number to search ");
        int n = scanner.nextInt();
        if(search(list, n) != -1 ){
            System.out.println("your number is located at index: " + n);
        }
        
        scanner.close();
        
    }

}
