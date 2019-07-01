package hu.lsm.droolsfools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ResultEvent {

    private Long id;
    private ResultEventType eventType;

    private String message;
    private int point;
    private int errorCode;

    private long timestamp;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum ResultEventType {
        NOTIFICATION, UNKNOWN
    }
}
