package org.tpokora.wh40khelper.army.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpokora.wh40khelper.army.ArmyService;
import org.tpokora.wh40khelper.army.model.ArmyEntity;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@Slf4j
public class ArmyController {

    private final ArmyService armyService;

    @GetMapping(value = "/api/army", produces = "application/json")
    public ResponseEntity<List<ArmyResponse>> getAllArmies(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(armyService.findArmies(PageRequest.of(page, size))
                .stream()
                .map(armyEntity -> new ArmyResponse(armyEntity.getName(), armyEntity.getFaction()))
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/api/army", produces = "application/json")
    public ResponseEntity<ArmyResponse> createArmy(@RequestBody ArmyRequest armyRequest) {
        var armyEntity = armyService.createArmy(new ArmyEntity(0L, armyRequest.getName(), armyRequest.getFaction()));
        log.debug(armyEntity.toString());
        return new ResponseEntity<>(new ArmyResponse(armyEntity.getName(), armyEntity.getFaction()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/army", produces = "application/json")
    public ResponseEntity<Void> deleteArmy(@RequestParam String name) {
        armyService.deleteByName(name);
        return ResponseEntity.status(204).build();
    }
}
