package utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OtherUtils {
    // a general util for sorting Map values
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        // a new arrayList is created for LinkedHashMap to use
        list.sort(Map.Entry.comparingByValue()); // uses the comparable implemented in passed in class
        // a LinkedHashmap is created
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) { // K and V are generics
            // adding values to result LinkedHashMap
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
