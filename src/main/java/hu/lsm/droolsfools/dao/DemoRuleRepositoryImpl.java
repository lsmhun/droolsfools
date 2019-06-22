package hu.lsm.droolsfools.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.lsm.droolsfools.entity.EEARule;
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

    @Override
    public void saveOrUpdate(EEARule eeaRule) {
        eeaRuleList =  eeaRuleList.stream().filter(k -> k.getId().equals(eeaRule.getId())).collect(Collectors.toList());
        eeaRuleList.add(eeaRule);
    }
}
