package questionnaire;

public interface Question {

  /**
   *
   * @returns the question itself.
   */
  String getPrompt();

  /**
   *
   * @returns whether or not the question is neccessary to be answered.
   */
  boolean isRequired();

  /**
   * allows the user to provide an answer to the question.
   */
  void answer(String answer);

  /**
   * allows the user to get the answer that is currently submitted or an empty string if nothing is answered.
   */
  String getAnswer();

  /**
   * returns a copy of the question with the data.
   */
  Question copy();


}
