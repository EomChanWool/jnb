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
                        <!-- Nav orders - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">수주 상세</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <tbody>
										<tr>
											<th>수주명</th>
											<td><span class="form-control val-area">${ordersVO.orName}</span></td>
											<th>수주일</th>
											<td><span class="form-control val-area">${ordersVO.orDte}</span></td>
										</tr>
										<tr>
											<th>견적서번호</th>
											<td><span class="form-control val-area" id="esIdx">${ordersVO.esIdx}</span></td>
											<th>자사 담당자</th>
											<td><span class="form-control val-area">${ordersVO.orManager}</span></td>
										</tr>
										<tr>
											<th>수주금액</th>
											<td><span class="form-control val-area" id="orMoney">${ordersVO.orMoney}</span></td>
											<th>화폐단위</th>
											<td><span class="form-control val-area">${ordersVO.orMnyUnit}</span></td>
										</tr>
										<tr>
											<th>수주낙찰가</th>
											<td><span class="form-control val-area" id="orBidPrice">${ordersVO.orBidPrice}</span></td>
											<th>납기일</th>
											<td><span class="form-control val-area">${ordersVO.orDueDte}</span></td>
										</tr>
										<tr>
											<th>최초 견적일</th>
											<td><span class="form-control val-area" id="esFstQutDte">${ordersVO.esFstQutDte}</span></td>
											<th>최초 견적금액</th>
											<td><span class="form-control val-area" id="esFstQutMoney">${ordersVO.esFstQutMoney}</span></td>
										</tr>
										<tr>
											<th>최종 견적일</th>
											<td><span class="form-control val-area" id="esFnlQutDte">${ordersVO.esFnlQutDte}</span></td>
											<th>최종 견적금액</th>
											<td><span class="form-control val-area" id="esFnlQutMoney">${ordersVO.esFnlQutMoney}</span></td>
										</tr>
										<tr>
										<th>납부상태</th>
										<td><span class="form-control val-area" id="esFnlQutDte">${ordersVO.orPayment}</span></td>
										
										</tr>
										<tr>
											<th>거래처</th>
											<td colspan="3"><span class="form-control val-area">${ordersVO.orDuePlace}</span></td>
										</tr>
										<tr>
											<th>비고</th>
											<td colspan="3"><textarea name="orNote" disabled="disabled">${ordersVO.orNote}</textarea></td>
										</tr>
									</tbody>
                               	</table>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/sales/ordersManage/ordersList.do'">목록</span>
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
		$('#salesMenu').addClass("active");
		$('#sales').addClass("show");
		$('#ordersList').addClass("active");
		
		let orMoney = $('#orMoney').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		let orBidPrice = $('#orBidPrice').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		let esFstQutMoney = $('#esFstQutMoney').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		let esFnlQutMoney = $('#esFnlQutMoney').text().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#orMoney').text(orMoney);
		$('#orBidPrice').text(orBidPrice);
		$('#esFstQutMoney').text(esFstQutMoney);
		$('#esFnlQutMoney').text(esFnlQutMoney);
	});
	</script>
</body>

</html>