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
                        <!-- Nav member - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">사용자 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/member/modifyMemberOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="mIdx" value="${memVO.mIdx}">
                            		<input type="hidden" name="mId" value="${memVO.mId}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>사원명  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="mName" id="mName" value="${memVO.mName}"/></td>
												<th>아이디  <span class="req">*</span></th>
												<td><input type="text" class="form-control" value="${memVO.mId}" disabled="disabled"/></td>
											</tr>
											<tr>
												<th>비밀번호  <span class="req">*</span></th>
												<td><input type="password" class="form-control" name="mPwd" id="mPwd"/></td>
												<th>비밀번호 확인  <span class="req">*</span></th>
												<td><input type="password" class="form-control" name="mPwdChk" id="mPwdChk"/></td>
											</tr>
											<tr>
												<th>연락처</th>
												<td><input type="text" class="form-control" name="mPhone" id="mPhone" value="${memVO.mPhone}"/></td>
												<th>소속</th>
												<td><input type="text" class="form-control" name="mBelong" id="mBelong" value="${memVO.mBelong}"/></td>
											</tr>
											<tr>
												<th>직급</th>
												<td><input type="text" class="form-control" name="mRank" id="mRank" value="${memVO.mRank}"/></td>
												<th>권한레벨</th>
												<td>
													<input type="text" class="form-control" name="saIdx" id="saIdx" value="${memVO.saIdx}" list="auList" autocomplete="off">
													<datalist id="auList">
														<c:forEach var="list" items="${authorityList}" varStatus="status">
															<option value="${list.saIdx}">${list.saName}</option>
														</c:forEach>
													</datalist>
												</td>
											</tr>
											<tr>
												<th>입사일</th>
												<td><input type="date" class="form-control" name="mEmpltDte" id="mEmpltDte" value="${memVO.mEmpltDte}"/></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="mNote">${memVO.mNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_member()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/member/memberList.do'">취소</span>
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
	function fn_modify_member(){
		const tel = /^\d{3}-\d{3,4}-\d{4}$/;
		
		if($('#mName').val() == ""){
			alert("이름을 확인 바랍니다.");
			return;
		}
		
		if($('#mPwd').val() == "" || $('#mPwdChk').val() == ""){
			alert("비밀번호를 입력해주세요.");
			return;
		}
		
		if($('#mPhone').val() != "" && !tel.test($('#mPhone').val())){
			alert("연락처를 확인 바랍니다.(010-xxxx-xxxx)");
			return;
		}
		

		if($('#saIdx').val() == ''){
			alert("권한레벨을 확인 바랍니다.");
			return;
		}
		
		if($('#mPwd').val() != $('#mPwdChk').val()) {
			alert('비밀번호가 일치하지 않습니다.');
			$('#mPwd').val('');
			$('#mPwdChk').val('');
			modifyForm.mPwd.focus();
			return;
		}
		
		modifyForm.submit();
	}
	
	$(function() {
		$('#basicInfoMenu').addClass("active");
		$('#basicInfo').addClass("show");
		$('#memberList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
	});
	</script>
</body>

</html>