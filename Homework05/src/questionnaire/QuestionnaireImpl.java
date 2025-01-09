package questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class QuestionnaireImpl implements Questionnaire {
  private final List<Question> questions;
  private final Map<String, Integer> questionMap;

  public QuestionnaireImpl() {
    this.questions = new ArrayList<>();
    this.questionMap = new HashMap<>();
  }

  @Override
  public void addQuestion(String identifier, Question q) {
    if (identifier == null || identifier.isEmpty()) {
      throw new IllegalArgumentException("identifier cannot be null");
    }
    if (questionMap.containsKey(identifier)) {
      throw new IllegalArgumentException("cannot add question with same identifier");
    }
    questionMap.put(identifier, questions.size());
    questions.add(q);
  }

  @Override
  public void removeQuestion(String identifier) {
    Integer index = questionMap.get(identifier);

    if (index == null) {
      throw new NoSuchElementException("No question found with identifier: " + identifier);
    }

    questions.remove(index.intValue());
    questionMap.remove(identifier);

    for (Map.Entry<String, Integer> mapEntry : questionMap.entrySet()) {
      if (mapEntry.getValue() > index) {
        questionMap.put(mapEntry.getKey(), mapEntry.getValue()-1);
      }
    }
  }


  @Override
  public Question getQuestion(int num) {
    if (num < 1 || num > questions.size()) {
      throw new IndexOutOfBoundsException("No question with that index");
    }
    return questions.get(num-1);
  }

  @Override
  public Question getQuestion(String identifier) {
    Integer index = questionMap.get(identifier);

    if (index == null) {
      throw new NoSuchElementException("No questions found with that Identifier");
    }
    return questions.get(index);
  }

  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> required = new ArrayList<>();
    for (Question question : questions) {
      if (question.isRequired()) {
        required.add(question);
      }
    }
    return required;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> isOptional = new ArrayList<>();
    for (Question question : questions) {
      if (!question.isRequired()) {
        isOptional.add(question);
      }
    }
    return isOptional;
  }


  @Override
  public boolean isComplete() {
    for (Question question : questions) {
      if (question.isRequired() && question.getAnswer().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<String> getResponses() {
    List<String> responses = new ArrayList<>();
    for (Question question : questions) {
      responses.add(question.getAnswer());
    }
    return responses;
  }


  @Override
  public Questionnaire filter(Predicate<Question> pq) throws IllegalArgumentException {
    if (pq == null) {
      throw new IllegalArgumentException("Predicate cannot be null");
    }
    Questionnaire filtered = new QuestionnaireImpl();
    for (Map.Entry<String, Integer> mapEntry : questionMap.entrySet()) {
      String identifier = mapEntry.getKey();
      Question question = questions.get(mapEntry.getValue());
      if (pq.test(question)) {
        filtered.addQuestion(identifier, question.copy());
      }
    }
    return filtered;
  }

  @Override
  public void sort(Comparator<Question> comp) throws IllegalArgumentException {
    if (comp == null) {
      throw new IllegalArgumentException("Comparator cannot be null");
    }
    questions.sort(comp);
    for (Map.Entry<String, Integer> mapEntry : questionMap.entrySet()) {
      String questionId = mapEntry.getKey();
      Question currentQuestion = getQuestion(questionId);
      int newSortedPosition = questions.indexOf(currentQuestion);
      questionMap.put(questionId, newSortedPosition);
    }
  }

  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) throws IllegalArgumentException {
    if (bf == null) {
      throw new IllegalArgumentException("BiFunction cannot be null");
    }
    R result = seed;
    for (Question question : questions) {
      result = bf.apply(question, result);
    }
    return result;
  }

  @Override
  public String toString() {
    if (questions.isEmpty()) {
      return "";
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      stringBuilder.append("Question: ").append(question.getPrompt()).append("\n\n");
      stringBuilder.append("Answer: ").append(question.getAnswer());
      if (i < questions.size() - 1) {
        stringBuilder.append("\n\n");
      }
    }
    return stringBuilder.toString();
  }
}