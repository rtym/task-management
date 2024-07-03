package net.tymoshuk;

public class App {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Please provide an input!");
            System.exit(0);
        }
        System.out.println(toLower(args[0]));

    }

    public static String toLower(String input) {
        return input.toLowerCase();
    }
}
