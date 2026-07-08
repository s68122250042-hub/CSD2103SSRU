public class ArrayCaseStudy {
    public static void main(String[] args) {
        int[] scores = {6, 8, 4, 9, 7, 5, 10, 3, 8, 2};
        int sumone = 0 ;
        int sum = 0 ;
        int summax = scores[0]; 
        int summin = scores[0]; 
        int sevenup = 0;
        int fivedown = 0;
        for (int t :scores) {
            sumone += t ;
            if (t > summax) {
                summax = t ;
            }
            if (t < summin) {
                    summin = t ;
                }
                if (t >= 7 ) {
                    sevenup++ ;
                }
                if (t < 5 ) {
                    fivedown++ ;
                }
        }
        sum = sumone / 10;
        System.out.println("TotalScore = "+sumone);
        System.out.println("average = "+sum);
        System.out.println("MaxScore = "+summax);
        System.out.println("MinScore = "+summin);
        System.out.println("ScoreUpSeven = "+sevenup);
        System.out.println("ScoreDownFive = "+fivedown);
    }
}