package com.ds.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ds.business.dto.HobbyDto;
import com.ds.business.repository.HobbyDao;

@Service("hobbyService")
public class HobbyServiceImpl implements HobbyService{

	@Resource(name="hobbyDao")
	HobbyDao dao;
	
	@Override
	public List<HobbyDto> getHobbyList(HobbyDto dto_h) {
		return dao.getHobbyList(dto_h);
	}


}
