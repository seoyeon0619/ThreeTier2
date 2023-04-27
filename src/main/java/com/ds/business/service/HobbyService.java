package com.ds.business.service;

import java.util.List;

import com.ds.business.dto.HobbyDto;

public interface HobbyService {
	List<HobbyDto> getHobbyList(HobbyDto dto_h);
}
