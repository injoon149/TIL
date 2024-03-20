package JAVA.problem;

//참과 거짓에 따라 특정 값을 구하는 경우 사용하는 삼항 연산자
//삼항 연산자 사용 전
/*public class CondOp1 {
    public static void main(String[] args) {
        int age = 18;
        String status;
        if(age >= 18) {
            status = "성인";
        } else {
            status = "미성년자";
        }
        System.out.println("age = " + age + " status = " + status);
    }
} */

//삼항 연산자 사용 후
public class CondOp1 {
    public static void main(String[] args) {
        int age = 18;
        String status = (age >= 18) ? "성인" : "미성년자";
        System.out.println("age = " + age + " status = " + status);
    }
}


