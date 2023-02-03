<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>MuhanSangsa - Client List</title>
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

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->

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
	}
		
	#client_table{
		 vertical-align: middle;
	}
	table{
	 text-align: center;
	}
	
	td {
	height: 100px;
	}
		
	.id_pht{
	 width: 80px;
	 transition: 0.5s;
	}
	
</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

	//AJAX 를 활용한 게시물 목록 표시에 사용될 페이지 번호값 미리 저장
	let pageNum = 1;
	
	$(function() {
		
		let searchType = $("#searchType").val(); // 검색 타입
		let keyword = $("#keyword").val(); // 검색어
		
		load_list(searchType, keyword); // 게시물 목록 조회 함수 호출
		
	});
	
	// 게시물 목록 조회 함수
	function load_list(searchType, keyword) {
		$.ajax({
			type: "GET",
			url: "ClientListJson?pageNum=" + pageNum + "&searchType=" + searchType + "&keyword=" + keyword,
			dataType: "json"
		})
		.done(function(clientList) { // 요청 성공 시
			for(let client of clientList) {
// 				alert(client.business_no);
				let strBn = '"' + client.business_no + '"'; // 파라미터 문자열로 보내려면 "" 결합해주기ㅠㅠㅠ!! - by. 킹갓제너럴영진
				let result = "<tr>"
							+ "<td onclick='openClientDetail(" + strBn + ")'>" + client.business_no + "</td>"
							+ "<td onclick='openClientDetail(" + strBn + ")'>" + client.cust_name + "</td>"
// 							+ "<td><a href='ClientDetail?business_no=" + client.business_no + "'>" + client.business_no + "</a></td>"
// 							+ "<td><a href='ClientDetail?business_no=" + client.business_no + "'>" + client.cust_name + "</a></td>"
							+ "<td>" + client.boss_name + "</td>"
							+ "<td>" + client.tel + "</td>"
							+ "<td>" + client.mobile_no + "</td>"
							+ "<td>" + client.addr + "</td>"
							+ "<td>" + client.remarks + "</td>"
							+ "</tr>";
				$("#client_table").append(result);
			}
		})
		.fail(function() {
			$("#client_table").append("요청 실패..ㅠㅠ");
		});
	}
	
	// 거래처 세부 정보 창 - 아아ㅏ아가가각가가각!!!1!!!!!! 파라미터 왜 이따구로 넘어와ㅠㅠㅠ 해겨류ㅠㅠㅠㅠㅠㅠ!!!!! - by. 킹갓제너럴영진
	function openClientDetail(business_no) {
// 		let business_no = strBn.replace("N", "");
// 		alert(business_no);
		window.open("ClientDetail?business_no=" + business_no, "_blank", "width=650, height=800, top=100, left=1000");
		
	}
	
</script>

</head>
<body>
    <!-- Left Panel -->
<jsp:include page="../inc/left.jsp"></jsp:include>
<!--     <aside id="left-panel" class="left-panel"> -->
<!--         <nav class="navbar navbar-expand-sm navbar-default"> -->

<!--             <div id="main-menu" class="main-menu collapse navbar-collapse"> -->
<!--                 <ul class="nav navbar-nav"> -->
<!--                     <li> -->
<!--                         <a href="index.html"><i class="menu-icon fa fa-laptop"></i>Dashboard </a> -->
<!--                     </li> -->
<!--                     <li class="menu-title">인사</li>/.menu-title -->
<!--                     <li class="menu-item-has-children dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-cogs"></i>사원 등록</a> -->
<!--                         <ul class="sub-menu children dropdown-menu">                            <li><i class="fa fa-puzzle-piece"></i><a href="ui-buttons.html">a</a></li> -->
<!--                             <li><i class="fa fa-id-badge"></i><a href="ui-badges.html">b</a></li> -->
<!--                             <li><i class="fa fa-bars"></i><a href="ui-tabs.html">c</a></li> -->

<!--                             <li><i class="fa fa-id-card-o"></i><a href="ui-cards.html">Cards</a></li> -->
<!--                             <li><i class="fa fa-exclamation-triangle"></i><a href="ui-alerts.html">Alerts</a></li> -->
<!--                             <li><i class="fa fa-spinner"></i><a href="ui-progressbar.html">Progress Bars</a></li> -->
<!--                             <li><i class="fa fa-fire"></i><a href="ui-modals.html">Modals</a></li> -->
<!--                             <li><i class="fa fa-book"></i><a href="ui-switches.html">Switches</a></li> -->
<!--                             <li><i class="fa fa-th"></i><a href="ui-grids.html">Grids</a></li> -->
<!--                             <li><i class="fa fa-file-word-o"></i><a href="ui-typgraphy.html">Typography</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     <li class="menu-item-has-children dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>사원 조회</a> -->
<!--                         <ul class="sub-menu children dropdown-menu"> -->
<!--                             <li><i class="fa fa-table"></i><a href="tables-basic.html">Basic Table</a></li> -->
<!--                             <li><i class="fa fa-table"></i><a href="tables-data.html">Data Table</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->

<!--                     <li class="menu-title">재고 관리</li>/.menu-title -->

<!--                     <li class="menu-item-has-children active dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-tasks"></i>기본 등록</a> -->
<!--                         <ul class="sub-menu children dropdown-menu"> -->
<!--                             <li><i class="menu-icon fa fa-fort-awesome active dropdown-toggle" id="client" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i><a href="ClientList">거래처 등록</a> -->
<!--                             	<span class="dropdown-menu grandchildren" aria-labelledby="client"> -->
<!-- 	                                <a class="dropdown-item media" href="ClientList"> -->
<!-- 	                                    <i class="fa fa-check"></i> -->
<!-- 	                                    <p>거래처 조회</p> -->
<!-- 	                                </a> -->
<!-- 	                                <a class="dropdown-item media" href="ClientInsertForm"> -->
<!-- 	                                    <i class="fa fa-info"></i> -->
<!-- 	                                    <p>거래처 등록</p> -->
<!-- 	                                </a> -->
<!--                             	</span> -->
<!--                             </li> -->
<!--                             <li><i class="menu-icon ti-themify-logo"></i><a href="font-themify.html">창고 등록</a></li> -->
<!--                             <li><i class="menu-icon ti-themify-logo"></i><a href="font-themify.html">품목 등록</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="widgets.html"> <i class="menu-icon ti-email"></i>Widgets </a> -->
<!--                     </li> -->
<!--                     <li class="menu-item-has-children dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Charts</a> -->
<!--                         <ul class="sub-menu children dropdown-menu"> -->
<!--                             <li><i class="menu-icon fa fa-line-chart"></i><a href="charts-chartjs.html">Chart JS</a></li> -->
<!--                             <li><i class="menu-icon fa fa-area-chart"></i><a href="charts-flot.html">Flot Chart</a></li> -->
<!--                             <li><i class="menu-icon fa fa-pie-chart"></i><a href="charts-peity.html">Peity Chart</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->

<!--                     <li class="menu-item-has-children dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-area-chart"></i>Maps</a> -->
<!--                         <ul class="sub-menu children dropdown-menu"> -->
<!--                             <li><i class="menu-icon fa fa-map-o"></i><a href="maps-gmap.html">Google Maps</a></li> -->
<!--                             <li><i class="menu-icon fa fa-street-view"></i><a href="maps-vector.html">Vector Maps</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     <li class="menu-title">Extras</li>/.menu-title -->
<!--                     <li class="menu-item-has-children dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-glass"></i>Pages</a> -->
<!--                         <ul class="sub-menu children dropdown-menu"> -->
<!--                             <li><i class="menu-icon fa fa-sign-in"></i><a href="page-login.html">Login</a></li> -->
<!--                             <li><i class="menu-icon fa fa-sign-in"></i><a href="page-register.html">Register</a></li> -->
<!--                             <li><i class="menu-icon fa fa-paper-plane"></i><a href="pages-forget.html">Forget Pass</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </div>/.navbar-collapse -->
<!--         </nav> -->
<!--     </aside>/#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <header id="header" class="header">
            <div class="top-left">
                <div class="navbar-header">
                    <a class="navbar-brand" href="./"><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo"></a>
                    <a class="navbar-brand hidden" href="./"><img src="${pageContext.request.contextPath}/resources/images/logo2.png" alt="Logo"></a>
                    <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <div class="header-left">
                        <button class="search-trigger"><i class="fa fa-search"></i></button>
                        <div class="form-inline">
                            <form class="search-form">
                                <input class="form-control mr-sm-2" type="text" placeholder="Search ..." aria-label="Search">
                                <button class="search-close" type="submit"><i class="fa fa-close"></i></button>
                            </form>
                        </div>

                        <div class="dropdown for-notification">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="notification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-bell"></i>
                                <span class="count bg-danger">3</span>
                            </button>
                            <div class="dropdown-menu" aria-labelledby="notification">
                                <p class="red">You have 3 Notification</p>
                                <a class="dropdown-item media" href="#">
                                    <i class="fa fa-check"></i>
                                    <p>Server #1 overloaded.</p>
                                </a>
                                <a class="dropdown-item media" href="#">
                                    <i class="fa fa-info"></i>
                                    <p>Server #2 overloaded.</p>
                                </a>
                                <a class="dropdown-item media" href="#">
                                    <i class="fa fa-warning"></i>
                                    <p>Server #3 overloaded.</p>
                                </a>
                            </div>
                        </div>

	<jsp:include page="../inc/left.jsp"></jsp:include>

		<div class=" pr-4 mr-4 mb-1 mt-4 float-right"><small> *접속 IP: ${ip}</small></div> 
        <div class=" breadcrumbs m-0">
            <div class="breadcrumbs-inner rounded-start p-2 " >
                <div class="row m-0 ">
                    <div class="col-sm-4 ">
                        <div class="page-header float-left rounded-start ">
                            <div class="page-title ">
                               <h1 class="m-1"><b>거래처 조회</b></h1>   
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-8 rounded-pill ">
                        <div class="page-header float-right rounded-start ">
                            <div class="page-title ">
                                <ol class="breadcrumb text-right">
                                    <li><a href="#">기본 등록</a></li>
                                    <li><a href="ClientList">거래처 등록</a></li>
                                    <li class="active">거래처 조회</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="content">
            <div class="animated fadeIn">
            	<section id="searchSection" class="m-0 d-flex justify-content-end">
				   <form action=ClientList>
						<!-- 검색 타입 추가 -->
						<select name="searchType" id="searchType" class="rounded-1 btn-sm p-1">
							<option value="business_no" <c:if test="${param.searchType eq 'business_no'}">selected</c:if>>거래처 코드</option>
							<option value="cust_name" <c:if test="${param.searchType eq 'cust_name'}">selected</c:if>>거래처명</option>
							<option value="boss_name" <c:if test="${param.searchType eq 'boss_name'}">selected</c:if>>대표자명</option>
							<option value="addr" <c:if test="${param.searchType eq 'addr'}">selected</c:if>>주소</option>
						</select>			
						<input type="text"  class="col-sm-5 bg-light border border-secondary rounded-1 px-1" name="keyword" id="keyword" value="${param.keyword }"> 
						<input type="submit" value="검색"  class=" mx-1 btn btn-sm btn-dark rounded-1" >
					</form>
				</section>

                                <table id="client_table" class="table ">
                                    <thead>
                                        <tr>
                                            <th>거래처 코드</th>
                                            <th>거래처명</th>
                                            <th>대표자명</th>
                                            <th>전화번호1</th>
                                            <th>전화번호2</th>
                                            <th>주소</th>
                                            <th>비고</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- AJAX를 통해 얻은 JSON 데이터 뿌려짐 -->
                                    </tbody>
                                </table>
						<button type="button" class="mx-1 btn btn-sm btn-dark rounded-1 float-right" onclick="location.href='ClientInsertForm'">거래처 등록</button>
            </div><!-- .animated -->
        </div><!-- .content -->

    <div class="clearfix"></div>

    <!-- footer -->
	   	<br><br><br><br><br><br><br><br><br><br><br><br>
		<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- footer -->

</div><!-- /#right-panel -->
<jsp:include page="../inc/footer.jsp"></jsp:include>
<!-- Right Panel -->

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>


</body>
</html>