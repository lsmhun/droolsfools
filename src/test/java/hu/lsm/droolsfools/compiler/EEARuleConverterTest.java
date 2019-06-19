package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EEARuleConverterTest {

    private EEARule eeaRule;

    private EEARuleConverter eeaRuleConverter = new EEARuleConverter();

    @Before
    public void setUp() throws Exception {
        eeaRule = TestUtil.getEEARule();
    }

    @Test
    public void testConvertSimpleRule() {
        String eeaRuleText = eeaRuleConverter.convertRule(eeaRule);

    }
}