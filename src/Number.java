import java.util.Arrays;

class Number {
    String content;
    boolean isArabic = false;
    int value;

    Number(String input) throws Exception {
        this.content = input;
        try {
            this.value = Integer.parseInt(input);
            this.isArabic = true;
        } catch (Exception e) {
//            System.out.println("Не могу преобразовать в арабские");
        }
        try {
            if (!this.isArabic) {
                this.value = Arrays.asList(Main.unitRoman).indexOf(input);
                this.isArabic = false;
            }
        } catch (Exception e) {
//            System.out.println("Не могу преобразовать в римские");
        }
        if (this.value < 1 || this.value > 10) {
            throw new Exception("неверный ввод");
        }
    }
}
