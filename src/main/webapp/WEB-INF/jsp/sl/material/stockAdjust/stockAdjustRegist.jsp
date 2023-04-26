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
                    <h1 class="h3 mb-2 text-gray-800">재고 실사 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/material/stockAdjust/registStockAdjustOk.do" name="registForm" method="post">
									<input type="hidden" name="itemName">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>자재코드  <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="itemCd" id="itemCd" list="mtList" autocomplete="off"/>
													<datalist id="mtList">
														<c:forEach var="list" items="${materialList}" varStatus="status">
															<option value="${list.itemCd}">${list.itemName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>단가  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="adPrice" id="adPrice"></td>
											</tr>
											<tr>
												<th>현 재고수량</th>
												<td><span class="form-control val-area" id="adCnt"></span><input type="hidden" name="adCnt"></td>
												<th>실사수량  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="adRealCnt" id="adRealCnt"></td>
											</tr>
											<tr>
												<th>조정수량</th>
												<td><span class="form-control val-area" id="adAdjCnt"></span><input type="hidden" name="adAdjCnt"></td>
												<th>단위</th>
												<td><input type="text" class="form-control" name="adUom" id="adUom" value="kg"></td>
											</tr>
											<tr>
												<th>보관위치</th>
												<td><input type="text" class="form-control" name="adPlace" id="adPlace"></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_stockAdjust()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/material/stockAdjust/stockAdjustList.do'">취소</span>
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
	function fn_regist_stockAdjust(){
		const num = /^\d+$/;
		
		if($('#itemCd').val() == ''){
			alert("물품코드를 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#adPrice').val())){
			alert("단가를 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#adRealCnt').val())){
			alert("실사수량을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	$(function() {
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#stockAdjustList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#itemCd').change(function(){
			itemInfoAjax();
		});
		
		$('#adRealCnt').focusout(function(){
			var adjCnt = $('#adRealCnt').val() - $('#adCnt').text();
			$('#adAdjCnt').text(adjCnt);
			registForm.adAdjCnt.value = adjCnt;
		});
	});
	
	function itemInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/material/stockAdjust/stockAdjustItemCntInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'itemCd':$('#itemCd').val()
			  },
			  success:function(result){
				  console.log(result);
				  $('#adCnt').text(result.item_info[0].itemStock);
				  registForm.adCnt.value = result.item_info[0].itemStock;
				  registForm.adPrice.value = result.item_info[0].itemPerPrice;
				  registForm.itemName.value = result.item_info[0].itemName;
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>