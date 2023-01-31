<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html >
<head>
	<title>인사 조회</title>

    <meta charset="utf-8">
    <meta name="description" content="Ela Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
    <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
	<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- 모달창 -->
<!-- Remember to include jQuery :) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
</head>
<style type="text/css">

	@font-face {
	    font-family: 'Pretendard-Regular';
	    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
	    font-weight: 400;
	    font-style: normal;
	}

	
	@font-face {
	    font-family: 'NEXON Lv1 Gothic OTF';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	
	body {
	 font-family: 'NEXON Lv1 Gothic OTF';
	 width: 100%;
	 height: 100%;
	}
		
	#vertical-align{
		 vertical-align: middle;
	}
	table{
	 text-align: center;
	}
	
	th{
	height: 100px;
	}
		
	.id_pht{
	 width: 80px;
	 transition: 0.5s;
	}
	

	#modal_container{
		height: 300px;
		width: 500px;
	
	}
	
	a.close-modal{
		display: hidden;
	}
	
/* 	.id_pht:hover{ */
/* 	 width: 300px; */
/* 	 position: absolute; */
/* 	} */


#modal_container_dept{
  width: 70%;
  height: 700px;
  position: fixed;
  top: 10%;
  left: 50%;
  overflow-y: scroll;
}
	
#modal_container > th {
	height: 10px;
	}
 a{
 text-decoration: none;
 color: 	#000080;
}
 a:visited :active{
 text-decoration: none;
 color: 	#000080;
}
</style>
<script src="resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
		
	
	
	

	$(document).on("change","input[name=empChecked]",function(){
		$("#selectCount").html($('input:checkbox[name=empChecked]:checked').length +" 개 선택됨");
		
// 		alert("체크됨: "+ $(this).val());
		let idx = $(this).val().split("/")[0];
		let emp_name = $(this).val().split("/")[1];
		let emp_num = $(this).val().split("/")[2];
		let emp_info = {"emp_num":emp_num,"emp_name":emp_name};
		map.set(emp_num,emp_info);
	
		
// 		let text = " <span class='badge badge-secondary'>"+map.get(emp_num)+"</span> "
// 		$("#selected_empList").append(text);

// 		let tr_id  = $(this).closest('tr').attr('id');// 버튼이 속한 tr태그의 id가져오기
		
	});
	
	   $('a[href="#modal_container"]').click(function(event) {
		      event.preventDefault();
		      $('a[href="#modal_container"]').modal({
		        fadeDuration: 250
		      });

		      
		      
		    });
	   

	$(function() {
		
		//상세조회 버튼 클릭
		$("#detailBtn").on("click", function () {
// 			alert("");
			window.open("employees/detail?");
			
			
			
		});
		
		//모달창
// 		$("input[type=radio]").


		
		//
		$("#changeCollective").on("click",function(){
		  
		     	 
		     	 
		     	$('input:checkbox[name=empChecked]').each(function (index) {
		     		if($(this).is(":checked")==true){
		     	    	console.log($(this).val());
		     			let idx = $(this).val().split("/")[0];
		     			let emp_name = $(this).val().split("/")[1];
		     			let emp_num = $(this).val().split("/")[2];
		     	    	
				  		let text = " <span class='badge badge-secondary p-1'>"+emp_name+"("+emp_num+")"+"</span> "
				  		$("#selected_empList").append(text);
		     	    }
		     	});
		     	 
		     	 

		});
		
		
	 $('#dept_td').click(function(event) {
	      event.preventDefault(); 
	      $(this).modal({
	        fadeDuration: 250,
	/*
	      escapeClose: false,
	      clickClose: false,
	      showClose: false
	*/
	      });
	    });
			

    
	});
	
	
	
	
	function dept_modal(dept_cd,dept_name) {
		console.log(dept_cd);
		console.log(dept_name);

		
		$.ajax({
			
		
			        type: "post",
			        url: "dept_detail",
			        data: {
			        	"dept_cd":dept_cd,
			        	"dept_name": dept_name
			        },
			        dataType: "html",
			        success: function(data,status,xhr) {
			          
			               
			            $("#modal_container_dept").html(data);
			
			        },
			        error: function(xhr,status,error) {
			            console.log(error);
			        }

			
		});
		
		
	}
	
	


</script>
<body>

<jsp:include page="inc/left.jsp"></jsp:include>

 <div class=" pr-4 mr-4 mb-1 mt-4 float-right"><small> *접속 IP: ${ip}</small></div> 
        <div class=" breadcrumbs m-0">
            <div class="breadcrumbs-inner rounded-start p-2 " >
                <div class="row m-0 ">
                    <div class="col-sm-4 ">
                        <div class="page-header float-left rounded-start ">
                            <div class="page-title ">
                               <h1 class="m-1"><b>사원 관리</b></h1>   
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-8 rounded-pill ">
                        <div class="page-header float-right rounded-start ">
                            <div class="page-title ">
                                <ol class="breadcrumb text-right ">
                                    <li><a href="employees">인사</a></li>
                                    <li><a href="#">사원 관리</a></li>
                                   
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<!-- Modal HTML embedded directly into document -->

	<div id="modal_container" class="modal">
 	<div>
	<div class=" m-3 border border-light border-top-0 rounded-2 border border-1"> 
		<div class="p-2 bg-light text-black well rounded-2" >&#128505;선택된 사원</div>
		<div class="mx-3 py-3" id="selected_empList">
			
			
		</div>
		<div>
		<input type="radio" name="changeInfo" value="work_change">재직상태 변경 
		<input type="radio" name="changeInfo" value="dept_change">부서 변경<br>
		<select>
			<option disabled selected value="">선택하세요</option>
			<option>재직</option>
			<option>휴직</option>
			<option>퇴사</option>
		</select>
		<select>
			<option disabled selected value="">선택하세요</option>
			<option>인사팀</option>
			<option>개발팀</option>
			<option>물류관리팀</option>
			<option>영업팀</option>
		</select>
		
		
		</div>
		
	</div>
	<div>
 	
 	</div>
 	</div>
 <input type="button" value="일괄 수정" class = "btn btn-primary btn-sm m-3 " name="" id="">
  <a href="#" rel="modal:close">Close</a>
 
</div><!-- end of DIV #modal_container -->

<!-- Link to open the modal -->
<p><a href="#modal_container" rel="modal:open">Open Modal</a></p>

<div class="content">
   <div class="animated fadeIn">
   <div id = "selectCount"> 0 개 선택됨</div>
	   <section id="searchSection" class="m-0 d-flex justify-content-end">
	   <form action="employees_search">
				<!-- 검색 타입 추가 -->
				<select name="searchType" id="searchType" class="rounded-1 btn-sm p-1">
					<option value="emp_name" <c:if test="${param.searchType eq 'emp_name'}">selected</c:if>>사원이름</option>
					<option value="emp_num" <c:if test="${param.searchType eq 'emp_num'}">selected</c:if>>사원번호</option>
					<option value="emp_email" <c:if test="${param.searchType eq 'emp_email'}">selected</c:if>>이메일</option>
					<option value="dept_name" <c:if test="${param.searchType eq 'dept_name'}">selected</c:if>>부서</option>
				</select>			
				<input type="text"  class="col-sm-5 bg-light border border-secondary rounded-1 px-1" name="keyword" id="keyword" value="${param.keyword }"> 
				<input type="submit" value="검색"  class=" mx-1 btn btn-sm btn-dark rounded-1" >
		</form>
	   </section>
	<table class="table "  id="vertical-align">
		<thead>
			<tr>
			<c:if test="${priv eq 1 }">
<!-- 				<th></th> -->
				<th></th><!-- 관리 권한 부여 시 조회 가능 -->
			</c:if>
				<th>사진</th>
				<th>사원번호</th>
				<th>사원명</th>
				<th>부서명</th>
				<th>직급</th>
				<th>연락처</th>
				<th>E-Mail</th>
			<c:if test="${priv eq 1 }">
				<th>관리</th><!-- 관리 권한 부여 시 조회 가능 -->
			</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${empList }" var="emp" varStatus="status" >
			<tr>
			<c:if test="${priv eq 1 }"><!-- 관리 권한 부여 시 보여짐 가능 -->
<%-- 				<td>${status.index}</td> --%>
				<td><input type="checkbox" name="empChecked" id="empChecked" value="${status.index}/${emp.emp_name }/${emp.emp_num }"></td>
			</c:if>
				<td><img class="id_pht" alt="증명사진" src="resources/images/id_photo01.jpg"></td>
				<td>${emp.emp_num }</td>
				<td>${emp.emp_name }</td>
				<td onclick="dept_modal('${emp.dept_cd}','${emp.dept_name }');">
				<a href="#modal_container_dept" rel="modal:open">
				${emp.dept_name }</a></td>
				<td>${emp.grade_name }</td>
				<td>${emp.emp_tel }</td>
				<td><a href="#">${emp.emp_email }</a></td>
				<c:if test="${priv eq 1 }">
					<th><!-- 관리 권한 부여 시 조회 가능 -->
						<input type="button" value="상세 조회" class = "btn btn-primary btn-sm m-1" name="detailBtn" id="detailBtn"> 
		 				<input type="button" value="수정" class = "btn btn-primary btn-sm m-1" name="updateBtn" id="updateBtn">
		 			</th>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	
	</table>
	<div class="float-right">
		<input type="button" value="신규등록" class = "btn btn-sm btn-success m-2" name="">
		<a href="#modal_container" rel="modal:open"><input type="button" value="일괄변경" class = "btn btn-sm btn-success m-2" id="changeCollective"></a>
	</div>

<!-- 부서 정보 관련 모달 영역 DIV  -->

	<div id="modal_container_dept" class="modal">

 		 <a href="#" rel="modal:close">Close</a>
 
	</div><!-- end of DIV #modal_container -->


</div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br>
<jsp:include page="inc/footer.jsp"></jsp:include>
    
<!-- Scripts -->
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script> -->
<!-- <script src="resources/assets/js/main.js"></script> -->
</body>
</html>
