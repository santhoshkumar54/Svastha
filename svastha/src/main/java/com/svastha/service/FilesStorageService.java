package com.svastha.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();

  public String save(MultipartFile file, Path p);

  public Resource load(Path p,String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
  
  public Path createFolder(String folderName);
}
