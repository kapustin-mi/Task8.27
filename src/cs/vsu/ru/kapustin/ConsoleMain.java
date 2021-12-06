package cs.vsu.ru.kapustin;

import cs.vsu.ru.kapustin.utils.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;

public class ConsoleMain {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        CmdArgs.CmdParams params = CmdArgs.parseArgs(args);

        if (params.help) {
            printHelp(params.error);
            System.exit(params.error ? 1 : 0);
        } else if (params.window) {
            GuiMain.main(args);
        } else {
            executeCheck(params);
        }
    }

    private static void printHelp(boolean error) {
        PrintStream out = error ? System.err : System.out;
        out.println("Usage: ");
        out.println("  <cmd> args <input-file> (<output-file>)");
        out.println("  <cmd> --help");
    }

    private static void executeCheck(CmdArgs.CmdParams params) throws FileNotFoundException {
        int[][] arr = ArrayUtils.readIntArray2FromFile(params.inputFile);

        if (arr == null) {
            System.out.printf("Can't read array from \"%s\"%n", params.inputFile);
            System.exit(2);
        }

        CheckingForSequence checking = new CheckingForSequence();
        boolean isArraySequence = checking.checkArray(arr);

        PrintStream out = params.outputFile != null ? new PrintStream(params.outputFile) : System.out;
        out.println("The array is an ordered sequence: " + isArraySequence);
        out.close();
    }
}
