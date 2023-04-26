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
                        <!-- Nav prodPlan - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">생산계획 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/prodPlan/registProdPlanOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>수주번호 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="orIdx" id="orIdx" value="${prdtPlanVO.orIdx}" list="orList" autocomplete="off">
													<datalist id="orList">
														<c:forEach var="list" items="${ordersList}" varStatus="status">
															<option value="${list.orIdx}">${list.orName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>생산계획명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="ppName" id="ppName" value="${prdtPlanVO.ppName}"></td>
											</tr>
											<tr>
												<th>제품코드 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="itemCd" id="itemCd" value="${prdtPlanVO.itemCd}" list="itList" autocomplete="off">
													<datalist id="itList"></datalist>
												</td>
												<th>생산량 (kg)<span class="req">*</span></th>
												<td><input type="text" class="form-control" name="ppPrdtCnt" id="ppPrdtCnt" value="${prdtPlanVO.ppPrdtCnt}"></td>
											</tr>
											<tr>
												<th>납품예정일</th>
												<td><input type="date" class="form-control" name="ppDlvDte" id="ppDlvDte" value="${prdtPlanVO.ppDlvDte}"></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="ppNote">${prdtPlanVO.ppNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_prodPlan()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/prodPlan/prodPlanList.do'">취소</span>
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
	function fn_regist_prodPlan(){
		var num = /^\d+$/;
		if($('#ppName').val() == ''){
			alert("생산계획명을 확인 바랍니다.");
			return;
		}
		
		if($('#orIdx').val() == ''){
			alert("수주번호를 확인 바랍니다.");
			return;
		}
		
		if($('#itemCd').val() == ''){
			alert("품목코드를 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#ppPrdtCnt').val())){
			alert("생산수량을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#prodPlanList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#orIdx').change(function(){
			$('#itList').empty();
			itemListAjax();
		});
		
		$('#itemCd').change(function(){
			itemCntAjax();
		});
	});
	
	function itemListAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/prodPlan/prodPlanItemListAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'orIdx':$('#orIdx').val()
			  },
			  success:function(result){
				  for(var i=0;i<result.item_ajax.length;i++){
					  var option = $('<option value="'+result.item_ajax[i].prodCd+'">'+
							  								result.item_ajax[i].prodName+'</option>');
					  $('#itList').append(option);
				  }
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	function itemCntAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/prodPlan/prodPlanItemCntAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'orIdx':$('#orIdx').val(),
				  'itemCd':$('#itemCd').val()
			  },
			  success:function(result){
				  $('#ppPrdtCnt').val(result.item_cnt.itemCnt);
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>