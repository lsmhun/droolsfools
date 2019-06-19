package hu.lsm.droolsfools.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.lsm.droolsfools.entity.EEARule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DemoRuleRepositoryImpl implements RuleRepository {

    public final Logger LOG = LoggerFactory.getLogger(DemoRuleRepositoryImpl.class);

    private final static String[] DEMO_RULES = {
            "{\"id\":1,\"name\":\"Test rule\",\"enabled\":true,\"eeaRuleConditionGroups\":[{\"id\":1,\"eeaRuleConditions\":[{\"id\":1,\"option\":{\"optionName\":\"ERROR_CODE\"},\"ruleOperator\":\"EQUAL\",\"value\":\"200\"}]}],\"eeaRuleActions\":[{\"id\":1,\"ruleActionType\":\"GENERATE_EVENT\"}]}"
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
        Optional<EEARule> eeaRule = eeaRuleList.stream().filter(k -> k.getId() == id).findFirst();
        return eeaRule.orElseGet(null);
    }

    @Override
    public List<EEARule> findByRepositoryId(String repositoryId, boolean enabled) {
        List<EEARule> retVal;
        if(enabled){
            retVal = eeaRuleList.stream().filter(eeaRule -> eeaRule.isEnabled()).collect(Collectors.toList());
        }else{
            retVal = eeaRuleList;
        }
        return retVal;
    }

    @Override
    public void saveOrUpdate(EEARule eeaRule) {
        eeaRuleList =  eeaRuleList.stream().filter(k -> k.getId() != eeaRule.getId()).collect(Collectors.toList());
        eeaRuleList.add(eeaRule);
    }
}
