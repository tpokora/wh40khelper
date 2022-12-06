package org.tpokora.wh40khelper.army.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.wh40khelper.army.ArmyFactionEnum;
import org.tpokora.wh40khelper.army.ArmyService;
import org.tpokora.wh40khelper.army.model.ArmyEntity;
import org.tpokora.wh40khelper.exception.ItemAlreadyExistsException;
import org.tpokora.wh40khelper.exception.ItemNotFoundException;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArmyController.class)
class ArmyControllerTest {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @MockBean
    private ArmyService armyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllArmiesShouldReturnPageWithArmies() throws Exception {
        // given
        var firstArmy = new ArmyEntity(1L, "Test Army 1", ArmyFactionEnum.DEATHWATCH.toString());
        var secondArmy = new ArmyEntity(2L, "Test Army 2", ArmyFactionEnum.DEATHWATCH.toString());
        var armyEntityList = Arrays.asList(firstArmy, secondArmy);

        when(armyService.findArmies(PageRequest.of(0, 10))).thenReturn(armyEntityList);

        // then
        this.mockMvc.perform(get("/api/army")
                .with(testUserForRequest())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is(firstArmy.getName())))
                .andExpect(jsonPath("$.[0].faction", is(firstArmy.getFaction())))
                .andExpect(jsonPath("$.[1].name", is(secondArmy.getName())))
                .andExpect(jsonPath("$.[1].faction", is(secondArmy.getFaction())));
    }

    @Test
    void createArmyShouldReturnCreatedArmy() throws Exception {
        // given
        var firstArmyRequest = new ArmyRequest("Test Army 1", ArmyFactionEnum.DEATHWATCH.toString());
        var armyString = OBJECT_MAPPER.writeValueAsString(firstArmyRequest);

        when(armyService.createArmy(any())).thenReturn(new ArmyEntity(1L, firstArmyRequest.getName(), firstArmyRequest.getFaction()));

        // then
        this.mockMvc.perform(post("/api/army")
                .with(testUserForRequest())
                .with(csrf())
                .content(armyString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(firstArmyRequest.getName())))
                .andExpect(jsonPath("$.faction", is(firstArmyRequest.getFaction())));
    }

    @Test
    void createArmyShouldReturn409IfArmyAlreadyExist() throws Exception {
        // given
        var firstArmyRequest = new ArmyRequest("Test Army 1", ArmyFactionEnum.DEATHWATCH.toString());
        var armyString = OBJECT_MAPPER.writeValueAsString(firstArmyRequest);

        when(armyService.createArmy(any())).thenThrow(ItemAlreadyExistsException.class);

        // expect
        this.mockMvc.perform(post("/api/army")
                .with(testUserForRequest())
                .with(csrf())
                .content(armyString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void deleteArmyShouldReturn204IfDeleteSuccessfully() throws Exception {
        // expect
        this.mockMvc.perform(delete("/api/army")
                .param("name", "test")
                .with(testUserForRequest())
                .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteArmyShouldReturn404IfArmyNotExist() throws Exception {
        // when
        doThrow(new ItemNotFoundException()).when(armyService).deleteByName(anyString());

        // expect
        this.mockMvc.perform(delete("/api/army")
                .param("name", "test")
                .with(testUserForRequest())
                .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateArmyShouldReturnUpdatedArmy() throws Exception {
        // given
        var updateArmyRequest = new ArmyRequest("Test Army 1", ArmyFactionEnum.DEATHWATCH.toString());
        var updateArmyString = OBJECT_MAPPER.writeValueAsString(updateArmyRequest);
        var updatedArmy = new ArmyEntity(1L, updateArmyRequest.getName(), updateArmyRequest.getFaction());

        // when
        when(armyService.updateArmy(any(), any())).thenReturn(updatedArmy);

        // then
        this.mockMvc.perform(put("/api/army")
                .param("name", "oldArmyName")
                .with(testUserForRequest())
                .with(csrf())
                .content(updateArmyString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name", is(updateArmyRequest.getName())))
                .andExpect(jsonPath("$.faction", is(updateArmyRequest.getFaction())));
    }

    @Test
    void updateArmyShouldShouldReturn409IfAlreadyExist() throws Exception {
        // given
        var updateArmyRequest = new ArmyRequest("Test Army 1", ArmyFactionEnum.DEATHWATCH.toString());
        var updateArmyString = OBJECT_MAPPER.writeValueAsString(updateArmyRequest);

        // when
        when(armyService.updateArmy(any(), any())).thenThrow(ItemAlreadyExistsException.class);

        // then
        this.mockMvc.perform(put("/api/army")
                .param("name", "oldArmyName")
                .with(testUserForRequest())
                .with(csrf())
                .content(updateArmyString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    private static SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor testUserForRequest() {
        return user("testUser").password("testPassword");
    }
}