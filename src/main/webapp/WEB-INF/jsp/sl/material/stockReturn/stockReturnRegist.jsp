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
                    <div class="btn_bottom_wrap">
                   		<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">환입 등록</h1>
                   		<div style="display: inline-block; float: right; margin-top: -5px;">
                   			<button type="button" class="btn btn-success btn-icon-split" onclick="addRow()" style="border:none;"><span class="text">추가</span></button>
							<button type="button" class="btn btn-danger btn-icon-split" onclick="delRow()" style="border:none;"><span class="text">삭제</span></button>
                   		</div>
					</div>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/material/stockReturn/registStockReturnOk.do" name="registForm" method="post">
									<input type="hidden" name="woGroup">
									<input type="hidden" name="chIdx">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>작업지시서  <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="woIdx" id="woIdx" list="woList" autocomplete="off"/>
													<datalist id="woList">
														<c:forEach var="list" items="${woList}" varStatus="status">
															<option value="${list.woIdx}">${list.woName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>거래처  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="acName" id="acName"></td>
											</tr>
											<tr>
												<th>제품명</th>
												<td><span class="form-control val-area" id="itemName"></span></td>
												<th>환입량(kg)  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="woPdtCnt" id="woPdtCnt"></td>
											</tr>
											<tr>
											<th>작업자  </th>
												<td><input type="text" class="form-control" name="reManager" id="reManager"></td>
											</tr>
											
										</tbody>
	                                </table>
	                                
	                                 <span>* 추가 원자재 투입량</span>
	                                <table class="table table-bordered" id="dataTable2">
	                                	<thead>
	                                		<tr>
                                   		 	 	<th style="padding-top: 1rem; width: 29%;">품목코드</th>
	                                            <th style="padding-top: 1rem; width: 31%;">품명</th>
												<th style="padding-top: 1rem;">소모량(kg)</th>
	                                        </tr>
	                                	</thead>
                                   		<tbody>
                                   			<tr>
                                   				<td>
                                   					<input type="text" class="form-control" name="itemCd1" id="itemCd1" list="mtList" autocomplete="off" style="text-align: center;">
                                   					<datalist id="mtList">
                                   						<c:forEach var="list" items="${materialList}">
                                   							<option value="${list.itemCd}">${list.itemName}</option>
                                   						</c:forEach>
                                   					</datalist>
                                   				</td>
                                   				<td><input type="text" class="form-control" name="itemName1" id="itemName1" style="text-align: center;"></td>
                                   				<td><input type="text" class="form-control" name="cnt1" id="cnt1" style="text-align: right;"></td>
                                   			</tr>
                                   		</tbody>
	                                </table>
	                                
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_stockAdjust()" style="border:none;">확인</button>
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
	function fn_regist_stockAdjust(){
		
		
		if($('#woidx').val() == ''){
			alert("작업지시서를 확인 바랍니다.");
			return;
		}
		
		
		
		registForm.submit();
	}
	
	$(function() {
		$('#materialMenu').addClass("active");
		$('#material').addClass("show");
		$('#stockReturnList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#woIdx').change(function(){
			itemInfoAjax();
		});
		
		changeChk();
	});
	
	function addRow(){
		var trCnt = $('#dataTable2 tr').length;
		if(trCnt <= 10){
			var innerHtml = "";
			innerHtml += '<tr>';
			innerHtml += '	<td>';
			innerHtml += '		<input type="text" class="form-control" name="itemCd'+(trCnt)+'" id="itemCd'+(trCnt)+'" list="mtList" autocomplete="off" style="text-align: center;">';
			innerHtml += '		<datalist id="mtList">';
			innerHtml += '			<c:forEach var="list" items="${materialList}">';
			innerHtml += '				<option value="${list.itemCd}">${list.itemName}</option>';
			innerHtml += '			</c:forEach>';
			innerHtml += '		</datalist>';
			innerHtml += '	</td>';
			innerHtml += '	<td><input type="text" class="form-control" name="itemName'+(trCnt)+'" id="itemName'+(trCnt)+'" style="text-align: center;"></td>';
			innerHtml += '	<td><input type="text" class="form-control" name="cnt'+(trCnt)+'" id="cnt'+(trCnt)+'" style="text-align: right;"></td>';
			innerHtml += '</tr>';
			
			$('#dataTable2 > tbody:last').append(innerHtml);
		}else{
			alert("최대 10개까지만 가능합니다.");
			return;
		}
		
		changeChk();
	}
	
	function delRow(){
		var trCnt = $('#dataTable2 tr').length;
		if(trCnt > 2){
			$('#dataTable2 > tbody:last > tr:last').remove();
		}else{
			return;
		}
		
		changeChk();
	}
	
	function changeChk() {
		  for (let i = 1; i <= 10; i++) {
		    $('#itemCd' + i).change(function() {
		      bomItemInfoAjax($('#itemCd' + i).val(), i);
		    });
		  }
		}
	
	function itemInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/material/stockReturn/stockReturnInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'woIdx':$('#woIdx').val()
			  },
			  success:function(result){
				  console.log(result);
				  registForm.acName.value = result.woInfo.acName;
				 // registForm.itemName.value = result.woInfo.itemName;
				 // $('#acName').text(result.woInfo.acName);
				  $('#itemName').text(result.woInfo.itemName);
				 // $('#woPdtCnt').text(result.woInfo.woPdtCnt);
				  registForm.woPdtCnt.value = result.woInfo.woPdtCnt;
				  registForm.woGroup.value = result.woInfo.woGroup;
				  registForm.chIdx.value= result.woInfo.chIdx;
				  
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	
	function bomItemInfoAjax(value, index){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/material/stockReturn/bomItemInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'itemCd':value
			  },
			  success:function(result){
				  var str = '#itemName'+index;
				  $(str).val(result.item_info[0].itemName);
				  
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>