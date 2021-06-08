package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;


public class ApplicationUtil {
    // ApplicationUtil is used to create responses for Backend
    public static ObjectNode createResponse(Object response, boolean ok) {
        // a Response is sent in the following format
        // { "status": true, "response" : [] }
        ObjectNode result = Json.newObject();
        // a new Json Object is to create a result
        result.put("status", ok);
        if (response instanceof String)
            result.put("response", (String) response);
        else result.set("response", (JsonNode) response);
        // Returning result ObjectNode
        return result;
    }
}