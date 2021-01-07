package shopManager.service;

import java.util.List;

import shopManager.dto.ManagerDto;

public interface ManagerService {
	List<ManagerDto> selectManagerList() throws Exception;
	
	void insertManager(ManagerDto manager) throws Exception;
	
	void deleteManager(int idx) throws Exception;
}
