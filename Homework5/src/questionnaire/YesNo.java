package questionnaire;

public class YesNo implements Question {
  private String questionText;
  private boolean required;
  private String answer;

  public YesNo(String question, boolean required) {
    if (question == null || question.trim().isEmpty()) {
      throw new IllegalArgumentException("Question text cannot be null or empty");
    }
    this.questionText = question;
    this.required = required;
    this.answer = "";
  }


  @Override
  public String getPrompt() {
    return this.questionText;
  }

  @Override
  public boolean isRequired() {
    return this.required;
  }

  @Override
  public void answer(String answerText) {
    if (answerText == null) {
      throw new IllegalArgumentException("Response cannot be null");
    }

    if (answerText.equalsIgnoreCase("yes") || answerText.equalsIgnoreCase("no")) {
      this.answer = answerText;
    } else {
      throw new IllegalArgumentException("Invalid response for Yes/No question");
    }
  }

  @Override
  public String getAnswer() {
    return this.answer;
  }

  @Override
  public Question copy() {
    YesNo copy = new YesNo(this.questionText, this.required);
    copy.answer = this.answer;
    return copy;
  }
}
