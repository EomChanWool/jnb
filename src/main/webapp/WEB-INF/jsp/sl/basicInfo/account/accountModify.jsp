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
                    <h1 class="h3 mb-2 text-gray-800">거래처 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/account/modifyAccountOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="acCode" value="${accountVO.acCode}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>사업장코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="cIdx" id="cIdx" value="${accountVO.cIdx}" list="coList" autocomplete="off"/>
													<datalist id="coList">
														<option value="">선택</option>
														<c:forEach var="list" items="${companyList}" varStatus="status">
															<option value="${list.cIdx}">${list.cName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>거래처명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="acName" id="acName" value="${accountVO.acName}"/></td>
											</tr>
											<tr>
												<th>공통코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="ccCode" id="ccCode" value="${accountVO.ccCode}" list="comnList" autocomplete="off">
													<datalist id="comnList">
														<c:forEach var="list" items="${comnCodeList}" varStatus="status">
															<option value="${list.ccCode}">${list.ccName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>대표자 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="acCeo" id="acCeo" value="${accountVO.acCeo}"/></td>
											</tr>
											<tr>
												<th>사업자등록번호</th>
												<td><input type="text" class="form-control" name="acBsnsNo" id="acBsnsNo" value="${accountVO.acBsnsNo}"/></td>
												<th>업종</th>
												<td><input type="text" class="form-control" name="acSector" id="acSector" value="${accountVO.acSector}"/></td>
											</tr>
											<tr>
												<th>업태</th>
												<td><input type="text" class="form-control" name="acBusiness" id="acBusiness" value="${accountVO.acBusiness}"/></td>
												<th>구매담당자</th>
												<td><input type="text" class="form-control" name="acPurManager" id="acPurManager" value="${accountVO.acPurManager}"/></td>
											</tr>
											<tr>
												<th>전화번호</th>
												<td><input type="text" class="form-control" name="acTel" id="acTel" value="${accountVO.acTel}"/></td>
												<th>FAX번호</th>
												<td><input type="text" class="form-control" name="acFax" id="acFax" value="${accountVO.acFax}"/></td>
											</tr>
											<tr>
												<th>홈페이지주소</th>
												<td><input type="text" class="form-control" name="acHomepage" id="acHomepage" value="${accountVO.acHomepage}"/></td>
												<th>EMAIL</th>
												<td><input type="text" class="form-control" name="acEmail" id="acEmail" value="${accountVO.acEmail}"/></td>
											</tr>
											<tr>
												<th>결제계좌은행</th>
												<td><input type="text" class="form-control" name="acPayBank" id="acPayBank" value="${accountVO.acPayBank}"/></td>
												<th>결제계좌번호</th>
												<td><input type="text" class="form-control" name="acPayAccount" id="acPayAccount" value="${accountVO.acPayAccount}"/></td>
											</tr>
											<tr>
												<th>예금주</th>
												<td><input type="text" class="form-control" name="acAccHolder" id="acAccHolder" value="${accountVO.acAccHolder}"/></td>
												<th>주소 <span class="req">*</span></th>
												<td colspan="3"><input type="text" class="form-control" name="acAddr1" id="acAddr1" value="${accountVO.acAddr1}"/></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="acNote" id="acNote">${accountVO.acNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_account()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/account/accountList.do'">취소</span>
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
		function fn_modify_account(){
			//연락처
			var localPhone = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
			var cellPhone = /^\d{3}-\d{3,4}-\d{4}$/;
			//사업자 등록번호
			const regitNo = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
			//이메일
			const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			
			if($('#cIdx').val() == ''){
				alert("사업장코드를 확인 바랍니다.");
				return;
			}
			
			if($('#acName').val() == ''){
				alert("거래처명을 확인 바랍니다.");
				return;
			}
			
			if($('#ccCode').val() == ''){
				alert("공통코드를 확인 바랍니다.");
				return;
			}
			
			if($('#acCeo').val() == ''){
				alert("대표자를 확인 바랍니다.");
				return;
			}
			
			if($('#acAddr1').val() == ''){
				alert("주소를 확인 바랍니다.");
				return;
			}
			
			if($('#acTel').val() != '' && !localPhone.test($('#acTel').val())){
				alert("전화번호를 확인 바랍니다.");
				return;
			}
			
			if($('#acFax').val() != '' && !localPhone.test($('#acFax').val())){
				alert("팩스번호를 확인 바랍니다.");
				return;
			}
			
			if($('#acBsnsNo').val() != '' && !regitNo.test($('#acBsnsNo').val())){
				alert("사업자등록 번호를 확인 바랍니다.");
				return;
			}
			
			if($('#acEmail').val() != '' && !email.test($('#acEmail').val())){
				alert("이메일을 확인 바랍니다.");
				return;
			}
			
			modifyForm.submit();
		}
		
		$(function() {
			$('#basicInfoMenu').addClass("active");
			$('#basicInfo').addClass("show");
			$('#accountList').addClass("active");
			
			let msg = '${msg}';
			if(msg) {
				alert(msg);
			}
		});
	</script>
</body>

</html>