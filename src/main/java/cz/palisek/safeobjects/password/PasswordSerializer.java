package cz.palisek.safeobjects.password;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Lubos.Palisek
 */
public class PasswordSerializer extends StdSerializer<PasswordDto> {

    public PasswordSerializer() {
        this(null);
    }

    public PasswordSerializer(Class<PasswordDto> t) {
        super(t);
    }

    public void serialize(PasswordDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getValue());
    }
}
