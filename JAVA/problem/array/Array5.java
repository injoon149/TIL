package JAVA.problem.array;

import java.util.Scanner;

public class Array5 {
    public static void main(String[] args) {
        System.out.println("5개의 정수를 입력하세요: ");
        Scanner scanner = new Scanner(System.in);
        int n;
        int[] array = new int[5];
        for(int i = 0; i<array.length; i++)
        {
            n = scanner.nextInt();
            array[i] = n;
        }
        System.out.println("입력한 정수를 역순으로 출력:");
        for(int i = array.length-1; i>0; i--)
        {
            System.out.print(array[i] +", ");
        }
        System.out.print(array[0]);
    }
}
