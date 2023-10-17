package bo.edu.ucb.mabuserregistry.service;


import bo.edu.ucb.mabuserregistry.dto.FileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient("mab-file-uploader")
public interface FileUploaderService {

    @PostMapping(value = "/api/v1/files", consumes = {"multipart/form-data"})
    public FileDto uploadFile(@RequestParam("file") MultipartFile file,
                              @RequestParam("bucket") String bucket,
                              @RequestParam(value = "customFilename",required = false, defaultValue = "false") boolean customFilename
    );


}
