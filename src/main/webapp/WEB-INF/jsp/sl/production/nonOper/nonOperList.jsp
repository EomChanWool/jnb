<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>

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
                    <h1 class="h3 mb-2 text-gray-800">비가동관리</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/production/nonOper/nonOperList.do" method="post">
									<input type="hidden" name="noIdx">
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									
									<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition" id="searchCondition">
										<option value="">사업장 선택</option>
						    			<c:forEach var="list" items="${companyList}" varStatus="status">
						    				<option value="${list.cIdx}" <c:if test="${searchVO.searchCondition eq list.cIdx}">selected="selected"</c:if>>${list.cName}</option>
						    			</c:forEach>
						    		</select>
						    		<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition2" id="searchCondition2">
						    			<option value="">공장 선택</option>
						    		</select>
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_nonOper()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_nonOper()">
	                                <span class="text">전체목록</span>
	                            </a>
	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_nonOper()" style="float: right;">
	                                <span class="text">등록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
                                            <th>사업장명</th>
											<th>공장</th>
											<th>설비명</th>
											<th>비가동시간</th>
											<th>수정/삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${nonOperList}" varStatus="status">
	                                   		<tr>
	                                   			<td>${result.cName}</td>
	                                   			<td>${result.fName}</td>
	                                   			<td>${result.faName}</td>
	                                   			<td>${result.noStDte} ~ ${result.noEdDte}</td>
	                                            <td class="list_btn" onclick="event.cancelBubble=true" style="padding: 5px 0px; cursor: default;">
	                                            	<a href="#" class="btn btn-warning btn-icon-split" onclick="fn_modify_nonOper_go('${result.noIdx}')">
				                                        <span class="text">수정</span>
				                                    </a>
				                                    <a href="#" class="btn btn-danger btn-icon-split" onclick="fn_delete_nonOper('${result.noIdx}')">
				                                        <span class="text">삭제</span>
				                                    </a>
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty nonOperList}"><tr><td colspan='5'>결과가 없습니다.</td><del></del></c:if>
                                    </tbody>
                                </table>
                                <div class="btn_page">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_pageview"/>
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
	function fn_pageview(pageNo) {
		listForm.pageIndex.value = pageNo;
	   	listForm.submit();
	}
	
	function fn_search_nonOper(){
		listForm.submit();
	}
	
	function fn_searchAll_nonOper(){
		listForm.searchCondition.value = "";
		listForm.searchCondition2.value = "";
		listForm.pageIndex.value = 1;
		listForm.submit();
	}
	
	function fn_regist_nonOper(){
		listForm.action = "${pageContext.request.contextPath}/sl/production/nonOper/registNonOper.do";
		listForm.submit();
	}
	
	function fn_modify_nonOper_go(noIdx){
		listForm.noIdx.value = noIdx;
		listForm.action = "${pageContext.request.contextPath}/sl/production/nonOper/modifyNonOper.do";
		listForm.submit();
	}
	
	function fn_delete_nonOper(noIdx){
		if(confirm('해당 내역을 삭제하시겠습니까?')) {
			listForm.noIdx.value = noIdx;
			listForm.action = "${pageContext.request.contextPath}/sl/production/nonOper/deleteNonOper.do";
			listForm.submit();
		}
	}
	
	$(function() {
		factoryListAjax();
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#nonOperList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#searchCondition').change(function(){
			$('#searchCondition2').empty();
			var option = $('<option value="">공장 선택</option>');
			$('#searchCondition2').append(option);
			factoryListAjax();
			listForm.submit();
		});
		
		$('#searchCondition2').change(function(){
			listForm.pageIndex.value = 1;
			listForm.submit();
		});
		
		$('#searchCondition2').val('${searchVO.searchCondition2}').prop('selected',true);
	});
	
	function factoryListAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/nonOper/nonOperFactoryListAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'cIdx': $('#searchCondition').val()
			  },
			  async: false,
			  success:function(result){
				  console.log('${searchVO.searchCondition2}');

				  for(var i=0;i<result.fList_ajax.length;i++){
					  var option = $('<option value="'+result.fList_ajax[i].fCode+'" <c:if test="${searchVO.searchCondition2 eq '+ result.fList_ajax[i].fCode+'}">selected="selected"</c:if>>'+
							  				result.fList_ajax[i].fName+'</option>');
					  $('#searchCondition2').append(option);
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