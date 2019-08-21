package cn.itcast.jk.domain;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-20
 */
public class Export {
	private String id;
	private Integer pnum;
	private Integer extnum;
	
	private java.util.Date inputDate;
	private String contractIds;
	private String customerContract;
	private String lcNo;
	private String consignee;
	private String marks;
	private String shipmentPort;
	private String destinationPort;
	private String transportMode;
	private String priceCondition;
	private String remark;
	
	private Integer state;
	private String createBy;
	private String createDept;
	private java.util.Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public Integer getExtnum() {
		return extnum;
	}
	public void setExtnum(Integer extnum) {
		this.extnum = extnum;
	}
	public java.util.Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(java.util.Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getContractIds() {
		return contractIds;
	}
	public void setContractIds(String contractIds) {
		this.contractIds = contractIds;
	}
	public String getCustomerContract() {
		return customerContract;
	}
	public void setCustomerContract(String customerContract) {
		this.customerContract = customerContract;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getShipmentPort() {
		return shipmentPort;
	}
	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getPriceCondition() {
		return priceCondition;
	}
	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
}
