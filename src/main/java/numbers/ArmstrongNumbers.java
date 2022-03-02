package numbers;

import java.util.ArrayList;
import java.util.List;

public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int number){
        if (number<0){
            throw new IllegalArgumentException("Number can't be negative: " + number);
        }
        return number == calculateSumOfPowers(number);
    }


    public int calculateSumOfPowers(int number){
        int sum = 0;
        List<Integer> digits = getDigits(number);
        int exponent = digits.size();

        for (int d : digits){
            sum += Math.pow(d,exponent);
        }
        return sum;
    }

    public List<Integer> getDigits(int number){
        List<Integer> output = new ArrayList<>();
        while (number > 0) {
            output.add(number%10);
            number = number / 10;
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(new ArmstrongNumbers().getDigits(12));
        System.out.println(new ArmstrongNumbers().getDigits(4));
        System.out.println(new ArmstrongNumbers().getDigits(123));
    }

}
