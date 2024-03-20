package JAVA.problem;

/*public class Switch1 {
    public static void main(String[] args){
        int grade = 2;

        int coupon;
        switch(grade){
            case 1 :
                coupon= 1000;
                break;
            case 2:
                coupon = 2000;
                break;
            case 3:
                coupon = 3000;
                break;
            default:
                coupon = 500;
        }
        System.out.println("발급받은 쿠폰 " + coupon);
    }*/


//2등급도 3등급과 같이 3000원 쿠폰이 주어짐.
/*public class Switch1 {
    public static void main(String[] args){
        int grade = 2;
        int coupon;

        switch(grade){
            case 1:
                coupon = 1000;
                break;
            case 2:
            case 3:
                coupon = 3000;
                break;
            default:
                coupon = 500;
                break;
        }
        System.out.println("발급받은 쿠폰 " + coupon);
    }
}*/

//자바14 새로운 switch문
public class Switch1 {
    public static void main(String[] args){
        int grade = 2;
        int coupon = switch(grade){
            case 1-> 1000;
            case 2 -> 2000;
            case 3 -> 3000;
            default -> 500;
        };
        System.out.println("발급받은 쿠폰 " + coupon);
    }
}
