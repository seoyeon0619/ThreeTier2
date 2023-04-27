package com.ds.business.repository;

import java.util.List;

import com.ds.business.dto.EmpDto;

public interface EmpDao {
	List<EmpDto> getEmpList(EmpDto dto);
	EmpDto getView(EmpDto dto);
	
	void insert(EmpDto dto);
	void update(EmpDto dto);
	void delete(EmpDto dto);
}


