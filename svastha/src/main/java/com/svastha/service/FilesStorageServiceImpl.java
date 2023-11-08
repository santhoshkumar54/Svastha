package com.svastha.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	//private final Path root = Paths.get("C:\\Users\\smsan\\work\\copy\\testit");
      private final Path root = Paths.get("/dev/svastha/images");
	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload! " + root);
		}
	}

	public Path createFolder(String folderName) {
		try {
			Path p = Paths.get(this.root.toString()+folderName);
			Files.createDirectories(p);
			return p;
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload! " + folderName);
		}
	}

	@Override
	public String save(MultipartFile file, Path p) {
		try {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			String fileName = getCurrentTimeStamp().getTime()+"."+ext;
			Files.copy(file.getInputStream(), p.resolve(fileName));
			return fileName;
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Resource load(Path p,String filename) {
		try {
			Path file = p.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
	
	@Override
	public MediaType getContentType(Path p,String filename) throws IOException
	{
		Path file = p.resolve(filename);
		String contentType = Files.probeContentType(file);
		MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
		if( contentType.equals("image/jpeg"))
		{
			mediaType = MediaType.IMAGE_JPEG;
		}
		else if(contentType.equals("image/png"))
		{
			mediaType = MediaType.IMAGE_PNG;
		}
		else if(contentType.equals("image/gif"))
		{
			mediaType = MediaType.IMAGE_GIF;
		}
		return mediaType;
	}
	public Timestamp getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}