package questionnaire;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YesNoTests {

  @Test
  void testYesNoQuestionValidation() {
    YesNo yesNoQuestion = new YesNo("Do you agree?", true);
    assertDoesNotThrow(() -> yesNoQuestion.answer("Yes"));
    assertDoesNotThrow(() -> yesNoQuestion.answer("No"));
    assertThrows(IllegalArgumentException.class, () -> yesNoQuestion.answer("Maybe"));
  }

  @Test
  void testYesNoQuestionCopy() {
    YesNo question = new YesNo("Do you agree?", false);
    question.answer("Yes");
    YesNo copy = (YesNo) question.copy();
    assertEquals("Yes", copy.getAnswer());
    assertNotSame(question, copy);
  }
}
