<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Sidebar - Brand -->
<%-- <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/sl/basicInfo/companyList.do"> --%>
	<c:if test="${memberVO.saLev eq '1'}">
		<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/sl/material/income/incomeList.do">
	</c:if>
	<c:if test="${memberVO.saLev eq '2'}">
		<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/sl/basicInfo/member/memberList.do">
	</c:if>
    <div class="sidebar-brand-icon rotate-n-15">
<!--         <i class="fas fa-laugh-wink"></i> -->
		<img alt="제이앤비로고" src="/resources/conf/images/jungin.png" style="width: 55px; transform: rotate(15deg);">
		
    </div>
    <div class="sidebar-brand-text mx-3">제이앤비(주)</div>
</a>

<!-- Divider -->
<hr class="sidebar-divider my-0">
            
<li class="nav-item" id="dashboard">
    <a class="nav-link" href="${pageContext.request.contextPath}/sl/monitoring/dashBoard.do">
        <i class="fas fa-fw fa-tachometer-alt"></i>
        <span>Dashboard</span>
    </a>
</li>
<!-- Divider -->
<hr class="sidebar-divider">

<li class="nav-item" id="basicInfoMenu">
	<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#basicInfo"
	    aria-expanded="true" aria-controls="basicInfo">
	    <i class="fas fa-fw fa-cog"></i>
	    <span>기준정보관리</span>
	</a>
	<div id="basicInfo" class="collapse" aria-labelledby="headingBasicInfo" data-parent="#accordionSidebar">
	   	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="memberList" href="${pageContext.request.contextPath}/sl/basicInfo/member/memberList.do">사용자정보관리</a>
		    <a class="collapse-item" id="companyList" href="${pageContext.request.contextPath}/sl/basicInfo/company/companyList.do">사업장관리</a>
		    <a class="collapse-item" id="factoryList" href="${pageContext.request.contextPath}/sl/basicInfo/factory/factoryList.do">공장관리</a>
		    <a class="collapse-item" id="facilityList" href="${pageContext.request.contextPath}/sl/basicInfo/facility/facilityList.do">설비관리</a>
		    <a class="collapse-item" id="deptList" href="${pageContext.request.contextPath}/sl/basicInfo/dept/deptList.do">부서코드관리</a>
		    <a class="collapse-item" id="itemList" href="${pageContext.request.contextPath}/sl/basicInfo/item/itemList.do">품목코드관리</a>
		    <a class="collapse-item" id="accountList" href="${pageContext.request.contextPath}/sl/basicInfo/account/accountList.do">거래처관리</a>
		    <a class="collapse-item" id="storageList" href="${pageContext.request.contextPath}/sl/basicInfo/storage/storageList.do">창고관리</a>
		    <a class="collapse-item" id="bomList" href="${pageContext.request.contextPath}/sl/basicInfo/bom/bomList.do">BOM관리</a>
		    <a class="collapse-item" id="badItemList" href="${pageContext.request.contextPath}/sl/basicInfo/badItem/badItemList.do">불량항목관리</a>
		</div>
	</div>
</li>
<li class="nav-item" id="salesMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#sales"
        aria-expanded="true" aria-controls="sales">
        <i class="fas fa-fw fa-folder"></i>
        <span>영업관리</span>
    </a>
    <div id="sales" class="collapse" aria-labelledby="headingSales" data-parent="#accordionSidebar">
        <div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="estimateList" href="${pageContext.request.contextPath}/sl/sales/estimate/estimateList.do">견적관리</a>
		    <a class="collapse-item" id="ordersList" href="${pageContext.request.contextPath}/sl/sales/ordersManage/ordersList.do">수주관리</a>
		    <a class="collapse-item" id="deliveryList" href="${pageContext.request.contextPath}/sl/sales/delivery/deliveryList.do">납품관리</a>
		    <a class="collapse-item" id="stateList" href="${pageContext.request.contextPath}/sl/sales/stateManage/stateList.do">현황관리</a>
		</div>
    </div>
</li>
<li class="nav-item" id="materialMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#material"
        aria-expanded="true" aria-controls="material">
        <i class="fas fa-fw fa-folder"></i>
        <span>자재관리</span>
    </a>
    <div id="material" class="collapse" aria-labelledby="headingMaterial" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="incomeList" href="${pageContext.request.contextPath}/sl/material/income/incomeList.do">입고관리</a>
		    <a class="collapse-item" id="dispensingList" href="${pageContext.request.contextPath}/sl/material/dispensing/dispensingList.do">불출관리</a>
		    <a class="collapse-item" id="stockReturnList" href="${pageContext.request.contextPath}/sl/material/stockReturn/stockReturnList.do">환입관리</a>
		    <a class="collapse-item" id="stockStateList" href="${pageContext.request.contextPath}/sl/material/mtStockState/stockStateList.do">재고현황</a>
		    <a class="collapse-item" id="stockAdjustList" href="${pageContext.request.contextPath}/sl/material/stockAdjust/stockAdjustList.do">재고조정관리</a>
		    <a class="collapse-item" id="shipmentList" href="${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do">출고관리</a>
		</div>
    </div>
</li>
<li class="nav-item" id="productionMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#production"
        aria-expanded="true" aria-controls="production">
        <i class="fas fa-fw fa-folder"></i>
        <span>생산관리</span>
    </a>
    <div id="production" class="collapse" aria-labelledby="headingProduction" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="prodPlanList" href="${pageContext.request.contextPath}/sl/production/prodPlan/prodPlanList.do">생산계획</a>
		    <a class="collapse-item" id="workOrderList" href="${pageContext.request.contextPath}/sl/production/workOrder/workOrderList.do">작업지시</a>
		    <a class="collapse-item" id="prodResultList" href="${pageContext.request.contextPath}/sl/production/prodResult/prodResultList.do">생산실적</a>
		    <a class="collapse-item" id="prodCurStateList" href="${pageContext.request.contextPath}/sl/production/prodCurState/prodCurStateList.do">생산실적현황</a>
		    <a class="collapse-item" id="lotList" href="${pageContext.request.contextPath}/sl/production/lot/lotList.do">로트목록</a>
		    <a class="collapse-item" id="nonOperList" href="${pageContext.request.contextPath}/sl/production/nonOper/nonOperList.do">비가동관리</a>
		</div>
    </div>
</li>
<li class="nav-item" id="qualityMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#quality"
        aria-expanded="true" aria-controls="quality">
        <i class="fas fa-fw fa-folder"></i>
        <span>품질관리</span>
    </a>
    <div id="quality" class="collapse" aria-labelledby="headingQuality" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="instList" href="${pageContext.request.contextPath}/sl/quality/inst/instList.do">검사기준서관리</a>
		    <a class="collapse-item" id="wostList" href="${pageContext.request.contextPath}/sl/quality/wost/wostList.do">작업표준서관리</a>
		    <a class="collapse-item" id="checkProdList" href="${pageContext.request.contextPath}/sl/quality/checkProd/checkProdList.do">유무검사관리</a>
		    <a class="collapse-item" id="incongruentList" href="${pageContext.request.contextPath}/sl/quality/incongruent/incongruentList.do">부적합관리</a>
		    <a class="collapse-item" id="incongruentStateList" href="${pageContext.request.contextPath}/sl/quality/incongruentState/incongruentStateList.do">부적합추이현황</a>
		    <a class="collapse-item" id="analyManageList" href="${pageContext.request.contextPath}/sl/quality/analyManage/analyManageList.do">검사관리</a>
		</div>
    </div>
</li>
<li class="nav-item" id="kpiMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#kpi"
        aria-expanded="true" aria-controls="kpi">
        <i class="fas fa-fw fa-folder"></i>
        <span>KPI관리</span>
    </a>
    <div id="kpi" class="collapse" aria-labelledby="headingKpi" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="kpiGoalList" href="${pageContext.request.contextPath}/sl/kpi/kpiGoal/kpiGoalList.do">KPI목표등록</a>
		    <a class="collapse-item" id="kpiStateList" href="${pageContext.request.contextPath}/sl/kpi/kpiState/kpiStateList.do">KPI현황</a>
		</div>
    </div>
</li>
<li class="nav-item" id="monitoringMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#monitoring"
        aria-expanded="true" aria-controls="monitoring">
        <i class="fas fa-fw fa-folder"></i>
        <span>모니터링</span>
    </a>
    <div id="monitoring" class="collapse" aria-labelledby="headingMonitoring" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="ordersOutput" href="${pageContext.request.contextPath}/sl/monitoring/ordersOutput/ordersOutput.do">수주대실적현황</a>
		    <a class="collapse-item" id="moStockState" href="${pageContext.request.contextPath}/sl/monitoring/moStockState/moStockState.do">재고현황</a>
		    <a class="collapse-item" id="lineActivity" href="${pageContext.request.contextPath}/sl/monitoring/lineActivity/lineActivity.do">라인가동현황</a>
		    <a class="collapse-item" id="prodAggregate" href="${pageContext.request.contextPath}/sl/monitoring/prodAggregate/prodAggregate.do">생산집계현황</a>
		    <a class="collapse-item" id="noticeList" href="${pageContext.request.contextPath}/sl/monitoring/notice/noticeList.do">공지사항</a>
		    <a class="collapse-item" id="equipEff" href="${pageContext.request.contextPath}/sl/monitoring/equipEff/equipEff.do">설비종합효율</a>
		</div>
    </div>
</li>
<li class="nav-item" id="systemMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#system"
        aria-expanded="true" aria-controls="system">
        <i class="fas fa-fw fa-folder"></i>
        <span>시스템관리</span>
    </a>
    <div id="system" class="collapse" aria-labelledby="headingSystem" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <a class="collapse-item" id="programList" href="${pageContext.request.contextPath}/sl/system/program/programList.do">프로그램관리</a>
		    <a class="collapse-item" id="sysAuthorityList" href="${pageContext.request.contextPath}/sl/system/sysAuthority/sysAuthorityList.do">시스템권한관리</a>
		    <a class="collapse-item" id="progAuthorityList" href="${pageContext.request.contextPath}/sl/system/progAuthority/progAuthorityList.do">권한별프로그램관리</a>
		    <a class="collapse-item" id="userAuthorityList" href="${pageContext.request.contextPath}/sl/system/userAuthority/userAuthorityList.do">사용자별권한관리</a>
		    <a class="collapse-item" id="comnCodeList" href="${pageContext.request.contextPath}/sl/system/comnCode/comnCodeList.do">공통코드관리</a>
		    <a class="collapse-item" id="sysLogList" href="${pageContext.request.contextPath}/sl/system/sysLog/sysLogList.do">시스템사용로그</a>
		</div>
    </div>
</li>
<!-- Divider -->
<hr class="sidebar-divider d-none d-md-block">
<!-- Sidebar Toggler (Sidebar) -->
<div class="text-center d-none d-md-inline">
    <button class="rounded-circle border-0" id="sidebarToggle"></button>
</div>
