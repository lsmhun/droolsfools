package hu.lsm.droolsfools.compiler;

import hu.lsm.droolsfools.entity.EEARule;
import org.springframework.stereotype.Component;

@Component
public class EEARuleConverter implements RuleToDroolsConverter {

    private final static String TEMPLATE =
            "";

    @Override
    public String convertRule(EEARule eeaRule) {
        // TODO
        return null;
    }

}
