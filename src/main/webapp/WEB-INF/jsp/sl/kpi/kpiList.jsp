<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../header.jsp" %>

<body id="page-top">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Side Menu Section -->
			<%@ include file="../menu/sideMenu.jsp" %>
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

                        <!-- Nav kpi - User Information -->
                        <%@ include file="../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">KPI관리</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/quality/kpiGoal/kpiGoalList.do" method="post">
									<input type="hidden" name="exIdx">
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									
						    		<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition" id="searchCondition">
						    			<option value="생산량" <c:if test="${searchVO.searchCondition eq '생산량'}">selected="selected"</c:if>>생산량</option>
						    			<option value="매출액" <c:if test="${searchVO.searchCondition eq '매출액'}">selected="selected"</c:if>>매출액</option>
						    		</select>
									<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition2" id="searchCondition2">
										<option value="">선택</option>
							    		<c:forEach begin="${date.begin}" end="${date.end}" varStatus="status">
							    			<option value="${status.begin+status.count}" <c:if test="${searchVO.searchCondition2 eq status.begin+status.count}">selected="selected"</c:if>>${status.begin+status.count}</option>
							    		</c:forEach>
						    		</select>
						    		<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition3" id="searchCondition3">
							    		<option value="">선택</option>
							    		<c:forEach begin="1" end="12" varStatus="status">
							    			<option value="${status.count}" <c:if test="${searchVO.searchCondition3 eq status.count}">selected="selected"</c:if>>${status.count}월</option>
							    		</c:forEach>
						    		</select>
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_kpi()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_kpi()">
	                                <span class="text">전체목록</span>
	                            </a>
	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_kpi()" style="float: right;">
	                                <span class="text">등록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
                                            <th>년도</th>
											<th>월</th>
											<th>목표생산량(kg)</th>
											<th>목표매출액(만원)</th>
											<th>수정/삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${kpiList}" varStatus="status">
	                                   		<tr>
	                                            <td>${result.exYear}년</td>
												<td>${result.exMonth}월</td>
												<td>${result.exTrgOutput}</td>
												<td>${result.exTrgSales}</td>
	                                            <td style="padding: 5px 0px;">
	                                            	<a href="#" class="btn btn-warning btn-icon-split" onclick="fn_modify_kpi_go('${result.exIdx}')">
				                                        <span class="text">수정</span>
				                                    </a>
				                                    <a href="#" class="btn btn-danger btn-icon-split" onclick="fn_delete_kpi('${result.exIdx}')">
				                                        <span class="text">삭제</span>
				                                    </a>
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty kpiList}"><tr><td colspan='5'>결과가 없습니다.</td><del></del></c:if>
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
            <%@ include file="../menu/footer/footer.jsp" %>
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
    <%@ include file="../menu/logout/logout_alert.jsp" %>
    
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
		
		function fn_search_kpi(){
			listForm.submit();
		}
		
		function fn_searchAll_kpi(){
			listForm.searchCondition.value = "";
			listForm.searchCondition2.value = "";
			listForm.searchCondition3.value = "";
			listForm.pageIndex.value = 1;
			listForm.submit();
		}
		
		function fn_regist_kpi(){
			listForm.action = "${pageContext.request.contextPath}/sl/kpi/kpiGoal/registKpi.do";
			listForm.submit();
		}
		
		function fn_modify_kpi_go(exIdx){
			listForm.exIdx.value = exIdx;
			listForm.action = "${pageContext.request.contextPath}/sl/kpi/kpiGoal/modifyKpi.do";
			listForm.submit();
		}
		
		function fn_delete_kpi(exIdx){
			if(confirm('해당 내역을 삭제하시겠습니까?')) {
				listForm.exIdx.value = exIdx;
				listForm.action = "${pageContext.request.contextPath}/sl/kpi/kpiGoal/deleteKpi.do";
				listForm.submit();
			}
		}
		
		$(function() {
			$('#kpiMenu').addClass("active");
			$('#kpi').addClass("show");
			$('#kpiGoalList').addClass("active");
			
			let msg = '${msg}';
			if(msg) {
				alert(msg);
			}
			
			$('#searchCondition').change(function(){
				listForm.submit();
			});
			$('#searchCondition2').change(function(){
				listForm.submit();
			});
			$('#searchCondition3').change(function(){
				listForm.submit();
			});
		});
	</script>
</body>

</html>