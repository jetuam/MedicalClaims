package com.hcl.medicalclaims.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.medicalclaims.constants.MedicalClaimsConstants;
import com.hcl.medicalclaims.dto.FileUploadResponseDTO;

/**
 * @author srinivas
 * to upload file
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class FileUploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	/**
	 * @param file
	 * @return fileUploadResponseDTO
	 * @throws IOException
	 * to upload file
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FileUploadResponseDTO fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		LOGGER.info("entered into fileUpload() mthod of FileUploadController class----");
		String path = MedicalClaimsConstants.FILE_PATH;
		String fileName = path + file.getOriginalFilename();
		File convertFile = new File(fileName);
		if (convertFile.createNewFile()) {
			try (FileOutputStream fileOut = new FileOutputStream(convertFile)) {
				fileOut.write(file.getBytes());
			}
		}
		FileUploadResponseDTO fileUploadResponse = new FileUploadResponseDTO();
		fileUploadResponse.setStatusCode(201);
		fileUploadResponse.setMessage(MedicalClaimsConstants.FILE_SUCCESSFUL_UPLOAD_MSG);
		fileUploadResponse.setFilePath(fileName);
		return fileUploadResponse;
	}

}
