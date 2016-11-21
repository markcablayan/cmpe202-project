package deadlockmodel;

/**
 * Created by mark on 11/20/16.
 */
public class ChatModel {
    String content = "";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addContent(String content) {
        this.content += content + "\n";
    }
}
