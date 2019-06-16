package hu.lsm.droolsfools.service.impl;

import hu.lsm.droolsfools.dao.RuleRepository;
import hu.lsm.droolsfools.entity.EEARule;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class KieSessionInventory {

    @Autowired
    private RuleRepository ruleRepository;

    private Map<String, KieSession> kieSessionMap = new HashMap<>();

    private KieServices kieServices;
    private KieSession kieSession;

    @PostConstruct
    private void init() {
        try {

            kieServices = KieServices.Factory.get();

            // Set up listeners.
            //kieSession.addEventListener(new DebugAgendaEventListener());
            //kieSession.addEventListener(new DebugRuleRuntimeEventListener());

            // Set up a file-based audit logger.
            //KieRuntimeLogger logger = KieServices.get().getLoggers().newFileLogger(kieSession, "./target/helloworld");

            // Set up a ThreadedFileLogger so that the audit view reflects events while debugging.
            //KieRuntimeLogger logger2 = kieServices.getLoggers().newThreadedFileLogger( kieSession, "./target/helloworld", 1000 );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        List<String> rules = Arrays.asList("HelloWorld.drl");
        for (String rule : rules) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(rule));
        }
        return kieFileSystem;

    }

    public KieContainer getKieContainer() throws IOException {
        getKieRepository();

        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();

        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kContainer;

    }*/

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    /*
    private KieSession initKieSession(){
        getKieRepository();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        kieFileSystem.write(ResourceFactory.newClassPathResource("HelloWorld.drl"));

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();

        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieSession myKieSession = kContainer.newKieSession();
        return myKieSession;
    }*/

    public KieSession getKieSession(String repositoryId){
        KieSession kieSession = kieSessionMap.get(repositoryId);
        if( kieSession == null){
            kieSession = initKieSession(repositoryId);
            kieSessionMap.put(repositoryId, kieSession);
        }
        return kieSession;
    }



    private KieSession initKieSession(String repositoryId) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();

        KieRepository kieRepository = kieServices.getRepository();

        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();

        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);

        KieSession ksession = kieContainer.newKieSession();

        return ksession;
    }

}
