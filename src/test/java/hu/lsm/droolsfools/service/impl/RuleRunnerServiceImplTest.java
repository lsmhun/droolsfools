package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.compiler.EEARuleConverter;
import hu.lsm.droolsfools.dao.RuleRepository;
import hu.lsm.droolsfools.dto.IncomingData;
import hu.lsm.droolsfools.dto.IncomingDataAdapter;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.service.RuleActionService;
import hu.lsm.droolsfools.service.RuleRunnerService;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class RuleRunnerServiceImplTest {

    @Mock
    private RuleRepository ruleRepository = mock(RuleRepository.class);

    @Mock
    private EEARuleConverter eeaRuleConverter = mock(EEARuleConverter.class);

    @Mock
    private RuleActionService ruleActionService = mock(RuleActionServiceImpl.class);

    private KieSessionInventoryImpl kieSessionInventory = new KieSessionInventoryImpl(ruleRepository, eeaRuleConverter);

    private RuleRunnerServiceImpl ruleRunnerService = new RuleRunnerServiceImpl(kieSessionInventory, ruleActionService);

    @Before
    public void setUp() throws Exception {
        List<EEARule> ruleList = Collections.singletonList(TestUtil.getEEARule());
        when(ruleRepository.findByRepositoryId(anyString(), anyBoolean())).thenReturn(ruleList);
        when(eeaRuleConverter.convertRule(any(EEARule.class))).thenReturn(TestUtil.HELLO_WORLD_DRL);

    }

    @Test
    public void ruleTriggered() {
        IncomingData incomingData = new IncomingData();
        incomingData.setErrorCode(200);
        ruleRunnerService.runRules(RuleRunnerService.DEFAULT_REPO, incomingData);
        verify(ruleActionService, times(1)).generateEvent(any(IncomingData.class));
        //assertNotEquals("OK", incomingData.getMessage());
    }

    @Test
    public void ruleNotTriggered() {
        IncomingData incomingData = new IncomingData();
        incomingData.setErrorCode(123);
        ruleRunnerService.runRules(RuleRunnerService.DEFAULT_REPO, incomingData);
        verify(ruleActionService, times(0)).generateEvent(any(IncomingData.class));
        //assertEquals("OK", incomingData.getMessage());
    }
}