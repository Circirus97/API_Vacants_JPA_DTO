package com.riwi.vacants.controllers;

import com.riwi.vacants.services.interfaces.IVacantsService;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.VacantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/vacants")
@RequiredArgsConstructor
public class VacantController {

    private final IVacantsService iVacantsService;

    @GetMapping
    public ResponseEntity<Page<VacantResponse>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "7")Integer size
    ){
        return ResponseEntity.ok(this.iVacantsService.getAll(page -1, size));
    }


    @PostMapping
    public ResponseEntity<VacantResponse> insert(
            @Validated
            @RequestBody
            VacantRequest vacant){

        return ResponseEntity.ok(this.iVacantsService.create(vacant));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VacantResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.iVacantsService.getById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){

        Map<String, String> response = new HashMap<>();

        response.put("message", "Vacante eliminada correctamente");

        this.iVacantsService.delete(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VacantResponse> update(
            @Validated
            @PathVariable Long id,
            @RequestBody VacantRequest vacant
    ){
        return ResponseEntity.ok(this.iVacantsService.update(vacant, id));
    }


}
