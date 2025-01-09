package questionnaire;

abstract class AbstractQuestion implements Question {
  protected final String prompt;
  protected final boolean required;
  protected String answer;

  /**
   * protected AbstractQuestion that allows all sub-classes to initialize
   * @param prompt - the test of the question
   * @param required - whether a question is required or not
   * @throws IllegalArgumentException - given an improper input
   */
  protected AbstractQuestion(String prompt, boolean required)
      throws IllegalArgumentException {
    if (prompt == null || prompt.isEmpty()) {
      throw new IllegalArgumentException("Prompt cannot be null or empty");
    }
    this.prompt = prompt;
    this.required = required;
    this.answer = "";
  }

  @Override
  public String getPrompt() {
    return prompt;
  }

  @Override
  public boolean isRequired() {
    return required;
  }

  @Override
  public String getAnswer() {
    return answer;
  }

  @Override
  public void answer(String response) {
    validateResponse(response);
    this.answer = response;
  }

  protected void validateResponse(String response) {
    // No validation by default, only if neccessary.
  }
}