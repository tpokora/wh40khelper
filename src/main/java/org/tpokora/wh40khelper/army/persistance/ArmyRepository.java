package org.tpokora.wh40khelper.army.persistance;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.tpokora.wh40khelper.army.model.ArmyEntity;

public interface ArmyRepository extends PagingAndSortingRepository<ArmyEntity, Integer> {

}
