package com.offcn.action.action.crediteor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.domain.creditor.CreditorModel;
import com.offcn.service.CreditorService;
import com.offcn.utils.ClaimsType;
import com.offcn.utils.FrontStatusConstants;
import com.offcn.utils.RandomNumberUtil;
import com.offcn.utils.Response;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Namespace("/creditor")
public class CreadIterAction extends BaseAction  implements ModelDriven<CreditorModel>{

	 private CreditorModel creditorModel=new CreditorModel();
	 @Autowired
	 private CreditorService creditorService;
	
	@Action("addCreditor")
	public void addCreditor(){
		creditorModel.setDebtNo("ZQNO" + RandomNumberUtil.randomNumber(new Date()));//债权id
		creditorModel.setBorrowerId(1);//贷款人id
		creditorModel.setDebtStatus(ClaimsType.UNCHECKDE); //债权状态
		creditorModel.setMatchedMoney(0.00);//匹配金额
		creditorModel.setMatchedStatus(ClaimsType.UNMATCH); //匹配状态
		creditorModel.setDebtType(FrontStatusConstants.NULL_SELECT_OUTACCOUNT);//标的类型
		creditorModel.setAvailablePeriod(creditorModel.getDebtTransferredPeriod());//可用期限
		creditorModel.setAvailableMoney(creditorModel.getDebtTransferredMoney());//可用金额
		creditorService.addCreditor(creditorModel);
		try {
			getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public CreditorModel getModel() {
		// TODO Auto-generated method stub
		return creditorModel;
	}
	
	@Action("download")
	public void download() {
		
		// 1.1得到文件位置
		String path = this.getRequest().getSession().getServletContext()
				.getRealPath("/WEB-INF/excelTemplate/ClaimsBatchImportTemplate.xlsx");
		// 设置下载时两个响应头
		String mimeType = this.getRequest().getSession().getServletContext()
				.getMimeType("ClaimsBatchImportTemplate.xlsx");
		this.getResponse().setHeader("content-type", mimeType);
		this.getResponse().setHeader("content-disposition",
				"attachment;filename=" + (new Date().toLocaleString() + ".xlsx"));
		// 1.2获取输入流
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			// 2.将资源写出,使用response来获取流
			OutputStream os = this.getResponse().getOutputStream();
			IOUtils.copy(fis, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Action("upload")
	public  void upload(){
			//获取到上传的内容 文件  然后保存起来
		try {
			getResponse().getWriter().write(Response.build().setStatus("101").toJSON());
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
