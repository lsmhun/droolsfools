package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.compiler.EEARuleConverter;
import hu.lsm.droolsfools.dao.RuleRepository;
import hu.lsm.droolsfools.dto.IncomingData;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.entity.ResultEvent;
import hu.lsm.droolsfools.service.RuleActionService;
import hu.lsm.droolsfools.service.RuleRunnerService;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
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
    public void setUp() {
        List<EEARule> ruleList = Collections.singletonList(TestUtil.getEEARule());
        when(ruleRepository.findByRepositoryId(anyString(), anyBoolean())).thenReturn(ruleList);
        when(eeaRuleConverter.convertRule(any(EEARule.class))).thenReturn(TestUtil.RESULT_RULE_TEXT);
    }

    @Test
    public void ruleTriggered() {
        IncomingData incomingData = new IncomingData();
        incomingData.setErrorCode(200);
        ruleRunnerService.runRules(RuleRunnerService.DEFAULT_REPO, incomingData);
        verify(ruleActionService, times(1)).processResultEvent(any(ResultEvent.class));
        //assertNotEquals("OK", incomingData.getMessage());
    }

    @Ignore
    @Test
    public void ruleNotTriggered() {
        IncomingData incomingData = new IncomingData();
        incomingData.setErrorCode(123);
        ruleRunnerService.runRules(RuleRunnerService.DEFAULT_REPO, incomingData);
        //verify(ruleActionService, times(0)).processResultEvent(any(ResultEvent.class));
        //assertEquals("Message", incomingData.());
    }
}