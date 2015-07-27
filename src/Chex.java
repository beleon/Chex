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
                    case 'm':
                        if(!chess.move(commandScanner.next(), commandScanner.next())) {
                            System.out.println("Illegal move.");
                        }
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

    private Chex() {
    }
}
