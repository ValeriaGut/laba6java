import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Dictionary {
    private Map<String, Integer> dictionary;


    public Dictionary() {
        dictionary = new HashMap<String, Integer>();
    }

    public Dictionary(Dictionary to_copy) {
        dictionary = new HashMap<String, Integer>(to_copy.dictionary);
    }

    public Dictionary(HashMap<String, Integer> to_copy) {
        dictionary = to_copy;
    }

    public void InsertWord(String to_parse) throws IOException {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(to_parse);
        while (matcher.find()) {
            if (!dictionary.containsKey(matcher.group()))
                dictionary.put(matcher.group(), 1);
            else {
                int words = dictionary.get(matcher.group());
                dictionary.replace(matcher.group(), words, ++words);
            }
        }
    }

    public void InsertData(String word, int value) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()) {
            dictionary.put(matcher.group(), value);
        }
    }

    @Override
    public String toString() {
        String result = "";
        Iterator<Map.Entry<String, Integer>> it = dictionary.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
            String key = entry.getKey();
            int value = entry.getValue();
            result += "Word: \"" + key + "\", Reiterations: " + value + "\n";
        }
        return result;
    }


    public Map<String, Integer> getDictionary() {
        return dictionary;
    }

    public List<Map.Entry<String, Integer>> Sort() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(dictionary.entrySet());
        entries.sort(new MyComparator());
        return entries;
    }

}
class MyComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        int reverseValueCompared = e1.getKey().length() - e2.getKey().length();
        if (reverseValueCompared == 0) {
            return e1.getKey().hashCode() - e2.getKey().hashCode();
        }
        return reverseValueCompared;
    }
}

