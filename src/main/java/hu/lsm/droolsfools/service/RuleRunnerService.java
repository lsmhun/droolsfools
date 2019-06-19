package hu.lsm.droolsfools.service;

import hu.lsm.droolsfools.dto.IncomingData;

public interface RuleRunnerService {

    String DEFAULT_REPO = "DEFAULT";

    void runRules(String repositoryId, IncomingData incoming);

}
