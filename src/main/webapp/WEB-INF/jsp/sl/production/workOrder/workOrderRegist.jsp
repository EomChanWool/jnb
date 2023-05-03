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
                        <!-- Nav WorkOrder - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">작업지시 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/workOrder/registWorkOrderOk.do" name="registForm" method="post">
                            		<input type="hidden" name="itemCd">
                            		<input type="hidden" name="woPdtCnt">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>생산계획코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="ppIdx" id="ppIdx" list="ppList" autocomplete="off">
													<datalist id="ppList">
														<c:forEach var="list" items="${prodPlanList}" varStatus="status">
															<option value="${list.ppIdx}">${list.ppName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>작업지시명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="woName" id="woName"></td>
											</tr>
											<tr>
												<th>제품코드</th>
												<td><span class="form-control val-area" id="itemCd"></span></td>
												<th>계획량 (kg)</th>
												<td><span class="form-control val-area" id="woPdtCnt"></span></td>
											</tr>
											<tr>
												<th>작업지시일 <span class="req">*</span></th>
												<td><input type="date" class="form-control" name="woItnDte" id="woItnDte"></td>
												<th>작업완료요구일</th>
												<td><input type="date" class="form-control" name="woCmtDte" id="woCmtDte"></td>
											</tr>
											<tr>
												<th>납품예정일</th>
												<td><input type="date" class="form-control" name="woDlvDte" id="woDlvDte"></td>
												<th>공정</th>
												<td>
													<select class="form-control" name="process">
														<c:forEach var="list" items="${processList}" varStatus="status">
															<option value="${list.prListGroup}">${list.prListGroup}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textarea name="woNote"></textarea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_workOrder()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/workOrder/workOrderList.do'">취소</span>
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
	function fn_regist_workOrder(){
		var num = /^\d+$/;
		if($('#woName').val() == ''){
			alert("작업지시명을 확인 바랍니다.");
			return;
		}
		
		if($('#woItnDte').val() == ''){
			alert("작업지시일을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#workOrderList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#woItnDte').val(new Date().toISOString().slice(0,10));
		
		$('#ppIdx').change(function(){
			prodPlanAjax();
		});
	});
	
	function prodPlanAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/workOrder/workOrderProdPlanInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'ppIdx':$('#ppIdx').val()
			  },
			  success:function(result){
				  console.log(result.pp_info);
				  $('#itemCd').text(result.pp_info.itemCd+" ("+result.pp_info.itemName+")");
				  registForm.itemCd.value = result.pp_info.itemCd;
				  $('#woPdtCnt').text(result.pp_info.ppPrdtCnt);
				  registForm.woPdtCnt.value = result.pp_info.ppPrdtCnt;
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>