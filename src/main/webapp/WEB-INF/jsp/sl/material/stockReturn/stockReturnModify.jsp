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
                    <h1 class="h3 mb-2 text-gray-800">재고 실사 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/material/stockReturn/modifyStockReturnOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="reIdx" value="${streVO.reIdx}">
								
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>작업지시서 번호  </th>
												<td><input type="text" class="form-control" name="woIdx" id="woIdx" value="${streVO.woIdx}" readonly></td>
												<th>작업지시명 </th>
												<td><input type="text" class="form-control" name="woName" id="woName" value="${streVO.woName}" readonly></td>
											</tr>
											<tr>
												<th>제품명 </th>
												<td><input type="text" class="form-control" name="itemName" value="${streVO.itemName}" readonly></td>
												<th>등록일 </th>
												<td><input type="text" class="form-control" name="diRegDte" id="diRegDte" value="${streVO.diRegDte}" readonly></td>
											</tr>
											<tr>
												<th>환입량</th>
												<td><input type="text" class="form-control" name="reCnt" value="${streVO.reCnt}" readonly></td>
												<th>작업자</th>
												<td><input type="text" class="form-control" name="reManager" id="reManager" value="${streVO.reManager}"></td>
											</tr>
											
										</tbody>
	                                </table>
	                                
	                                <table class="table table-bordered" id="dataTable2">
	                                	<thead>
	                                		<tr>
                                   		 	 	<th style="padding-top: 1rem; width: 29%;">품목코드</th>
	                                            <th style="padding-top: 1rem; width: 31%;">품명</th>
												<th style="padding-top: 1rem;">소모량</th>
	                                        </tr>
	                                	</thead>
                                   		<tbody>
                                   			<c:forEach var="list" items="${itemList}" varStatus="status">
                                   				<tr>
                                   					<td>
	                                   					<input type="text" class="form-control" name="itemCd${status.count}" id="itemCd${status.count}" value="${list.itemCd}" readonly style="text-align: center;">
	                                   					
	                                   				</td>
	                                   				<td><input type="text" class="form-control" name="itemName${status.count}" id="itemName${status.count}" value="${list.itemName}" readonly style="text-align: center;"></td>
	                                   				<td><input type="text" class="form-control" name="cnt${status.count}" id="cnt${status.count}" value="${list.cnt}" readonly style="text-align: right;"></td>
                                   				</tr>
                                   			</c:forEach>
                                   		</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_stockReturn()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/material/stockReturn/stockReturnList.do'">취소</span>
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
	function fn_modify_stockReturn(){

		
		modifyForm.submit();
	}
	
	$(function() {
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#stockReturnList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
	
	});
	

	</script>
</body>

</html>