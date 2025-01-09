package questionnaire;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractQuestionTests {

  @Test
  void testAbstractQuestionConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new YesNo(null, true));
    assertThrows(IllegalArgumentException.class, () -> new YesNo("", true));
    assertDoesNotThrow(() -> new YesNo("Is this a valid prompt?", true));
  }

  @Test
  void testAnswerMethodAndGetAnswer() {
    Question question = new ShortAnswer("Short answer question?", true);
    question.answer("Some answer");
    assertEquals("Some answer", question.getAnswer());
  }
}