package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

// Assuming that negatives are allowed in the input and assuming that sequential only means is 1,2,3 is 1-3 and 3,2,1 is 3-1.
public class NumberRangeImplementation implements NumberRangeSummarizer {
    /**
     * collect takes in a string input and uses the delimeter of "," and splits it into an array
     * the array is then mapped to an integer array and then streamed into a list of integers.
     * 
     * @param input is the string given to process
     */
    @Override
    public Collection<Integer> collect(String input) {
        int[] inputConverted = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        List<Integer> collection = Arrays.stream(inputConverted).boxed().collect(Collectors.toList());
        return collection;
    }

    /**
     * summarizeCollection iterates through input list and saves the starting and
     * previous elements,
     * this allows an O(n) implementation.
     * 
     * @param input the list of integers that are summarized.
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {

        StringBuilder sb = new StringBuilder(); // I used a String builder because it is more effiecent to append to a
                                                // string builder than to concatenate a String.
        Iterator<Integer> it = input.iterator();// I used an iterator because since the list is an object it is more
                                                // correct to iterate through the elements.
        int element, prev = 0, start = 0;
        boolean asc = false, desc = false;
        if (it.hasNext()) {
            prev = it.next();
            start = prev;
        }
        while (it.hasNext()) {
            element = it.next();
            if (element - 1 == prev) { // checks if the element and its predicessor are sequencial.
                if (!it.hasNext()) { // if there are no more elements in the list, then add the sequence to the
                                     // string builder.
                    sb.append(", " + start + "-" + element);
                } else if (start != prev && desc == true) {
                    sb.append(", " + start + "-" + prev);
                    start = element;
                }
                desc = false;
                asc = true;
            } else if (element == prev -1) { // checks if the element and its predicessor are sequencial(Reverse).
                if (!it.hasNext()) { // if there are no more elements in the list, then add the sequence to the
                                     // string builder.
                    sb.append(", " + start + "-" + element);
                } else if (start != prev && asc == true) {
                    sb.append(", " + start + "-" + prev);
                    start = element;
                } 
                asc = false;
                desc = true;
            } else {
                if (start == prev) { // if this is true, this means that the previous number is not sequencial.
                    sb.append(", " + start);
                } else { // if they are not the same then there was a previous sequence to output.
                    sb.append(", " + start + "-" + prev);
                }
                if (!it.hasNext()) { // if there are no more elements in the list, then add the last element if the
                                     // other checks didnt add it.
                    sb.append(", " + element);
                }
                start = element; // only setting start element if there is no sequence found between element and
                                 // prev.
            }
            prev = element; // always setting previous element.

        }
        return sb.toString().substring(2, sb.length()); // I substringed the sb.toString because since I added ", " to
                                                        // each append that means that the start would have an extra
                                                        // ", " and thise is more efficient than checking every append
                                                        // if the element is the starting element.
    }

}
