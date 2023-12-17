package com.blogapp.apis.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.apis.repositories.PostRepository;
import com.blogapp.apis.services.FileService;


@Service
public class FileServiceImpl implements FileService {
	
	@Override
	public boolean uploadFile(MultipartFile multipartFile) {
		boolean isFileUploaded = false;

		try {
			
			File txtFile = new File(new ClassPathResource(".").getFile().getPath() + "/static/images/");
			if (!txtFile.exists()) {
				txtFile.mkdirs();
			}
			String UPLOAD_PATH = new ClassPathResource("\\static\\images\\").getFile().getAbsolutePath();
			Path newFilePathAndName = Paths.get(UPLOAD_PATH + "\\" + multipartFile.getOriginalFilename());
			System.out.println("" + newFilePathAndName);

			Files.copy(multipartFile.getInputStream(), newFilePathAndName, StandardCopyOption.REPLACE_EXISTING);
			isFileUploaded = true;
		} catch (Exception e) {
			System.out.println("here exeption is comming");
			e.printStackTrace();
		}

		return isFileUploaded;
	}

}
