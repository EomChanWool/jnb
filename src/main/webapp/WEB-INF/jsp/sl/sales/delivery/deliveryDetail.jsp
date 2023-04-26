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
                    <h1 class="h3 mb-2 text-gray-800">납품 상세</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <tbody>
										<tr>
											<th>수주코드</th>
											<td><span class="form-control val-area" id="orIdx">${deliveryVO.orIdx}</span></td>
											<th>납품명</th>
											<td><input type="text" class="form-control" id="deName" value="${deliveryVO.deName}"></td>
										</tr>
										<tr>
											<th>거래처번호</th>
											<td><span class="form-control val-area" id="acCode"></span></td>
											<th>거래처 연락처</th>
											<td><span class="form-control val-area" id="acTel"></span></td>
										</tr>
										<tr>
											<th>납품요청일</th>
											<td><span class="form-control val-area" id="orDueDte"></span></td>
											<th>납품일</th>
											<td><span class="form-control val-area">${deliveryVO.deDte}</span></td>
										</tr>
										<tr>
											<th>자사 담당자</th>
											<td><input type="text" class="form-control" id="deChkManager" value="${deliveryVO.deChkManager}"></td>
											<th>거래처 담당자</th>
											<td><input type="text" class="form-control" id="deChkAcManager" value="${deliveryVO.deChkAcManager}"></td>
										</tr>
										<tr>
											<th>납품 확인일</th>
											<td><span class="form-control val-area">${deliveryVO.deChkDte}</span></td>
											<th>상태</th>
											<td>
												<span class="form-control val-area">
													<c:if test="${deliveryVO.deState eq '0'}">대기</c:if>
													<c:if test="${deliveryVO.deState eq '1'}">납품확인</c:if>
												</span>
											</td>
										</tr>
										<tr>
											<th>비고</th>
											<td colspan="3"><textArea disabled="disabled">${deliveryVO.deNote}</textArea></td>
										</tr>
									</tbody>
                                </table>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/sales/delivery/deliveryList.do'">목록</span>
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
		deliveryOrdersInfoAjax();
		$('#salesMenu').addClass("active");
		$('#sales').addClass("show");
		$('#deliveryList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#deDte').val(new Date().toISOString().slice(0,10));
	});
	
	function deliveryOrdersInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/sales/delivery/deliveryOrdersInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'orIdx':$('#orIdx').text()
			  },
			  success:function(result){
				  $('#orDueDte').text(result.orders_info.orDueDte);
				  $('#acCode').text(result.orders_info.acCode+" ("+result.orders_info.acName+")");
				  $('#acTel').text(result.orders_info.acTel);
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>