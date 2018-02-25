package cz.palisek.safeobjects.password;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Lubos.Palisek
 * <p>
 * Extends {@link cz.palisek.safeobjects.password.Password} for using it as part of JSON object.
 * Password value is not (de)serialized as new object but as field. It means your API is not affected by using safe domain primitive.
 *
 * E.g.:
 *
 * class UserDto {
 *     private String username;
 *     private PasswordDto pw;
 * }
 *
 * is represented in JSON:
 *
 * {
 *     "username": "<username_value>",
 *     "pw": "<password_value>"
 * }
 */
@JsonSerialize(using = PasswordSerializer.class)
@JsonDeserialize(using = PasswordDeserializer.class)
public class PasswordDto extends Password {
    public PasswordDto(String value) {
        super(value);
    }
}
