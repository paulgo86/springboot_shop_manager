package shopManager.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import shopManager.dto.ImageDto;
import shopManager.dto.ItemDto;
import shopManager.mapper.ItemMapper;
import shopManager.common.FileUtils;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private FileUtils fileUtils;

	@Override
	public List<ItemDto> selectItemList() throws Exception {
		return itemMapper.selectItemList();
	}

	@Override
	public void insertItem(ItemDto item, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		// TODO Auto-generated method stub
		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				System.out.println("file tag name : "+name);
				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : list) {
					System.out.println("start file information");
					System.out.println("file type : "+multipartFile.getName());
					System.out.println("file name : "+multipartFile.getOriginalFilename());
					System.out.println("file size : "+multipartFile.getSize());
					System.out.println("file content type : "+multipartFile.getContentType());
					System.out.println("end file information.\n");
				}
			}
		}
		
		itemMapper.insertItem(item);
		List<ImageDto> list = fileUtils.parseFileInfo(item.getIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false) {
			itemMapper.insertItemImage(list);
		}
	}

	@Override
	public ItemDto selectItemDetail(int idx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void updateItem(ItemDto item, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void deleteItem(int idx) throws Exception{
		
	}
}
