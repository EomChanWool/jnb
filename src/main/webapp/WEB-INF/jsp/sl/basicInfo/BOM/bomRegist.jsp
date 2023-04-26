<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
	.table th{
		padding-top: 1.3rem;
	}
	
	.table > td > input{
		text-align: center;
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
                        <!-- Nav bom - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="btn_bottom_wrap">
                   		<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">품목코드 등록</h1>
                   		<div style="display: inline-block; float: right; margin-top: -5px;">
                   			<button type="button" class="btn btn-success btn-icon-split" onclick="addRow()" style="border:none;"><span class="text">추가</span></button>
							<button type="button" class="btn btn-danger btn-icon-split" onclick="delRow()" style="border:none;"><span class="text">삭제</span></button>
                   		</div>
					</div>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/bom/registBomOk.do" name="registForm" method="post">
	                                <table class="table table-bordered">
	                                    <tbody>
											<tr>
												<th>제품코드 <span class="req">*</span></th>
												<td colspan="3">
													<input type="text" class="form-control" name="itemCd" id="itemCd" value="${bomVO.itemCd}" list="pdList" autocomplete="off"/>
													<datalist id="pdList">
														<c:forEach var="list" items="${prodList}" varStatus="status">
															<option value="${list.itemCd}">${list.itemName}</option>
														</c:forEach>
													</datalist>
												</td>
											</tr>
										</tbody>
	                                </table>
	                                <span>* 생산에 들어가는 자재 비율</span>
	                                <table class="table table-bordered" id="dataTable">
	                                	<thead>
	                                		<tr>
                                   		 	 	<th style="padding-top: 1rem; width: 29%;">품목코드</th>
	                                            <th style="padding-top: 1rem; width: 31%;">품명</th>
												<th style="padding-top: 1rem;">소모량(%)</th>
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
									<button type="submit" class="btn_ok" onclick="fn_regist_bom()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/bom/bomList.do'">취소</span>
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
	function fn_regist_bom(){
		if($('#itemCd').val() == ''){
			alert("제품코드를 확인 바랍니다.");
			return;
		}
		
		if($('#itemCd1').val() == ''){
			alert("품목코드를 확인 바랍니다.");
			return;
		}
		if($('#cnt1').val() == ''){
			alert("소모량을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	function addRow(){
		var trCnt = $('#dataTable tr').length;
		if(trCnt <= 15){
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
			
			$('#dataTable > tbody:last').append(innerHtml);
		}else{
			alert("최대 15개까지만 가능합니다.");
			return;
		}
		
		changeChk();
	}
	
	function delRow(){
		var trCnt = $('#dataTable tr').length;
		if(trCnt > 2){
			$('#dataTable > tbody:last > tr:last').remove();
		}else{
			return;
		}
		
		changeChk();
	}
	
	$(function() {
		$('#basicInfoMenu').addClass("active");
		$('#basicInfo').addClass("show");
		$('#bomList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		changeChk();
	});
	
	function changeChk(){
		$('#itemCd1').change(function(){ bomItemInfoAjax($('#itemCd1').val(),1);	});
		$('#itemCd2').change(function(){ bomItemInfoAjax($('#itemCd2').val(),2);	});
		$('#itemCd3').change(function(){ bomItemInfoAjax($('#itemCd3').val(),3);	});
		$('#itemCd4').change(function(){ bomItemInfoAjax($('#itemCd4').val(),4);	});
		$('#itemCd5').change(function(){ bomItemInfoAjax($('#itemCd5').val(),5);	});
		$('#itemCd6').change(function(){ bomItemInfoAjax($('#itemCd6').val(),6);	});
		$('#itemCd7').change(function(){ bomItemInfoAjax($('#itemCd7').val(),7);	});
		$('#itemCd8').change(function(){ bomItemInfoAjax($('#itemCd8').val(),8);	});
		$('#itemCd9').change(function(){ bomItemInfoAjax($('#itemCd9').val(),9);	});
		$('#itemCd10').change(function(){ bomItemInfoAjax($('#itemCd10').val(),10);	});
		$('#itemCd11').change(function(){ bomItemInfoAjax($('#itemCd11').val(),11);	});
		$('#itemCd12').change(function(){ bomItemInfoAjax($('#itemCd12').val(),12);	});
		$('#itemCd13').change(function(){ bomItemInfoAjax($('#itemCd13').val(),13);	});
		$('#itemCd14').change(function(){ bomItemInfoAjax($('#itemCd14').val(),14);	});
		$('#itemCd15').change(function(){ bomItemInfoAjax($('#itemCd15').val(),15);	});
	}
	
	function bomItemInfoAjax(value, index){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/sales/bom/bomItemInfoAjax.do'/>",	  		  			  
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