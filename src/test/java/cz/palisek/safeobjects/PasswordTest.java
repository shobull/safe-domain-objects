/*
 * Copyright 2002-2018 Ataccama Software, s.r.o. All rights reserved.
 * ATACCAMA PROPRIETARY/CONFIDENTIAL.
 * Any use of this source code is prohibited without prior written
 * permission of Ataccama Software, s.r.o.; Czech Republic, Id.no.: 28235550
 * http://www.ataccama.com
 */
package cz.palisek.safeobjects;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.palisek.safeobjects.password.Password;
import cz.palisek.safeobjects.password.PasswordDto;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2/21/2018.
 *
 * @author Lubos.Palisek
 */
public class PasswordTest {

    @Test
    public void new_instance_test() {
        Exception caught = null;
        try {
            new Password(null);
        } catch (IllegalArgumentException e) {
            caught = e;
        }
        Assert.assertNotNull(caught);
        Assert.assertEquals("Password value can't be null", caught.getMessage());

        Assert.assertEquals(new Password(""), Password.empty());
        Assert.assertTrue(Password.empty().isEmpty());
    }

    @Test
    public void tostring_test() {
        Assert.assertEquals("password=*****", new Password("secret-password").toString());
    }

    @Test
    public void equality_test() {
        Password pw1 = new Password("secret-password");
        Password pw2 = new Password("secret-password");

        Assert.assertEquals(pw1, pw2);

        Password anotherPw = new Password("another-password");

        Assert.assertNotEquals(pw1, anotherPw);
    }

    @Test
    public void serialization_test() throws JsonProcessingException {
        UserDto userDto = new UserDto();
        userDto.setPassword(new PasswordDto("secret-password"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userDto);

        Assert.assertEquals("{\"password\":\"secret-password\"}", json);
    }

    @Test
    public void deserialization_test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"password\":\"secret-password\"}";
        UserDto userDto = objectMapper.readValue(json, UserDto.class);
        Assert.assertEquals("secret-password", userDto.getPassword().getValue());

        String json2 = "{\"password\": null}";
        UserDto userDto2 = objectMapper.readValue(json2, UserDto.class);
        Assert.assertNull(userDto2.getPassword());
    }

    private static class UserDto {

        private PasswordDto password;

        public PasswordDto getPassword() {
            return password;
        }

        public void setPassword(PasswordDto password) {
            this.password = password;
        }
    }

}
