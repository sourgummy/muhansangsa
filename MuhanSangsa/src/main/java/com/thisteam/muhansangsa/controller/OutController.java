package com.thisteam.muhansangsa.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thisteam.muhansangsa.service.OutService;
import com.thisteam.muhansangsa.vo.Emp_viewVO;
import com.thisteam.muhansangsa.vo.Out_scheduleListVO;
import com.thisteam.muhansangsa.vo.Out_scheduleVO;
import com.thisteam.muhansangsa.vo.Out_schedule_per_productArrVO;
import com.thisteam.muhansangsa.vo.Out_schedule_per_productVO;
import com.thisteam.muhansangsa.vo.Stock_viewVO;


@Controller
public class OutController {
	//log4j
	private static final Logger logger = LoggerFactory.getLogger(OutController.class);
	@Autowired	
	private OutService service ;
	

	
	

	//========================= SEWON ======================================= 
	
	//출고 예정 목록
	@GetMapping(value = "/OutWaitingSelectList")
	public	String outWaitingSelectList(
			@RequestParam(defaultValue = "") String msg,
			Model model) {
	
	model.addAttribute("msg", msg);
			
	return "out/out_waiting_seletList";
}

	
	//출고 예정 목록 조회 페이지 조회용(JSON)
	//출고 예정목록은 전체, 진행중, 완료 탭으로 구분한다!
	//다만, Mapper를 여러개 사용하지 않기 위해 int status에 -1,0,1이라는 값을 줘서 상태를 구분할꺼다!!!
	@ResponseBody
	@GetMapping(value = "/OutWaitingSelectListJson")
		public	void groupBottomSelectListJson(
				@RequestParam(defaultValue = "") String searchType,
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "1") int pageNum,
				@RequestParam(defaultValue = "-1") int status,
				Model model,
				HttpSession session,
				HttpServletResponse response) {
		
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		
		// 출고 예정 목록(전체,진행중,완료) 가져오기
		List<Out_scheduleListVO> outList = service.getOutList(searchType, keyword, startRow, listLimit,status);
		
		// JSON 형식 변환
		JSONArray jsonArray = new JSONArray();
		
		for(Out_scheduleListVO out : outList) {
			JSONObject jsonObject = new JSONObject(out);
			
			jsonArray.put(jsonObject);
		}
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	
	
	//출고 처리목록
	@GetMapping(value = "/OutProcessingSeletList")
		public	String outProcessingSelectList(
				@RequestParam(defaultValue = "") String msg,
				Model model) {
		model.addAttribute("msg", msg);
		
		// 접속 ip 확인 코드
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return "out/out_processing_seletList";
		
	}
	

	//==========================================================================================

















//======================================= hawon =================================================
	
	//출고 등록
	@GetMapping(value = "/OutInsertForm")
		public	String outInsertForm(
				@RequestParam(defaultValue = "") String msg,
				Model model) {
		model.addAttribute("msg", msg);
		
		// 접속 ip 확인 코드
		InetAddress local;
		String ip;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			model.addAttribute("ip", ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
		
		 // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd00");
        int formatedNow = Integer.parseInt(now.format(formatter));
        model.addAttribute("now", now);
        
		return "out/out_insertForm";
		
	}	

	@PostMapping(value = "/OutInsertFormPro")
	public	String OutInsertFormPro(
									@ModelAttribute Out_scheduleVO out_schedule,
									@ModelAttribute Out_schedule_per_productArrVO out_product_Arr
									,Model model) {
		
		out_schedule.setOut_complete("0");//진행상테 미완료
		int insertCount2 =0;
		
		System.out.println(out_schedule);
		System.out.println(out_product_Arr);
		//1. 출고예정번호(out_schedule_cd) 생성을 위한 오늘날짜 가져오기
		
			 // 현재 날짜 구하기
	        LocalDate now = LocalDate.now();
	        // 포맷 정의 
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        int today = Integer.parseInt(now.format(formatter));
	   
	        //출고예정코드 생성
	        String out_schedule_cd =service.createOut_schedule_cd(today);
	        out_schedule.setOut_schedule_cd(out_schedule_cd);
	        
	        // 신규 출고예정 insert작업 
	        int insertCount1 =  service.registerOutSchedule(out_schedule);
	        System.out.println("신규 출고 발주서 생성 >> " + insertCount1);

	        for(int i = 0; i <out_product_Arr.getP_out_date().length; i++) {
	        	
	        	Out_schedule_per_productVO osp = new Out_schedule_per_productVO();
	        	
	        	osp.setOut_schedule_cd(out_schedule_cd);
	        	osp.setProduct_cd(out_product_Arr.getProduct_cd()[i]);//상품코드
	        	osp.setOut_date(out_product_Arr.getP_out_date()[i]);//납기일자
	        	osp.setOut_schedule_qty(out_product_Arr.getOut_schedule_qty()[i]);//출고예정수량
	        	osp.setStock_cd(out_product_Arr.getStock_cd()[i]);//재고번호
	        	osp.setRemarks(out_product_Arr.getP_remarks()[i]);//적요
	        	osp.setOut_complete("0");//진행상테 미완료
	        	osp.setOut_qty(0);//출고수량
	        	
//	 	       // 신규 출고예정품목 insert작업 
	  	      insertCount2 =  service.registerOutScheduleProduct(osp);
	         }

	        if(insertCount1 > 0 &&insertCount2 >0) {
	        	model.addAttribute("msg", "등록에 성공하였습니다");
	        	model.addAttribute("url", "/OutWaitingSelectList");
	        	return "redirect";
	        }else {
	        	model.addAttribute("msg", "일시적인 오류로 등록에 실패했습니다.");
	        	return "fail_back";
	        }
	       
	}
	
	@ResponseBody
	@GetMapping(value = "/Search_emp.ajax")
	public void Search_emp(@RequestParam(defaultValue = "") String keyword,
							HttpServletResponse response) {
			
		List<Emp_viewVO> empList =  service.searchEmp(keyword);
		JSONArray empArr = new JSONArray();
		for(Emp_viewVO emp : empList) {
			empArr.put(new JSONObject(emp));
			
		}
	      try {
			      response.setCharacterEncoding("UTF-8");
			      response.getWriter().print(empArr); // toString() 생략됨
			      System.out.println("empList: " + empArr);
		   } catch (IOException e) {
		      e.printStackTrace();
		   }
	
	
	}

	
	//품목검색 ajax요청 받는 서블릿주소
	@GetMapping(value = "/findProduct_StockExist.ajax")
	public String findProduct_stockExist(Model model, @RequestParam(defaultValue = "") String keyword) {
		System.out.println("keyword:"+keyword);
			List<Stock_viewVO> product = service.getproduct_stockExist(keyword);
			model.addAttribute("product", product);
		
		return "out/productList_StockExist";
	}
	
	
	//품목검색에서 검색 요청 ajax
	@ResponseBody
	@GetMapping(value = "/findProduct_StockExist_search.ajax")
	public void findProduct_search(Model model, @RequestParam(defaultValue = "") String keyword,HttpServletResponse response) {
		System.out.println("keyword:"+keyword);
		List<Stock_viewVO> product = service.getproduct_stockExist(keyword);
		JSONArray productList = new JSONArray();
		for( Stock_viewVO pro: product) {
			productList.put(new JSONObject(pro));
		}
		
	      try {
		      response.setCharacterEncoding("UTF-8");
		      response.getWriter().print(productList); // toString() 생략됨
		      System.out.println("productList: " + productList);
	   } catch (IOException e) {
	      e.printStackTrace();
	   }
	}
 	//========================= HAWON 끝 ======================================= 
  
  
  
  
  
  
  
  
}













