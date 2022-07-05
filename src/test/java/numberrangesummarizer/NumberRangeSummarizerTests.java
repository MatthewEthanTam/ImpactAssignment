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
   public void BaseTest() {
      String example1 = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
      String expectedAnswer1 = "1, 3, 6-8, 12-15, 21-24, 31";
      Collection<Integer> arr = test.collect(example1);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAnswer1);
   }

   /**
    * BaseDescendingTest:
    * I first thought that given a randomly ordered input I should first sort the
    * input and then
    * output the sequences but then in `NumberRangeSummarizer` I read `grouping the
    * numbers into a range when they are sequential`, this meant
    * that I should not change the order because that would not be a true
    * reflection of the given input.
    * 
    * I then also looked at the meaning of sequential and realized that it could be
    * ascending or descending so I accounted for 1,2,3 = 1-3 and 3,2,1 = 3-1
    */
   @Test
   public void BaseDescendingTest() {
      String example2 = "31,24,23,22,21,15,14,13,12,8,7,6,3,1";
      String expectedAsnwer2 = "31, 24-21, 15-12, 8-6, 3, 1";
      Collection<Integer> arr = test.collect(example2);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAsnwer2);
   }

   /**
    * NegativeInputTest:
    * has the input `"-31,-30,-29,-22,21,15,14,13,-12,8,7,6,3,1"`
    * and the numberRangeSummarizer outputs -31--29, -22, 21, 15, 14, 13, -12, 8,
    * 7, 6, 3, 1
    * The display when there is a negative sequence does not look great but it does
    * find the sequences.
    */
   @Test
   public void NegativeInputTest() {
      String example3 = "-31,-30,-29,-22,21,15,14,13,-12,8,7,6,3,1";
      String expectedAnswer3 = "-31--29, -22, 21, 15-13, -12, 8-6, 3, 1";
      Collection<Integer> arr = test.collect(example3);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAnswer3);
   }

   @Test
   public void PositiveFullSequence() {
      String example4 = "400,401,402,403,404,405,406,407,408,409,410,411";
      String expectedAsnwer4 = "400-411";
      Collection<Integer> arr = test.collect(example4);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAsnwer4);
   }

   @Test
   public void NegativeFullSequence() {
      String example4 = "-400,-401,-402,-403,-404,-405,-406,-407,-408,-409,-410,-411";
      String expectedAsnwer4 = "-400--411";
      Collection<Integer> arr = test.collect(example4);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAsnwer4);
   }

   /**
    * AscAndDescSequence has a connection from 1-4 ascending and 4-1 descending
    * to not create repitition I start from the next number which would be 3 and
    * thus it will be
    * 1-4,3-1
    */
   @Test
   public void AscAndDescSequence() {
      String example5 = "1,2,3,4,3,2,1";
      String expectedAsnwer5 = "1-4, 3-1";
      Collection<Integer> arr = test.collect(example5);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAsnwer5);
   }

   @Test
   public void DescAndAscSequence() {
      String example6 = "4,3,2,1,2,3";
      String expectedAsnwer6 = "4-1, 2-3";
      Collection<Integer> arr = test.collect(example6);
      String output = test.summarizeCollection(arr);
      assertEquals(output, expectedAsnwer6);
   }
}
