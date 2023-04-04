package com.thisteam.muhansangsa.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thisteam.muhansangsa.service.EmployeesService;
import com.thisteam.muhansangsa.service.WMSWhService;
import com.thisteam.muhansangsa.service.WarehouseService;
import com.thisteam.muhansangsa.vo.ClientVO;
import com.thisteam.muhansangsa.vo.Privilege;
import com.thisteam.muhansangsa.vo.WarehouseVO;
import com.thisteam.muhansangsa.vo.WhAreaVO;
import com.thisteam.muhansangsa.vo.WhLocAreaVO;

@Controller
public class WMSWhController {
	
	@Autowired
	private WMSWhService service;
	@Autowired
	private WarehouseService whService;
	@Autowired
	private EmployeesService empService; // 사원 서비스
	
	@GetMapping(value = "WmsWarehouse")
	public String wmsWarehouse(
			Model model,
			HttpSession session
			) {
		
		// 세션 아이디
		String sId = "";
		if(session.getAttribute("sId") != null) {
			sId = (String)session.getAttribute("sId");
		} else {
			model.addAttribute("msg", "로그인이 필요합니다");
			model.addAttribute("url", "/Login");
			return "redirect"; // 어떻게 alert 후에 보내지? => 해결 by. 하원
		}
		
		// 아이피 주소
		InetAddress local = null;
		String ip = "";
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(sId != null && !sId.equals("")) {  // 세션 아이디 있을 경우
			
			System.out.println("sId : " + sId);

			// 권한 조회 메서드
		    boolean isRightUser = empService.getPrivilege(sId, Privilege.WMS관리);
		    System.out.println("거래처등록 권한: " + isRightUser);
//		    isRightUser = true; // 임시
		    
		    if(isRightUser) { // 권한 존재할 경우
	            model.addAttribute("priv", "1");
		
				List<WarehouseVO> whList = null; // 창고 목록
//				List<WhAreaVO> whaList = null; // 창고 구역 목록
//				Map<String, List<WhAreaVO>> whWhaList = null; // 특정 창고에 해당하는 창고 구역의 목록
//				List<WhLocAreaVO> whlaList = null; // 창고 구역 내 위치 목록
//				Map<String, List<WhLocAreaVO>> whWhlaList = null; // 특정 창고 구역에 해당하는 창고 구역 내 위치 목록
				
				// 창고 가져오기
				whList = whService.getWarehouseList("", "", 0, 100, -1);
				
//				whWhaList = new HashMap<String, List<WhAreaVO>>(); // <창고 코드, 창고 코드에 해당하는 창고 구역 목록>
//				whWhlaList = new HashMap<String, List<WhLocAreaVO>>(); // <창고 구역, 창고 구역에 해당하는 위치 목록>
				
//				for(WarehouseVO wh : whList) { // 모든 창고 목록에 접근
//					
//					String wh_cd = wh.getWh_cd(); // 창고 코드 변수에 저장
//					String wh_name = wh.getWh_name(); // 창고 이름 변수에 저장
//					
//					whaList = service.getWhAreaList(wh_cd); // 해당 창고의 창고 구역 가져오기
//					whWhaList.put(wh_name, whaList); // 특정 창고에 해당하는 창고 구역 목록을 저장
//					
//					for(WhAreaVO wha : whaList) { // 모든 창고 구역 목록에 접근
//						
//						int wh_area_cd = wha.getWh_area_cd(); // 창고 구역 코드 변수에 저장
//						String wh_area = wha.getWh_area(); // 창고 구역 변수에 저장
//						
//						whlaList = service.getWhLocAreaList(wh_area_cd); // 해당 창고 구역 내 위치 가져오기
//						whWhlaList.put(wh_area, whlaList); // 특정 창고 구역에 해당하는 창고 구역 내 위치 목록을 저장
//						
//					}
//				
//				}
		
//				System.out.println(whWhaList);
//				System.out.println(whWhlaList);
//				System.out.println("부산 창고 구역 목록 : " + whWhaList.get("부산"));
				
				model.addAttribute("whList", whList);
//				model.addAttribute("whWhaList", whWhaList);
//				model.addAttribute("whWhlaList", whWhlaList);
				
				// Wms_wh_viewVO 사용
//				List<Wms_wh_viewVO> whViewList = service.getWhViewList();
//				
//				model.addAttribute("whViewList", whViewList);
				
				return "wms_wh/wms_list";
				
		    } else { // 권한 없을 경우
				model.addAttribute("msg", "권한이 없습니다.");
				return "fail_back";
			}
		
		} else { // 세션 아이디 없을 경우
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "fail_back";
		}
		
	}
	
	// 창고 구역 목록 조회
	@ResponseBody
	@GetMapping(value = "WmsWarehouseArea")
	public void wmsWarehouseArea(
			@RequestParam String wh_cd,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
		// IP 주소
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		List<WhAreaVO> whaList = service.getWhAreaList(wh_cd); // 특정 창고 구역 목록 가져오기
		
		// JSON 형식 변환
		JSONArray jsonArray = new JSONArray();
		
		for(WhAreaVO whArea : whaList) {
			JSONObject jsonObject = new JSONObject(whArea);
			System.out.println(jsonObject.get("wh_area"));
			
			jsonArray.put(jsonObject);
		}
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 창고 구역 위치 목록 조회
	@ResponseBody
	@GetMapping(value = "WmsWarehouseLocArea")
	public void wmsWarehouseLocArea(
			@RequestParam int wh_area_cd,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
		List<WhLocAreaVO> whlaList = service.getWhLocAreaList(wh_area_cd); // 특정 창고 구역 위치 목록 가져오기
		
		// JSON 형식 변환
		JSONArray jsonArray = new JSONArray();
		
		for(WhLocAreaVO whLocArea : whlaList) {
			JSONObject jsonObject = new JSONObject(whLocArea);
			System.out.println(jsonObject.get("wh_loc_in_area"));
			
			jsonArray.put(jsonObject);
		}
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// 창고 구역 등록
	@GetMapping(value = "RegistWhArea")
	public String registWhArea (
			@ModelAttribute WhAreaVO whArea,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
//		System.out.println(whArea);
		
		// 세션 아이디
		String sId = "";
		if(session.getAttribute("sId") != null) {
			sId = (String)session.getAttribute("sId");
		} else {
			model.addAttribute("msg", "로그인이 필요합니다");
			model.addAttribute("url", "/Login");
			return "redirect"; // 어떻게 alert 후에 보내지? => 해결 by. 하원
		}
		
		// 아이피 주소
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(sId != null && !sId.equals("")) {  // 세션 아이디 있을 경우
			
			System.out.println("sId : " + sId);

			// 권한 조회 메서드
		    boolean isRightUser = empService.getPrivilege(sId, Privilege.WMS관리);
		    System.out.println("거래처등록 권한: " + isRightUser);
//		    isRightUser = true; // 임시
		    
		    if(isRightUser) { // 권한 존재할 경우
	            model.addAttribute("priv", "1");
		
					int insertCount = service.registWhArea(whArea);
					
					if(insertCount > 0) {
						
						try {
							response.getWriter().print("<script>");
							response.getWriter().print("alert('창고 구역이 등록되었습니다.')");
							response.getWriter().print("</script>");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						return "redirect:/WmsWarehouse";
					} else {
						model.addAttribute("msg", "창고 구역이 등록되지 않았습니다.");
						return "fail_back";
					}
					
		    } else { // 권한 없을 경우
				model.addAttribute("msg", "권한이 없습니다.");
				return "fail_back";
			}
		
		} else { // 세션 아이디 없을 경우
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "fail_back";
		}
		
	}
	
	// 창고 구역 내 위치 등록
	@GetMapping(value = "RegistWhLocArea")
	public String registWhArea (
			@ModelAttribute WhLocAreaVO whLocArea,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
//		System.out.println(whLocArea);
		
		// 세션 아이디
		String sId = "";
		if(session.getAttribute("sId") != null) {
			sId = (String)session.getAttribute("sId");
		} else {
			model.addAttribute("msg", "로그인이 필요합니다");
			model.addAttribute("url", "/Login");
			return "redirect"; // 어떻게 alert 후에 보내지? => 해결 by. 하원
		}
		
		// 아이피 주소
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(sId != null && !sId.equals("")) {  // 세션 아이디 있을 경우
			
			System.out.println("sId : " + sId);

			// 권한 조회 메서드
		    boolean isRightUser = empService.getPrivilege(sId, Privilege.WMS관리);
		    System.out.println("거래처등록 권한: " + isRightUser);
//		    isRightUser = true; // 임시
		    
		    if(isRightUser) { // 권한 존재할 경우
	            model.addAttribute("priv", "1");
		
				int insertCount = service.registWhLocArea(whLocArea);
				
				if(insertCount > 0) {
					try {
						response.getWriter().print("<script>");
						response.getWriter().print("alert('창고 구역 내 위치가 등록되었습니다.')");
						response.getWriter().print("</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return "redirect:/WmsWarehouse";
				} else {
					model.addAttribute("msg", "창고 구역 내 위치가 등록되지 않았습니다.");
					return "fail_back";
				}
			
		    } else { // 권한 없을 경우
				model.addAttribute("msg", "권한이 없습니다.");
				return "fail_back";
			}
		
		} else { // 세션 아이디 없을 경우
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "fail_back";
		}
		
	}
	
	// 창고 구역 수정
	@GetMapping(value = "ModifyWhArea")
	public String modifyWhArea (
			@ModelAttribute WhAreaVO whArea,
//			@RequestParam int wh_area_cd,
//			@RequestParam String wh_area,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
		// 세션 아이디
		String sId = "";
		if(session.getAttribute("sId") != null) {
			sId = (String)session.getAttribute("sId");
		} else {
			model.addAttribute("msg", "로그인이 필요합니다");
			model.addAttribute("url", "/Login");
			return "redirect"; // 어떻게 alert 후에 보내지? => 해결 by. 하원
		}
		
		// 아이피 주소
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(sId != null && !sId.equals("")) {  // 세션 아이디 있을 경우
			
			System.out.println("sId : " + sId);

			// 권한 조회 메서드
		    boolean isRightUser = empService.getPrivilege(sId, Privilege.WMS관리);
		    System.out.println("거래처등록 권한: " + isRightUser);
//		    isRightUser = true; // 임시
		    
		    if(isRightUser) { // 권한 존재할 경우
	            model.addAttribute("priv", "1");
		
				int updateCount = service.modifyWhArea(whArea);
				
				if(updateCount > 0) {
					try {
						response.getWriter().print("<script>");
						response.getWriter().print("alert('창고 구역 내 위치가 등록되었습니다.')");
						response.getWriter().print("</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return "redirect:/WmsWarehouse";
				} else {
					model.addAttribute("msg", "창고 구역 내 위치가 등록되지 않았습니다.");
					return "fail_back";
				}
				
		    } else { // 권한 없을 경우
				model.addAttribute("msg", "권한이 없습니다.");
				return "fail_back";
			}
		
		} else { // 세션 아이디 없을 경우
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "fail_back";
		}
		
	}
	
	// 창고 구역 내 위치 수정
	@GetMapping(value = "ModifyWhLocArea")
	public String modifyWhLocArea (
			@ModelAttribute WhLocAreaVO whLocArea,
//			@RequestParam String newWhLocArea,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
		// 세션 아이디
		String sId = "";
		if(session.getAttribute("sId") != null) {
			sId = (String)session.getAttribute("sId");
		} else {
			model.addAttribute("msg", "로그인이 필요합니다");
			model.addAttribute("url", "/Login");
			return "redirect"; // 어떻게 alert 후에 보내지? => 해결 by. 하원
		}
		
		// 아이피 주소
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(sId != null && !sId.equals("")) {  // 세션 아이디 있을 경우
			
			System.out.println("sId : " + sId);

			// 권한 조회 메서드
		    boolean isRightUser = empService.getPrivilege(sId, Privilege.WMS관리);
		    System.out.println("거래처등록 권한: " + isRightUser);
//		    isRightUser = true; // 임시
		    
		    if(isRightUser) { // 권한 존재할 경우
	            model.addAttribute("priv", "1");
		
				int updateCount = service.modifyWhLocArea(whLocArea);
				
				if(updateCount > 0) {
					try {
						response.getWriter().print("<script>");
						response.getWriter().print("alert('창고 구역 내 위치가 등록되었습니다.')");
						response.getWriter().print("</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return "redirect:/WmsWarehouse";
				} else {
					model.addAttribute("msg", "창고 구역 내 위치가 등록되지 않았습니다.");
					return "fail_back";
				}
				
		    } else { // 권한 없을 경우
				model.addAttribute("msg", "권한이 없습니다.");
				return "fail_back";
			}
		
		} else { // 세션 아이디 없을 경우
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "fail_back";
		}
		
	}
	
	// 창고 구역 삭제
	@GetMapping(value = "DeleteWhArea")
	public void deleteWhArea (
			@RequestParam int wh_area_cd, // 창고 구역 코드
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
		int deleteCount = service.deleteWhArea(wh_area_cd);
		
//		if(deleteCount > 0) {
//			
//		} else {
//			
//		}
		
	}
	
	// 창고 구역 내 위치 삭제
	@GetMapping(value = "DeleteWhLocArea")
	public void deleteWhLocArea (
			@RequestParam int wh_loc_in_area_cd,
			Model model,
			HttpSession session,
			HttpServletResponse response
			) {
		
		int deleteCount = service.deleteWhLocArea(wh_loc_in_area_cd);
		
//		if(deleteCount > 0) {
//			
//		} else {
//			
//		}
		
	}
	
	
	
	
	
}














