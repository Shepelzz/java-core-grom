package gromcode.main.lesson17.homework17_1;

public class Demo {
    public static void main(String[] args) {
        String str = "one two r i 56 rap"; //4
        Solution solution = new Solution();
        System.out.println(solution.countWords(str));
        System.out.println();

        test();
    }

    public static void test(){
        Solution solution = new Solution();


        //0. работа с разделителями
        System.out.println(solution.countWords("onE two thr7e Four FIVE   O")); //5

        //1.null value
        System.out.println(solution.countWords(null)); //0

        //2. first char == space
        System.out.println(solution.countWords(" ffff iii")); //2

        //3." " value
        System.out.println(solution.countWords(" ")); //0

        //4/ "" value
        System.out.println(solution.countWords("")); //0

        //5. not text values
        System.out.println(solution.countWords("123 456 7  8 454lololo ")); //0

        //6. diff case words
        System.out.println(solution.countWords("Upper lowER C_se")); //2
    }
}
