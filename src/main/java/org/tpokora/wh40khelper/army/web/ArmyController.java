package org.tpokora.wh40khelper.army.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.wh40khelper.army.persistance.ArmyEntity;

import java.util.Collections;
import java.util.List;


@RestController
public class ArmyController {

    @GetMapping(value = "/api/army", produces = "application/json")
    public ResponseEntity<List<ArmyEntity>> getAllArmies() {
        return ResponseEntity.ok(Collections.singletonList(new ArmyEntity("Test Army")));
    }
}
