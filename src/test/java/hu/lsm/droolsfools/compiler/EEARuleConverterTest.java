package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EEARuleConverterTest {

    private EEARule eeaRule;

    private final EEARuleConverter eeaRuleConverter = new EEARuleConverter();


    @BeforeEach
    public void setUp() {
        eeaRule = TestUtil.getEEARule();
    }

    @Test
    public void testConvertSimpleRule() {
        String eeaRuleText = eeaRuleConverter.convertRule(eeaRule);
        assertEquals(TestUtil.RESULT_RULE_TEXT, eeaRuleText);
    }
}