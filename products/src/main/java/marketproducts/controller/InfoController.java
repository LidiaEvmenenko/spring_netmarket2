package marketproducts.controller;



import lombok.extern.slf4j.Slf4j;
import marketproducts.model.MapDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/statistic")
@Slf4j
//@RequiredArgsConstructor
public class InfoController {
  //  private final String appName;
  //  private final ControllerLogAspect controllerLogAspect;

 //   public InfoController(ControllerLogAspect controllerLogAspect) {
//        this.controllerLogAspect = controllerLogAspect;
//    }

    @GetMapping
    public List<MapDto> printMap(){
        List<MapDto> list = new ArrayList<>();
//        HashMap<String, Long> m = (HashMap<String, Long>) controllerLogAspect.getMapService();
//        m.toString();
//        for (Map.Entry<String, Long> e : m.entrySet()) {
//            String key = e.getKey();
//            Long value = e.getValue();
//            MapDto dto = new MapDto();
//            dto.setTitle(key);
//            dto.setTimeService(value);
//            list.add(dto);
//        }
        return list;
    }


}

