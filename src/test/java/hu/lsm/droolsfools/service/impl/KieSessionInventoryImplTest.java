package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.compiler.EEARuleConverter;
import hu.lsm.droolsfools.dao.RuleRepository;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KieSessionInventoryImplTest {

    @Mock
    private RuleRepository ruleRepository = mock(RuleRepository.class);

    @Mock
    private EEARuleConverter eeaRuleConverter = mock(EEARuleConverter.class);

    private KieSessionInventoryImpl kieSessionInventory;

    @Before
    public void setUp(){
        List<EEARule> ruleList = Collections.singletonList(TestUtil.getEEARule());
        when(ruleRepository.findByRepositoryId(anyString(), anyBoolean())).thenReturn(ruleList);
        when(eeaRuleConverter.convertRule(any(EEARule.class))).thenReturn(TestUtil.HELLO_WORLD_DRL);
        kieSessionInventory = new KieSessionInventoryImpl(ruleRepository, eeaRuleConverter);
    }

    @Test
    public void getKieSession() {
        KieSession kieSession = kieSessionInventory.getKieSession("eeaRule");
        assertNotNull(kieSession);
    }
}