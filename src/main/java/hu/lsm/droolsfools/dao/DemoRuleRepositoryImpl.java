package hu.lsm.droolsfools.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.entity.EEARuleAction;
import hu.lsm.droolsfools.entity.EEARuleCondition;
import hu.lsm.droolsfools.entity.EEARuleConditionGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemoRuleRepositoryImpl implements RuleRepository {

    private final Logger LOG = LoggerFactory.getLogger(DemoRuleRepositoryImpl.class);

    private final static String[] DEMO_RULES = {
        "{\"id\":1,\"name\":\"Test rule\",\"enabled\":true,\"priority\":100,\"eeaRuleConditionGroups\":[{\"id\":1,\"eeaRuleConditions\":[{\"id\":1,\"option\":{\"optionName\":\"errorCode\"},\"ruleOperator\":\"EQUAL\",\"value\":\"200\"}]}],\"eeaRuleActions\":[{\"id\":1,\"ruleActionType\":\"POPULATE_MESSAGE\",\"value\":\"Message\"}]}"
    };

    private List<EEARule> eeaRuleList = new ArrayList<>();


    @PostConstruct
    private void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        for(String json: DEMO_RULES){
            try {
                eeaRuleList.add(objectMapper.readValue(json, EEARule.class));
            } catch (Exception ex){
                LOG.error("Rule is not valid", ex);
            }
        }
    }

    @Override
    public EEARule findById(Long id) {
        Optional<EEARule> eeaRule = eeaRuleList.stream().filter(k -> id.equals(k.getId())).findFirst();
        return eeaRule.get();
    }

    @Override
    public List<EEARule> findByRepositoryId(String repositoryId, boolean enabled) {
        List<EEARule> retVal;
        if(enabled){
            retVal = eeaRuleList.stream().filter(EEARule::isEnabled).collect(Collectors.toList());
        }else{
            retVal = eeaRuleList;
        }
        return retVal;
    }

    /**
     * JPA can handle that better, but for demo, it is enough
     * @param eeaRule
     */
    private void generateIdForEEARule(EEARule eeaRule){
        if(eeaRule.getId() == null || eeaRule.getId().compareTo(0L) == 0){
            eeaRule.setId(System.currentTimeMillis());
        }
        if(eeaRule.getEeaRuleConditionGroups() != null){

            for(EEARuleConditionGroup group: eeaRule.getEeaRuleConditionGroups()){
                if(group.getId() == null || group.getId().compareTo(0L) == 0){
                    group.setId(System.currentTimeMillis());
                }
                for(EEARuleCondition cond: group.getEeaRuleConditions()){
                    if(cond.getId() == null || cond.getId().compareTo(0L) == 0){
                        cond.setId(System.currentTimeMillis());
                    }
                }
            }
        }
        if(eeaRule.getEeaRuleActions() != null){
            for(EEARuleAction action: eeaRule.getEeaRuleActions()){
                if(action.getId() == null || action.getId().compareTo(0L) == 0){
                    action.setId(System.currentTimeMillis());
                }
            }
        }

    }

    @Override
    public void saveOrUpdate(EEARule eeaRule) {
        if(eeaRule == null){
            LOG.error("Unable to save null value");
            return;
        }
        eeaRuleList =  eeaRuleList.stream().filter(k -> !k.getId().equals(eeaRule.getId())).collect(Collectors.toList());
        generateIdForEEARule(eeaRule);
        eeaRuleList.add(eeaRule);
    }
}
