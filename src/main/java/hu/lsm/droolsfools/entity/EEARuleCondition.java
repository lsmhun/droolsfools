package hu.lsm.droolsfools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class EEARuleCondition implements Serializable {

    private Long id;
    private RuleConditionOption option;
    private RuleOperator ruleOperator = RuleOperator.EQUAL;
    private String value = "";

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum RuleOperator {

        EQUAL("=="), NOT_EQUAL("!="), LT("<"), LEQ("<="), GT(">"), GEQ(">="), REGEXP("(?s).*");

        @Getter
        private String operator;

        RuleOperator(String operator) {
            this.operator = operator;
        }

    }
}
