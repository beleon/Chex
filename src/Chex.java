import java.util.Scanner;

public final class Chex {
    public static void main(String[] args) {
        boolean done = false;
        Scanner inScanner = new Scanner(System.in);
        Scanner commandScanner;
        String commandLine, command;
        Chess chess = new Chess();

        while (!done) {
            System.out.print("chex> ");

            if (inScanner.hasNextLine()) {
                commandLine = inScanner.nextLine();
                commandScanner = new Scanner(commandLine);
                command = commandScanner.next();

                switch (command.toLowerCase().charAt(0)) {
                    case 'p':
                        System.out.println(chess);
                        break;
                    case 'r':
                        chess = new Chess();
                        break;
                    case 'm':
                        if (commandScanner.hasNext()) {
                            String from = commandScanner.next();
                            if (commandScanner.hasNext()) {
                                String to = commandScanner.next();
                                if (validate(from) && validate(to)) {
                                    if(chess.move(from, to)) {
                                        System.out.println("Ok.");
                                    } else {
                                        System.out.println("Illegal move.");
                                    }
                                } else {
                                    System.out.println("Invalid square.");
                                }
                                break;
                            }
                        }
                        System.out.println("Invalid number of arguments.");
                        break;
                    case 'q': done = true; break;
                    default:
                        System.out.println("Unknown command: " + command); break;
                }
            } else {
                done = true;
            }
        }
    }

    private static boolean validate(String square) {
        return square.length() == 2 && square.toLowerCase().charAt(0) <= 'h' && square.toLowerCase().charAt(0) >= 'a'
                && square.charAt(1) >= '1' && square.charAt(1) <= '8';
    }

    private Chex() {
    }
}
