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
                        case 'i':
                            if (commandScanner.hasNext()) {
                                String target = commandScanner.next();
                                if (validateChessVector(target)) {
                                    for (Vector2d vector2d : chess.moveInfo(Vector2d.fromChessNotation(target))) {
                                        System.out.println(Vector2d.toChessNotation(vector2d));
                                    }
                                } else {
                                    System.out.printf("Invalid square.");
                                }
                                break;
                            }
                            System.out.println("Invalid number of arguments.");
                            break;
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
                        case 'h':
                            System.out.println("Usage:\n");
                            System.out.println("p\t\t\t\t\t\t\t\tprint the board.");
                            System.out.println("m start end\t\t\t\t\t\tmove piece from start point to end point using standard chess notation. e.g. m e2 e4.");
                            System.out.println("l\t\t\t\t\t\t\t\tprint move list.");
                            System.out.println("n\t\t\t\t\t\t\t\tnew game.");
                            System.out.println("r target [player figure]\t\treplace target square with no piece or given piece for give player. eg. r e2 b q -> black queen on e2.");
                            System.out.println("r target\t\t\t\t\t\tshow all possible moves for piece.");
                            System.out.println("h\t\t\t\t\t\t\t\tthis help text.");
                            System.out.println("q\t\t\t\t\t\t\t\tquit. Also crl-d.");
                            break;
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
