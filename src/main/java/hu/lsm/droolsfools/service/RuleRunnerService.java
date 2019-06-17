package hu.lsm.droolsfools.service;

import hu.lsm.droolsfools.dto.IncomingData;

public interface RuleRunnerService {

    void runRules(IncomingData incoming);

}
