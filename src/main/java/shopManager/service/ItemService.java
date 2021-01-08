package shopManager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import shopManager.dto.ItemDto;

public interface ItemService {
	List<ItemDto> selectItemList() throws Exception;
	
	ItemDto selectItemDetail(int idx) throws Exception;
	
	void insertItem(ItemDto item, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	
	void deleteItem(int idx) throws Exception;
	
//	void updateItem(ItemDto item, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	
	
}
