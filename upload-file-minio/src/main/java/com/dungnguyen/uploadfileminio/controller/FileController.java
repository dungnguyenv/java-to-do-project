package com.dungnguyen.uploadfileminio.controller;

import com.dungnguyen.uploadfileminio.dto.FileDto;
import com.dungnguyen.uploadfileminio.dto.RemoveObjectRequestDto;
import com.dungnguyen.uploadfileminio.service.MinioService;
import com.dungnguyen.uploadfileminio.util.BucketName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.web.servlet.HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE;

@Slf4j
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {


    private final MinioService minioService;

    @GetMapping
    public ResponseEntity<Object> getFiles() {
        return ResponseEntity.ok(minioService.getListObjects());
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Object> upload(@ModelAttribute FileDto request) {
        return ResponseEntity.ok().body(minioService.uploadFile(request));
    }

    @GetMapping(value = "/**")
    public ResponseEntity<Object> getFile(HttpServletRequest request) throws IOException {
        String pattern = (String) request.getAttribute(BEST_MATCHING_PATTERN_ATTRIBUTE);
        String filename = new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());

        // change default filename "f.txt" to real filename
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename(filename)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(IOUtils.toByteArray(minioService.getObject(filename)));
    }

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeObjects(@RequestBody RemoveObjectRequestDto removeObjectRequestDto) throws Exception{
        minioService.removeObjects(BucketName.DEMO_BUCKET, removeObjectRequestDto.getObjectNameList());
    }


}
