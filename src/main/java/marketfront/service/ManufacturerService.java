package marketfront.service;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Manufacturer;
import marketfront.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> findAll(){
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> findByTitle(String title) {
        return manufacturerRepository.findByTitle(title);
    }

    @Transactional
    public void create(String title) {
        manufacturerRepository.insertManufacturer(title, (double) 0);
    }
}
