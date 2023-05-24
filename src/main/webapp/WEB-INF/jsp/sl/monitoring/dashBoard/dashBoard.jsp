<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
	.scroll{
		overflow-y: scroll;
		-ms-overflow-style: none;
		scrollbar-width: none;
		scroll-behavior: smooth;
	}
	
	.notice, .stockState{
		height: 320px !important;
		margin-top: 15px;
	}
</style>

<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
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

                        <!-- Nav member - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid" style="overflow: hidden;">

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/monitoring/dashBoard.do" method="post">
								<input type="hidden" name="noIdx" id="noIdx">
								<div class="cont_wrap">
									<div class="cont">
								      <h1>수주대실적현황</h1>
								      <div id="ordersOutputGraph" style="width: 100%; height:300px;"></div>
								    </div>
								    <div class="cont">
								      <h1>생산실적현황</h1>
								      <div id="prodAggregateGraph" style="width: 100%; height:300px;"></div>
								    </div>
								     <div class="cont">
								      <h1>설비종합효율</h1>
								      <div id="equipEffGraph" style="width: 100%; height:300px;"></div>
								    </div>
								    <div class="cont stockState scroll">
								      <h1>재고현황</h1>
								       <table class="table table-bordered" id="dataTable">
										<thead>
											<tr>
												<th>자재명</th>
												<th>현 재고량(kg)</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="result" items="${itemList}" varStatus="status">
												<tr>
													<td>${result.itemName}</td>
													<td>${result.itemStock}kg</td>
												</tr>
											</c:forEach>
											<c:if test="${empty itemList}"><tr><td colspan='2'>결과가 없습니다.</td><del></del></c:if>
										</tbody>
								      </table>
								    </div>
								      <div class="cont stockState scroll">
								      <h1>라인가동현황</h1>
								       <table class="table table-bordered" id="dataTable">
										<thead>
											<tr>
												<th>탱크명</th>
												<th>상태</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="result" items="${lineList}" varStatus="status">
												<tr>
													<td>${result.pdTank}</td>
													<td><c:if test="${result.pdStatus == 1}">작동중</c:if>
													<c:if test="${result.pdStatus == 0}">정지</c:if>
													</td>
												</tr>
											</c:forEach>
											<c:if test="${empty lineList}"><tr><td colspan='2'>결과가 없습니다.</td><del></del></c:if>
										</tbody>
								      </table>
								    </div>
								    <div class="cont notice scroll">
								      <h1>공지사항</h1>
								       <table class="table table-bordered" id="dataTable">
										<thead>
											<tr>
												<th>내용</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="result" items="${noticeList}" varStatus="status">
												<tr>
													<td>${result.noCont}</td>
												</tr>
											</c:forEach>
											<c:if test="${empty noticeList}"><tr><td colspan='1'>결과가 없습니다.</td><del></del></c:if>
										</tbody>
								      </table>
								    </div>
								  
								   
								    
						    	</div>
					    	</form>
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
	function fn_detail_notice(noIdx){
		$('#noIdx').val(noIdx);
		listForm.action = "${pageContext.request.contextPath}/sl/monitoring/detailNotice.do";
		listForm.submit();
	}

	$(function() {
		$('#dashboard').addClass("active");
		
// 		document.getElementById("sidebarToggle").click();

		window.onresize = function() {
			location.reload();
		}
		
		setTimeout(function(){
			location.reload();
		}, 60000);
		
		const stockArea = document.querySelector(".stockState");
		const noticeArea = document.querySelector(".notice");
		autoScroll(stockArea, 2000);
		autoScroll(noticeArea, 3000);
	});
	
	function autoScroll(obj, interval){
		setInterval(function(){
			obj.scrollTop = obj.scrollTop + 50;
			if(obj.offsetHeight + obj.scrollTop >= obj.scrollHeight){
				obj.scrollTop = 0;
			}
		}, interval);
	}
	</script>
	<script>
	//수주대실적 현황
	var chartDom = document.getElementById('ordersOutputGraph');
	var myChart = echarts.init(chartDom);
	var option;
	
	let date = [];
	
	let orderCnt = [];
	let prodCnt = [];
	let deliveryCnt = [];
	
	const dataMin = 0;
	const dataMax = 0;
	const dataInterval = 1000;
	
	<c:forEach items="${orderCntList}" var="list">
		date.push('${list.month}월');
		orderCnt.push('${list.orderCnt}');
	</c:forEach>
	
	
	<c:forEach items="${prodCntList}" var="list">
		prodCnt.push('${list.prodCnt}');
	</c:forEach>
	
	<c:forEach items="${deliveryCntList}" var="list">
		deliveryCnt.push('${list.orderCnt}');
	</c:forEach>
	
	
	option = {
			  tooltip: {
			    trigger: 'axis',
// 			    formatter: '{b0}<br>{a0} : {c0} EA<br>{a1} : {c1} EA<br>{a2} : {c2} EA',
				},
			    axisPointer: {
			    	type: 'cross',
			    	axis: "auto",
			    	crossStyle: {
			        	color: '#999'
		    	}
			  },
			  toolbox: {
			    feature: {
			      dataView: { show: false, readOnly: false },
			      magicType: { show: false, type: ['line', 'bar'] },
			      restore: { show: false },
			      saveAsImage: { show: true }
			    }
			  },
			  legend: {
			    data: ['수주량', '생산량', '출하량']
			  },
			  xAxis: [
			    {
			      type: 'category',
			      data: date,
			      axisPointer: {
			        type: 'shadow'
			      }
			    }
			  ],
			  yAxis: [
			    {
			      type: 'value',
			      name: '개수',
			     
			      
			      axisLabel: {
			        formatter: '{value} kg'
			      }
			    }
			  ],
			  series: [
			    {
			      name: '수주량',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' kg';
			        }
			      },
			      data: orderCnt
			    },
			    {
		    	name: '생산량',
			    type: 'bar',
			    tooltip: {
			      valueFormatter: function (value) {
			        return value + ' kg';
			      }
			    },
			    data: prodCnt
			    },
			    {
		    	name: '출하량',
			    type: 'bar',
			    tooltip: {
			      valueFormatter: function (value) {
			        return value + ' kg';
			      }
			    },
			    data: deliveryCnt
			    }
			  ]
			};
	option && myChart.setOption(option);
	</script>
	<script>
	//생산실적 현황
	var chartDom = document.getElementById('prodAggregateGraph');
	var myChart = echarts.init(chartDom);
	var option;
	
	let date2 = [];
	

	let prodCnt2 = [];

	let orderCnt2 = [];
	
	
	<c:forEach items="${ordersList}" var="list">
		orderCnt2.push('${list.orCnt}');
	</c:forEach>
	
	<c:forEach items="${prodList}" var="list">
		date2.push('${list.month}월');
		prodCnt2.push('${list.prCnt}');
	</c:forEach>
	
	
	console.log(prodCnt2);
	
	option = {
			  tooltip: {
			    trigger: 'axis',
// 			    formatter: '{b0}<br>{a0} : {c0} EA<br>{a1} : {c1} EA<br>{a2} : {c2} EA',
				},
			    axisPointer: {
			    	type: 'cross',
			    	axis: "auto",
			    	crossStyle: {
			        	color: '#999'
		    	}
			  },
			  toolbox: {
			    feature: {
			      dataView: { show: false, readOnly: false },
			      magicType: { show: false, type: ['line', 'bar'] },
			      restore: { show: false },
			      saveAsImage: { show: true }
			    }
			  },
			  legend: {
			    data: ['수주건수', '생산건수']
			  },
			  xAxis: [
			    {
			      type: 'category',
			      data: date2,
			      axisPointer: {
			        type: 'shadow'
			      }
			    }
			  ],
			  yAxis: [
			    {
			      type: 'value',
			      name: '개수',
			     
			      
			      axisLabel: {
			        formatter: '{value} EA'
			      }
			    }
			  ],
			  series: [
				  
				  {
				      name: '수주건수',
				      type: 'bar',
				      tooltip: {
				        valueFormatter: function (value) {
				          return value + ' EA';
				        }
				      },
				      data: orderCnt2
				    },
			  
			    {
		    	name: '생산건수',
			    type: 'bar',
			    tooltip: {
			      valueFormatter: function (value) {
			        return value + ' EA';
			      }
			    },
			    data: prodCnt2
			    }
			   
			  ]
			};
	option && myChart.setOption(option);
	</script>
	
	<script>
	
	 var chartDom = document.getElementById('equipEffGraph');
		var myChart = echarts.init(chartDom);
		var option;
		
		let faName = [];
		
		let percent = [];
		
		
		
		
		<c:forEach items="${faName}" var="list">
			
		faName.push('${list}');
		</c:forEach>
		
		
		<c:forEach items="${percent}" var="list">
		percent.push('${list}');
		</c:forEach>
		
		console.log(percent);
		
		option = {
				  tooltip: {
				    trigger: 'axis',
//	 			    formatter: '{b0}<br>{a0} : {c0} EA<br>{a1} : {c1} EA<br>{a2} : {c2} EA',
					},
				    axisPointer: {
				    	type: 'cross',
				    	axis: "auto",
				    	crossStyle: {
				        	color: '#999'
			    	}
				  },
				  toolbox: {
				    feature: {
				      dataView: { show: false, readOnly: false },
				      magicType: { show: false, type: ['line', 'bar'] },
				      restore: { show: false },
				      saveAsImage: { show: true }
				    }
				  },
				  legend: {
				    data: ['가동률']
				  },
				  xAxis: [
				    {
				      type: 'category',
				      data: faName,
				      axisPointer: {
				        type: 'shadow'
				      }
				    }
				  ],
				  yAxis: [
				    {
				      type: 'value',
				      name: '가동률',
				     
				      
				      axisLabel: {
				        formatter: '{value} %'
				      }
				    }
				  ],
				  series: [
				    {
				      name: '가동률',
				      type: 'bar',
				      tooltip: {
				        valueFormatter: function (value) {
				          return value + ' %';
				        }
				      },
				      data: percent
				    }
				  ]
				};
		option && myChart.setOption(option);
	
	</script>
	
</body>

</html>