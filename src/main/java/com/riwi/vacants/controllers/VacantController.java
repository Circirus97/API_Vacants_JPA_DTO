package com.riwi.vacants.controllers;

import com.riwi.vacants.services.interfaces.IVacantsService;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.VacantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<VacantResponse> insert(@RequestBody VacantRequest vacant){

        return ResponseEntity.ok(this.iVacantsService.create(vacant));
    }

}
