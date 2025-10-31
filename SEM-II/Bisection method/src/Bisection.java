public class Bisection {
    
    static double bisection(double a, double b){
        double m = (a+b)/2;

        if (Math.abs(a-b)<0.001) return Math.round(m*10000.0)/10000.0;       
       
        if ( (function(a)*function(m)) > 0 ) return bisection(m, b);
        
        else return bisection(a, m);
    }

    static double function (double n){
        return Math.pow(n, 3) - n - 2 ;
    }

    public static void main(String[] args) {
        System.out.println(bisection(1.0, 2.0));
    }
}
