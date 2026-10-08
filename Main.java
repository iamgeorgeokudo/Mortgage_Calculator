import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte PERCENT = 100;
    final static short numberOfMonths = 12;
    public static void main() {
        int principal = (int)readNumber("Principal: ", 1000, 1000000);
        double annualInterestRate = readNumber("Interest Rate: ", 1, 30);
        int numberOfPayments = (int)readNumber("Number of Payments (YEARS):", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, numberOfPayments);
        printMortgage(mortgage);
        printPaymentSchedule(numberOfPayments, principal, annualInterestRate);
    }

    private static void printMortgage(double mortgage) {
        System.out.print("Mortgage:");
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(
            int numberOfPayments,
            int principal,
            double annualInterestRate) {
        System.out.println("PAYMENT SCHEDULE");
        for (short month = 1; month <= numberOfPayments; month++) {
            double balance = calculateRemainingBalance(principal, annualInterestRate, numberOfPayments, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, int min, int max){
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + "and" + max);
        }
        return value;
    }
    public static double calculateMortgage(
            int principal,
            double annualInterestRate,
            int numberOfPayments){

        int totalRepayments = numberOfPayments * numberOfMonths;
        double monthlyInterestRate = annualInterestRate / PERCENT / numberOfMonths;

        double mortgage = principal *
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalRepayments))
                / (Math.pow(1 + monthlyInterestRate, totalRepayments) - 1);
        return mortgage;
    }

    public static double calculateRemainingBalance(
            int principal,
            double annualInterestRate,
            int numberOfPayments,
            int numberOfPaymentsMade){
        double monthlyInterestRate = annualInterestRate / PERCENT / numberOfMonths;
        double balance = principal *
                (Math.pow(1+monthlyInterestRate, numberOfPayments)-Math.pow(1+monthlyInterestRate, numberOfPaymentsMade))
                /(Math.pow(1+monthlyInterestRate, numberOfPayments)-1);
        return balance;
    }
}