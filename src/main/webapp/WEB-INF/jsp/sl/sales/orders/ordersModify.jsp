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
                        <!-- Nav orders - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">수주 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/sales/ordersManage/modifyOrdersOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="orIdx" id="orIdx" value="${ordersVO.orIdx}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
										<tr>
											<th>수주명  <span class="req">*</span></th>
											<td><input type="text" class="form-control" name="orName" id="orName" value="${ordersVO.orName}"/></td>
											<th>수주일  <span class="req">*</span></th>
											<td><input type="date" class="form-control" name="orDte" id="orDte" value="${ordersVO.orDte}"/></td>
										</tr>
										<tr>
											<th>견적서번호</th>
											<td><input type="text" class="form-control" name="esIdx" id="esIdx" disabled="disabled" value="${ordersVO.esIdx}"></td>
											<th>자사 담당자</th>
											<td><input type="text" class="form-control" name="orManager" id="orManager" value="${ordersVO.orManager}"/></td>
										</tr>
										<tr>
											<th>수주금액 <span class="req">*</span></th>
											<td><input type="text" class="form-control" name="orMoney" id="orMoney" value="${ordersVO.orMoney}"/></td>
											<th>화폐단위</th>
											<td><input type="text" class="form-control" name="orMnyUnit" id="orMnyUnit"  value="${ordersVO.orMnyUnit}"/></td>
										</tr>
										<tr>
											<th>수주낙찰가</th>
											<td><input type="text" class="form-control" name="orBidPrice" id="orBidPrice" value="${ordersVO.orBidPrice}"/></td>
											<th>납기일  <span class="req">*</span></th>
											<td><input type="date" class="form-control" name="orDueDte" id="orDueDte" value="${ordersVO.orDueDte}"/></td>
										</tr>
										<tr>
											<th>최초 견적일</th>
											<td><input type="text" class="form-control" id="esFstQutDte" disabled="disabled"></td>
											<th>최초 견적금액</th>
											<td><input type="text" class="form-control" id="esFstQutMoney" disabled="disabled"></td>
										</tr>
										<tr>
											<th>최종 견적일</th>
											<td><input type="text" class="form-control" id="esFnlQutDte" disabled="disabled"></td>
											<th>최종 견적금액</th>
											<td><input type="text" class="form-control" id="esFnlQutMoney" disabled="disabled"></td>
										</tr>
										<tr>
											<th>상태 <span class="req">*</span></th>
												<td>
													<select name="payment" id="payment" class="form-control">
														<option value="납부" <c:if test="${ordersVO.orPayment eq '납부'}">selected="selected"</c:if>>납부</option>
														<option value="미납" <c:if test="${ordersVO.orPayment eq '미납'}">selected="selected"</c:if>>미납</option>
													</select>
												</td>	
											<th>납품지 <span class="req">*</span></th>
											<td colspan="3"><input type="text" class="form-control" name="orDuePlace" id="orDuePlace" value="${ordersVO.orDuePlace}"/></td>
										</tr>
										<tr>
											<th>비고</th>
											<td colspan="3"><textarea name="orNote">${ordersVO.orNote}</textarea></td>
										</tr>
									</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_orders()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/sales/ordersManage/ordersList.do'">취소</span>
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
	function fn_modify_orders(){
		const num = /^\d+$/;
		if($('#orName').val() == ''){
			alert("수주명을 확인 바랍니다.");
			return;
		}
		
		if($('#orDte').val() == ''){
			alert("수주일을 확인 바랍니다.");
			return;
		}
		
		if($('#esIdx').val() == ''){
			alert("견적서번호를 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#orMoney').val())){
			alert("수주금액을 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#orBidPrice').val())){
			alert("수주낙찰가를 확인 바랍니다.");
			return;
		}
		
		if($('#orDueDte').val() == ''){
			alert("납기일을 확인 바랍니다.");
			return;
		}
		
		if($('#orDuePlace').val() == ''){
			alert("납품지를 확인 바랍니다.");
			return;
		}
		
		modifyForm.submit();
	}
	
	function estimateInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/sales/ordersManage/estimateInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'esIdx':$('#esIdx').val()
			  },
			  success:function(result){
				  $('#esFstQutDte').val(result.estimate_ajax[0].esFstQutDte);
				  $('#esFstQutMoney').val(result.estimate_ajax[0].esFstQutMoney);
				  $('#esFnlQutDte').val(result.estimate_ajax[0].esFnlQutDte);
				  $('#esFnlQutMoney').val(result.estimate_ajax[0].esFnlQutMoney);
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	$(function() {
		$('#salesMenu').addClass("active");
		$('#sales').addClass("show");
		$('#ordersList').addClass("active");
		
		estimateInfoAjax();
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#esIdx').change(function(){
			estimateInfoAjax();
		});
		
	});
	</script>
</body>

</html>