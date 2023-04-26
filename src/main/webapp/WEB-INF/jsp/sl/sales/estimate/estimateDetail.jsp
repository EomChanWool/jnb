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
                        <!-- Nav estimate - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">견적서 상세</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable">
                                    <tbody>
									<tr>
										<th>견적서 제목  <span class="req">*</span></th>
										<td><span class="form-control val-area">${estimateVO.esName}</span></td>
										<th>자사 담당자  <span class="req">*</span></th>
										<td><input type="text" class="form-control" name="esComManager" value="${estimateVO.esComManager}"/></td>
									</tr>
									<tr>
										<th>사업장  <span class="req">*</span></th>
										<td><span class="form-control val-area" id="cIdx">${estimateVO.cIdx}</span></td>
										<th>거래처  <span class="req">*</span></th>
										<td><span class="form-control val-area" id="aIdx">${estimateVO.aIdx}</span></td>
									</tr>
									<tr>
										<th>대표자</th>
										<td><input type="text" class="form-control" id="cOwner" disabled="disabled"></td>
										<th>사업자 등록번호</th>
										<td><input type="text" class="form-control" id="cBsnsNo" disabled="disabled"></td>
									</tr>
									<tr>
										<th>주소</th>
										<td><input type="text" class="form-control" id="cAddr" disabled="disabled"></td>
										<th>이메일</th>
										<td><input type="text" class="form-control" id="cEmail" disabled="disabled"></td>
									</tr>
									<tr>
										<th>전화</th>
										<td><input type="text" class="form-control" id="cTel" disabled="disabled"></td>
										<th>팩스</th>
										<td><input type="text" class="form-control" id="cFax" disabled="disabled"></td>
									</tr>
									<tr>
										<th>비고</th>
										<td colspan="3"><textArea disabled="disabled">${estimateVO.esNote}</textArea></td>
									</tr>
								</tbody>
                                </table>
                                <table class="table table-bordered" id="dataTable">
                                	<thead>
										<tr>
											<th colspan="3"><span onclick="prodBtn()" id="prodBtn" style="cursor: pointer; width:100%; height:30px; margin-top: 10px;">펼치기</span></th>
										</tr>
									</thead>
									<tbody class="prodList" style="display: none;">
										<tr>
											<th style="text-align: center;">제품코드</th>
											<th style="text-align: center;">수량(kg)</th>
											<th style="text-align: center;">단가(원)</th>
										</tr>
										<c:forEach var="list" items="${prList}" varStatus="status">
											<c:if test='${list.prod != "null"}'>
												<tr>
													<td><input type="text" class="form-control prod" id="prod${status.count}" <c:if test='${list.prod != "null"}'>value="${list.prod}"</c:if> style="text-align: center;"></td>
													<td><input type="text" class="form-control cnt" id="cnt${status.count}" <c:if test='${list.cnt != "null"}'>value="${list.cnt}"</c:if> style="text-align: center;"></td>
													<td><input type="text" class="form-control perPrice" id="perPrice${status.count}" <c:if test='${list.perPrice != "null"}'>value="${list.perPrice}"</c:if> style="text-align: right;"></td>
												</tr>
											</c:if>
										</c:forEach>
										<tr>
											<th colspan="2">합계(원)</th>
											<td style="text-align: right; padding-top: 1.1rem;">
												<span id="total"></span>
												<input type="hidden" name="totalPrice" id="totalPrice">
											</td>
										</tr>
									</tbody>
                                </table>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/sales/estimate/estimateList.do'">목록</span>
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
	function prodBtn(){
		if($('#prodBtn').text() == '펼치기'){
			$('#prodBtn').text("접기");
			$('.prodList').show();
		}else{
			$('#prodBtn').text("펼치기");
			$('.prodList').hide();
		}
	}
	
	$(function() {
		companyInfoAjax();
		calcTotalPrice();
		
		$('#salesMenu').addClass("active");
		$('#sales').addClass("show");
		$('#estimateList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		var len = document.getElementsByClassName('cnt');
		for(var i=0;i<len.length;i++){
			var cntId = '#cnt'+(i+1);
			var perPriceId = '#perPrice'+(i+1);
			let cnt = $(cntId).val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			let perPrice = $(perPriceId).val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			$(cntId).val(cnt);
			$(perPriceId).val(perPrice);
		}
	});
	
	function calcTotalPrice(){
		var perPrice = document.getElementsByClassName('perPrice');
		var cnt = document.getElementsByClassName('cnt');
		var total = 0;
		for(var i=0;i<perPrice.length;i++){
			if(perPrice[i].value != ''){
				total += (perPrice[i].value*cnt[i].value);
			}
		}

		$('#totalPrice').val(total);
		let result = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#total').text(result);
	}
	
	function companyInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/sales/estimate/companyInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'cIdx':$('#cIdx').text()
			  },
			  success:function(result){
				  $('#cOwner').val(result.com_info[0].cOwner);
				  $('#cAddr').val(result.com_info[0].cAddr);
				  $('#cBsnsNo').val(result.com_info[0].cBsnsNo);
				  $('#cEmail').val(result.com_info[0].cEmail);
				  $('#cTel').val(result.com_info[0].cTel);
				  $('#cFax').val(result.com_info[0].cFax);
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>