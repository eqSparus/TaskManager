package ru.manager.services.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import ru.manager.models.dto.UserDtoRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserJsonServiceTest {

    @Test
    void toJsonTest() throws JsonProcessingException {

        var jsonTest = "{\"login\":\"Sparus\",\"password\":\"rootroot\"}";
        var user = new UserDtoRequest("Sparus", "rootroot");

        var json = ParserJsonService.toJson(user);

        assertEquals(jsonTest, json);

    }

    @Test
    void toObjectTest() throws JsonProcessingException {

        var jsonTest = "{\"login\":\"Sparus\",\"password\":\"rootroot\"}";

        var user = ParserJsonService.toObject(jsonTest, UserDtoRequest.class);

        assertEquals("Sparus",user.getLogin());
        assertEquals("rootroot",user.getPassword());
    }

}
