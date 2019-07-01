package hu.lsm.droolsfools.controller;

import hu.lsm.droolsfools.dto.IncomingData;
import hu.lsm.droolsfools.service.RuleRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomingDataController {

    @Autowired
    private RuleRunnerService ruleRunnerService;

    @PostMapping(path = "/api/trigger_rules/{repositoryId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void triggerRules(
            @PathVariable(name = "repositoryId") String repositoryId,
            @RequestBody IncomingData incomingData) {
        ruleRunnerService.runRules(repositoryId, incomingData);
    }


}
