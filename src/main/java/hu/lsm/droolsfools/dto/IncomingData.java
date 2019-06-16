package hu.lsm.droolsfools.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class IncomingAdapter implements Serializable {
    private String message;
    private int errorCode;

    private String value;
}
