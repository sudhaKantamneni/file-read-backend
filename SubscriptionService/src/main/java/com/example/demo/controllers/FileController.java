package com.example.demo.controllers;



import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Greeting;
import com.example.demo.entities.FILEENUM;
import com.example.demo.entities.FileListResponse;
import com.example.demo.entities.FileObject;
import com.google.gson.Gson;

@RestController
@RequestMapping("/")
public class FileController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	

	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/FileList")
	public FileListResponse getFileList(@RequestParam(name = "filePath") String filePath) throws IOException {
		FileListResponse fileListResponse=new FileListResponse();
		List<FileObject> fileObjectList=new ArrayList();
	
				List<File> filesInFolder = Files.walk(Paths.get(filePath))
              
                .map(Path::toFile)
                .collect(Collectors.toList());
				
				
				for(File fileObj :filesInFolder)
				{
					FileObject fileObject=new FileObject();
					fileObject.setFileFullPath(fileObj.getAbsolutePath());
					fileObject.setFileSize(fileObj.length());
					fileObject.setFileType(fileObj.isFile() ? FILEENUM.FILE:FILEENUM.DIRECTORY);
					fileObjectList.add(fileObject);
				}
				fileListResponse.setFileObject(fileObjectList);
			
		return fileListResponse;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/FileDescription")
	public DosFileAttributes getFileDescription(@RequestParam(name = "filePath") String filePath) throws IOException {
		
		Path file1=Paths.get(filePath);
		DosFileAttributes attr = Files.readAttributes(file1, DosFileAttributes.class);

		
			
		return attr;
	}
	
}