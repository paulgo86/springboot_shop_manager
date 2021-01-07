package shopManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shopManager.dto.ManagerDto;
import shopManager.service.ManagerService;

@RequestMapping("/manager")
@Controller
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("")
	public ModelAndView managerList() throws Exception{
		ModelAndView mv = new ModelAndView("/manager/managerList");
		List<ManagerDto> list = managerService.selectManagerList();
		mv.addObject("list",list);
		
		return mv;
	}
	
	@RequestMapping("/add")
	public String managerAdd() throws Exception{
		return "/manager/managerAdd";
	}
	
	@RequestMapping("/addManager")
	public String managerInsert(ManagerDto manager) throws Exception{
		managerService.insertManager(manager);
		return "redirect:/manager";
	}
	
	@RequestMapping("/deleteManager")
	public String managerDelete(int idx) throws Exception{
		System.out.println(idx);
		managerService.deleteManager(idx);
		return "redirect:/manager";
	}
	
}
