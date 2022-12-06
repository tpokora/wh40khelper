package org.tpokora.wh40khelper.army;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tpokora.wh40khelper.army.model.ArmyEntity;
import org.tpokora.wh40khelper.army.persistance.ArmyRepository;
import org.tpokora.wh40khelper.exception.ItemAlreadyExistsException;
import org.tpokora.wh40khelper.exception.ItemNotFoundException;

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
        checkIfArmyAlreadyExists(armyEntity);
        return armyRepository.save(new ArmyEntity(0L, armyEntity.getName(), armyEntity.getFaction()));
    }

    public void deleteByName(String name) {
        armyRepository.deleteByName(
                armyRepository.findByName(name)
                        .orElseThrow(ItemNotFoundException::new)
                        .getName());
    }

    public ArmyEntity updateArmy(ArmyEntity oldArmyEntity, ArmyEntity newArmyEntity) {
        newArmyEntity = setNewArmyEntityId(oldArmyEntity, newArmyEntity);
        checkIfArmyAlreadyExists(newArmyEntity);
        return this.armyRepository.save(newArmyEntity);
    }

    private ArmyEntity setNewArmyEntityId(ArmyEntity oldArmyEntity, ArmyEntity newArmyEntity) {
        var oldArmyId = this.armyRepository.findByName(oldArmyEntity.getName())
                .orElseThrow(ItemNotFoundException::new).getId();
        return new ArmyEntity(oldArmyId, newArmyEntity.getName(), newArmyEntity.getFaction());
    }

    private void checkIfArmyAlreadyExists(ArmyEntity armyEntity) {
        armyRepository.findByName(armyEntity.getName()).ifPresent(s -> {
            throw new ItemAlreadyExistsException();
        });
    }
}
