package com.ds.business.repository;

import java.util.List;

import com.ds.business.dto.DeptDto;




public interface DeptDao {
	List<DeptDto> getDeptList(DeptDto dto_d);
}
