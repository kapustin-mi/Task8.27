package cs.vsu.ru.kapustin;

import cs.vsu.ru.kapustin.utils.ArrayUtils;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckingForSequenceTest {
    int [][] testArray;
    boolean expected;
    boolean actual;
    CheckingForSequence checking = new CheckingForSequence();

    @Test
    public void oneLineArrayTest() throws FileNotFoundException {
        testArray = ArrayUtils.readIntArray2FromFile("tests/input01.txt");
        assert testArray != null;
        actual = checking.checkArray(testArray);

        Scanner scn = new Scanner(new File("tests/output01.txt"));
        expected = scn.nextBoolean();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneColumnArrayTest() throws FileNotFoundException {
        testArray = ArrayUtils.readIntArray2FromFile("tests/input02.txt");
        assert testArray != null;
        actual = checking.checkArray(testArray);

        Scanner scn = new Scanner(new File("tests/output02.txt"));
        expected = scn.nextBoolean();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneElementArrayTest() throws FileNotFoundException {
        testArray = ArrayUtils.readIntArray2FromFile("tests/input03.txt");
        assert testArray != null;
        actual = checking.checkArray(testArray);

        Scanner scn = new Scanner(new File("tests/output03.txt"));
        expected = scn.nextBoolean();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testOfArrayOfEqualNumbers() throws FileNotFoundException {
        testArray = ArrayUtils.readIntArray2FromFile("tests/input04.txt");
        assert testArray != null;
        actual = checking.checkArray(testArray);

        Scanner scn = new Scanner(new File("tests/output04.txt"));
        expected = scn.nextBoolean();

        Assert.assertEquals(expected, actual);
    }

    @Test
   public void decreasingArrayTest() throws FileNotFoundException {
        testArray = ArrayUtils.readIntArray2FromFile("tests/input05.txt");
        assert testArray != null;
        actual = checking.checkArray(testArray);

        Scanner scn = new Scanner(new File("tests/output05.txt"));
        expected = scn.nextBoolean();

        Assert.assertEquals(expected, actual);
    }
}