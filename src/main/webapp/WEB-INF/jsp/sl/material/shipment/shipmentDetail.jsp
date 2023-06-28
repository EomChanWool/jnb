<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp"%>
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
	width: 72%;
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
	background-size: 540px 600px;
	width: 540px;
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
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Side Menu Section -->
			<%@ include file="../../menu/sideMenu.jsp"%>
		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>
					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav collect - User Information -->
						<%@ include file="../../menu/logout/nav_user.jsp"%>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div class="btn_bottom_wrap">
						<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">출고 상세</h1>
						<div style="display: inline-block; float: right; margin-top: -5px;">
							<button type="button" class="btn btn-success btn-icon-split"
								style="border: none;" data-toggle="modal"
								data-target="#analysis">
								<span class="text">인쇄양식</span>
							</button>
						</div>
					</div>
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
									<span class="btn_cancel"
										onclick="location.href='${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do'">목록</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<%@ include file="../../menu/footer/footer.jsp"%>
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
					<form name="listForm" class="listForm"
						action="${pageContext.request.contextPath}/sl/material/shipment/detailShipment.do"
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
									<span style="margin-left:100px;font-weight:bold;font-size:20px;">${shipmentVO.itemName}</span> 									
								</div>
								<div class="content">
									<span style="display:inline-block; margin-left:100px; width:190px;font-size:18px; font-weight:bold;">${shipmentVO.acName}</span>
									<span style="display:inline-block; margin-left:50px;font-size:21px;font-weight:bold;">${shipmentVO.woPdtCnt}kg</span> 
																
								</div>
								<div class="content" style="margin-top:10px;">
									<span style="margin-left:100px;font-size:18px;font-weight:bold;">${shipmentVO.woIdx}</span> 									
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@ include file="../../menu/logout/logout_alert.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/conf/vendor/jquery/jquery.min.js"></script>
	<script
		src="/resources/conf/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/conf/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>

	<script>
	function fn_print() {

		window.onbeforeprint = function() {
			document.body.innerHTML = document.getElementById('modalAn').innerHTML;
		}
		window.onafterprint = function() {
			//document.body.innerHTML = initBody;
			listForm.submit();
		}
		window.print();
	}
		$(function() {
			$('#materialMenu').addClass("active");
			$('#material').addClass("show");
			$('#shipmentList').addClass("active");

			let result = $('#money').text().replace(/\B(?=(\d{3})+(?!\d))/g,
					',');
			$('#money').text(result + "원");
		});
	</script>
</body>

</html>