package org.tpokora.wh40khelper.army.persistance;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.tpokora.wh40khelper.army.model.ArmyEntity;

import java.util.Optional;

public interface ArmyRepository extends PagingAndSortingRepository<ArmyEntity, Integer> {

    Optional<ArmyEntity> findByName(String name);
    void deleteByName(String name);
}
