import java.util.ArrayList;
import java.util.Scanner;

public class Linkedlist {
    public static void printlist(ArrayList<int[]> list){
        for (int i=0;i<list.size();i++){
            int[] currentArray = list.get(i);
            System.out.println(currentArray[0] + "," + currentArray[1] + "," + currentArray[2]);
        }
    }
    public  static void sortlist (ArrayList<int []> list, int [] nums_to_enter){
        list.get(0)[1] = nums_to_enter[0];
        for (int i=1;i<nums_to_enter.length;i++){

            int num = nums_to_enter[i];
            int value_to_read = 0;
            while (true) {
                if (num<list.get(value_to_read)[1]){
                        if (list.get(value_to_read)[0] == -1) {
                            list.add(new int[]{-1, num, -1});
                            list.get(value_to_read)[0] = list.size()-1;
                            break;
                        } else{
                            value_to_read = list.get(value_to_read)[0];
                        }
                } 
                else {
                        if (list.get(value_to_read)[2] == -1) {
                            list.add(new int[]{-1, num, -1});
                            list.get(value_to_read)[2] = list.size()-1;
                            break;
                        } else{
                            value_to_read = list.get(value_to_read)[2];
                        }
                }
           }
        }
    }
    public static int [] creatlist(int n,Scanner scanner){
        int [] newlist = new int[n];
        
        for (int i=0;i<n;i++){
            System.out.println("please enter number" + (i + 1) + ": ");
            newlist[i] = scanner.nextInt();
        }
        return newlist;
    }
    public static void insert(ArrayList<int[]>list,int num){
        int value_to_read = 0;
        while (true) {
            if (num == list.get(0)[1]){
            System.out.println(num +" alread is presnt in list at index:" + value_to_read);
            break;
            }
            if (num<list.get(value_to_read)[1]){
                    if (list.get(value_to_read)[0] == -1) {
                        list.add(new int[]{-1, num, -1});
                        list.get(value_to_read)[0] = list.size() -1;
                        break;
                    } else{
                        value_to_read = list.get(value_to_read)[0];
                    }
            } 
            else {
                    if (list.get(value_to_read)[2] == -1) {
                        list.add(new int[]{-1, num, -1});
                        list.get(value_to_read)[2] = list.size() -1;
                        break;
                    } else{
                        value_to_read = list.get(value_to_read)[2];
                    }
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        list.add(new int[]{-1, 0, -1});
        System.out.println("please input Linkedlist size :");
        int n = scanner.nextInt();
        int [] newlist =creatlist(n, scanner);
        sortlist(list, newlist);
        printlist(list); 
        System.out.println("please input new value to add :");
        n = scanner.nextInt();
        insert(list, n);
        printlist(list);
        scanner.close();
    }
}
