package hu.lsm.droolsfools.dao;

import hu.lsm.droolsfools.entity.ResultEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultEventRepositoryImpl implements ResultEventRepository {

    private List<ResultEvent> resultEvents = new ArrayList<>();

    @Override
    public List<ResultEvent> findAll() {
        return resultEvents;
    }

    @Override
    public void addResultEvent(ResultEvent resultEvent) {
        resultEvents.add(resultEvent);
    }

}
