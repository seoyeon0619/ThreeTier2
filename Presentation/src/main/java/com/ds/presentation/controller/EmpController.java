package com.ds.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ds.business.dto.DeptDto;
import com.ds.business.dto.EmpDto;
import com.ds.business.dto.EmpHobbyDto;
import com.ds.business.dto.HobbyDto;
import com.ds.presentation.service.PresentationService;


@Controller
public class EmpController {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	PresentationService service;
	
	// 사용자 포털로 이동
	@RequestMapping(value = "/user")
	public String user_portal(EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh, Model model) {
		List<EmpDto> list = service.getEmpList(dto);
//		List<EmpHobbyDto> list_eh = service.getEmpHobbyList(dto_eh);
		model.addAttribute("EmpDto", dto);
//		model.addAttribute("EmpHobbyDto", dto_eh);
		
		return "/user";
	}
	
	// insert 값 저장
	@RequestMapping(value = "/user/save")
	public String save(EmpDto dto, DeptDto dto_d, EmpHobbyDto dto_eh) {
//		empService.insert(dto);
//		restTemplate.postForObject("http://localhost:9999/api/empinsert", dto, EmpDto.class);
		service.insert(dto);
		if (dto_eh.getHobby_cd().contains(",")) {
			String[] hobby_list = dto_eh.getHobby_cd().split(",");
				for (int i = 0; i < hobby_list.length; i++) {
					dto_eh.setHobby_cd(hobby_list[i]);
//					emphobbyService.insert_eh(dto_eh);
//					restTemplate.postForObject("http://localhost:9999/api/emphobbyinsert", dto_eh, EmpHobbyDto.class);
					service.insert_eh(dto_eh);
				}
		}
		else {
//			restTemplate.postForObject("http://localhost:9999/api/emphobbyinsert", dto_eh, EmpHobbyDto.class);
			service.insert_eh(dto_eh);
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
		
//		List<EmpDto> list = restTemplate.getForObject("http://localhost:9999/api/emplist?searchkeword="+searchkeword, List.class);
		List<EmpDto> list = service.getEmpList(dto);
		model.addAttribute("list", list);
		
		return "/admin_list";
	}
	
	
	
	// 부서 리스트 가져오기
	@ModelAttribute("list_d")
	public List<DeptDto> getDeptList(DeptDto dto_d) {
//		List<DeptDto> list_d = restTemplate.getForObject("http://localhost:9999/api/deptlist", List.class);
		List<DeptDto> list_d = service.getDeptList(dto_d);
		return list_d;
	}
	
	// 취미 리스트 가져오기
	@ModelAttribute("list_h")
	public List<HobbyDto> getHobbyList(HobbyDto dto_h) {
//		List<HobbyDto> list_h = restTemplate.getForObject("http://localhost:9999/api/hobbylist", List.class);
		List<HobbyDto> list_h = service.getHobbyList(dto_h);
		return list_h;
	}
	
	@RequestMapping(value = "/admin/{emp_id}")
	public String getView(@PathVariable("emp_id") String emp_id, EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh, Model model) {
		EmpDto resultDto = service.getView(emp_id, dto, dto_h, dto_eh);
		model.addAttribute("dto", resultDto);
		
		List<EmpDto> list = service.getEmpList(dto);
		model.addAttribute("list", list);

		List<HobbyDto> list_h = service.getHobbyList(dto_h);
		model.addAttribute("list_h", list_h);
		
		String list_eh = service.getEmpHobbyView(emp_id,dto_eh);
		model.addAttribute("emp_hobby_list", list_eh);
		return "/admin_view";
	}
	
	@RequestMapping("/admin/update/{emp_id}")
	public String update(@PathVariable("emp_id") String emp_id, EmpDto dto, DeptDto dto_d, EmpHobbyDto dto_eh, Model model) {
//		empService.update(dto);
//		restTemplate.postForObject("http://localhost:9999/api/empupdate", dto,  EmpDto.class);
		service.update(dto);
//		emphobbyService.update_eh(dto_eh);
//		restTemplate.postForObject("http://localhost:9999/api/emphobbyupdate", dto_eh, EmpHobbyDto.class);
		service.update_eh(dto_eh);
		if (dto_eh.getHobby_cd().contains(",")) {
			String[] hobby_list = dto_eh.getHobby_cd().split(",");
				for (int i = 0; i < hobby_list.length; i++) {
					dto_eh.setHobby_cd(hobby_list[i]);
//					restTemplate.postForObject("http://localhost:9999/api/emphobbyupdate", dto_eh, EmpHobbyDto.class);
					service.update_eh(dto_eh);
				}
			}
		else {
//			restTemplate.postForObject("http://localhost:9999/api/emphobbyupdate", dto_eh, EmpHobbyDto.class);
			service.update_eh(dto_eh);
		}
		
		return "redirect:/admin"; 
	}
	
	@RequestMapping(value = "/admin/delete/{emp_id}")
	public String delete(EmpDto dto, Model model)
	{
//		empService.delete(dto);
//		restTemplate.postForObject("http://localhost:9999/api/empdelete", dto, EmpDto.class);
		service.delete(dto);
		return "redirect:/admin";
	}
	
	
	
	
	
	
	
	
	

	
	
	

	
	
	
}
