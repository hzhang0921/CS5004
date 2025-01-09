package questionnaire;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShortAnswerTests {

  @Test
  void testShortAnswerQuestionValidation() {
    ShortAnswer shortAnswerQuestion = new ShortAnswer("Explain briefly:", true);
    assertDoesNotThrow(() -> shortAnswerQuestion.answer("Short answer within 280 characters"));
    assertThrows(IllegalArgumentException.class, () -> shortAnswerQuestion.answer("x".repeat(281)));
  }

  @Test
  void testShortAnswerQuestionCopy() {
    ShortAnswer question = new ShortAnswer("Explain:", false);
    question.answer("Some answer");
    ShortAnswer copy = (ShortAnswer) question.copy();
    assertEquals("Some answer", copy.getAnswer());
    assertNotSame(question, copy);
  }
}
