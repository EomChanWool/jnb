<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>

<body id="page-top">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
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

                        <!-- Nav member - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">생산집계현황</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                        
                        <div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/monitoring/prodAggregate/prodAggregate.do" method="post">
									
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									
									<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition2" id="searchCondition2">
						    			
						    			<c:forEach var="list" items="${prYearList}" varStatus="status">
						    				<option value="${list.prYear}" <c:if test="${searchVO.searchCondition2 eq list.prYear or status.count eq 1}">selected="selected"</c:if>>${list.prYear}년도</option>
						    			</c:forEach>
						    		</select>
						    								
   									
						    	</form>
						    	
	                           
							</div> 
                        
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
								<div id="graph" style="width: 100%; height:500px;"></div>
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
		$('#monitoringMenu').addClass("active");
		$('#monitoring').addClass("show");
		$('#prodAggregate').addClass("active");
		 $('#searchCondition2').change(function(){
				listForm.submit();
			});
		
		window.onresize = function() {
			location.reload();
		}
	});
	
	var chartDom = document.getElementById('graph');
	var myChart = echarts.init(chartDom);
	var option;
	
	let date = [];
	

	let prodCnt = [];
	
	let orderCnt = [];

	let prodCntCnt = [];
	
	const dataMin = 0;
	const dataMax = 0;
	const dataInterval = 1000;
	
	
	<c:forEach items="${ordersList}" var="list">
		orderCnt.push('${list.orCnt}');
	</c:forEach>
	
	<c:forEach items="${prodCntList}" var="list">
	prodCntCnt.push('${list.prodCnt}');
</c:forEach>
	
	<c:forEach items="${prodList}" var="list">
		date.push('${list.month}월');
		prodCnt.push('${list.prCnt}');
	</c:forEach>
	
	
	
	
	console.log(prodCnt);
	
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
			    data: ['수주건수', '생산건수', '생산량']
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
			        formatter: '{value} EA'
			      }
			    },
			    {
		    		  type: 'value',
			      	  name: 'kg',
			      	  position: 'right',
			      	  axisLabel: {
			            formatter: '{value} kg'
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
				      data: orderCnt
				    },
			    {
		    	name: '생산건수',
			    type: 'bar',
			    tooltip: {
			      valueFormatter: function (value) {
			        return value + ' EA';
			      }
			    },
			    data: prodCnt
			    },
			    {
			    	name: '생산량',
			    	yAxisIndex: 1,
				    type: 'line',
				    tooltip: {
				      valueFormatter: function (value) {
				        return value + ' kg';
				      }
				    },
				    data: prodCntCnt
				    }
			   
			  ]
			};
	option && myChart.setOption(option);
	</script>
</body>

</html>