package hu.lsm.droolsfools.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EEARule  implements Serializable {

    private Long id;
    private String name;
    private boolean enabled;

    private List<EEARuleConditionGroup> eeaRuleConditionGroups;
    private List<EEARuleAction> eeaRuleActions;

}
