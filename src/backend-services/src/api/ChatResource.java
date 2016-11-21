package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import deadlockmodel.ChatController;
import org.json.JSONObject;
import org.restlet.engine.util.StringUtils;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * Created by mark on 11/20/16.
 */
public class ChatResource extends ServerResource {
    ChatController controller = ChatController.getInstance();

    @Get
    public Representation get() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return new JsonRepresentation(mapper.writeValueAsString(controller.getChatContent()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new JsonRepresentation(e.getMessage());
        }
    }

    @Put
    public void put(JsonRepresentation jsonReq) {
        JSONObject jsonObject = jsonReq.getJsonObject();
        String message = jsonObject.get("message").toString();
        String user = jsonObject.get("user").toString();
        if(!StringUtils.isNullOrEmpty(message)) {
            controller.addChatContent(user + " : " + message);
        }
    }
}
