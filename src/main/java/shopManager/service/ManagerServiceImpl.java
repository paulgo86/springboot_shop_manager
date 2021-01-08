package shopManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopManager.dto.ManagerDto;
import shopManager.mapper.ManagerMapper;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public List<ManagerDto> selectManagerList() throws Exception {
		return managerMapper.selectManagerList();
	}

	@Override
	public void insertManager(ManagerDto manager) throws Exception {
		// TODO Auto-generated method stub
		managerMapper.insertManager(manager);
		System.out.println(manager.getIdx()+"번 매니저 생성");
	}

	@Override
	public void deleteManager(int idx) throws Exception {
		// TODO Auto-generated method stub
		managerMapper.deleteManager(idx);
	}

}
