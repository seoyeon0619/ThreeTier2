package com.ds.business.repository;

import java.util.List;
import java.util.Map;

import com.ds.business.dto.HobbyDto;

public interface HobbyDao {
	List<HobbyDto> getHobbyList(HobbyDto dto_h);
}
