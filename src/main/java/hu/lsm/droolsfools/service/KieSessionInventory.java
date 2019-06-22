package hu.lsm.droolsfools.service;

import org.kie.api.runtime.KieSession;

public interface KieSessionInventory {

    KieSession getKieSession(String repositoryId);

    void resetKieSession(String repositoryId);

}
