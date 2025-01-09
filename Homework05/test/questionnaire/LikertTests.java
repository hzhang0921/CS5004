package questionnaire;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LikertTests {

  @Test
  void testLikertQuestionAnswerValidation() {
    Likert likertQuestion = new Likert("How much do you agree?", true);
    assertDoesNotThrow(() -> likertQuestion.answer("Agree"));
    assertThrows(IllegalArgumentException.class, () -> likertQuestion.answer("Not a valid answer"));
    assertEquals("Agree", likertQuestion.getAnswer());
  }

  @Test
  void testLikertQuestionCopy() {
    Likert likertQuestion = new Likert("Do you agree?", false);
    likertQuestion.answer("Strongly Disagree");
    Likert copy = (Likert) likertQuestion.copy();
    assertEquals(likertQuestion.getAnswer(), copy.getAnswer());
    assertNotSame(likertQuestion, copy);
  }
}
