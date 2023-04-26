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
                        <!-- Nav collect - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">출고 상세</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <tbody>
										<tr>
											<th>거래처</th>
											<td><span class="form-control val-area">${shipmentVO.acName}</span></td>
											<th>거래처주소</th>
											<td><span class="form-control val-area">${shipmentVO.acAddr1}</span></td>
										</tr>
										<tr>
											<th>거래처 연락처</th>
											<td><span class="form-control val-area">${shipmentVO.acTel}</span></td>
											<th>납품지</th>
											<td><span class="form-control val-area">${shipmentVO.shPlace}</span></td>
										</tr>
										<tr>
											<th>수주명</th>
											<td><span class="form-control val-area">${shipmentVO.orName}</span></td>
											<th>수주금액</th>
											<td><span class="form-control val-area" id="money">${shipmentVO.orMoney}</span></td>
										</tr>
										<tr>
											<th>납기일</th>
											<td><span class="form-control val-area">${shipmentVO.orDueDte}</span></td>
											<th>출고일</th>
											<td><span class="form-control val-area">${shipmentVO.shDte}</span></td>
										</tr>
										<tr>
											<th>출고 담당자</th>
											<td><span class="form-control val-area">${shipmentVO.shManager}</span></td>
											<th>상태</th>
											<td><span class="form-control val-area">${shipmentVO.shState}</span></td>
										</tr>
									</tbody>
                                </table>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do'">목록</span>
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
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#shipmentList').addClass("active");
		
		let result = $('#money').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#money').text(result + "원");
	});
	</script>
</body>

</html>