package hu.lsm.droolsfools.controller;

import hu.lsm.droolsfools.dao.RuleRepository;
import hu.lsm.droolsfools.entity.EEARule;
import hu.lsm.droolsfools.service.RuleRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EEARuleController {

    @Autowired
    private RuleRepository ruleRepository;

    @GetMapping("/api/eea-rules/{repositoryId}/get-rules")
    @ResponseBody
    public List<EEARule> getEEARuleList(@PathVariable(name="repositoryId") String repositoryId){
        boolean notAllEnabledRules = false;
        return ruleRepository.findByRepositoryId(RuleRunnerService.DEFAULT_REPO, notAllEnabledRules);
    }

    @GetMapping("/api/eea-rules/{repositoryId}/get-rule-by-id/{ruleId}")
    @ResponseBody
    public EEARule getEEARuleById(@PathVariable(name="repositoryId") String repositoryId,
                                        @PathVariable(name="ruleId") Long ruleId){
        return ruleRepository.findById(ruleId);
    }

    @PostMapping(path = "/api/eea-rules/{repositoryId}/update-rule",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void triggerRules(
            @PathVariable(name="repositoryId") String repositoryId,
            @RequestBody EEARule eeaRule){
        ruleRepository.saveOrUpdate(eeaRule);
    }
}
