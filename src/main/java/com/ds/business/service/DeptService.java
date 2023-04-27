package com.ds.business.service;

import java.util.List;

import com.ds.business.dto.DeptDto;
import com.ds.business.dto.EmpHobbyDto;

public interface DeptService {
	List<DeptDto> getDeptList(DeptDto dto_d);
}
