import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(){
        Scanner scanner = new Scanner(System.in);

        final byte PERCENT = 100;
        final short numberOfMonths = 12;

        System.out.print("Principal: ");
        int principal = scanner.nextInt();
        System.out.print("Interest Rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Number of Payments (YEARS): ");
        int numberOfPayments = scanner.nextInt() * numberOfMonths;

        double monthlyInterestRate = interestRate / PERCENT / numberOfMonths;

        double mortgage = principal *
                (monthlyInterestRate * Math.pow(1+monthlyInterestRate, numberOfPayments))
                        /(Math.pow(1+monthlyInterestRate, numberOfPayments) - 1);

        System.out.print("Mortgage:");
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println(mortgageFormatted);

    }
}
