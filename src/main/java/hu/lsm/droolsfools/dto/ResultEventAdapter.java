package hu.lsm.droolsfools.dto;

import hu.lsm.droolsfools.entity.ResultEvent;
import lombok.Getter;

public class ResultEventAdapter {

    @Getter
    private ResultEvent resultEvent;

    public ResultEventAdapter(ResultEvent resultEvent){
        this.resultEvent = resultEvent;
        this.resultEvent.setTimestamp(System.currentTimeMillis());
    }

    public void populateResultEventType(String eventTypeStr){
        ResultEvent.ResultEventType resultEventType = ResultEvent.ResultEventType.valueOf(eventTypeStr);
        if(resultEventType != null){
            this.resultEvent.setEventType(resultEventType);
        }
    }

    public void populateMessage(String message){
        this.resultEvent.setMessage(message);
    }

    public void populatePoint(int point){
        this.resultEvent.setPoint(point);
    }

    public void populateDefaultValues(IncomingData incomingData){
        this.resultEvent.setErrorCode(incomingData.getErrorCode());
    }

}
