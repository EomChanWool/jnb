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
                        <!-- Nav nonOper - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">비가동 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/nonOper/modifyNonOperOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="noIdx" value="${nonOperVO.noIdx}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>사업장 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="cIdx" id="cIdx" value="${nonOperVO.cIdx}" list="cList" autocomplete="off">
													<datalist id="cList">
														<c:forEach var="list" items="${companyList}" varStatus="status">
															<option value="${list.cIdx}">${list.cName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>공장 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="fCode" id="fCode" value="${nonOperVO.fCode}" list="fList" autocomplete="off">
													<datalist id="fList"></datalist>
												</td>
											</tr>
											<tr>
												<th>설비 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="faIdx" id="faIdx" value="${nonOperVO.faIdx}" list="faList" autocomplete="off">
													<datalist id="faList"></datalist>
												</td>
												<th>담당자</th>
												<td><input type="text" class="form-control" name="noManager" id="noManager" value="${nonOperVO.noManager}"></td>
											</tr>
											<tr>
												<th>비가동 시작일 <span class="req">*</span></th>
												<td><input type="datetime-local" class="form-control" name="noStDte" id="noStDte" value="${nonOperVO.noStDte}"></td>
												<th>비가동 종료일 <span class="req">*</span></th>
												<td><input type="datetime-local" class="form-control" name="noEdDte" id="noEdDte" value="${nonOperVO.noEdDte}"></td>
											</tr>
											<tr>
												<th>사유</th>
												<td colspan="3"><input type="text" class="form-control" name="noReason" id="noReason" value="${nonOperVO.noReason}"></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="noNote">${nonOperVO.noNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_nonOper()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/nonOper/nonOperList.do'">취소</span>
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
	function fn_modify_nonOper(){
		var num = /^\d+$/;
		if($('#cIdx').val() == ''){
			alert("사업장을 확인 바랍니다.");
			return;
		}
		
		if($('#fCode').val() == ''){
			alert("공장을 확인 바랍니다.");
			return;
		}
		
		if($('#faIdx').val() == ''){
			alert("설비를 확인 바랍니다.");
			return;
		}
		
		if($('#noStDte').val() == ''){
			alert("비가동 시작일을 확인 바랍니다.");
			return;
		}
		
		if($('#noEdDte').val() == ''){
			alert("비가동 종료일을 확인 바랍니다.");
			return;
		}
		
		modifyForm.submit();
	}
	
	$(function() {
		factoryListAjax();
		facilityListAjax();
		
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#nonOperList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#cIdx').change(function(){
			$('#fList').empty();
			factoryListAjax();
		});
		
		$('#fCode').change(function(){
			$('#faList').empty();
			facilityListAjax();
		});
	});
	
	function factoryListAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/nonOper/nonOperFactoryListAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'cIdx': $('#cIdx').val()
			  },
			  success:function(result){
				  for(var i=0;i<result.fList_ajax.length;i++){
					  var option = $('<option value="'+result.fList_ajax[i].fCode+'">'+result.fList_ajax[i].fName+'</option>');
					  $('#fList').append(option);
				  }
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	function facilityListAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/nonOper/nonOperFacilityListAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'fCode': $('#fCode').val()
			  },
			  success:function(result){
				  for(var i=0;i<result.faList_ajax.length;i++){
					  var option = $('<option value="'+result.faList_ajax[i].faIdx+'">'+result.faList_ajax[i].faName+'</option>');
					  $('#faList').append(option);
				  }
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>