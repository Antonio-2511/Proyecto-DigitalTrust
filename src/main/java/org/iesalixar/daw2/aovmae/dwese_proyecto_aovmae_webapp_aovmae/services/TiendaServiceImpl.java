package org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.services;

import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.entities.Plan;
import org.iesalixar.daw2.aovmae.dwese_proyecto_aovmae_webapp_aovmae.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> listarTodos() {
        // Retorna los planes: Basico, Estandar, Premium y Empresarial
        return planRepository.findAll();
    }

    @Override
    public Plan obtenerPorNombre(String nombre) {
        return planRepository.findById(nombre).orElse(null);
    }
}