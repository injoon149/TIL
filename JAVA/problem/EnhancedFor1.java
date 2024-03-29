package JAVA.problem;

public class EnhancedFor1 {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5};

        //일반 for문
        for(int i = 0; i<numbers.length; i++){
            int number = numbers[i];
            System.out.println(number);
        }

        //향상된 for문, for-each문
        for(int number : numbers) {
            System.out.println(number);
            //number : 반복할 때마다 찾은 값을 저장할 변수
            //iter 단축키로 자동으로 만들 수 있다.
        }

        //for-each문을 사용할 수 없는 경우, 증가하는 index 값을 출력해야 할 때
        for(int i = 0; i<numbers.length; i++)
        {
            System.out.println("number" + i + "번의 결과는: " + numbers[i]);
        }
    }
}
