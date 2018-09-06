package com.offcn.domain.productAcount;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

public class ExpectedReturn {
	@Id
	@GeneratedValue()
	@Column(name="T_ID", nullable=false)
	private Integer id;  //������������������
	
	@Column(name="T_UID")
	private Integer userId;  //������������������ID
	
	@Column(name="T_PID")
	private Integer productId; //������������������ID
	
	@Column(name="T_RID")
	private Integer investRcord; //������������������������������������ID
	
	@Column(name="T_EXPECTED_DATE")
	private String expectedDate; //������������������������������������
	
	@Column(name="T_EXPECTED_MONEY")
	private Double expectedMoney; //������������������������������������
	
	@Column(name="T_CREATE_DATE")
	private Date createDate; //������������������������������������
	
	

	/**
	* <p>Title: </p>
	* <p>Description: </p>
	*
	*/ 
	public ExpectedReturn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedReturn(String expectedDate, Double expectedMoney) {
		super();
		this.expectedDate = expectedDate;
		this.expectedMoney = expectedMoney;
	}
	
	/**
	 * @return id
	 *
	 */
	
	public Integer getId() {
		return id;
	}

	/**
	 * @param id ������������������������������������ id
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return userId
	 *
	 */
	
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId ������������������������������������ userId
	 *
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return productId
	 *
	 */
	
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId ������������������������������������ productId
	 *
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return investRcord
	 *
	 */
	
	public Integer getInvestRcord() {
		return investRcord;
	}

	/**
	 * @param investRcord ������������������������������������ investRcord
	 *
	 */
	public void setInvestRcord(Integer investRcord) {
		this.investRcord = investRcord;
	}

	/**
	 * @return expectedDate
	 *
	 */
	
	public String getExpectedDate() {
		return expectedDate;
	}

	/**
	 * @param expectedDate ������������������������������������ expectedDate
	 *
	 */
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	/**
	 * @return expectedMoney
	 *
	 */
	
	public Double getExpectedMoney() {
		return expectedMoney;
	}

	/**
	 * @param expectedMoney ������������������������������������ expectedMoney
	 *
	 */
	public void setExpectedMoney(Double expectedMoney) {
		this.expectedMoney = expectedMoney;
	}

	/**
	 * @return createDate
	 *
	 */
	
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate ������������������������������������ createDate
	 *
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
