import java.util.Objects;
import java.util.Scanner;

public class Main {
    static String[] unitRoman = {"N", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static String[] decimalRoman = {"N", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

    public static void main(String[] args) {
        System.out.println("Строка: ");
        Scanner cin = new Scanner(System.in);
        String inputString = cin.nextLine();
        try {
            System.out.println("Результат: " + calc(inputString));
        } catch (Exception e) {
            System.out.println("Нет результата: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] arr = input.split("((?=-)|(?<=-))|((?=[+])|(?<=[+]))|((?=[*])|(?<=[*]))|((?=/)|(?<=/))");
        if (arr.length != 3) throw new Exception("неверный ввод");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }

        Number firstOperand = new Number(arr[0]);
        Number secondOperand = new Number(arr[2]);
        String operation = arr[1];

        if (firstOperand.isArabic != secondOperand.isArabic) {
            throw new Exception("разные системы счисления");
        }
        if (firstOperand.value <= secondOperand.value && !firstOperand.isArabic && Objects.equals(operation, "-")) {
            throw new Exception("римские числа, результат <= 0");
        }

        System.out.println("Первое число: " + firstOperand.value + "\nВторое число: " + secondOperand.value);

        int result = 0;
        System.out.print("Операция: ");
        switch (operation) {
            case "+":
                System.out.println("сложение");
                result = firstOperand.value + secondOperand.value;
                break;
            case "-":
                System.out.println("вычитание");
                result = firstOperand.value - secondOperand.value;
                break;
            case "*":
                System.out.println("умножение");
                result = firstOperand.value * secondOperand.value;
                break;
            case "/":
                System.out.println("деление");
                result = firstOperand.value / secondOperand.value;
                break;
            default:
                System.out.println("Что-то не так");
        }
        String resultString = Integer.toString(result);

        if (!firstOperand.isArabic) {
            resultString = convertToRoman(result);
        }

        return resultString;
    }

    private static String convertToRoman(int result) {
        if (result == 100) return "C";

        String[] arrRoman = {"", ""};

        int unitPlace = result % 10;
        result /= 10;
        int decimalPlace = result % 10;

        if (unitPlace != 0) {
            arrRoman[1] = unitRoman[unitPlace];
        }
        if (decimalPlace != 0) {
            arrRoman[0] = decimalRoman[decimalPlace];
        }

        return String.join("", arrRoman);
    }
}