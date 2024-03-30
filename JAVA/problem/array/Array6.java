package JAVA.problem.array;

import java.util.Scanner;

public class Array6 {
    public static void main(String[] args) {
        System.out.println("5개의 정수를 입력하세요: ");
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[5];
        int sum = 0;
        for(int i = 0; i<array.length; i++)
        {
            array[i] = scanner.nextInt();
            sum += array[i];
        }
        System.out.println("입력한 정수의 합계: " + sum);
        System.out.println("입력한 정수의 평균: " + (double)sum / array.length);

    }
}
