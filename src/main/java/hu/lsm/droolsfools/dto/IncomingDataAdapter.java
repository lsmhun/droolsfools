package hu.lsm.droolsfools.dto;

import hu.lsm.droolsfools.entity.ResultEvent;
import hu.lsm.droolsfools.service.RuleActionService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class IncomingDataAdapter {

    @Getter
    private IncomingData incomingData;

    @Getter
    private ResultEventAdapter resultEventAdapter;

    @Getter @Setter
    private RuleActionService ruleActionService;

    public IncomingDataAdapter(IncomingData incomingData){
        this.incomingData = incomingData;
        resultEventAdapter = new ResultEventAdapter(new ResultEvent());
    }



}
