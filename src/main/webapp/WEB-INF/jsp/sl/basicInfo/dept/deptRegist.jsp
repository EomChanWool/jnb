<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
	.table th{
		padding-top: 1.3rem;
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
                        <!-- Nav Item - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">부서 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/dept/registdeptOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable"  >
	                                    <tbody>
											<tr>
												<th>사업장코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="cIdx" id="cIdx" list="coList" autocomplete="off"/>
													<dataList id="coList">
														<c:forEach var="list" items="${companyList}" varStatus="state">
															<option value="${list.cIdx}">${list.cName}</option>
														</c:forEach>
													</dataList>
												</td>
												<th>부서명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="dcName" id="dcName"/></td>
											</tr>
											<tr>
												<th>공통코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="ccCode" id="ccCode" list="comnList" autocomplete="off">
													<datalist id="comnList">
														<c:forEach var="list" items="${comnCodeList}" varStatus="status">
															<option value="${list.ccCode}">${list.ccName}</option>
														</c:forEach>
													</datalist>
												</td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_dept()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/dept/deptList.do'">취소</span>
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
		function fn_regist_dept(){
			if($('#cIdx').val() == ''){
				alert("사업장코드를 확인 바랍니다.");
				return;
			}
			
			if($('#dcName').val() == ''){
				alert("부서명을 확인 바랍니다.");
				return;
			}

			if($('#ccCode').val() == ''){
				alert("공통코드를 확인 바랍니다.");
				return;
			}
			
			registForm.submit();
		}
		
		$(function() {
			$('#basicInfoMenu').addClass("active");
			$('#basicInfo').addClass("show");
			$('#deptList').addClass("active");
			
			let msg = '${msg}';
			if(msg) {
				alert(msg);
			}
		});
	</script>
</body>

</html>