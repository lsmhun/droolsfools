package hu.lsm.droolsfools.controller;

import hu.lsm.droolsfools.dto.IncomingData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomingMessageController {

    @PostMapping(path = "/api/vacation/add-request/{companyUrlName}/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addVacationRequest(@RequestBody IncomingData incomingData){
        System.out.println(incomingData);
    }


}
