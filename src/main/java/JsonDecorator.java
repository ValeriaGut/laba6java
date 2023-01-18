import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JsonDecorator implements IReadWriter {
    @Override
    public void Read(String file_name, Dictionary dictionary) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Dictionary dictionary1 = objectMapper.readValue(new File(file_name), Dictionary.class);
        dictionary = dictionary1;
    }

    @Override
    public void Write(String file_name, Dictionary dictionary) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        FileOutputStream fileOutputStream = new FileOutputStream(file_name);
        objectMapper.writeValue(fileOutputStream, dictionary);
    }
}
