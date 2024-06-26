package com.example.project.controller;

import com.example.project.dto.UploadFileDTO;
import com.example.project.dto.UploadResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(@RequestBody UploadFileDTO uploadFileDTO){

        log.info("=================== Upload POST..... ===================");
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null){

            final List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile -> {

                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);

                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

                boolean image = false;

                try{
                    multipartFile.transferTo(savePath);
                    //이미지 파일의 종류라면
                    if(Files.probeContentType(savePath).startsWith("image")){

                        log.info("=================== Upload POST Image File..... ===================");
                        image = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid+"_"+originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                }catch (IOException e){
                    log.info("=================== Upload POST Image File Error Fail..... ===================");
                    e.printStackTrace();
                }

                list.add(UploadResultDTO.builder()
                                .uuid(uuid)
                                .fileName(originalName)
                                .img(image).build());
            });
            return list;
        }
        return null;
    }

    @Operation(summary = "view 파일", description = "GET 방식으로 첨부파일 조회")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){

        log.info("=================== ViewFile GET..... ===================");
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);

        String resourcePath = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Operation(summary = "remove 파일",description = "DELETE 방식으로 삭제")
    @DeleteMapping(value = "/remove/{fileName}")
    public Map<String,Boolean> removeFile(@PathVariable String fileName){

        log.info("=================== Remove Delete File..... ===================");
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try{
            log.info("=================== removeFile process Success!!! ===================");
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            if(contentType.startsWith("image")){
                log.info("=================== File Image is Deleting..... ===================");
              File thumbnailFile = new File(uploadPath+File.separator + "s_" + fileName);
              thumbnailFile.delete();
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        resultMap.put("result",removed);

        return resultMap;
    }
}
