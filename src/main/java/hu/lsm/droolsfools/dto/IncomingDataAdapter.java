package hu.lsm.droolsfools.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
public class IncomingDataAdapter {

    @Getter
    private IncomingData incomingData;

    public IncomingDataAdapter(IncomingData incomingData){
        this.incomingData = incomingData;
    }

}
