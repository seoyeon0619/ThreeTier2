package com.ds.presentation.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.business.dto.DeptDto;
import com.ds.business.dto.EmpDto;
import com.ds.business.dto.EmpHobbyDto;
import com.ds.business.dto.HobbyDto;

@Service
public class PresentationService {
	private final RestTemplate restTemplate = new RestTemplate();
	// emp---------------------------------------------------------------------------------------------------------
	public List<EmpDto> getEmpList(EmpDto dto) {
		String searchKeyword = dto.getSearchKeyword();
		String uri = "http://localhost:9999/api/emplist?searchKeyword="+searchKeyword;
		List<EmpDto> emplist = restTemplate.getForObject(uri, List.class);
		return emplist;
	}
	
	public EmpDto getView(String emp_id, EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh) {
		String searchKeyword = dto.getSearchKeyword();
		URI uri = UriComponentsBuilder
	               .fromUriString("http://localhost:9999")
	               .path("/api/empview")
	               .queryParam("emp_id", emp_id)
	               .encode()
	               .build()
	               .toUri();
	               
		EmpDto view = restTemplate.getForObject(uri, EmpDto.class);
		return view;
	}
	
	public void insert(@RequestBody EmpDto dto) {
		String uri = "http://localhost:9999/api/empinsert";
		restTemplate.postForObject(uri, dto, EmpDto.class);
	}
	public void update(@RequestBody EmpDto dto) {
		String uri = "http://localhost:9999/api/empupdate";
		restTemplate.postForObject(uri, dto, EmpDto.class);
	}
	public void delete(@RequestBody EmpDto dto) {
		String uri = "http://localhost:9999/api/empdelete";
		restTemplate.postForObject(uri, dto, EmpDto.class);
	}
	
	// dept------------------------------------------------------------------------------------------------------
	public List<DeptDto> getDeptList(DeptDto dto_d) {
		String uri = "http://localhost:9999/api/deptlist";
		List<DeptDto> deptlist = restTemplate.getForObject(uri, List.class);
		return deptlist;
	}
	
	// hobby-------------------------------------------------------------------------------------------------------------
	public List<HobbyDto> getHobbyList(HobbyDto dto_h) {
		String uri = "http://localhost:9999/api/hobbylist";
		List<HobbyDto> hobbylist = restTemplate.getForObject(uri, List.class);
		return hobbylist;
	}
	
	// emphobby---------------------------------------------------------------------------------------------------
//	public StringBuffer getEmpHobbyList(EmpHobbyDto dto_eh) {
//		String uri = "http://localhost:9999/api/emphobbylist";
//		ResponseEntity<List<EmpHobbyDto>> response = 
//				restTemplate.exchange(uri, HttpMethod.GET,  null, new ParameterizedTypeReference<List<EmpHobbyDto>>() {});
//		return response.getBody();
//	}

	public String getEmpHobbyView(String emp_id, EmpHobbyDto dto_eh) {
		URI uri = UriComponentsBuilder
	               .fromUriString("http://localhost:9999")
	               .path("/api/emphobbyview")
	               .queryParam("emp_id", emp_id)
	               .encode()
	               .build()
	               .toUri();
		String emphobbyview = restTemplate.getForObject(uri, String.class);
		return emphobbyview;
	}
	
	public void insert_eh(@RequestBody EmpHobbyDto dto_eh) {
		String uri = "http://localhost:9999/api/emphobbyinsert";
		restTemplate.postForObject(uri, dto_eh, EmpHobbyDto.class);
	}
	public void update_eh(@RequestBody EmpHobbyDto dto_eh) {
		String uri = "http://localhost:9999/api/emphobbyupdate";
		restTemplate.postForObject(uri, dto_eh, EmpHobbyDto.class);
	}
	public void delete_eh(@RequestBody EmpHobbyDto dto_eh) {
		String uri = "http://localhost:9999/api/emphobbydelete";
		restTemplate.postForObject(uri, dto_eh, EmpHobbyDto.class);
	}
	
}
