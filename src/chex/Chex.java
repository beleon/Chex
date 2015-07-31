package chex;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class Chex {
    private static List<Character> abbreviations;
    static {
        abbreviations = new LinkedList<>();
        abbreviations.add('k');
        abbreviations.add('q');
        abbreviations.add('b');
        abbreviations.add('n');
        abbreviations.add('r');
        abbreviations.add('p');
    }

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
                if (commandScanner.hasNext()) {
                    command = commandScanner.next();
                    switch (command.toLowerCase().charAt(0)) {
                        case 'p':
                            System.out.println(chess);
                            break;
                        case 'r':
                            if (commandScanner.hasNext()) {
                                String target = commandScanner.next();
                                if (commandScanner.hasNext()) {
                                    String pl = commandScanner.next();
                                    if (commandScanner.hasNext()) {
                                        String abbr = commandScanner.next();
                                        if (validateChessVector(target) && validatePlayer(pl) && validateAbbreviation(abbr)) {
                                            chess.replace(target, pl, abbr);
                                        } else {
                                            System.out.println("Invalid square or player or abbreviation.");
                                        }
                                        break;
                                    }
                                } else {
                                    if (validateChessVector(target)) {
                                        chess.replace(target);
                                    } else {
                                        System.out.println("Invalid square.");
                                    }
                                    break;
                                }
                            }
                            System.out.println("Invalid number of arguments.");
                        case 'l':
                            System.out.println(chess.moveListAsString());
                            break;
                        case 'n':
                            chess = new Chess();
                            break;
                        case 'm':
                            if (commandScanner.hasNext()) {
                                String from = commandScanner.next();
                                if (commandScanner.hasNext()) {
                                    String to = commandScanner.next();
                                    if (validateChessVector(from) && validateChessVector(to)) {
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
                }
            } else {
                done = true;
            }
        }
    }

    private static boolean validatePlayer(String pl) {
        return pl.toLowerCase().charAt(0) == 'w' || pl.toLowerCase().charAt(0) == 'b';
    }

    private static String valCodeToString(int valCode) {
        switch (valCode) {
            case 1: return "Not your piece.";
            case 101: return "Not a valid move for a pawn.";
        }

        return "Unknown Error: " + valCode;
    }

    private static boolean validateChessVector(String square) {
        return square.length() == 2 && square.toLowerCase().charAt(0) <= 'h' && square.toLowerCase().charAt(0) >= 'a'
                && square.charAt(1) >= '1' && square.charAt(1) <= '8';
    }

    private static boolean validateAbbreviation(String abbreviation) {
        return abbreviations.contains(abbreviation.toLowerCase().charAt(0));
    }

    private Chex() {
    }
}
