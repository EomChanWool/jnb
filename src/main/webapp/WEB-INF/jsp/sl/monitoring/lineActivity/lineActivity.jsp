<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp"%>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Side Menu Section -->
			<%@ include file="../../menu/sideMenu.jsp"%>
		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav stockAdjust - User Information -->
						<%@ include file="../../menu/logout/nav_user.jsp"%>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">라인가동현황</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<div class="search">
<%-- 								<form name="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/monitoring/lineActivity/lineActivity.do" method="post"> --%>
<!-- 									<input type="hidden" name="pdIdx"> <input type="hidden" -->
<!-- 										name="pageIndex" -->
<%-- 										value="<c:out value='${searchVO.pageIndex}'/>" /> --%>


<!-- 								</form> -->
								
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/monitoring/lineActivity/lineActivity.do" method="post">
									<input type="hidden" name="stIdx">
									<input type="hidden" name="itemCd">
									<input type="hidden" name="stCnt">
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
						    		<input type="text" class="form-control bg-light border-0 small" name="searchKeyword"
						    									value="${searchVO.searchKeyword}" placeholder="탱크명을 입력해 주세요"
						    									style="background-color:#eaecf4; width: 25%; float: left; margin: 0 5px 0 0">
					    			
					    			<input class="btn btn-secondary searchDate" id="searchStDate" name="searchStDate" value="${searchVO.searchStDate}" type="date">
									<span class="dash" style="display: inline-block; float: left; margin: 0.5rem 0.3rem 0 0">~</span>
									<input class="btn btn-secondary searchDate" id="searchEdDate" name="searchEdDate" value="${searchVO.searchEdDate}" type="date">
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_lineActivity()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_lineActivity()">
	                                <span class="text">전체목록</span>
	                            </a>
<!-- 	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_income()" style="float: right;"> -->
<!-- 	                                <span class="text">등록</span> -->
<!-- 	                            </a> -->
							</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable">
									<thead>
										<tr>
											<th>탱크명</th>
											<th>설정값</th>
											<th>실제값</th>
											<th>상태</th>
											<th>등록시간</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="result" items="${lineActivity}"
											varStatus="status">
											<tr>

												<td>${result.pdTank}</td>
												
												<td>${result.pdSetting}</td>
												<td>${result.pdActVal}</td>
												<td><c:if test="${result.pdStatus == 1}">작동중</c:if> <c:if
														test="${result.pdStatus == 0}">중지</c:if></td>
												<td>${result.pdRegDte}</td>

											</tr>
										</c:forEach>
										<c:if test="${empty lineActivity}">
											<tr>
												<td colspan='6'>결과가 없습니다.</td>
												<del></del>
										</c:if>
									</tbody>
								</table>
								<div class="btn_page">
									<ui:pagination paginationInfo="${paginationInfo}" type="image"
										jsFunction="fn_pageview" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<%@ include file="../../menu/footer/footer.jsp"%>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@ include file="../../menu/logout/logout_alert.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/conf/vendor/jquery/jquery.min.js"></script>
	<script
		src="/resources/conf/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/conf/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>

	<script>
		function fn_pageview(pageNo) {
			listForm.pageIndex.value = pageNo;
			listForm.submit();
		}
		
		function fn_search_lineActivity(){
			listForm.submit();
		}
		
		function fn_searchAll_lineActivity(){
			listForm.searchKeyword.value = "";
			listForm.searchStDate.value = "";
			listForm.searchEdDate.value = "";
			listForm.pageIndex.value = 1;
			listForm.submit();
		}

		$(function() {
			$('#materialMenu').addClass("active");
			$('#monitoring').addClass("show");
			$('#lineActivity').addClass("active");

			let msg = '${msg}';
			if (msg) {
				alert(msg);
			}
		});
	</script>
</body>

</html>