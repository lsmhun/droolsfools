package hu.lsm.droolsfools.util;

import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.entity.EEARuleAction;
import hu.lsm.droolsfools.entity.EEARuleCondition;
import hu.lsm.droolsfools.entity.EEARuleConditionGroup;
import hu.lsm.droolsfools.entity.RuleConditionOption;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestUtil {

    public static final String HELLO_WORLD_DRL = "import hu.lsm.droolsfools.dto.IncomingDataAdapter;\n" +
            "\n" +
            "rule \"Hello World\"\n" +
            "      dialect \"mvel\"\n" +
            "  when\n" +
            "    m : IncomingDataAdapter( errorCode == 200, value == \"SERVER RESPONSE\", message : message )\n" +
            "  then\n" +
            "\n" +
            "    System.out.println( message );\n" +
            "    //modify ( m ) { message = \"OK\" };\n" +
            "    m.setMessage(\"OK\");\n" +
            "    System.out.println( message );\n" +
            "end";

    public static EEARuleCondition getEEARuleCondition(){
        EEARuleCondition eeaRuleCondition = new EEARuleCondition();
        eeaRuleCondition.setId(1L);
        RuleConditionOption ruleConditionOption = new RuleConditionOption();
        ruleConditionOption.setOptionName("ERROR_CODE");
        eeaRuleCondition.setOption(ruleConditionOption);
        eeaRuleCondition.setRuleOperator(EEARuleCondition.RuleOperator.EQUAL);
        eeaRuleCondition.setValue("200");
        return eeaRuleCondition;
    }
    public static EEARuleConditionGroup getEEARuleConditionGroup(){
        EEARuleConditionGroup eeaRuleConditionGroup = new EEARuleConditionGroup();
        eeaRuleConditionGroup.setId(1L);
        eeaRuleConditionGroup.setEeaRuleConditions(Collections.singletonList(getEEARuleCondition()));
        return eeaRuleConditionGroup;
    }

    public static EEARuleAction getEEARuleAction(){
        EEARuleAction eeaRuleAction = new EEARuleAction();
        eeaRuleAction.setId(1L);
        eeaRuleAction.setRuleActionType(EEARuleAction.RuleActionType.GENERATE_EVENT);
        return eeaRuleAction;
    }

    public static EEARule getEEARule(){
        EEARule eeaRule = new EEARule();
        eeaRule.setId(1L);
        eeaRule.setName("Test rule");
        eeaRule.setEnabled(true);
        eeaRule.setEeaRuleConditionGroups(Collections.singletonList(getEEARuleConditionGroup()));
        eeaRule.setEeaRuleActions(Collections.singletonList(getEEARuleAction()));
        return eeaRule;
    }

}
