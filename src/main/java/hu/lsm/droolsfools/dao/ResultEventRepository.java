package hu.lsm.droolsfools.dao;

import hu.lsm.droolsfools.entity.ResultEvent;

import java.util.List;

public interface ResultEventRepository
        //extends CrudRepository<ResultEvent, Long>
{
    List<ResultEvent> findAll();

    void addResultEvent(ResultEvent resultEvent);
}
