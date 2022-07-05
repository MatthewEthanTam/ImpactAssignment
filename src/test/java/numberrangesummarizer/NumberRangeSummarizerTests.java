package numberrangesummarizer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumberRangeSummarizerTests {

   NumberRangeImplementation test;
   @BeforeEach
   public void BeforeTests() {
      test = new NumberRangeImplementation();
   }

   /**
    * BaseTest:
    * The sample input given "1,3,6,7,8,12,13,14,15,21,22,23,24,31", 
    * and ouput should be "1, 3, 6-8, 12-15, 21-24, 31"
    */
    @Test
    public void BaseTest()
    {
       String example1 = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
       String expectedAnswer1 = "1, 3, 6-8, 12-15, 21-24, 31";
       Collection<Integer> arr = test.collect(example1);
       String output = test.summarizeCollection(arr);
       assertEquals(output, expectedAnswer1);
    }

    /**
     * BaseDescendingTest:
     * I first thought that given a randomly ordered input I should first order the input and then 
     * output the sequences but then in `NumberRangeSummarizer` I read `grouping the numbers into a range when they are sequential`, this meant
     * that I should not change the order because that would not be a true reflection of the given input.
     */
    @Test
    public void BaseDescendingTest()
    {
       String example2 = "31,24,23,22,21,15,14,13,12,8,7,6,3,1";
       String expectedAsnwer2 = "31, 24, 23, 22, 21, 15, 14, 13, 12, 8, 7, 6, 3, 1";
       Collection<Integer> arr = test.collect(example2);
       String output = test.summarizeCollection(arr);
       assertEquals(output, expectedAsnwer2);
    }

    /**
     * NegativeInputTest:
     * has the input `"-31,-30,-29,-22,21,15,14,13,-12,8,7,6,3,1"`
     * and the numberRangeSummarizer outputs -31--29, -22, 21, 15, 14, 13, -12, 8, 7, 6, 3, 1
     * The display when there is a negative sequence does not look great but it does find the sequences.
     */
    @Test
    public void NegativeInputTest()
    {
       String example3 = "-31,-30,-29,-22,21,15,14,13,-12,8,7,6,3,1";
       String expectedAnswer3 = "-31--29, -22, 21, 15, 14, 13, -12, 8, 7, 6, 3, 1";
       Collection<Integer> arr = test.collect(example3);
       String output = test.summarizeCollection(arr);
       assertEquals(output, expectedAnswer3);
    }
}
