package at.spengergasse.boignerdbiaptitudetest.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonSchemaTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void givenInvalidInput_whenValidation_thenInvalid() throws IOException{
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                JsonSchemaTest.class.getResourceAsStream("/schema.json"));
        JsonNode jsonNode = mapper.readTree(
                JsonSchemaTest.class.getResourceAsStream("/department_invalid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isNotEmpty().asString().contains("name");
    }

    @Test
    public void givenValidInput_whenValidating_thenValid() throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                JsonSchemaTest.class.getResourceAsStream("/schema.json"));
        JsonNode jsonNode = mapper.readTree(
                JsonSchemaTest.class.getResourceAsStream("/department_valid.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        assertThat(errors).isEmpty();
    }
}
