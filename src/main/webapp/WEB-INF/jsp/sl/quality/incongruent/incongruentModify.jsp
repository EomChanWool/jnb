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
                    <h1 class="h3 mb-2 text-gray-800">부적합관리 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/quality/incongruent/modifyIncongruentOk.do" name="modifyForm" method="post" encType="multipart/form-data">
                            		<input type="hidden" name="inIdx" value="${incoVO.inIdx}">
                            		<%-- <input type="hidden" name="doFilNm" value="${documentVO.doFilNm}"> --%>
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>부적합명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="inName" id="inName" value="${incoVO.inName}"></td>
												<th>검사번호 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="tiIdx" id="tiIdx" value="${incoVO.tiIdx}" readonly></td>
											</tr>
											<tr>
												<th>검사명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="tiName" id="tiName" value="${incoVO.tiName}" readonly></td>
												<th>분석번호 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="azIdx" id="azIdx" value="${incoVO.azIdx}" readonly></td>
											</tr>
											<tr>
											<th>불량항목 번호 <span class="req">*</span></th>
												<td>
												<input type="text" class="form-control" name="biIdx" id="biIdx" list="biList" value="${incoVO.biIdx}" autocomplete="off">
													<datalist id="biList">
														<c:forEach var="list" items="${biList}" varStatus="status">
															<option value="${list.biIdx}">${list.biName}</option>
														</c:forEach>
													</datalist>
												</td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="inNote" id="inNote">${incoVO.inNote}</textArea></td>
											</tr>
											
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_document()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/quality/incongruent/incongruentList.do'">취소</span>
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
	function fn_modify_document(){
		if($('#inName').val() == ''){
			alert("부적합명을 확인 바랍니다.");
			return;
		}
		
		if($('#biIdx').val() == ''){
			alert("불량 항목 번호를 확인 바랍니다.");
			return;
		}
		
		
		
		modifyForm.submit();
	}
	
	$(function() {
		$('#qualityMenu').addClass("active");
		$('#quality').addClass("show");
		$('#incongruentList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		
	});
	</script>
</body>

</html>