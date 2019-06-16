package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;

public interface RuleToDroolsCompiler {

    String convertRule(EEARule eeaRule);
    void compile();

}
