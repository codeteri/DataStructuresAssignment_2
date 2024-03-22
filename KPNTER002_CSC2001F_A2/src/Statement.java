/**
 * This is a JavaDoc comment for the Statement class.
 * It provides a brief description of the class.
 */

public class Statement implements Comparable<Statement> {
    private String term;
    private String sentence;
    private double confidenceScore;
    /**
     * This is a JavaDoc comment for the constructor of the Statement class.
     * It provides a brief description of the constructor.
     *
     * @param term   The term for the statement.
     * @param sentence The sentence for the statement.
     * @param confidenceScore The confidence score for the statement.
     */

    // Constructor
    public Statement(String term, String sentence, double confidenceScore) {
        this.term = term;
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }

    public Statement(String term) {
        this.term = term;
        this.sentence = "";  // Default sentence
        this.confidenceScore = 0.0;  // Default confidence score
    }

    // Getter methods


    public String getTerm() {
        return term;
        /**
         * This is a JavaDoc comment for a method in the Statement class.
         * It provides a brief description of the method.
         *
         * @return The term of the statement.
         */
    }

    public String getSentence() {
        return sentence;
        /**
         * This is a JavaDoc comment for a method in the Statement class.
         * It provides a brief description of the method.
         *
         * @return The sentence of the statement.
         */
    }

    public double getConfidenceScore() {
        return confidenceScore;
        /**
         * This is a JavaDoc comment for a method in the Statement class.
         * It provides a brief description of the method.
         *
         * @return The sentence of the statement.
         */
    }

    // Setter methods
    public void setTerm(String newTerm) {
        this.term = newTerm;
    }

    public void setSentence(String newSentence) {
        this.sentence = newSentence;
    }

    public void setConfidenceScore(double newConfidenceScore) {
        this.confidenceScore = newConfidenceScore;
    }

    @Override
    public int compareTo(Statement other) {
        // Implement comparison logic based on your requirements
        // For example, compare terms or sentences
        return this.getTerm().compareTo(other.getTerm());
    }

    @Override
    public String toString() {
        return term + ": " + sentence + " ("+ confidenceScore + ")";
    }
}
