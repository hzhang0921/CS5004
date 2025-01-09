package questionnaire;

public class Likert implements Question {
  private String questionText;
  private boolean required;
  private String answer;

  public Likert(String question, boolean required) {
      if (question == null || question.isEmpty()) {
        throw new IllegalArgumentException("Question cannot be null or empty");
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
  public void answer(String response) {
    if (response == null) {
      throw new IllegalArgumentException("Response cannot be null");
    }

    boolean validResponse = false;

    for (LikertResponseOption option : LikertResponseOption.values()) {
      if (response.equalsIgnoreCase(option.getText())) {
        this.answer = option.getText();
        validResponse = true;
        break;
      }
    }

    if (!validResponse) {
      throw new IllegalArgumentException("Invalid response for Likert scale");
    }
  }

  @Override
  public String getAnswer() {
    return this.answer;
  }

  @Override
  public Question copy() {
    Likert copy = new Likert(this.questionText, this.required);
    copy.answer = this.answer;
    return copy;
  }
}
