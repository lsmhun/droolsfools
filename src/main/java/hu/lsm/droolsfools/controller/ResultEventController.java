package hu.lsm.droolsfools.controller;

import hu.lsm.droolsfools.dao.ResultEventRepository;
import hu.lsm.droolsfools.entity.ResultEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultEventController {

    @Autowired
    private ResultEventRepository resultEventRepository;

    @GetMapping("/api/result-event/get-result-events")
    @ResponseBody
    public List<ResultEvent> getEEARuleList() {
        return resultEventRepository.findAll();
    }

}
