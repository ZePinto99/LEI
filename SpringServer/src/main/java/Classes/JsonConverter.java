package Classes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    public String JsonConverter(Tags tg){

        ObjectMapper objectMapper = new ObjectMapper();

        String json="{\"rows\": ";

        // tag to json
        //json += objectMapper.writeValueAsString(cpu.cpu());

        json+="}";
        return json;

    }
}
