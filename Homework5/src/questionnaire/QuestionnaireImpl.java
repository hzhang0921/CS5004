package questionnaire;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class QuestionnaireImpl implements Questionnaire {
  private Map<String, Question> questions;

  public QuestionnaireImpl() {
    this.questions = new LinkedHashMap<>();
  }

  @Override
  public void addQuestion(String identifier, Question q) {
    if (identifier == null || identifier.trim().isEmpty()) {
      throw new IllegalArgumentException("Identifier cannot be null or empty");
    }
    questions.put(identifier, q);
  }

  @Override
  public void removeQuestion(String identifier) {
    if (questions.containsKey(identifier)) {
      questions.remove(identifier);
    } else {
      throw new NoSuchElementException("No such question");
    }
  }

  @Override
  public Question getQuestion(int num) {
    if (num < 1 || num > questions.size()) {
      throw new IndexOutOfBoundsException("Invalid question index selected");
    }
    return (Question) questions.values().toArray()[num - 1];
  }

  @Override
  public Question getQuestion(String identifier) {
    if (questions.containsKey(identifier)) {
      return questions.get(identifier);
    } else {
      throw new NoSuchElementException("No question with identifier");
    }
  }

  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredQuestions = new ArrayList<>();
    for (Question question : questions.values()) {
      if (question.isRequired()) {
        requiredQuestions.add(question);
      }
    }
    return requiredQuestions;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalQuestions = new ArrayList<>();
    for (Question question : questions.values()) {
      if (!question.isRequired()) {
        optionalQuestions.add(question);
      }
    }
    return optionalQuestions;
  }

  @Override
  public boolean isComplete() {
    for (Question question : questions.values()) {
      if (question.isRequired() && question.getAnswer().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<String> getResponses() {
    List<String> responses = new ArrayList<>();
    for (Question question : questions.values()) {
      responses.add(question.getAnswer());
    }
    return responses;
  }

  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    if (pq == null) {
      throw new IllegalArgumentException("Predicate cannot be null");
    }

    QuestionnaireImpl filteredQuestionnaire = new QuestionnaireImpl();
    for (Map.Entry<String, Question> entry : questions.entrySet()) {
      if (pq.test(entry.getValue())) {
        filteredQuestionnaire.addQuestion(entry.getKey(), entry.getValue().copy());
      }
    }
    return filteredQuestionnaire;
  }

  @Override
  public void sort(Comparator<Question> comp) {
    if (comp == null) {
      throw new IllegalArgumentException("Comparator cannot be null");
    }
    List<Question> sortedQuestions = new ArrayList<>(questions.values());
    sortedQuestions.sort(comp);
    questions.clear();
    int count = 1;
    for (Question question : sortedQuestions) {
      questions.put(String.valueOf(count), question);
      count++;
    }
  }

  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    if (bf == null) {
      throw new IllegalArgumentException("BiFunction cannot be null");
    }
    R result = seed;
    for (Question question : questions.values()) {
      result = bf.apply(question, result);
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Question> entry : questions.entrySet()) {
      result.append("Identifier: ").append(entry.getKey()).append("\n");
      result.append("Question: ").append(entry.getValue().getPrompt()).append("\n");
      result.append("Answer: ").append(entry.getValue().getAnswer()).append("\n\n");
    }
    return result.toString();
  }
}
