package shopManager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shopManager.dto.ManagerDto;

@Mapper
public interface ManagerMapper {
	List<ManagerDto> selectManagerList() throws Exception;

	void insertManager(ManagerDto manager) throws Exception;
	
	void deleteManager(int idx) throws Exception;
}
