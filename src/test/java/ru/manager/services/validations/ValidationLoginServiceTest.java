package ru.manager.services.validations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationLoginServiceTest {

    @Test
    void validTrue() {
        var login = "Sparus";
        var valid = new ValidationLoginService();
        assertTrue(valid.isValid(login));
    }

    @Test
    void validFalse() {
        var login = "";
        var valid = new ValidationLoginService();
        assertFalse(valid.isValid(login));
    }

}
