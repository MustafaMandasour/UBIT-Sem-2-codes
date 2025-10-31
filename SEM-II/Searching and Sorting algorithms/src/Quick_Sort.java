import java.util.ArrayList;
public class Quick_Sort {

    public static void sort(ArrayList<int[]> list,int low,int high){
        if (low < high) {
            int pivotIndex = sperate(list, low, high);
            sort(list, low, pivotIndex - 1);
            sort(list, pivotIndex + 1, high);
        }
    }

    public static int sperate(ArrayList<int[]> list,int low,int high){
        int i = low -1;
        for (int j = low ;j <high;j++){
            if (list.get(j)[0] < list.get(high)[0]){
                i++;
                int temp = list.get(i)[0];
                list.get(i)[0] = list.get(j)[0];
                list.get(j)[0] = temp;
            }
        }
        int temp = list.get(i+1)[0];
        list.get(i+1)[0] = list.get(high)[0];
        list.get(high)[0] = temp; 
        return i+1;
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
        sort(list, 0, list.size()-1);

        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i)[0]);
        }
    }
}
