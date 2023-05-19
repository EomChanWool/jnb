<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>

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

                        <!-- Nav stockAdjust - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">환입관리</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/material/stockReturn/stockReturnList.do" method="post">
									<input type="hidden" name="reIdx">
									
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
						    		
						    		<input type="text" class="form-control bg-light border-0 small" name="searchKeyword"
						    									value="${searchVO.searchKeyword}" placeholder="작업지시서명을 입력해 주세요"
						    									style="background-color:#eaecf4; width: 25%; float: left;">
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_stockReturn()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_stockReturn()">
	                                <span class="text">전체목록</span>
	                            </a>
	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_stockReturn()" style="float: right;">
	                                <span class="text">등록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
											<th>작업지시명</th>
											<th>거래처</th>
											<th>제품명</th>
											<th>환입일</th>
											<th>환입량(kg)</th>
											<th>수정/삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${stockReturnList}" varStatus="status">
	                                   		<tr>
												<td>${result.woName}</td>
												<td>${result.acName}</td>
												<td>${result.itemName}</td>
												<td>${result.diRegDte}</td>
												<td>${result.reCnt}kg</td>
	                                            <td style="padding: 5px 0px;">
	                                            	<a href="#" class="btn btn-warning btn-icon-split" onclick="fn_modify_stockReturn_go('${result.reIdx}')">
				                                        <span class="text">수정</span>
				                                    </a>
				                                    <a href="#" class="btn btn-danger btn-icon-split" onclick="fn_delete_stockReturn('${result.reIdx}')">
				                                        <span class="text">삭제</span>
				                                    </a>
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty stockReturnList}"><tr><td colspan='6'>결과가 없습니다.</td><del></del></c:if>
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
	
	function fn_search_stockReturn(){
		listForm.submit();
	}
	
	function fn_searchAll_stockReturn(){
		listForm.searchKeyword.value = "";
		listForm.pageIndex.value = 1;
		listForm.submit();
	}
	
	function fn_regist_stockReturn(){
		listForm.action = "${pageContext.request.contextPath}/sl/material/stockReturn/registStockReturn.do";
		listForm.submit();
	}
	
	function fn_modify_stockReturn_go(reIdx){
		listForm.reIdx.value = reIdx;
		listForm.action = "${pageContext.request.contextPath}/sl/material/stockReturn/modifyStockReturn.do";
		listForm.submit();
	}
	
	function fn_delete_stockReturn(reIdx){
		if(confirm('해당 내역을 삭제하시겠습니까?')) {
			listForm.reIdx.value = reIdx;
		
			listForm.action = "${pageContext.request.contextPath}/sl/material/stockReturn/deleteStockReturn.do";
			listForm.submit();
		}
	}
	
	$(function() {
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#stockReturnList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
	});
	</script>
</body>

</html>