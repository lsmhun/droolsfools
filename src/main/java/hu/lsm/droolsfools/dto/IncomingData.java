package hu.lsm.droolsfools.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class IncomingData implements Serializable {
    private int errorCode;

    private String value;
}
