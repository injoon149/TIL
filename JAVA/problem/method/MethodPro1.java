package JAVA.problem.method;

public class MethodPro1 {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        System.out.println("평균값: " + average(a,b,c));

        int x = 15, y = 25, z =35;
        System.out.println("평균값: " + average(x,y,z));
    }

    public static double average(int a, int b, int c) {
        int sum = a + b + c;
        return sum / 3.0;
    }
}
