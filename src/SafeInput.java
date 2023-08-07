import java.util.Scanner;

public class SafeInput {

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);
        return retString;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user with no range
     * @return an int value that is not constrained
     */
    public static int getInt(Scanner pipe, String prompt) {

        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while(!done);

        return retVal;

    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user with no range
     * @return a double value that is not constrained
     */
    public static double getDouble(Scanner pipe, String prompt) {

        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while(!done);

        return retVal;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user that does not include range
     * @param low low bound of inclusive range
     * @param high high bound of inclusive range
     * @return an int value that is within the inclusive range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "]: " + retVal);
                }
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        }while(!done);

        return retVal;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user that does not include range
     * @param low low bound of inclusive range
     * @param high high bound of inclusive range
     * @return a double value that is within the inclusive range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {

        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "]: " + retVal);
                }
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        }while(!done);

        return retVal;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user that does not require [Y/N]
     * @return a boolean value that is true for yes and false for no
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        boolean retVal = true;
        String response = "";
        boolean gotAVal = false;

        do
        {
            System.out.print("\n" + prompt + " [Y/N]? ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")){
                gotAVal = true;
                retVal = true;
            }
            else if (response.equalsIgnoreCase("N")){
                gotAVal = true;
                retVal = false;
            }
            else {
                System.out.println("You must answer [Y/N]: " + response);
            }
        }while(!gotAVal);

        return retVal;

    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx RegEx pattern to check input
     * @return a boolean value that is true for yes and false for no
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        String response = "";
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                gotAVal = true;
            }
            else {
                System.out.println("\n" + response + " must match the pattern " + regEx);
                System.out.println("Try again!");
            }
        }while(!gotAVal);

        return response;
    }

    /**
     *
     * @param msg a message input from user
     */
    public static void prettyHeader(String msg) {

        int length = msg.length();
        int space = (54 - length) / 2;

        System.out.println("");
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.println("");
        for (int i = 0; i < 3; i++) {
            System.out.print("*");
        }
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }

        System.out.print(msg);

        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("*");
        }
        System.out.println("");
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }

    }
}


