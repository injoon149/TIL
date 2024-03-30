package JAVA.problem.array;

import java.util.Scanner;

public class Array8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] studentScore = new int[4][3];
        int sum = 0;
        double avg = 0.0;
        for(int i = 0; i<4; i++)
        {
            System.out.println((i+1) +"번 학생의 성적을 입력하세요: ");
            System.out.print("국어 점수:");
            studentScore[i][0] = scanner.nextInt();
            System.out.print("영어 점수:");
            studentScore[i][1] = scanner.nextInt();
            System.out.print("수학 점수:");
            studentScore[i][2] = scanner.nextInt();
        }
        for(int i = 1; i<=4; i++)
        {
            for(int j = 0; j<studentScore[0].length; j++)
            {
                sum += studentScore[i-1][j];
            }
            avg = (double) sum/3.0;
            System.out.println(i +"번 학생의 총점: " + sum+", 평균: " + avg);
            sum = 0;
        }

    }
}
