import java.util.ArrayList;

public class Bubble_Sort {

    public static void sort(ArrayList<int[]>list){

        for (int i = 0; i< list.size()-1;i++){
            for (int j = 0;j<list.size()-i-1;j++){
                if (list.get(j)[0]>list.get(j+1)[0]) {
                    int temp = list.get(j+1)[0];
                    list.get(j+1)[0] = list.get(j)[0];
                    list.get(j)[0] = temp;
                }
            }
        }

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
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i)[0]);
        }
    }
}
