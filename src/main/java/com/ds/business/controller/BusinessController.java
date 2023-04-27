package com.ds.business.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.business.dto.DeptDto;
import com.ds.business.dto.EmpDto;
import com.ds.business.dto.EmpHobbyDto;
import com.ds.business.dto.HobbyDto;
import com.ds.business.service.DeptService;
import com.ds.business.service.EmpHobbyService;
import com.ds.business.service.EmpService;
import com.ds.business.service.HobbyService;


@RestController
@RequestMapping("/api")
public class BusinessController {
	
	@Resource(name="empService")
	EmpService empService;
	@Resource(name="deptService")
	DeptService deptService;
	@Resource(name="hobbyService")
	HobbyService hobbyService;
	@Resource(name="emphobbyService")
	EmpHobbyService emphobbyService;
	
	// EmpService -------------------------------------------------------------
	@RequestMapping("/emplist")
	public List<EmpDto> getEmpList(EmpDto dto) {
		return empService.getEmpList(dto);
	}
	
	@RequestMapping("/empview")
	public EmpDto getView(EmpDto dto) {
		return empService.getView(dto);
	}
	
	@RequestMapping("/empinsert")
	public ResponseEntity<EmpDto> insert(@RequestBody EmpDto dto) {
		empService.insert(dto);
		return ResponseEntity.ok(dto);
	}
	
	@RequestMapping("/empupdate")
	public ResponseEntity<EmpDto> update(@RequestBody EmpDto dto) {
		empService.update(dto);
		return ResponseEntity.ok(dto);
	}
	
	@RequestMapping("/empdelete")
	public ResponseEntity<EmpDto> delete(@RequestBody EmpDto dto) {
		empService.delete(dto);
		return ResponseEntity.ok(dto);
	}
	
	// dept--------------------------------------------------------------------
	@RequestMapping("/deptlist")
	public List<DeptDto> getDeptList(DeptDto dto_d) {
		return deptService.getDeptList(dto_d);
	}
	
	// hobby-----------------------------------------------------------------------
	@RequestMapping("/hobbylist")
	public List<HobbyDto> getHobbyList(HobbyDto dto_h) {
		return hobbyService.getHobbyList(dto_h);
	}
	
	// emphobby-------------------------------------------------------------------------
	@RequestMapping("/emphobbylist")
	public List<EmpHobbyDto> getEmpHobbyList(EmpHobbyDto dto_eh) {
		return emphobbyService.getEmpHobbyList(dto_eh);
	}
	
	@RequestMapping("/emphobbyview")
	public StringBuffer getEmpHobbyView(EmpHobbyDto dto_eh) {
		List<EmpHobbyDto> list_eh = emphobbyService.getEmpHobbyView(dto_eh);
		StringBuffer emp_hobby_list = new StringBuffer();
		for (int i = 0; i < list_eh.size(); i++) {
			emp_hobby_list.append(list_eh.get(i).getHobby_cd());
		}
		return emp_hobby_list;
	}
	
	@RequestMapping("/emphobbyinsert")
	public ResponseEntity<EmpHobbyDto> insert_eh(@RequestBody EmpHobbyDto dto_eh) {
		emphobbyService.insert_eh(dto_eh);
		return ResponseEntity.ok(dto_eh);
	}
	@RequestMapping("/emphobbyupdate")
	public ResponseEntity<EmpHobbyDto> update_eh(@RequestBody EmpHobbyDto dto_eh) {
		emphobbyService.update_eh(dto_eh);
		return ResponseEntity.ok(dto_eh);
	}
	@RequestMapping("/emphobbydelete")
	public ResponseEntity<EmpHobbyDto> delete_eh(@RequestBody EmpHobbyDto dto_eh) {
		emphobbyService.delete_eh(dto_eh);
		return ResponseEntity.ok(dto_eh);
	}
	
// searchkeyword----------------------------------------------------------------	
	@RequestMapping("/searchKeyword")
	public String getSearchKeyword(EmpDto dto) {
		String searchKeyword = dto.getSearchKeyword();
		return searchKeyword;
	}
	
	
	
//	// 사용자 포털로 이동
//	@RequestMapping(value = "/user")
//	public String user_portal(EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh, Model model) {
//		model.addAttribute("EmpDto", dto);
//		model.addAttribute("EmpHobbyDto", dto_eh);
//		
//		return "/user";
//	}
//	
//	// insert 값 저장
//	@RequestMapping(value = "/user/save")
//	public String save(EmpDto dto, DeptDto dto_d, EmpHobbyDto dto_eh) {
//		empService.insert(dto);
//		if (dto_eh.getHobby_cd().contains(",")) {
//			String[] hobby_list = dto_eh.getHobby_cd().split(",");
//				for (int i = 0; i < hobby_list.length; i++) {
//					dto_eh.setHobby_cd(hobby_list[i]);
//					emphobbyService.insert_eh(dto_eh);
//				}
//			}
//		else {
//			emphobbyService.insert_eh(dto_eh);
//		}
//		return "redirect:/admin";
//	}
//	
//	// 관리자 포털로 이동
//	@RequestMapping(value = "/admin")
//	public String admin_portal(EmpDto dto, Model model) {
//		model.addAttribute("searchKeyword", dto.getSearchKeyword());
//		
//		List<EmpDto> list = empService.getEmpList(dto);
//		model.addAttribute("list", list);
//		
//		return "/admin_list";
//	}
//	
////	// 사용자 리스트 가져오기
////	@ModelAttribute("list")
////	public List<EmpDto> getEmpList(EmpDto dto, Model model){
////		List<EmpDto> list = empService.getEmpList(dto);
////		
////		return list;
////	}
//	
//	// 부서 리스트 가져오기
//	@ModelAttribute("list_d")
//	public List<DeptDto> getDeptList(DeptDto dto_d) {
//		List<DeptDto> list_d = deptService.getDeptList(dto_d);
//		return list_d;
//	}
//	
//	// 취미 리스트 가져오기
//	@ModelAttribute("list_h")
//	public List<HobbyDto> getHobbyList(HobbyDto dto_h) {
//		List<HobbyDto> list_h = hobbyService.getHobbyList(dto_h);
//		
//		return list_h;
//	}
//	
//	@RequestMapping(value = "/admin/{emp_id}")
//	public String getView(@PathVariable("emp_id") String emp_id, EmpDto dto, HobbyDto dto_h, EmpHobbyDto dto_eh, Model model) {
//		model.addAttribute("searchKeyword", dto.getSearchKeyword());
//		
//		EmpDto resultDto = empService.getView(dto);
//		model.addAttribute("dto", resultDto);
//		
//		List<EmpDto> list = empService.getEmpList(dto);
//		model.addAttribute("list", list);
//
//		List<HobbyDto> list_h = hobbyService.getHobbyList(dto_h);
//		model.addAttribute("list_h", list_h);
//		
//		List<EmpHobbyDto> list_eh = emphobbyService.getEmpHobbyView(dto_eh);
//		StringBuffer emp_hobby_list = new StringBuffer();
//		for (int i = 0; i < list_eh.size(); i++) {
//			emp_hobby_list.append(list_eh.get(i).getHobby_cd());
//		}
//		model.addAttribute("emp_hobby_list", emp_hobby_list);
//		System.out.println(dto.getSearchKeyword());
//		return "/admin_view";
//	}
//	
//	@RequestMapping("/admin/update/{emp_id}")
//	public String update(@PathVariable("emp_id") String emp_id, EmpDto dto, DeptDto dto_d, EmpHobbyDto dto_eh, Model model) {
//		empService.update(dto);
//		emphobbyService.update_eh(dto_eh);
//		if (dto_eh.getHobby_cd().contains(",")) {
//			String[] hobby_list = dto_eh.getHobby_cd().split(",");
//				for (int i = 0; i < hobby_list.length; i++) {
//					dto_eh.setHobby_cd(hobby_list[i]);
//					emphobbyService.insert_eh(dto_eh);
//				}
//			}
//		else {
//			emphobbyService.insert_eh(dto_eh);
//		}
//		
//		return "redirect:/admin"; 
//	}
//	
//	@RequestMapping(value = "/admin/delete/{emp_id}")
//	public String delete(EmpDto dto, Model model)
//	{
//		empService.delete(dto);
//		return "redirect:/admin";
//	}
	
	
	
	
	
	
	
	
	

	
	
	

	
	
	
}
