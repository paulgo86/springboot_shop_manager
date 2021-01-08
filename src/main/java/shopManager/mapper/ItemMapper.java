package shopManager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shopManager.dto.ImageDto;
import shopManager.dto.ItemDto;

@Mapper
public interface ItemMapper {
	List<ItemDto> selectItemList() throws Exception;
	
	ItemDto selectItemDetail() throws Exception;
		
	void insertItem(ItemDto item) throws Exception;
	
	void deleteItem(int idx) throws Exception;
	
	void insertItemImage(List<ImageDto> list) throws Exception;
	
	List<ImageDto> selectItemImage(int idx) throws Exception;
}
