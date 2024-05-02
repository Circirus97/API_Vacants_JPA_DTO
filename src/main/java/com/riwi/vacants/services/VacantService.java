package com.riwi.vacants.services;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.entities.Vacant;
import com.riwi.vacants.repositories.CompanyRepository;
import com.riwi.vacants.repositories.VacantRepository;
import com.riwi.vacants.services.interfaces.IVacantsService;
import com.riwi.vacants.utils.dto.request.CompanyRequest;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.CompanyToVancantResponse;
import com.riwi.vacants.utils.dto.response.VacantResponse;
import com.riwi.vacants.utils.enums.StatusVacant;
import com.riwi.vacants.utils.exceptions.IdNotFoundException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacantService implements IVacantsService {

    private final VacantRepository vacantRepository;
    private final CompanyRepository companyRepository;

    @Override
    public Page<VacantResponse> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        /*Obtenemos todas las vacantes y las iteramos para convertir cada una a el DTO de respuesta*/
        return this.vacantRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public VacantResponse create(VacantRequest request) {


        /* Buscamos la compañia que corresponda con el ID que esta dentro del request*/
        Company company = this.companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new IdNotFoundException("Company"));

        /* Convertimos el request a una instancia de vacante */
        Vacant vacant = this.requestToVacant(request, new Vacant());

        vacant.setCompany(company);

        /* Guardamos en BD y convertimos la nueva entidad al DTO de respuesta*/
        return this.entityToResponse(this.vacantRepository.save(vacant));
    }

    @Override
    public VacantResponse update(VacantRequest request, Long id) {

    Vacant vacantToUpdate = this.find(id);

    Vacant vacant = this.requestToVacant(request, vacantToUpdate);

    return this.entityToResponse(this.vacantRepository.save(vacant));

    }

    @Override
    public void delete(Long id) {
        Vacant vacant = this.find(id);

        this.vacantRepository.delete(vacant);
    }

    @Override
    public VacantResponse getById(Long id) {

        Vacant vacant = this.find(id);

        return  this.entityToResponse(vacant);
    }

    private VacantResponse entityToResponse(Vacant entity){
        /**
         * Creamos instancion del DTO de vacante
         */

        VacantResponse response = new VacantResponse();

        /**
         * Copiar toda la entidad en el DTO
         */

        BeanUtils.copyProperties(entity, response);

        /* Creamos instancia del DTO de compañia de la vacante */
        CompanyToVancantResponse companyDTO = new CompanyToVancantResponse();

        /* Copio todas las propiedades de la compañia que se encuentra dentro de la entidad (Vacante) en el DTO de respuesta*/
        BeanUtils.copyProperties(entity.getCompany(), companyDTO);

        /* Agrego el DTO lleno a la respuesta final */
        response.setCompany(companyDTO);

        return response;
    }

    private Vacant requestToVacant(VacantRequest request, Vacant entity){

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setStatus(StatusVacant.ACTIVE);

        return entity;
    }

    private Vacant find(Long id){
        return  this.vacantRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Vacant"));
    }
}
