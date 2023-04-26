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
                    <h1 class="h3 mb-2 text-gray-800">출고 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/material/shipment/registShipmentOk.do" name="registForm" method="post">
                            		<input type="hidden" name="acCode">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>수주코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="orIdx" id="orIdx" list="orList" autocomplete="off">
													<datalist id="orList">
														<c:forEach var="list" items="${orderList}" varStatus="status">
															<option value="${list.orIdx}">${list.orName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>담당자 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="shManager" id="shManager"></td>
											</tr>
											<tr>
												<th>거래처번호 <span class="req">*</span></th>
												<td><span class="form-control val-area" id="acCode"></span></td>
												<th>납품지 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="shPlace" id="shPlace"></td>
											</tr>
											<tr>
												<th>납품요청일</th>
												<td><span class="form-control val-area" id="orDueDte"></span></td>
												<th>출고일 <span class="req">*</span></th>
												<td><input type="date" class="form-control" name="shDte" id="shDte"></td>
											</tr>
											<tr>
												<th>상태 <span class="req">*</span></th>
												<td>
													<select class="form-control" name="shState" id="shState">
														<option value="">선택</option>
														<option value="확인전">확인전</option>
														<option value="확인">확인</option>
													</select>
												</td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_shipment()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do'">취소</span>
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
	function fn_regist_shipment(){
		if($('#orIdx').val() == ''){
			alert("수주번호를 확인 바랍니다.");
			return;
		}
		
		if($('#shPlace').val() == ''){
			alert("납품지를 확인 바랍니다.");
			return;
		}
		
		if($('#shManager').val() == ''){
			alert("작업 담당자를 확인 바랍니다.");
			return;
		}
		
		if($('#shState').val() == ''){
			alert("상태를 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	$(function() {
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#shipmentList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#shDte').val(new Date().toISOString().slice(0,10));
		
		$('#orIdx').change(function(){
			shipmentOrdersInfoAjax();
		});
	});
	
	function shipmentOrdersInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/material/shipment/shipmentOrdersInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'orIdx':$('#orIdx').val()
			  },
			  success:function(result){
				  $('#orDueDte').text(result.orders_info.orDueDte);
				  $('#shPlace').val(result.orders_info.orDuePlace);
				  $('#acCode').text(result.orders_info.acCode+" ("+result.orders_info.acName+")")
				  registForm.acCode.value = result.orders_info.acCode;
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>