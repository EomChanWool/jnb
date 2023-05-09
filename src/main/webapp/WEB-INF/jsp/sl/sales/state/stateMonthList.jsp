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

                        <!-- Nav delivery - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">현황관리 - 수주 월 집계</h1>
                    <a href="#" class="btn btn-info btn-icon-split" onclick="fn_go_state()" style="margin-left: 0.3rem;">
	                                <span class="text">수주현황</span>
	                            </a>
	                <a href="#" class="btn btn-info btn-icon-split" onclick="fn_go_noPayList()" style="margin-left: 0.3rem;">
	                                <span class="text">수주미납현황</span>
	                            </a>
	                <a href="#" class="btn btn-info btn-icon-split" onclick="fn_go_stateMonthList()" style="margin-left: 0.3rem;">
	                                <span class="text">수주 월 집계</span>
	                            </a>
	                 <a href="#" class="btn btn-info btn-icon-split" onclick="fn_go_deliveryStateList()" style="margin-left: 0.3rem;">
	                                <span class="text">납품현황</span>
	                            </a>                                   

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/sales/state/stateMonthList.do" method="post">
									
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									
									<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition2" id="searchCondition2">
						    			
						    			<c:forEach var="list" items="${orYearList}" varStatus="status">
						    				<option value="${list.orYear}" <c:if test="${searchVO.searchCondition2 eq list.orYear or status.count eq 1}">selected="selected"</c:if>>${list.orYear}년도</option>
						    			</c:forEach>
						    		</select>
						    								
   									
						    	</form>
						    	
	                           
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
                                        	<th>월 </th>
											<th>수주 건수</th>
											<th>수주금액 월 총합</th>
											
											
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${stateMonthList}" varStatus="status">
	                                   		<tr>
	                                   			<td>${result.month}월</td>
												<td>${result.orCount}건</td>
												<td>${result.totalMoney}원</td>
												
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty stateMonthList}"><tr><td colspan='4'>결과가 없습니다.</td><del></del></c:if>
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
	
	function fn_go_state(){
		listForm.action = "${pageContext.request.contextPath}/sl/sales/state/stateList.do";
		listForm.submit();
	}
	
	function fn_go_noPayList(){
		listForm.action = "${pageContext.request.contextPath}/sl/sales/state/noPayList.do";
		listForm.submit();
	}
	
	function fn_go_stateMonthList(){
		listForm.action = "${pageContext.request.contextPath}/sl/sales/state/stateMonthList.do";
		listForm.submit();
	}	
	
	function fn_go_deliveryStateList(){
		listForm.action = "${pageContext.request.contextPath}/sl/sales/state/deliveryStateList.do";
		listForm.submit();
	}	
	
	
	
	function fn_search_noPay(){
		listForm.submit();
	}
	
	function fn_searchAll_noPay(){
		listForm.searchKeyword.value = "";
		listForm.searchStDate.value = "";
		listForm.searchEdDate.value = "";
		listForm.pageIndex.value = 1;
		listForm.submit();
	}
	

	

	
	$(function() {
		$('#salesMenu').addClass("active");
		$('#sales').addClass("show");
		$('#stateList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		 $('#searchCondition2').change(function(){
			listForm.submit();
		});
		
	});
	</script>
</body>

</html>