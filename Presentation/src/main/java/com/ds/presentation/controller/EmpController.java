package com.ds.presentation.controller;

import java.net.URI;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.business.dto.DeptDto;
import com.ds.business.dto.EmpDto;
import com.ds.business.dto.EmpHobbyDto;
import com.ds.business.dto.HobbyDto;


@Controller
public class EmpController {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	// 사용자 포털로 이동
	@RequestMapping(value = "/user")
	public String user_portal(EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh, Model model) {
		List<Data> list = restTemplate.getForObject("http://localhost:9999/api/emplist", List.class);
		List<HobbyDto> list_eh = restTemplate.getForObject("http://localhost:9999/api/emphobbylist", List.class);
		model.addAttribute("EmpDto", dto);
		model.addAttribute("EmpHobbyDto", dto_eh);
		
		return "/user";
	}
	
	// insert 값 저장
	@RequestMapping(value = "/user/save")
	public String save(EmpDto dto, DeptDto dto_d, EmpHobbyDto dto_eh) {
//		empService.insert(dto);
		restTemplate.postForObject("http://localhost:9999/api/empinsert", dto, EmpDto.class);
		if (dto_eh.getHobby_cd().contains(",")) {
			String[] hobby_list = dto_eh.getHobby_cd().split(",");
				for (int i = 0; i < hobby_list.length; i++) {
					dto_eh.setHobby_cd(hobby_list[i]);
//					emphobbyService.insert_eh(dto_eh);
					restTemplate.postForObject("http://localhost:9999/api/emphobbyinsert", dto_eh, EmpHobbyDto.class);
				}
		}
		else {
			restTemplate.postForObject("http://localhost:9999/api/emphobbyinsert", dto_eh, EmpHobbyDto.class);
		}
		return "redirect:/admin";
	}
	
	// 관리자 포털로 이동
	@RequestMapping(value = "/admin")
	public String admin_portal(EmpDto dto, Model model) {
//		URI uri_search = UriComponentsBuilder
//				.fromUriString("http://localhost:9999")
//				.path("/api/searchKeyword")
//				.queryParam("searchKeyword")
//				.encode()
//				.build()
//				.toUri();
		
		String searchkeword = dto.getSearchKeyword();
		
		model.addAttribute("searchKeyword", searchkeword);
		System.out.println(searchkeword);
		
		List<EmpDto> list = restTemplate.getForObject("http://localhost:9999/api/emplist?searchkeword="+searchkeword, List.class);
		
		model.addAttribute("list", list);
		
		return "/admin_list";
	}
	
	
	
	// 부서 리스트 가져오기
	@ModelAttribute("list_d")
	public List<DeptDto> getDeptList(DeptDto dto_d) {
		List<DeptDto> list_d = restTemplate.getForObject("http://localhost:9999/api/deptlist", List.class);
		return list_d;
	}
	
	// 취미 리스트 가져오기
	@ModelAttribute("list_h")
	public List<HobbyDto> getHobbyList(HobbyDto dto_h) {
		List<HobbyDto> list_h = restTemplate.getForObject("http://localhost:9999/api/hobbylist", List.class);
		return list_h;
	}
	
	@RequestMapping(value = "/admin/{emp_id}")
	public String getView(@PathVariable("emp_id") String emp_id, EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh, Model model) {
		String searchkeword = restTemplate.getForObject("http://localhost:9999/api/searchKeyword", String.class);
		model.addAttribute("searchKeyword", searchkeword);
		
		URI uri = UriComponentsBuilder
	               .fromUriString("http://localhost:9999")
	               .path("/api/empview")
	               .queryParam("emp_id", emp_id)
	               .encode()
	               .build()
	               .toUri();
		
		URI uri2 = UriComponentsBuilder
				.fromUriString("http://localhost:9999")
				.path("/api/emphobbyview")
				.queryParam("emp_id", emp_id)
				.encode()
				.build()
				.toUri();
	               
		EmpDto resultDto = restTemplate.getForObject(uri, EmpDto.class);
		model.addAttribute("dto", resultDto);
		
//		List<EmpDto> list = empService.getEmpList(dto);
		List<EmpDto> list = restTemplate.getForObject("http://localhost:9999/api/emplist", List.class);
		model.addAttribute("list", list);

//		List<HobbyDto> list_h = hobbyService.getHobbyList(dto_h);
		List<HobbyDto> list_h = restTemplate.getForObject("http://localhost:9999/api/hobbylist", List.class);
		model.addAttribute("list_h", list_h);
		
//		List<EmpHobbyDto> list_eh = emphobbyService.getEmpHobbyView(dto_eh);
//		List<HobbyDto> list_eh = restTemplate.getForObject("http://localhost:9999/api/emphobbyview", List.class);
		
		StringBuffer list_eh = restTemplate.getForObject(uri2, StringBuffer.class);
		
		model.addAttribute("emp_hobby_list", list_eh);
		return "/admin_view";
	}
	
	@RequestMapping("/admin/update/{emp_id}")
	public String update(@PathVariable("emp_id") String emp_id, EmpDto dto, DeptDto dto_d, EmpHobbyDto dto_eh, Model model) {
//		empService.update(dto);
		restTemplate.postForObject("http://localhost:9999/api/empupdate", dto,  EmpDto.class);
//		emphobbyService.update_eh(dto_eh);
		restTemplate.postForObject("http://localhost:9999/api/emphobbyupdate", dto_eh, EmpHobbyDto.class);
		if (dto_eh.getHobby_cd().contains(",")) {
			String[] hobby_list = dto_eh.getHobby_cd().split(",");
				for (int i = 0; i < hobby_list.length; i++) {
					dto_eh.setHobby_cd(hobby_list[i]);
					restTemplate.postForObject("http://localhost:9999/api/emphobbyupdate", dto_eh, EmpHobbyDto.class);
				}
			}
		else {
			restTemplate.postForObject("http://localhost:9999/api/emphobbyupdate", dto_eh, EmpHobbyDto.class);
		}
		
		return "redirect:/admin"; 
	}
	
	@RequestMapping(value = "/admin/delete/{emp_id}")
	public String delete(EmpDto dto, Model model)
	{
//		empService.delete(dto);
		restTemplate.postForObject("http://localhost:9999/api/empdelete", dto, EmpDto.class);
		return "redirect:/admin";
	}
	
	
	
	
	
	
	
	
	

	
	
	

	
	
	
}
