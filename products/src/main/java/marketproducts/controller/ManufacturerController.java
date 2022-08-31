package marketproducts.controller;


import lombok.RequiredArgsConstructor;
import marketapi.ApiManufacturerListView;
import marketapi.ApiManufacturerView;
import marketproducts.entity.Manufacturer;
import marketproducts.model.ManufacturerDto;
import marketproducts.service.ManufacturerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @PostMapping()
    public void createNewManufacturer(@RequestBody ApiManufacturerView manufacturer) {
        manufacturerService.create(manufacturer.getTitle());
    }


    @GetMapping
    public ApiManufacturerListView findAll(){
        ApiManufacturerListView manufacturerListView = new ApiManufacturerListView();
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        for (Manufacturer c: manufacturers) {
            ApiManufacturerView view = new ApiManufacturerView();
            view.setTitle(c.getTitle());
            manufacturerListView.addItem(view);
        }
        return manufacturerListView;
    }
}
