package shopManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import shopManager.dto.ItemDto;
import shopManager.dto.ManagerDto;
import shopManager.dto.ResponseDto;
import shopManager.service.ItemService;
import shopManager.service.ManagerService;

@RequestMapping("/item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("")
	public ModelAndView itemList() throws Exception{
		ModelAndView mv = new ModelAndView("/item/itemList");
		List<ItemDto> list = itemService.selectItemList();
		mv.addObject("list",list);
		
		return mv;
	}
	
	@RequestMapping("/add")
	public ModelAndView itemAdd() throws Exception{
		ModelAndView mv = new ModelAndView("/item/itemAdd");
		List<ManagerDto> list = managerService.selectManagerList();
		mv.addObject("list",list);
		return mv;
	}
	
	@RequestMapping("/addItem")
	@ResponseBody
	public ResponseDto itemInsert(ItemDto item, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		ResponseDto res = new ResponseDto();
		try {
			itemService.insertItem(item, multipartHttpServletRequest);
			res.setCode("1");
			res.setMessage("ok");
			return res;	
		} catch(Exception e) {
			res.setCode("2");
			res.setMessage("error");
			System.out.println(e.toString());
			return res;	
		}			
	}
	
	@RequestMapping("/deleteItem")	
	public String itemDelete(int idx) throws Exception{
		itemService.deleteItem(idx);
		return "redirect:/item";
	}
	
}
