package hu.lsm.droolsfools.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * This is for OR condition between condition groups
 */
@Data
public class EEARuleConditionGroup implements Serializable {
    private Long id;

    private List<EEARuleCondition> eeaRuleConditions;

}
