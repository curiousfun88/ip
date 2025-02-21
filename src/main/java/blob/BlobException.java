package blob;

/**
 * Represents BlobException, which are specific exceptions to be captured by the chatbot.
 */
public class BlobException extends Exception {
    /**
     * Constructor for BlobException class.
     *
     * @param message specific exception message.
     */
    public BlobException(String message) {
        super(message);
    }
}
