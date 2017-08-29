package com.inventory.controller;

import java.util.List;

import com.inventory.common.BaseController;
import com.inventory.dto.JsonResult;
import com.inventory.service.InventoryService;
import com.inventory.service.ManagementService;
import com.jfinal.plugin.activerecord.Record;;

public class InventoryController extends BaseController{
	  private InventoryService inventoryservice = this.enhance(InventoryService.class);
		private ManagementService managementservice = this.enhance(ManagementService.class);
	  public void index() {
		  
	  }
	  //采购ruku
	  public void ruku(){
		  
	  }
	  //采购细明
	  public void searchRukudetail(){
		  JsonResult result;
		  String customerId = getPara("number");
		  try{
			  List<Record> customer = managementservice.searchRowById("customer",customerId);
			  result = new JsonResult("200", "查找成功！",customer);
		  }catch(Exception e){
			  result = new JsonResult("0", "查找失败，id不存在");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
}
