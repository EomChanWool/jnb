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
                        <!-- Nav document - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">유무검사관리 상세보기</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <tbody>
										<tr>
											<th>부적합명 <span class="req">*</span></th>
											<td><span class="form-control val-area">${checkVO.inName}</span></td>
											<th>불량유형 <span class="req">*</span></th>
											<td><span class="form-control val-area">${checkVO.chState}</span></td>
										</tr>
										<tr>
											<th>제품명 <span class="req">*</span></th>
											<td><span class="form-control val-area">${checkVO.itemName}</span></td>
											<th>검사명 <span class="req">*</span></th>
											<td><span class="form-control val-area">${checkVO.tiName}</span></td>
										</tr>
										<tr>
											<th>총량 (kg)<span class="req">*</span></th>
											<td><span class="form-control val-area" id="woPdtCnt">${checkVO.woPdtCnt}kg</span></td>
											<th>재사용여부 <span class="req">*</span></th>
											<td><span class="form-control val-area">${checkVO.chRecycle}</span></td>
										</tr>
										<tr>
										<th>작성일 <span class="req">*</span></th>
											<td><span class="form-control val-area">${checkVO.chRegDte}</span></td>
										
										</tr>
										<tr>
											<th>변경이유</th>
											<td colspan="3"><textArea name="doNote" id="doNote" disabled="disabled">${checkVO.chReason}</textArea></td>
										</tr>
										
									</tbody>
                                </table>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/quality/checkProd/checkProdList.do'">목록</span>
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
		$('#qualityMenu').addClass("active");
		$('#quality').addClass("show");
		$('#checkProdList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		let cnt = $('#woPdtCnt').text().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#woPdtCnt').text(cnt);
	});
	</script>
</body>

</html>