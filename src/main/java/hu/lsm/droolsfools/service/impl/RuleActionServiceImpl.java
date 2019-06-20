package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.dto.IncomingData;
import hu.lsm.droolsfools.service.RuleActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RuleActionServiceImpl implements RuleActionService {

    private static final Logger LOG = LoggerFactory.getLogger(RuleActionServiceImpl.class);
    @Override
    public void generateEvent(IncomingData incomingData) {
        // TODO
        LOG.info("EVENT");
        LOG.info("" + incomingData);
    }
}
