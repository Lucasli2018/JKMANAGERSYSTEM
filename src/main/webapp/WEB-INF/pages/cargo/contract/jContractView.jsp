<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>
<body>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		查看购销合同
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">收购方：</td>
	            <td class="tableContent">${obj.offeror}</td>
	            <td class="columnTitle_mustbe">打印版式：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${obj.printStyle=='2'}">两个货物</c:if>
	            	<c:if test="${obj.printStyle=='1'}">一个货物</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent">${obj.customName}</td>	        
	            <td class="columnTitle_mustbe">合同号：</td>
	            <td class="tableContent">${obj.contractNo}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">签单日期：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.signingDate}" pattern="yyyy-MM-dd"/></td>
	            <td class="columnTitle_mustbe">制单人：</td>
	            <td class="tableContent">${obj.inputBy}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">审单人：</td>
	            <td class="tableContent">${obj.checkBy}</td>
	            <td class="columnTitle_mustbe">验货员：</td>
	            <td class="tableContent">${obj.inspector}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.shipTime}" pattern="yyyy-MM-dd"/></td>		            
	            <td class="columnTitle_mustbe">交货期限：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.deliveryPeriod}" pattern="yyyy-MM-dd"/></td>	            
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">重要程度：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${obj.importNum=='1'}">★</c:if>
	            	<c:if test="${obj.importNum=='2'}">★★</c:if>
	            	<c:if test="${obj.importNum=='3'}">★★★</c:if>
	            </td>	            
	            <td class="columnTitle_mustbe">贸易条款：</td>
	            <td class="tableContentAuto">
	            	<c:if test="${obj.tradeTerms=='T/T'}">T/T</c:if>
	            	<c:if test="${obj.tradeTerms=='FOB'}">FOB</c:if>
	            </td>	            
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent" colspan="3"><pre>&nbsp;&nbsp;&nbsp;${obj.crequest}</pre></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent" colspan="3"><pre>${obj.remark}</pre></td>
	        </tr>
		</table>
	</div>
</div>
 
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		货物信息列表
    </div> 
    </div>
    </div>
    
    
	<div class="eXtremeTable" >
	<table id="ec_table" class="tableRegion" width="98%" >
		<thead>
		<tr>
			<td class="tableHeader">序号</td>
			<td class="tableHeader">厂家</td>
			<td class="tableHeader">货号</td>
			<td class="tableHeader" width="200">货物描述</td>
			<td class="tableHeader">数量</td>
			<td class="tableHeader">包装单位</td>
			<td class="tableHeader">单价</td>
			<td class="tableHeader">总金额</td>
		</tr>
		</thead>
		<tbody class="tableBody" >
		
		<c:forEach items="${obj.contractProducts}" var="o" varStatus="status">
		<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td>&nbsp;<i>${status.index+1}</i></td>
			<td>${o.factory.factoryName}</td>
			<td>${o.productNo}</td>
			<td width="410">${o.productDesc}</td>
			<td>${o.cnumber}</td>
			<td>${o.packingUnit}</td>
			<td>${o.price}</td>
			<td>${o.amount}</td>
		</tr>
			<c:forEach items="${o.extCproducts}" var="e" varStatus="status">
			<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
				<td align="right"><font color="blue">附件：${status.index+1}</font>&nbsp;</td>
				<td>${e.factory.factoryName}</td>
				<td>${e.productNo}</td>
				<td width="410">${e.productDesc}</td>
				<td>${e.cnumber}</td>
				<td>${e.packingUnit}</td>
				<td>${e.price}</td>
				<td>${e.amount}</td>				
			</tr>
			</c:forEach>		
		</c:forEach>
		
		</tbody>
	</table>
	</div>
	
</div>
 
</body>
</html>

