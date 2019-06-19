package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.compiler.EEARuleConverter;
import hu.lsm.droolsfools.dao.RuleRepository;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.service.KieSessionInventory;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/kiegroup/drools/blob/master/drools-examples-api/kiefilesystem-example/src/main/java/org/drools/example/api/kiefilesystem/KieFileSystemExample.java
 */
@Service
public class KieSessionInventoryImpl implements KieSessionInventory {

    private final RuleRepository ruleRepository;

    private final EEARuleConverter eeaRuleConverter;

    private Map<String, KieSession> kieSessionMap = new HashMap<>();

    private KieServices kieServices;

    @Autowired
    public KieSessionInventoryImpl(final RuleRepository ruleRepository,
                                   final EEARuleConverter eeaRuleConverter){
        this.ruleRepository = ruleRepository;
        this.eeaRuleConverter = eeaRuleConverter;
    }

    @PostConstruct
    private void init() {
        kieServices = KieServices.Factory.get();
    }

    @Override
    public KieSession getKieSession(String repositoryId){
        KieSession kieSession = kieSessionMap.get(repositoryId);
        if( kieSession == null){
            kieSession = initKieSession(repositoryId);
            kieSessionMap.put(repositoryId, kieSession);
        }
        return kieSession;
    }

    private List<EEARule> getActiveRules(String repositoryId){
        return ruleRepository.findByRepositoryId(repositoryId, true);
    }


    private KieFileSystem addRules(String repositoryId){
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        for(EEARule eeaRule: getActiveRules(repositoryId)){
            String ruleText = eeaRuleConverter.convertRule(eeaRule);
            String ruleName = eeaRule.getName().trim().replaceAll("\\w", "");
            //
            kieFileSystem.write("src/main/resources/hu/lsm/droolsfools/rules/" + eeaRule.hashCode() + ".drl", ruleText);
        }
        //kieFileSystem.write(ResourceFactory.newClassPathResource("HelloWorld.drl"));

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }

        return kieFileSystem;
    }

    private void addLoggingListeners(KieSession kieSession){
        // Set up listeners.
        kieSession.addEventListener(new DebugAgendaEventListener());
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());

        // Set up a file-based audit logger.
        KieRuntimeLogger logger = KieServices.get().getLoggers().newFileLogger(kieSession, "./target/helloworld");

        // Set up a ThreadedFileLogger so that the audit view reflects events while debugging.
        //KieRuntimeLogger logger2 = kieServices.getLoggers().newThreadedFileLogger( kieSession, "./target/helloworld", 1000 );

    }

    private KieSession initKieSession(String repositoryId) {

        if(kieServices == null){
            init();
        }
        addRules(repositoryId);

        KieRepository kieRepository = kieServices.getRepository();

        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();

        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);

        KieSession ksession = kieContainer.newKieSession();

        addLoggingListeners(ksession);

        return ksession;
    }

}
