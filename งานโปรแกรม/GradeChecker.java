import java.util.Scanner;
    public class GradeChecker {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter midterm score: ");
        int midterm = scanner.nextInt();
        System.out.print("Enter final score: ");
        int finalScore = scanner.nextInt();
        int totalScore = midterm + finalScore;
        System.out.println("Total score = " + totalScore);
        if (totalScore >= 50) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        scanner.close();
    }
}