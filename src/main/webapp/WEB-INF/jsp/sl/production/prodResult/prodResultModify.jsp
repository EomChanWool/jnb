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
                    <h1 class="h3 mb-2 text-gray-800">생산실적 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/prodResult/modifyProdResultOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="prReIdx" value="${prodResultVO.prReIdx}">
                            		<input type="hidden" name="curSeq" value="${prodResultVO.prListSeq}">
                            		<input type="hidden" name="curPrReState" value="${prodResultVO.prReState}">
                            		<input type="hidden" name="curFaultyCnt" value="${prodResultVO.prReFaultyCnt}">
                            		<input type="hidden" name="woIdx" value="${prodResultVO.woIdx}">
                            		<input type="hidden" name="prListNm" value="${prodResultVO.prListNm}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>작업지시번호  <span class="req">*</span></th>
												<td><span class="form-control val-area">${prodResultVO.woIdx}</span></td>
												<th>공정명</th>
												<td><span class="form-control val-area" id="prListNm">${prodResultVO.prListNm}</span></td>
											</tr>
											<tr>
												<th>작업담당자</th>
												<td><input type="text" class="form-control" name="prReManager" id="prReManager" value="${prodResultVO.prReManager}"></td>
												<th>작업상태  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="prReState" id="prReState">
														<option value="">선택</option>
														<option value="0" <c:if test="${prodResultVO.prReState eq '0'}">selected="selected"</c:if>>작업중</option>
														<option value="1" <c:if test="${prodResultVO.prReState eq '1'}">selected="selected"</c:if>>작업종료</option>
													</select>
												</td>
											</tr>
											<tr>
												<th>작업시작일</th>
												<td><input type="datetime-local" class="form-control" name="prReStDte" id="prReStDte" value="${prodResultVO.prReStDte}"></td>
												<th>작업종료일</th>
												<td><input type="datetime-local" class="form-control" name="prReEdDte" id="prReEdDte" value="${prodResultVO.prReEdDte}"></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_prodResult()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/prodResult/prodResultList.do'">취소</span>
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
	function fn_modify_prodResult(){
		if($('#woIdx').val() == ''){
			alert("작업지시번호를 확인 바랍니다.");
			return;
		}
		
		if($('#prReState').val() == ''){
			alert("작업상태를 확인 바랍니다.");
			return;
		}
		console.log($('#prReState').val());
		if($('#prReState').val() == '1' && $('#prReEdTime').val() == ''){
			alert("작업종료일을 확인 바랍니다.");
			return;
		}
		
		modifyForm.submit();
	}
	
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#prodResultList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#woIdx').change(function(){
			prodResultWorkOrderInfoAjax();
		});
		
		$('#prReState').change(function(){
			if($('#prReState').val() == "0"){
				$('#prReEdTime').val("");
			}
		});
	});
	
	function prodResultWorkOrderInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/prodResult/prReWorkOrderInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'woIdx':$('#woIdx').val()
			  },
			  success:function(result){
				  $('#prListNm').text(result.wo_info.prCurNm);
				  modifyForm.prListNm.value = result.wo_info.prCurNm;
				  modifyForm.curSeq.value = result.wo_info.prCurSeq;
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>