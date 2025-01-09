package questionnaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireImplTests {

  private QuestionnaireImpl questionnaire;

  @BeforeEach
  void setUp() {
    questionnaire = new QuestionnaireImpl();
  }

  @Test
  void testAddAndRetrieveQuestion() {
    Question question = new YesNo("Do you like Java?", true);
    questionnaire.addQuestion("java_like", question);
    assertEquals(question, questionnaire.getQuestion("java_like"));
    assertEquals(question, questionnaire.getQuestion(1));
  }

  @Test
  void testRemoveQuestion() {
    Question question = new YesNo("Do you like Java?", true);
    questionnaire.addQuestion("java_like", question);
    questionnaire.removeQuestion("java_like");
    assertThrows(NoSuchElementException.class, () -> questionnaire.getQuestion("java_like"));
  }

  @Test
  void testGetRequiredQuestions() {
    questionnaire.addQuestion("q1", new YesNo("Required?", true));
    questionnaire.addQuestion("q2", new ShortAnswer("Optional?", false));
    List<Question> required = questionnaire.getRequiredQuestions();
    assertEquals(1, required.size());
    assertTrue(required.get(0).isRequired());
  }

  @Test
  void testIsComplete() {
    Question q1 = new YesNo("Q1?", true);
    Question q2 = new YesNo("Q2?", false);
    questionnaire.addQuestion("q1", q1);
    questionnaire.addQuestion("q2", q2);
    assertFalse(questionnaire.isComplete());
    q1.answer("Yes");
    assertTrue(questionnaire.isComplete());
  }

  @Test
  void testGetResponses() {
    Question q1 = new YesNo("Q1?", true);
    q1.answer("Yes");
    Question q2 = new ShortAnswer("Q2?", false);
    q2.answer("Short answer");
    questionnaire.addQuestion("q1", q1);
    questionnaire.addQuestion("q2", q2);
    List<String> responses = questionnaire.getResponses();
    assertEquals(2, responses.size());
    assertEquals("Yes", responses.get(0));
    assertEquals("Short answer", responses.get(1));
  }

  @Test
  void testFilter() {
    Question requiredQuestion = new YesNo("Required question?", true);
    Question optionalQuestion = new YesNo("Optional question?", false);
    questionnaire.addQuestion("req", requiredQuestion);
    questionnaire.addQuestion("opt", optionalQuestion);
    Questionnaire requiredOnly = questionnaire.filter(Question::isRequired);
    assertEquals(1, requiredOnly.getRequiredQuestions().size());
    assertEquals(requiredQuestion.getPrompt(), requiredOnly.getQuestion("req").getPrompt());
  }

  @Test
  void testSort() {
    Question question1 = new ShortAnswer("First question?", true);
    Question question2 = new YesNo("Second question?", true);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);

    questionnaire.sort((q1, q2) -> q1.getPrompt().compareTo(q2.getPrompt()));
    assertEquals("First question?", questionnaire.getQuestion(1).getPrompt());
    assertEquals("Second question?", questionnaire.getQuestion(2).getPrompt());
  }

  @Test
  void testToString() {
    Question q1 = new YesNo("Yes or no?", true);
    q1.answer("Yes");
    Question q2 = new ShortAnswer("Explain?", false);
    q2.answer("Explanation.");
    questionnaire.addQuestion("q1", q1);
    questionnaire.addQuestion("q2", q2);

    String expected = "Question: Yes or no?\n\nAnswer: Yes\n\nQuestion: Explain?\n\nAnswer: Explanation.";
    assertEquals(expected, questionnaire.toString());
  }
}
