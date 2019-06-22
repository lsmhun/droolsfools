package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.entity.EEARuleAction;
import hu.lsm.droolsfools.entity.EEARuleCondition;
import hu.lsm.droolsfools.entity.EEARuleConditionGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EEARuleConverter implements RuleToDroolsConverter {

    private final static String TEMPLATE =
            "import hu.lsm.droolsfools.dto.IncomingDataAdapter;\n" +
                    "import hu.lsm.droolsfools.dto.ResultEventAdapter;\n" +
                    "import hu.lsm.droolsfools.entity.ResultEvent;\n" +
                    "\n" +
                    "rule \"%s\"\n" +
                    "      dialect \"mvel\"\n" +
                    "  when\n" +
                    "    ida : IncomingDataAdapter(\n" +
                    "            %s " +
                    "            )\n" +
                    "  then\n" +
                    "    System.out.println( ida );\n" +
                    "    // \n" +
                    "    %s\n" +
                    "end";

    private String generateCondition(EEARuleCondition eeaRuleCondition){
        String ruleCond = "";
        ruleCond += " incomingData." + eeaRuleCondition.getOption().getOptionName() + " ";
        ruleCond += eeaRuleCondition.getRuleOperator().getOperator() + " ";
        ruleCond += eeaRuleCondition.getValue();
        return ruleCond;
    }

    private String generateConditionGroup(EEARuleConditionGroup eeaRuleConditionGroup){
        String whenBlock = "";
        List<String> conditions = new ArrayList<>(eeaRuleConditionGroup.getEeaRuleConditions().size());
        for(EEARuleCondition eeaRuleCondition : eeaRuleConditionGroup.getEeaRuleConditions()){
            conditions.add(generateCondition(eeaRuleCondition));
        }
        whenBlock += String.join( " && ", conditions);
        return whenBlock;
    }

    private String generateWhenBlock(EEARule eeaRule){
        List<String> conditionGroups = new ArrayList<>(eeaRule.getEeaRuleConditionGroups().size());
        for(EEARuleConditionGroup eeaRuleConditionGroup : eeaRule.getEeaRuleConditionGroups()){
            conditionGroups.add(generateConditionGroup(eeaRuleConditionGroup));
        }
        String whenBlock = String.join(" || ", conditionGroups);
        return whenBlock;
    }

    private String generateRuleAction(EEARuleAction eeaRuleAction){
        String ruleAction = "";
        switch (eeaRuleAction.getRuleActionType()) {
            case POPULATE_DEFAULT_VALUES:
                ruleAction += "rea.populateDefaultValues(ida.getIncomingData());\n";
                break;
            case POPULATE_MESSAGE:
                ruleAction += "rea.populateMessage(\"" + eeaRuleAction.getValue() + "\");\n";
                break;
            case POPULATE_RESULT_EVENT_TYPE:
                ruleAction += "rea.populateEventType(" + eeaRuleAction.getValue() + ");\n";
                break;
        }

        return ruleAction;
    }

    private String generateActionGroup(EEARule eeaRule){
        String actionBlock = "ResultEventAdapter rea = ida.getResultEventAdapter();\n";
        List<String> actions = new ArrayList<>(eeaRule.getEeaRuleActions().size());
        for(EEARuleAction eeaRuleAction : eeaRule.getEeaRuleActions()){
            actions.add(generateRuleAction(eeaRuleAction));
        }
        actionBlock += String.join(" ;\n ", actions);
        return actionBlock;
    }

    @Override
    public String convertRule(EEARule eeaRule) {
        // TODO
        String name = eeaRule.getName().trim().replaceAll("\"", "");

        String whenBlock = generateWhenBlock(eeaRule);

        String actionBlock = generateActionGroup(eeaRule);

        String ruleStr = String.format(TEMPLATE, name, whenBlock, actionBlock);
        return ruleStr;
    }

}
