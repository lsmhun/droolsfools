package hu.lsm.droolsfools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class EEARuleAction implements Serializable {
    private Long id;
    private RuleActionType ruleActionType = RuleActionType.GENERATE_EVENT;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum RuleActionType{
        GENERATE_EVENT("GENERATE_EVENT");

        private String actionType;

        RuleActionType(String actionType){
            this.actionType = actionType;
        }
    }
}
