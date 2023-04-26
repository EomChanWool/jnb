<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
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
                        <!-- Nav prodPlan - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">생산계획 상세</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/prodPlan/modifyProdPlanOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="ppIdx" value="${prodPlanVO.ppIdx}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>거래처명</th>
												<td><span class="form-control val-area">${prodPlanVO.acName}</span></td>
												<th>수주번호</th>
												<td><span class="form-control val-area">${prodPlanVO.orIdx} (${prodPlanVO.orName})</span></td>
											</tr>
											<tr>
												<th>생산계획명</th>
												<td><span class="form-control val-area">${prodPlanVO.ppName}</span></td>
												<th>제품코드</th>
												<td><span class="form-control val-area">${prodPlanVO.itemCd} (${prodPlanVO.itemName})</span></td>
											</tr>
											<tr>
												<th>생산량 (kg)</th>
												<td><span class="form-control val-area" id="ppPrdtCnt">${prodPlanVO.ppPrdtCnt}</span></td>
												<th>납품예정일</th>
												<td><span class="form-control val-area">${prodPlanVO.ppDlvDte}</span></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="ppNote" disabled="disabled">${prodPlanVO.ppNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/prodPlan/prodPlanList.do'">목록</span>
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
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#prodPlanList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		let cnt = $('#ppPrdtCnt').text().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#ppPrdtCnt').text(cnt);
	});
	</script>
</body>

</html>