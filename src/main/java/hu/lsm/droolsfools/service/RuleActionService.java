package hu.lsm.droolsfools.service;

import hu.lsm.droolsfools.dto.IncomingData;

public interface RuleActionService {

    void generateEvent(IncomingData incomingData);

}
