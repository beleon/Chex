package chex;

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
                    case 'l':
                        System.out.println(chess.moveListAsString());
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
                                    int valCode = chess.move(from, to);
                                    if(valCode == 0) {
                                        System.out.println("Ok.");
                                    } else {
                                        System.out.println("Illegal move: " + valCodeToString(valCode));
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

    private static String valCodeToString(int valCode) {
        switch (valCode) {
            case 1: return "Not your piece.";
            case 101: return "Not a valid move for a pawn.";
        }

        return "Unknown Error: " + valCode;
    }

    private static boolean validate(String square) {
        return square.length() == 2 && square.toLowerCase().charAt(0) <= 'h' && square.toLowerCase().charAt(0) >= 'a'
                && square.charAt(1) >= '1' && square.charAt(1) <= '8';
    }

    private Chex() {
    }
}
