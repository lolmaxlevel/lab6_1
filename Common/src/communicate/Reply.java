package communicate;


import java.io.Serializable;

/**
 * Class with which server is replying
 */
public class Reply implements Serializable {
    private final boolean isSuccess;
    private final Object reply;


    public Reply(boolean isSuccess, Object reply) {
        this.isSuccess = isSuccess;
        this.reply = reply;
    }

    public Reply(boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.reply = new String("");
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Object getReply() {
        return reply;
    }

}