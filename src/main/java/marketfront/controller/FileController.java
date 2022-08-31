package marketfront.controller;



import lombok.RequiredArgsConstructor;
import marketfront.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

//    @PostMapping
//    public Boolean saveString(@RequestBody FileSaveRequest request){
//        try {
//            fileService.save(request.getText(), request.getName());
//            return true;
//        } catch (IOException e) {
//           return false;
//        }
//    }
    @GetMapping(value = "/{fileName}", produces = "application/octet-stream")
    public byte[] getFile(@PathVariable String fileName){
        try {
            return fileService.getFileData(fileName);
        } catch (IOException e) {
            return new byte[] {};
        }
    }
    @GetMapping("/report")
    public String[] createReport(){
        return fileService.openBook();

    }
}



















