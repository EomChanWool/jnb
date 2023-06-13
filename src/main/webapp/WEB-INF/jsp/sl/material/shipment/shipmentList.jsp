<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
.table th {
	padding-top: 1.3rem;
}

.val-area {
	text-align: left;
}

.modal-content {
	position: relative;
	margin-left: 250px;
	flex-direction: column;
	width: 68%;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
}

.background-wrap {
	background-image:
		url('/resources/conf/images/label.png');
	background-size: 500px 600px;
	width: 500px;
	height: 600px;
	display: block;
	justify-content: center;
	align-items: center;
}

.content {
	display: block;
	flex-direction: column;
	margin-top: 6px;
	padding-top: 6px;

}

.content span {
	color: black;

}



.content span:nth-child(1) {
	font-size: 14px;
	font-weight: bold;
}

.content span:nth-child(2) {

	font-size: 14px;

}


@page {
  size: auto;
  margin: 0;
 }

@media print {
	
	 html,body {
		margin-top : -5%;
		margin-right : 3%;
		display: flex;
		justify-content: center;
		align-items: center;
		height: 100vh;
		-webkit-print-color-adjust:exact;
		-webkit-transform:rotate(45deg); 
	}
}

</style>

<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Side Menu Section -->
			<%@ include file="../../menu/sideMenu.jsp" %>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav shipment - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="btn_bottom_wrap">
						<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">출고 관리</h1>
						<div style="display: inline-block; float: right; margin-top: -5px;">
							<button type="button" class="btn btn-success btn-icon-split"
								style="border: none;" data-toggle="modal"
								data-target="#analysis">
								<span class="text">인쇄양식(직접작성)</span>
							</button>
						</div>
					</div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do" method="post">
									<input type="hidden" name="shIdx">
									<input type="hidden" name="orIdx">
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									
						    		<input type="text" class="form-control bg-light border-0 small" name="searchKeyword"
						    									value="${searchVO.searchKeyword}" placeholder="수주명을 입력해 주세요"
						    									style="background-color:#eaecf4; width: 25%; float: left; margin-right: 5px;">
						    									
   									<input class="btn btn-secondary searchDate" id="searchStDate" name="searchStDate" value="${searchVO.searchStDate}" type="date">
									<span class="dash" style="display: inline-block; float: left; margin: 0.5rem 0.3rem 0 0">~</span>
									<input class="btn btn-secondary searchDate" id="searchEdDate" name="searchEdDate" value="${searchVO.searchEdDate}" type="date">
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_shipment()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_shipment()">
	                                <span class="text">전체목록</span>
	                            </a>
	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_shipment()" style="float: right;">
	                                <span class="text">등록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
											<th>수주명</th>
											<th>출고일</th>
											<th>상태</th>
											<th>수정/삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${shipmentList}" varStatus="status">
	                                   		<tr onclick="fn_detail_shipment('${result.shIdx}')" style="cursor: pointer;">
												<td>${result.orName}</td>
												<td>${result.shDte}</td>
												<td>${result.shState}</td>
	                                            <td class="list_btn" onclick="event.cancelBubble=true" style="padding: 5px 0px; cursor: default;">
	                                            	<a href="#" class="btn btn-warning btn-icon-split" onclick="fn_modify_shipment_go('${result.shIdx}')">
				                                        <span class="text">수정</span>
				                                    </a>
				                                    <a href="#" class="btn btn-danger btn-icon-split" onclick="fn_delete_shipment('${result.shIdx}','${result.orIdx}')">
				                                        <span class="text">삭제</span>
				                                    </a>
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty shipmentList}"><tr><td colspan='4'>결과가 없습니다.</td><del></del></c:if>
                                    </tbody>
                                </table>
                                <div class="btn_page">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_pageview"/>
							    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@ include file="../../menu/footer/footer.jsp" %>
            <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
        
    </div>
    <!-- End of Page Wrapper -->
    <div class="modal fade" id="analysis" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">인쇄영역</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body" id="modalAn">
					<form name="listForm2" class="listForm"
						action="${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do"
						method="post">
						<input type="hidden" name="shIdx" value="${shipmentVO.shIdx}">
						<%-- <table class="table table-bordered">
						<tbody>
							<tr>
								<th>검사번호</th>
								<td><input type="text" class="form-control" name="tiIdx2" id="tiIdx2" value="${analyVO.tiIdx}"></td>								
							</tr>
							<tr>
								<th>로트번호</th>
								<td><input type="text" class="form-control" name="woIdx" id="woIdx" value="${analyVO.woIdx}"></td>
							</tr>
							<tr>
								<th>분석번호</th>
								<td><input type="text" class="form-control" name="azIdx" id="azIdx" value="${analyVO.azIdx}"></td>								
							</tr>
							<tr>
								<th>검사결과</th>
								<td><input type="text" class="form-control" name="tiState" id="tiState" value="${analyVO.tiState}"></td>
							</tr>
							<tr>
								<th>검사자</th>
								<td><input type="text" class="form-control" name="tiAnalyst" id="tiAnalyst" value="${analyVO.tiAnalyst}"></td>
							</tr>
							<tr>
								<th>검사일</th>
								<td><input type="text" class="form-control" name="tiRegDte" id="tiRegDte" value="${analyVO.tiRegDte}"></td>
							</tr>
							
							<img alt="label" src="/resources/conf/images/label.png" style="width: 450px; height: 600px" ;>
						</tbody>
					</table> --%>

						<div class="text-on-img">
							<div class="background-wrap">
								<div class="content">
									<input type="text" class="form-control" style="width:300px; height:25px; margin-left:80px; border: none; background: transparent;"id="itemName">									
									<span id="sp_itemName" style="margin-left:100px; display:none;"></span>
								</div>
								<div class="content" style="margin-top:10px;">
									<input type="text" class="form-control" style="display:inline-block; border: none; background: transparent; width:180px; height:35px; margin-left:90px; font-size:13px; "id="acName">
									<input type="text" class="form-control" style="display:inline-block; border: none; background: transparent; width:110px; height:35px; margin-left:25px; font-size:14px;"id="itemCnt">	 
									<span id="sp_acName" style="margin-left:90px; width:190px; display:none; font-size:14px;"></span>
									<span id="sp_itemCnt" style="margin-left:30px;font-size:20px;display:none;"></span>							
								</div>
								<div class="content" style="margin-top:12px;">
									<input type="text" class="form-control" style="width:300px; height:25px; margin-left:90px; margin-top:-8px; border: none; background: transparent;"id="woIdx2">									
									<span id="sp_woIdx2" style="margin-left:90px;font-size:16px;display:none;"></span> 	
								</div>
							</div>
						</div>

					</form>
				</div>

				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal" id="closeBtn">취소</button>
					<a class="btn btn-primary" href="#" onclick="fn_print()">인쇄</a>
				</div>
			</div>
		</div>
	</div>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <%@ include file="../../menu/logout/logout_alert.jsp" %>
    
    <!-- Bootstrap core JavaScript-->
    <script src="/resources/conf/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/conf/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    <!-- Core plugin JavaScript-->
    <script src="/resources/conf/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>

	<script>
	function fn_pageview(pageNo) {
		listForm.pageIndex.value = pageNo;
	   	listForm.submit();
	}
	
	function fn_search_shipment(){
		listForm.submit();
	}
	
	function fn_searchAll_shipment(){
		listForm.searchKeyword.value = "";
		listForm.searchStDate.value = "";
		listForm.searchEdDate.value = "";
		listForm.pageIndex.value = 1;
		listForm.submit();
	}
	
	function fn_regist_shipment(){
		listForm.action = "${pageContext.request.contextPath}/sl/material/shipment/registShipment.do";
		listForm.submit();
	}
	
	function fn_modify_shipment_go(shIdx){
		
		listForm.shIdx.value = shIdx;
		listForm.action = "${pageContext.request.contextPath}/sl/material/shipment/modifyShipment.do";
		listForm.submit();
	}
	
	function fn_detail_shipment(shIdx){
		listForm.shIdx.value = shIdx;
		listForm.action = "${pageContext.request.contextPath}/sl/material/shipment/detailShipment.do";
		listForm.submit();
	}
	
	function fn_delete_shipment(shIdx, orIdx){
		if(confirm('해당 내역을 삭제하시겠습니까?')) {
			listForm.shIdx.value = shIdx;
			listForm.orIdx.value = orIdx;
			listForm.action = "${pageContext.request.contextPath}/sl/material/shipment/deleteShipment.do";
			listForm.submit();
		}
	}
	
	$(function() {
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#shipmentList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#searchStDate').change(function(){
			listForm.submit();
		});
		$('#searchEdDate').change(function(){
			listForm.submit();
		});
	});
	
	function fn_print() {
		
		window.onbeforeprint = function() {
			$('#sp_itemName').text($('#itemName').val());
			$('#itemName').hide();
			$('#sp_itemName').css("display","inline-block");
			$('#sp_acName').text($('#acName').val());
			$('#acName').hide();
			$('#sp_acName').css("display","inline-block");
			$('#sp_itemCnt').text($('#itemCnt').val());
			$('#itemCnt').hide();
			$('#sp_itemCnt').css("display","inline-block");
			$('#sp_woIdx2').text($('#woIdx2').val());
			$('#woIdx2').hide();
			$('#sp_woIdx2').css("display","inline-block");
			document.body.innerHTML = document.getElementById('modalAn').innerHTML;
			
			
		}
		window.onafterprint = function() {
			//document.body.innerHTML = initBody;
			listForm2.submit();
		}
		window.print();
	}
		
	</script>
</body>

</html>