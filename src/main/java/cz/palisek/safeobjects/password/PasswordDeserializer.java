package cz.palisek.safeobjects.password;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Lubos.Palisek
 */
public class PasswordDeserializer extends StdDeserializer<Password> {

    public PasswordDeserializer() {
        this(null);
    }

    public PasswordDeserializer(Class<?> vc) {
        super(vc);
    }

    public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = p.getText();
        return new PasswordDto(text);
    }
}
