
package lets_make_a_deal;

import java.util.Random;
import java.util.Scanner;

public class Lets_Make_A_Deal_Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        play_game(input);
        input.close();
    }

    public static void play_game(Scanner input) {
        initialize_game(input);
        booby = booby_prize();
        show_door();
        change = get_change(input);
        get_win(prize, change, user_pick);
    }

    public static int prize;
    public static int user_pick;
    public static boolean change;
    public static String booby;

    /*
     * This is a global variable, but it's only used once since I wanted to
     * generate
     * a random set of strings I had to call the method, in the future, I will
     * recall the method to
     * generate new random strings, unless there is a more effective way
     */
public static void initialize_game(Scanner input) {
prize = gen_random(1,3);
/*
* This is calling the gen_random method to create a prize, the min is
1 and the max is 3
*/
System.out.println("Pick a door! Enter either 1, 2 or 3... Choose wisely");
user_pick = get_user_input(input, 1, 3);
//System.out.println(prize + " " + user_pick);
}

public static int get_user_input(Scanner input, int min, int max) {
int val = min - 1;
do {
try {
System.out.println("Enter a number between " + min + " and " + max);
val=input.nextInt();
}
catch(Exception e) {
System.out.println("Invalid entry, try entering an integer.");
/*
* if an exception occurs, go to here (catch), if anything
goes wrong, enter statement
* and tell them to do it again. It will not change the
value of the variable called val
*/
}
finally {
input.nextLine();
}
}
while(val < min || val > max);
return val;
}

    public static int gen_random(int min, int max) {
        Random rand = new Random();
        int rand_val = Math.abs(rand.nextInt());
        rand_val = (rand_val % (max - min + 1)) + min;
        /*
         * remember that the modulus divides the numbers and returns a whole
         * number integer that is the remainder
         */
        return rand_val;
    }

public static String booby_prize() {
String[] boobyPrizes = {
    "a used napkin!", 
    "a jar of unflavored jellybeans!", 
    "a collection of crappy John Mayer albums!", 
    "a roll of one ply toilet paper!", 
    "an old sock!"};
Random rand = new Random();
int picker = rand.nextInt(boobyPrizes.length);
/*
* picks from a random list of strings (boobyPrizes)
* Stack Overflow: https://stackoverflow.com/questions/6726963/random-
string-from-string-array-list
*/
return boobyPrizes[picker];
}

    public static void show_door() {
        /*
         * If the prize is behind 1, and they pick 2, we must show 3. If they
         * pick 2, prize = 3, then
         * show 2, et cetera
         */
        int show = 1;
        while (show == prize || show == user_pick) {
            show++;
            if (show > 3) {
                show = 1;
                /*
                 * Sometimes show_door() would not execute the print ln, so
                 * I sysout "show"
                 * and for some reason it would exceed 3, so in order to
                 * prevent that I
                 * created this if statement to reset to 1 if it exceeds
                 * the maximum value (3)
                 */
            }
            /*
             * We are starting with 1, and then we will got prize and then
             * add one, if user_pick or prize
             * == show, then the rest of the code will not execute until show
             * != prize or user pick
             */
        }
        System.out.println("Behind Door " + show + " is " + booby);
        /*
         * The int that is declared within the method, the compiler will look
         * for a local variable
         * first, then a global variable
         */
    }

    public static boolean get_change(Scanner input) {
        System.out.println("Do you want to switch doors? 1 for yes, 2 for no");
        int choice = get_user_input(input, 1, 2);
        if (choice == 1) {
            return true;
        }
        return false;
    }

    public static void get_win(int prize, boolean change, int user_pick) {
        String result;
        /*
         * Used variable result to generate a random String for booby_prize /
         * so the same booby didn't print if the user loses
         */
        if (change) {
            if (prize == user_pick) {
                /*
                 * this is a nested if statement so it will only run if the
                 * change was applied
                 */
                result = "Oh... Sorry, you got " + booby_prize();
                /*
                 * Change from var booby because the booby randome was not
                 * rerolled, must call method
                 * again in order to generate new booby
                 */
            } else {
                result = "You won!";
            }
        } else {
            if (prize == user_pick) {
                result = "You win!";
            } else {
                result = "Sorry you get " + booby_prize();
            }
        }
        System.out.println(result);
    }
}
