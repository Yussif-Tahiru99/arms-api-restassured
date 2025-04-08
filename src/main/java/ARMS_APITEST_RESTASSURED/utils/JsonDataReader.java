package ARMS_APITEST_RESTASSURED.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode loadJsonData(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
}
