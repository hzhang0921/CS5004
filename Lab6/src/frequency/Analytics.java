package frequency;

import java.util.HashMap;
import java.util.Map;

public class Analytics {
  public static Map<String, Double> wordFrequency(String message) {
    if (message == null || message.trim().isEmpty()) {
      return null;
    }

    String standardizedMessage = message.replaceAll("[^a-zA-Z\\s]", "").toUpperCase();

    String[] words = standardizedMessage.split("\\s+");
    int totalWords = words.length;

    Map<String, Double> frequencyMap = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (!word.isEmpty()) {
        frequencyMap.put(word, frequencyMap.getOrDefault(word, 0.0) + 1);
      }
    }

    String[] keys = frequencyMap.keySet().toArray(new String[0]);
    for (int i = 0; i < keys.length; i++) {
      String key = keys[i];
      frequencyMap.put(key, frequencyMap.get(key) / totalWords);
    }

    return frequencyMap;
  }

}
