package hu.lsm.droolsfools.dao;

import hu.lsm.droolsfools.entity.EEARule;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RuleRepositoryImpl implements RuleRepository {

    @Override
    public EEARule findById(Long id) {
        return null;
    }

    @Override
    public List<EEARule> findByRepositoryId(String repositoryId, boolean enabled) {
        return Collections.emptyList();
    }
}
