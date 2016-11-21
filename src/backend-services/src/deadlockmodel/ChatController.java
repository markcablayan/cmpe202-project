package deadlockmodel;

/**
 * Created by mark on 11/20/16.
 */
public class ChatController {
    ChatModel chatModel;
    private static ChatController controller;

    public ChatController() {
        this.chatModel = new ChatModel();
    }

    public String getChatContent() {
        return this.chatModel.getContent();
    }

    public void addChatContent(String content) {
        this.chatModel.addContent(content);
    }

    public static ChatController getInstance() {
        if (controller == null) {
            controller = new ChatController();
        }
        return controller;
    }
}
