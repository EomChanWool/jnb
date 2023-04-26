<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../header.jsp" %>
<style>
	.table th{
		padding-top: 1.3rem;
	}
	
	.val-area{
		text-align: left;
	}
</style>
<body id="page-top">
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
                        <!-- Nav collect - User Information -->
                        <%@ include file="../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">KPI 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/kpi/kpiGoal/modifyKpiOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="exIdx" value="${kpiVO.exIdx}">
									<input type="hidden" name="curYear" value="${kpiVO.exYear}">
									<input type="hidden" name="curMonth" value="${kpiVO.exMonth}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>년도  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="exYear">
														<option value="">선택</option>
														<c:forEach begin="${date.begin}" end="${date.end}" varStatus="status">
											    			<option value="${status.begin+status.count-1}" <c:if test="${status.begin+status.count-1 eq kpiVO.exYear}">selected="selected"</c:if>>${status.begin+status.count-1}</option>
											    		</c:forEach>
													</select>
												</td>
												<th>월  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="exMonth">
														<option value="">선택</option>
														<c:forEach begin="1" end="12" varStatus="status">
											    			<option value="${status.count}" <c:if test="${status.count eq kpiVO.exMonth}">selected="selected"</c:if>>${status.count}</option>
											    		</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<th>목표생산량(kg)  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="exTrgOutput" id="exTrgOutput" value="${kpiVO.exTrgOutput}"></td>
												<th>목표매출액(만원)  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="exTrgSales" id="exTrgSales" value="${kpiVO.exTrgSales}"></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_kpi()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/kpi/kpiGoal/kpiGoalList.do'">취소</span>
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
	function fn_modify_kpi(){
		var num = /^[0-9.]+$/;
		if($('#exYear').val() == ''){
			alert("년도를 확인 바랍니다.");
			return;
		}
		
		if($('#exMonth').val() == ''){
			alert("월을 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#exTrgOutput').val())){
			alert("목표 생산량을 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#exTrgSales').val())){
			alert("목표 매출액을 확인 바랍니다.");
			return;
		}
		
		modifyForm.submit();
	}
	
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#kpiList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
	});
	</script>
</body>

</html>