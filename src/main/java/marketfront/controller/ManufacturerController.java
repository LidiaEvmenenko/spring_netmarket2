package marketfront.controller;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Manufacturer;
import marketfront.model.ManufacturerDto;
import marketfront.service.ManufacturerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @PostMapping()
    public void createNewManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerService.create(manufacturer.getTitle());
    }


    @GetMapping("/list")
    public List<ManufacturerDto> findAll(){
        List<ManufacturerDto> manufacturerDtos = new ArrayList<>();
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        for (Manufacturer c: manufacturers) {
            ManufacturerDto manufacturerDto = new ManufacturerDto(c);
            manufacturerDtos.add(manufacturerDto);
        }
        return manufacturerDtos;
    }
}
