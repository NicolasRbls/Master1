package td1_poo;
public class td1_exo1{
    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int result = addition(a, b);
        System.out.println(result);

        int[] array = {1, 2, 3};
        int result2 = addition2(array);
        System.out.println(result2);
    }

    public static int addition(int num1, int num2) {
        return num1 + num2;
    }

    public static int addition2(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    
}
