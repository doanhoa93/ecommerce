package com.framgia.helper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class UploadFile {
	private String cloudName;
	private String apiKey;
	private String apiSecret;

	@SuppressWarnings("rawtypes")
	public Map upload(File file) throws IOException {
		Map uploadParams = ObjectUtils.asMap("invalidate", true);
		return getCloudinary().uploader().upload(file, uploadParams);
	}

	@SuppressWarnings("rawtypes")
	public Map upload(MultipartFile fultipartFile) throws IOException {
		File file = multipartFileToFile(fultipartFile);
		return upload(file);
	}

	public void destroy() throws Exception {
		getCloudinary().api().deleteAllResources(ObjectUtils.asMap("invalidate", true));
	}

	public Cloudinary getCloudinary() {
		return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
	}

	public String getCloudName() {
		return cloudName;
	}

	public void setCloudName(String cloudName) {
		this.cloudName = cloudName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	private File multipartFileToFile(MultipartFile image) throws IllegalStateException, IOException {
		File file = new File(image.getOriginalFilename());
		image.transferTo(file);
		return file;
	}
}
