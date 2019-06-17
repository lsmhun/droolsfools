package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;

public interface RuleToDroolsConverter {

    String convertRule(EEARule eeaRule);

}
