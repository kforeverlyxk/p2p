package com.offcn.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.offcn.action.common.BaseAction;
import com.offcn.domain.product.Product;
import com.offcn.domain.product.ProductEarningRate;
import com.offcn.service.ProductEarningRateService;
import com.offcn.service.ProductService;
import com.offcn.utils.FrontStatusConstants;
import com.offcn.utils.JsonMapper;
import com.offcn.utils.Response;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/product")
public class ProductAction extends  BaseAction  implements ModelDriven<Product>{
@Autowired
private  ProductEarningRateService productEarningRateService;
@Autowired
private  ProductService  productService;


@Action("delProduct")
public  void  deletbyporid(){
	//获取到产品的id
	String  proid=this.getRequest().getParameter("proId");
	long  id=Long.parseLong(proid);
	  
	productService.delete(id);
         try {
			this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}


private Product product = new Product();
@Action("addProduct")
public  void addproduct(){
	String  raote=this.getRequest().getParameter("proEarningRates");
	//{12:12,12:12,12:12}
	Map  ma=new  JsonMapper().fromJson(raote, Map.class);
	List<ProductEarningRate>  list=new  ArrayList<ProductEarningRate>();
	for(Object o:ma.keySet()){
		ProductEarningRate r=new ProductEarningRate();
		r.setMonth(Integer.parseInt(o.toString()));
		r.setIncomeRate(Double.parseDouble(ma.get(o).toString()));
	      System.out.println(product.getProId()+"呵呵呵呵呵呵呵呵呵");
		r.setProductId((int)(product.getProId()));
	   list.add(r);
	}
	
	product.setProEarningRate(list);
	//调用service处理业务逻辑
	 productService.add(product);
	 try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	@Action("modifyProduct")
  public  void update(){
		
	  //获取数据
	String proeacr=	this.getRequest().getParameter("proEarningRates");
//	{"12":12,"13":12}
	System.out.println(proeacr);
     Map map=new  JsonMapper().fromJson(proeacr, Map.class);
     
     //{12:12,13:3,14:1}
     List<ProductEarningRate> list=new ArrayList<ProductEarningRate>();
     for(Object o:map.keySet()){
    	 ProductEarningRate  pro=new  ProductEarningRate();
    	 pro.setMonth(Integer.parseInt(o.toString()));
    	 pro.setIncomeRate(Double.parseDouble(map.get(o).toString()));
        pro.setProductId((int)product.getProId());
        System.out.println(pro);
        list.add(pro);
       }
     
     //根据对应的产品的所有利率
     product.setProEarningRate(list);;
     //调用sevice当中的修改方式
   
     productService.update(product);
   
     try {
		this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Action("findRates")
	     public  void findRates(){
		this.getResponse().setCharacterEncoding("utf-8");
	    	//获取到对应产品的利率 
		//产品的id
	    	 String strid=this.getRequest().getParameter("proId");
	    	 //再去查询利率
	    	 List<ProductEarningRate> list= productEarningRateService.findbyRateProid(Integer.parseInt(strid));
	  //转换成json数据
	    	 try {
				this.getResponse().getWriter().write(Response.build().
						setStatus(FrontStatusConstants.SUCCESS).setData(list).toJSON());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	@Action("findProductById")  
	public void findProductById(){
		this.getResponse().setCharacterEncoding("utf-8");
	
		  //根据传过来的proid
		String strid=this.getRequest().getParameter("proId");
		  long  id=Long.parseLong(strid);
		  //从service获取数据
		    Product  p=  productService.findOne(id);
		  //  转变成json数据
		    try {
				this.getResponse().getWriter().write(Response.build().
		setStatus("1").setData(p).toJSON());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
				  }
	
	
	@Action("findAllProduct")
	public void   findall(){
		this.getResponse().setCharacterEncoding("utf-8");
		
		//获取到的数据
	List<Product>  list=productService.findAll();
		// list
		//返回数据json数据
	  	if(list!=null){
		   try {
				this.getResponse().getWriter().write(Response.build().setStatus("1")
						                                .setData(list).toJSON());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	   }
	}

	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
}
