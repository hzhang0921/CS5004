package questionnaire;

public class ShortAnswer implements Question {
  private String questionText;
  private boolean required;
  private String answer;

  public ShortAnswer(String question, boolean required) {
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
    if (response == null || response.length() > 280 || response.isEmpty()) {
      throw new IllegalArgumentException("Invalid response");
    }
    if (response.length() <= 280) {
      this.answer = response;
    }
  }

  @Override
  public String getAnswer() {
    return answer;
  }

  @Override
  public Question copy() {
    ShortAnswer copy = new ShortAnswer(this.questionText, this.required);
    copy.answer = this.answer;
    return copy;
  }
}