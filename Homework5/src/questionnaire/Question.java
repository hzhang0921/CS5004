package questionnaire;

public interface Question {

  /**
   *
   * @returns the question itself.
   */
  public String getprompt();


  /**
   *
   * @returns whether or not the question is neccessary to be answered.
   */
  public boolean isRequired();

  /**
   * allows the user to provide an answer to the question.
   */
  public void answer(String answer);

  /**
   * allows the user to get the answer that is currently submitted or an empty string if nothing is answered.
   */
  public String getAnswer();



}
