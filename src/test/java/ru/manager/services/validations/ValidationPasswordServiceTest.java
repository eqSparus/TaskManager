package ru.manager.services.validations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationPasswordServiceTest {

    @Test
    void validFalse() {
        var password = "root";
        var valid = new ValidationPasswordService();
        assertFalse(valid.isValid(password));
    }

    @Test
    void validTrue() {
        var password = "rootroot";
        var valid = new ValidationPasswordService();
        assertTrue(valid.isValid(password));
    }

}
