package com.blogapp.apis.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public boolean uploadFile(MultipartFile multipartFile);
}
