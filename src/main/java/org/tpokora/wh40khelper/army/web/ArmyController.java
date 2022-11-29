package org.tpokora.wh40khelper.army.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.wh40khelper.army.model.ArmyEntity;
import org.tpokora.wh40khelper.army.persistance.ArmyRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@Slf4j
public class ArmyController {

    private ArmyRepository armyRepository;

    @GetMapping(value = "/api/army", produces = "application/json")
    public ResponseEntity<List<ArmyResponse>> getAllArmies() {
        return ResponseEntity.ok(armyRepository.findAll(PageRequest.of(0, 100))
                .get()
                .map(armyEntity -> new ArmyResponse(armyEntity.getName(), armyEntity.getFaction()))
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/api/army", produces = "application/json")
    public ArmyResponse createArmy(@RequestBody ArmyRequest armyRequest) {
        ArmyEntity armyEntity = armyRepository.save(new ArmyEntity(0L, armyRequest.getName(), armyRequest.getFaction()));
        log.debug(armyEntity.toString());
        return new ArmyResponse(armyEntity.getName(), armyEntity.getFaction());
    }
}
