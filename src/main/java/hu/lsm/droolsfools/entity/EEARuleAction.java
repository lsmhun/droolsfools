package hu.lsm.droolsfools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class EEARuleAction implements Serializable {
    private Long id;
    private RuleActionType ruleActionType;

    private String value;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum RuleActionType {
        POPULATE_DEFAULT_VALUES("POPULATE_DEFAULT_VALUES"),
        POPULATE_RESULT_EVENT_TYPE("POPULATE_RESULT_EVENT_TYPE"),
        POPULATE_MESSAGE("POPULATE_MESSAGE"),
        POPULATE_POINT("POPULATE_RESULT_EVENT_TYPE");

        private String actionType;

        RuleActionType(String actionType) {
            this.actionType = actionType;
        }
    }
}
