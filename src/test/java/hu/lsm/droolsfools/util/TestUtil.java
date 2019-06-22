package hu.lsm.droolsfools.util;

import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.entity.EEARuleAction;
import hu.lsm.droolsfools.entity.EEARuleCondition;
import hu.lsm.droolsfools.entity.EEARuleConditionGroup;
import hu.lsm.droolsfools.entity.RuleConditionOption;

import java.util.Collections;

public class TestUtil {

    public static final String RESULT_RULE_TEXT = "" +
            "import hu.lsm.droolsfools.dto.IncomingDataAdapter;\n" +
            "import hu.lsm.droolsfools.dto.ResultEventAdapter;\n" +
            "import hu.lsm.droolsfools.entity.ResultEvent;\n" +
            "\n" +
            "rule \"Test rule\"\n" +
            "      dialect \"mvel\"\n" +
            "  when\n" +
            "    ida : IncomingDataAdapter(\n" +
            "             incomingData.errorCode == 200             )\n" +
            "  then\n" +
            "    System.out.println( ida );\n" +
            "    // \n" +
            "    ResultEventAdapter rea = ida.getResultEventAdapter();\n" +
            "rea.populateMessage(\"Message\");\n" +
            "\n" +
            "end";

    public static EEARuleCondition getEEARuleCondition(){
        EEARuleCondition eeaRuleCondition = new EEARuleCondition();
        eeaRuleCondition.setId(1L);
        RuleConditionOption ruleConditionOption = new RuleConditionOption();
        ruleConditionOption.setOptionName("errorCode");
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
        eeaRuleAction.setRuleActionType(EEARuleAction.RuleActionType.POPULATE_MESSAGE);
        eeaRuleAction.setValue("Message");
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
