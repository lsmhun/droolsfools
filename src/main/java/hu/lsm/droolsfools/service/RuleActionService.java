package hu.lsm.droolsfools.service;

import hu.lsm.droolsfools.entity.ResultEvent;

public interface RuleActionService {

    void processResultEvent(ResultEvent resultEvent);

}
