package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.dto.IncomingData;
import hu.lsm.droolsfools.service.KieSessionInventory;
import hu.lsm.droolsfools.service.RuleRunnerService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
https://github.com/eugenp/tutorials

 */
@Service
public class RuleRunnerServiceImpl implements RuleRunnerService {

    private static final String DEFAULT_REPO = "DEFAULT";

    private final KieSessionInventory kieSessionInventory;

    @Autowired
    public RuleRunnerServiceImpl(final KieSessionInventory kieSessionInventory){
        this.kieSessionInventory = kieSessionInventory;
    }

    @Override
    public void runRules(IncomingData incoming) {
        KieSession kieSession = kieSessionInventory.getKieSession(DEFAULT_REPO);
        kieSession.insert(incoming);
        kieSession.fireAllRules();

    }
}
