package JAVA.problem.scanner;

import java.util.Scanner;

public class ScannerPro2 {
    public static void main(String[] args) {
        System.out.print("구구단의 단 수를 입력해주세요: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(num + "단의 구구단: ");
        for(int i = 1; i<=9; i++)
        {
            System.out.println(num + " x " + i + " = " + num*i);
        }
    }
}
