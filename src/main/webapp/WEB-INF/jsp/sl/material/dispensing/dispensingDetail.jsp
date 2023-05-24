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
                    <h1 class="h3 mb-2 text-gray-800">불출 상세보기</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	
                            		<input type="hidden" name="diIdx" value="${dispensingVO.diIdx}">
                            		
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>작업지시번호  <span class="req">*</span></th>
												<td><span></span>
													<input type="text" class="form-control" name="woIdx" id="woIdx" value="${dispensingVO.woIdx}" list="workOrderList" autocomplete="off" readonly/>
													<%-- <datalist id="workOrderList">
														<c:forEach var="list" items="${workOrderList}" varStatus="status">
															<option value="${list.woIdx}">${list.woName}</option>
														</c:forEach>
													</datalist> --%>
												</td>
												<th>자재코드  <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="itemCd" id="itemCd" value="${dispensingVO.itemCd}" list="materialList" autocomplete="off" readonly/>
													<datalist id="materialList">
														<%-- <c:forEach var="list" items="${materialList}" varStatus="status">
															<option value="${list.itemCd}">${list.itemName}</option>
														</c:forEach> --%>
													</datalist>
												</td>
											</tr>
											<tr>
												<th>투입수량(kg)  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="cnt" id="cnt" value="${dispensingVO.cnt}" readonly/></td>
												<th>투입일  <span class="req">*</span></th>
												<td><input type="datetime-local" class="form-control" name="diRegDte" id="diRegDte" value="${dispensingVO.diRegDte}" readonly></td>
											</tr>
											<tr>
												<th>담당자  <span class="req">*</span></th>
												<td colspan="3"><input type="text" class="form-control" name="diManager" id="diManager" value="${dispensingVO.diManager}" readonly></td>
											</tr>
										</tbody>
	                                </table>
	                                
	                                <table class="table table-bordered" id="dataTable">
	                                	<thead>
	                                		<tr>
                                   		 	 	<th style="padding-top: 1rem; width: 29%;">품목코드</th>
	                                            <th style="padding-top: 1rem; width: 31%;">품명</th>
												<th style="padding-top: 1rem;">소모비율(%)</th>
												<th style="padding-top: 1rem;">실제소모량(kg)</th>
	                                        </tr>
	                                	</thead>
                                   		<tbody>
                                   			<c:forEach var="list" items="${bomMtList}" varStatus="status">
                                   				<tr>
                                   					<td>
	                                   					<input type="text" class="form-control" name="itemCd${status.count}" id="itemCd${status.count}" value="${list.itemCd}" list="mtList" autocomplete="off" disabled style="text-align: center;">
	                                   					<%-- <datalist id="mtList">
	                                   						<c:forEach var="list2" items="${materialList}">
	                                   							<option value="${list2.itemCd}">${list2.itemName}</option>
	                                   						</c:forEach>
	                                   					</datalist> --%>
	                                   				</td>
	                                   				<td><input type="text" class="form-control" name="itemName${status.count}" id="itemName${status.count}" value="${list.itemName}" disabled style="text-align: center;"></td>
	                                   				<td><input type="text" class="form-control" name="cnt${status.count}" id="cnt${status.count}" value="${list.cnt} %" disabled style="text-align: right;"></td>
	                                   				<td><input type="text" class="form-control" name="rcnt" id="rcnt" value="${dispensingVO.cnt*list.cnt/100} kg" disabled style="text-align: right;" ></td>
                                   				</tr>
                                   			</c:forEach>
                                   		</tbody>
	                                </table>
	                                
                                
                                <div class="btn_bottom_wrap">
									
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/material/dispensing/dispensingList.do'">목록가기</span>
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
		
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#dispensingList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
						
	});
	

	
	
	</script>
</body>

</html>