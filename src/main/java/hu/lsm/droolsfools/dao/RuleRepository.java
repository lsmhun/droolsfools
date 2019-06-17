package hu.lsm.droolsfools.dao;

import hu.lsm.droolsfools.entity.EEARule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository
        //extends CrudRepository<EEARule, Long>
{
    EEARule findById(Long id);
    List<EEARule> findByRepositoryId(String repositoryId, boolean enabled);
}
