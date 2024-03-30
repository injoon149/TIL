package JAVA.problem.array;

import java.util.Scanner;

public class Array9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxProducts = 10;
        String[] productNames = new String[maxProducts];
        int[] productPrices = new int[maxProducts];
        int n;
        String product;
        int productPrice = 0;
        int productCount = 0;
        while(true)
        {
            System.out.println("1. 상품 등록 | 2. 상품 목록 | 3. 종료");
            System.out.print("메뉴를 선택하세요: ");
            n = scanner.nextInt();
            scanner.nextLine();
            if(n == 1)
            {
                if(productCount >= 10){
                    System.out.println("더 이상 상품을 등록할 수 없습니다.");
                }
                else {
                    System.out.print("상품 이름을 입력하세요:");
                    product = scanner.nextLine();
                    productNames[productCount] = product;
                    System.out.print("상품 가격을 입력하세요:");
                    productPrice = scanner.nextInt();
                    scanner.nextLine();
                    productPrices[productCount] = productPrice;
                    productCount++;
                }
            }
            else if(n == 2)
            {
                if(productCount == 0){
                    System.out.print("등록된 상품이 없습니다.");
                }
                else {
                    for(int i = 0; i<productCount; i++)
                    {
                        System.out.println(productNames[i] +": " + productPrices[i] +"원");
                    }
                }
            }
            else {
                System.out.print("프로그램을 종료합니다.");
                return;
            }


        }
    }
}
