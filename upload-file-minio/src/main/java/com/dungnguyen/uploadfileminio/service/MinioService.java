package com.dungnguyen.uploadfileminio.service;

import com.dungnguyen.uploadfileminio.dto.FileDto;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MinioService {

  @Autowired private MinioClient minioClient;

  @Value("${minio.bucket.name}")
  private String bucketName;

  @Value("${spring.url}")
  private String appUrl;

  public List<FileDto> getListObjects() {
    List<FileDto> objects = new ArrayList<>();
    try {
      Iterable<Result<Item>> result =
          minioClient.listObjects(
              ListObjectsArgs.builder().bucket(bucketName).recursive(true).build());
      for (Result<Item> item : result) {
        objects.add(
            FileDto.builder()
                .filename(item.get().objectName())
                .size(item.get().size())
                .url(getPreSignedUrl(item.get().objectName()))
                .build());
      }
      return objects;
    } catch (Exception e) {
      log.error("Happened error when get list objects from minio: ", e);
    }

    return objects;
  }

  public FileDto uploadFile(FileDto request) {
    try {
      minioClient.putObject(
          PutObjectArgs.builder()
              .bucket(bucketName)
              .object(request.getFile().getOriginalFilename())
              .stream(request.getFile().getInputStream(), request.getFile().getSize(), -1)
              .build());
    } catch (Exception e) {
      log.error("Happened error when upload file: ", e);
    }
    return FileDto.builder()
        .title(request.getTitle())
        .description(request.getDescription())
        .size(request.getFile().getSize())
        .url(getPreSignedUrl(request.getFile().getOriginalFilename()))
        .filename(request.getFile().getOriginalFilename())
        .build();
  }

  public InputStream getObject(String filename) {
    InputStream stream;
    try {
      stream =
          minioClient.getObject(
              GetObjectArgs.builder().bucket(bucketName).object(filename).build());
    } catch (Exception e) {
      log.error("Happened error when get list objects from minio: ", e);
      return null;
    }

    return stream;
  }

  private String getPreSignedUrl(String filename) {
    return appUrl + "file/".concat(filename);
  }

  public void removeObjects(String bucketName, List<String> objects) throws Exception {
    List<DeleteObject> deleteObjects = objects.stream().map(DeleteObject::new).toList();
    Iterable<Result<DeleteError>> results =
        minioClient.removeObjects(
            RemoveObjectsArgs.builder().bucket(bucketName).objects(deleteObjects).build());
    for (Result<DeleteError> result : results) {
      DeleteError error = result.get();
      System.out.println("Error in deleting object " + error.objectName() + "; " + error.message());
    }
  }
}
