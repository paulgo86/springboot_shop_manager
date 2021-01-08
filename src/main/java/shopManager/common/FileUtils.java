package shopManager.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import shopManager.dto.ImageDto;

@Component
public class FileUtils {
	public List<ImageDto> parseFileInfo(int itemIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		List<ImageDto> imageList = new ArrayList<>();
		String path = "uploadedImages";
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		String newFileName, originalFileExtension, contentType, imageType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				if(multipartFile.isEmpty() == false) {
					contentType = multipartFile.getContentType();
					imageType = multipartFile.getName();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					}else {
						
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}else {
							break;
						}
						
					}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					ImageDto imageFile = new ImageDto();
					imageFile.setItemIdx(itemIdx);
					imageFile.setType(imageType);
					imageFile.setName(newFileName);
					imageList.add(imageFile);
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return imageList;
	}
}
