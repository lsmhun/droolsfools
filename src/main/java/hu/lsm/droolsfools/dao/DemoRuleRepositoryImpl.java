package hu.lsm.droolsfools.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.entity.EEARuleAction;
import hu.lsm.droolsfools.entity.EEARuleCondition;
import hu.lsm.droolsfools.entity.EEARuleConditionGroup;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DemoRuleRepositoryImpl implements RuleRepository {

    private final static String[] DEMO_RULES = {
            "{\"id\":1,\"name\":\"Rule 200\",\"enabled\":true,\"priority\":100,\"eeaRuleConditionGroups\":[{\"id\":1,\"eeaRuleConditions\":[{\"id\":1,\"option\":{\"optionName\":\"errorCode\"},\"ruleOperator\":\"EQUAL\",\"value\":\"200\"}]}],\"eeaRuleActions\":[{\"id\":1,\"ruleActionType\":\"POPULATE_MESSAGE\",\"value\":\"OK\"}]}",
            "{\"id\":2,\"name\":\"Rule 418\",\"enabled\":true,\"priority\":100,\"eeaRuleConditionGroups\":[{\"id\":2,\"eeaRuleConditions\":[{\"id\":2,\"option\":{\"optionName\":\"errorCode\"},\"ruleOperator\":\"EQUAL\",\"value\":\"418\"},{\"id\":3,\"option\":{\"optionName\":\"value\"},\"ruleOperator\":\"EQUAL\",\"value\":\"tea\"}]}],\"eeaRuleActions\":[{\"id\":2,\"ruleActionType\":\"POPULATE_MESSAGE\",\"value\":\"Tea time\"}]}",
            "{\"id\":3,\"name\":\"Rule 404\",\"enabled\":true,\"priority\":100,\"eeaRuleConditionGroups\":[{\"id\":3,\"eeaRuleConditions\":[{\"id\":4,\"option\":{\"optionName\":\"errorCode\"},\"ruleOperator\":\"EQUAL\",\"value\":\"404\"}]}],\"eeaRuleActions\":[{\"id\":1,\"ruleActionType\":\"POPULATE_MESSAGE\",\"value\":\"NOT FOUND\"}]}"
    };

    private List<EEARule> eeaRuleList = new ArrayList<>();


    @PostConstruct
    private void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        for (String json : DEMO_RULES) {
            try {
                eeaRuleList.add(objectMapper.readValue(json, EEARule.class));
            } catch (Exception ex) {
                log.error("Rule is not valid", ex);
            }
        }
    }

    @Override
    public EEARule findById(Long id) {
        Optional<EEARule> eeaRule = eeaRuleList.stream().filter(k -> id.equals(k.getId())).findFirst();
        return eeaRule.orElseThrow(() -> new RuntimeException("Id not found: " + id));
    }

    @Override
    public List<EEARule> findByRepositoryId(String repositoryId, boolean enabled) {
        List<EEARule> retVal;
        if (enabled) {
            retVal = eeaRuleList.stream().filter(EEARule::isEnabled).collect(Collectors.toList());
        } else {
            retVal = eeaRuleList;
        }
        return retVal;
    }

    /**
     * JPA can handle that better, but for demo, it is enough
     *
     * @param eeaRule - rule persistent representation
     */
    private void generateIdForEEARule(EEARule eeaRule) {
        if (eeaRule.getId() == null || eeaRule.getId().compareTo(0L) == 0) {
            eeaRule.setId(System.currentTimeMillis());
        }
        if (eeaRule.getEeaRuleConditionGroups() != null) {

            for (EEARuleConditionGroup group : eeaRule.getEeaRuleConditionGroups()) {
                if (group.getId() == null || group.getId().compareTo(0L) == 0) {
                    group.setId(System.currentTimeMillis());
                }
                for (EEARuleCondition cond : group.getEeaRuleConditions()) {
                    if (cond.getId() == null || cond.getId().compareTo(0L) == 0) {
                        cond.setId(System.currentTimeMillis());
                    }
                }
            }
        }
        if (eeaRule.getEeaRuleActions() != null) {
            for (EEARuleAction action : eeaRule.getEeaRuleActions()) {
                if (action.getId() == null || action.getId().compareTo(0L) == 0) {
                    action.setId(System.currentTimeMillis());
                }
            }
        }

    }

    @Override
    public void saveOrUpdate(EEARule eeaRule) {
        if (eeaRule == null) {
            log.error("Unable to save null value");
            return;
        }
        eeaRuleList = eeaRuleList.stream().filter(k -> !k.getId().equals(eeaRule.getId())).collect(Collectors.toList());
        generateIdForEEARule(eeaRule);
        eeaRuleList.add(eeaRule);
    }
}
