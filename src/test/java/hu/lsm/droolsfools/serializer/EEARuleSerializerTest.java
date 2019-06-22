package hu.lsm.droolsfools.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EEARuleSerializerTest {

    private static final String expectedEEARule = "{\"id\":1,\"name\":\"Test rule\",\"enabled\":true,\"priority\":100,\"eeaRuleConditionGroups\":[{\"id\":1,\"eeaRuleConditions\":[{\"id\":1,\"option\":{\"optionName\":\"errorCode\"},\"ruleOperator\":\"EQUAL\",\"value\":\"200\"}]}],\"eeaRuleActions\":[{\"id\":1,\"ruleActionType\":\"POPULATE_MESSAGE\",\"value\":\"Message\"}]}";

    @Test
    public void testEEARuleSerializer() throws JsonProcessingException {
        EEARule eeaRule = TestUtil.getEEARule();
        ObjectMapper objectMapper = new ObjectMapper();
        String eeaRuleStr = objectMapper.writeValueAsString(eeaRule);
        assertEquals(expectedEEARule, eeaRuleStr);
    }

    @Test
    public void testEEARuleDeserializer() throws IOException {
        EEARule testEEARule = TestUtil.getEEARule();
        ObjectMapper objectMapper = new ObjectMapper();
        EEARule resultEEARule = objectMapper.readValue(expectedEEARule, EEARule.class);
        assertEquals(testEEARule, resultEEARule);
    }

}
