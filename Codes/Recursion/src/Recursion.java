public class Recursion {
    
    static int Fibonacci(int n){
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    static int GCD(int n,int m){
        if(m == 0) return n;
        else return GCD(m,n%m);
    }

    static int maxArray(int [] arr, int n ){
        if (n == 1) return arr[0];
        int Max = maxArray(arr, n-1);
        if (arr[n-1]>Max){
            return arr[n-1];
        } else return Max;
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(10));
        System.out.println(GCD(36, 27));
        int [] arr = {5,41,56,8,79,15,32,65,48,59,62};
        int lenght = arr.length;
        System.out.println(maxArray(arr, lenght));
    }
}
