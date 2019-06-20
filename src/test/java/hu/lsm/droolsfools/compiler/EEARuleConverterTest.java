package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EEARuleConverterTest {

    private EEARule eeaRule;

    private EEARuleConverter eeaRuleConverter = new EEARuleConverter();

    private  static final String RESULT_RULE_TEXT = "" +
            "import hu.lsm.droolsfools.dto.IncomingDataAdapter;\n" +
            "\n" +
            "rule \"Test rule\"\n" +
            "      dialect \"mvel\"\n" +
            "  when\n" +
            "    ida : IncomingDataAdapter(\n" +
            "             incomingData.errorCode = 200             )\n" +
            "  then\n" +
            "    System.out.println( ida );\n" +
            "    // \n" +
            "    ida.triggerActions();\n" +
            "\n" +
            "end";

    @Before
    public void setUp() throws Exception {
        eeaRule = TestUtil.getEEARule();
    }

    @Test
    public void testConvertSimpleRule() {
        String eeaRuleText = eeaRuleConverter.convertRule(eeaRule);
        assertEquals(RESULT_RULE_TEXT, eeaRuleText);
    }
}