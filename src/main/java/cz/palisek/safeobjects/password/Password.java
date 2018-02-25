package cz.palisek.safeobjects.password;

import java.util.Objects;

/**
 * @author Lubos.Palisek
 * <p>
 * Implementation of safe password domain object.
 * It protects password value from being printed in plaintext.
 */
public class Password {

    private final String value;

    public Password(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Password value can't be null");
        }
        this.value = value;
    }

    public static Password empty() {
        return new Password("");
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value.length() == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public String toString() {
        return "password=*****";
    }
}
