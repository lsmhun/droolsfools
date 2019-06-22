package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.dto.IncomingData;
import hu.lsm.droolsfools.dto.IncomingDataAdapter;
import hu.lsm.droolsfools.service.KieSessionInventory;
import hu.lsm.droolsfools.service.RuleActionService;
import hu.lsm.droolsfools.service.RuleRunnerService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
https://github.com/eugenp/tutorials
 */
@Service
public class RuleRunnerServiceImpl implements RuleRunnerService {

    private final KieSessionInventory kieSessionInventory;

    private final RuleActionService ruleActionService;

    @Autowired
    public RuleRunnerServiceImpl(final KieSessionInventory kieSessionInventory, RuleActionService ruleActionService){
        this.kieSessionInventory = kieSessionInventory;
        this.ruleActionService = ruleActionService;
    }

    @Override
    public void runRules(String repositoryId, IncomingData incoming) {
        KieSession kieSession = kieSessionInventory.getKieSession(repositoryId);
        IncomingDataAdapter incomingDataAdapter = new IncomingDataAdapter(incoming);
        kieSession.insert(incomingDataAdapter);
        kieSession.fireAllRules();
        ruleActionService.processResultEvent(incomingDataAdapter.getResultEventAdapter().getResultEvent());
    }
}
