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

                        <!-- Nav program - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">프로그램 관리</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/system/program/programList.do" method="post">
									<input type="hidden" name="pIdx">
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
						    		<select  class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition" id="searchCondition">
							    		<option value="" <c:if test="${searchVO.searchCondition eq ''}">selected="selected"</c:if>>선택</option>
							    		<option value="기준정보관리" <c:if test="${searchVO.searchCondition eq '기준정보관리'}">selected="selected"</c:if>>기준정보관리</option>
							    		<option value="영업관리" <c:if test="${searchVO.searchCondition eq '영업관리'}">selected="selected"</c:if>>영업관리</option>
							    		<option value="자재관리" <c:if test="${searchVO.searchCondition eq '자재관리'}">selected="selected"</c:if>>자재관리</option>
							    		<option value="생산관리" <c:if test="${searchVO.searchCondition eq '생산관리'}">selected="selected"</c:if>>생산관리</option>
							    		<option value="품질관리" <c:if test="${searchVO.searchCondition eq '품질관리'}">selected="selected"</c:if>>품질관리</option>
							    		<option value="KPI관리" <c:if test="${searchVO.searchCondition eq 'KPI관리'}">selected="selected"</c:if>>KPI관리</option>
							    		<option value="모니터링" <c:if test="${searchVO.searchCondition eq '모니터링'}">selected="selected"</c:if>>모니터링</option>
							    		<option value="시스템관리" <c:if test="${searchVO.searchCondition eq '시스템관리'}">selected="selected"</c:if>>시스템관리</option>
					    			</select>
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_program()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_program()">
	                                <span class="text">전체목록</span>
	                            </a>
	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_program()" style="float: right;">
	                                <span class="text">등록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
                                            <th>메뉴그룹</th>
											<th>메뉴명</th>
											<th>메뉴URL</th>
											<th>사용여부</th>
											<th>수정/삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${programList}" varStatus="status">
	                                   		<tr>
	                                            <td>${result.pGroup}</td>
												<td>${result.pName}</td>
												<td>${result.pUrl}</td>						
												<td>${result.pUseFlag}</td>
	                                            <td style="padding: 5px 0px;">
	                                            	<a href="#" class="btn btn-warning btn-icon-split" onclick="fn_modify_program_go('${result.pIdx}')">
				                                        <span class="text">수정</span>
				                                    </a>
				                                    <a href="#" class="btn btn-danger btn-icon-split" onclick="fn_delete_program('${result.pIdx}')">
				                                        <span class="text">삭제</span>
				                                    </a>
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty programList}"><tr><td colspan='5'>결과가 없습니다.</td><del></del></c:if>
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
	
	function fn_search_program(){
		listForm.submit();
	}
	
	function fn_searchAll_program(){
		listForm.searchCondition.value = "";
		listForm.pageIndex.value = 1;
		listForm.submit();
	}
	
	function fn_regist_program(){
		listForm.action = "${pageContext.request.contextPath}/sl/system/program/registProgram.do";
		listForm.submit();
	}
	
	function fn_modify_program_go(idx){
		listForm.pIdx.value = idx;
		listForm.action = "${pageContext.request.contextPath}/sl/system/program/modifyProgram.do";
		listForm.submit();
	}
	
	function fn_delete_program(idx){
		if(confirm('해당 내역을 삭제하시겠습니까?')) {
			listForm.pIdx.value = idx;
			listForm.action = "${pageContext.request.contextPath}/sl/system/program/deleteProgram.do";
			listForm.submit();
		}
	}
	
	$(function() {
		$('#systemMenu').addClass("active");
		$('#system').addClass("show");
		$('#programList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#searchCondition').change(function(){
			listForm.submit();
		});
	});
	</script>
</body>

</html>