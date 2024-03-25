package JAVA.problem.scanner;

import java.util.Scanner;

public class ScannerPro3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("숫자를 입력하세요. 입력을 중단하려면 -1을 입력하세요:");
        double sum = 0.0;
        int average = 0;
        while(true)
        {
            int num = input.nextInt();
            if(num == -1) break;
            sum += num;
            average++;
        }
        System.out.println("입력한 숫자들의 합계: " + (int)sum);
        System.out.println("입력한 숫자들의 평균: " + sum / average);
    }
}
