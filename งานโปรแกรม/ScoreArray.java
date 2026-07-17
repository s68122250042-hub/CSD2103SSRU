import java.util.Scanner;
    public class ScoreArray {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] scores = new int[5];
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            System.out.print("Enter score " + (i + 1) + ": ");
            scores[i] = scanner.nextInt();
            sum += scores[i];
        }
        double average = (double) sum / scores.length;
        System.out.println("Total score = " + sum);
        System.out.println("Average score = " + average);
        scanner.close();
    }
}