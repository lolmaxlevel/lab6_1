package tranceiving;

/** Receiver exception class */
public class ReceiverException extends Exception {
    /**
     * Constructs a new exception with {@code null} as its detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public ReceiverException() {
        super();
    }
    public ReceiverException(String msg) {
        super(msg);
    }
    public ReceiverException(String msg, Throwable cause){
        super(msg, cause);
    }
}