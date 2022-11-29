package org.tpokora.wh40khelper.army;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tpokora.wh40khelper.army.model.ArmyEntity;
import org.tpokora.wh40khelper.army.persistance.ArmyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArmyService {

    private final ArmyRepository armyRepository;

    public List<ArmyEntity> findArmies(PageRequest pageRequest) {
        return armyRepository.findAll(pageRequest)
                .get()
                .collect(Collectors.toList());
    }

    public ArmyEntity createArmy(ArmyEntity armyEntity) {
        return armyRepository.save(new ArmyEntity(0L, armyEntity.getName(), armyEntity.getFaction()));
    }

    public void deleteByName(String name) {
        armyRepository.deleteByName(name);
    }
}
