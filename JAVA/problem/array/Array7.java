package JAVA.problem.array;

import java.util.Scanner;

public class Array7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.print("입력받을 숫자의 개수를 입력하세요: ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println(n + "개의 정수를 입력하세요: ");
        for(int i = 0; i<arr.length; i++)
        {
            arr[i] = scanner.nextInt();
        }
        int minNum = arr[0];
        int maxNum = arr[0];
        for(int i = 0; i<arr.length; i++)
        {
            if(arr[i] > maxNum)
            {
                maxNum = arr[i];
            }
            else if(arr[i] < minNum)
            {
                minNum = arr[i];
            }
        }
        System.out.println("가장 작은 정수: " + minNum);
        System.out.print("가장 큰 정수: " + maxNum);

    }
}
