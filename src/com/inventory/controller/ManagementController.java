package com.inventory.controller;

import java.util.List;

import com.inventory.common.BaseController;
import com.inventory.dto.JsonResult;
import com.inventory.model.*;
import com.inventory.service.ManagementService;
import com.inventory.service.UserService;
import com.jfinal.plugin.activerecord.Record;
/*
	进销存管理
*/

public class ManagementController extends BaseController{
	private ManagementService managementservice = this.enhance(ManagementService.class);
	private UserService userservice = this.enhance(UserService.class);
	  public void index() {
		  
	  }
  //参数设置
	  //增加计量单位
	  public void addUnits(){
		  String units = getPara("units");
		  String introduction = getPara("introduction");
		  if(managementservice.add(units,introduction)){
			  JsonResult result = new JsonResult("200", "保存成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "保存失败！");
			  renderJson(result);
		  }
	  }
	  //删除计量单位
	  public void deleteUnitsById(){
		  int unitId = getParaToInt("unitId");
		  if(managementservice.deleteUnit(unitId)){
			  JsonResult result = new JsonResult("200","删除成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0","删除失败！");
			  renderJson(result);
		  }
	  }
	  //查询计量单位
	  public void searchUnits(){
		  List<Record> units = managementservice.search();
		  if(units != null){
			  JsonResult result = new JsonResult("200", "查询成功", units);
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0","查询失败！");
			  renderJson(result);
		  }
	  }
	  //增加商品类别
	  public void addCategory(){
		  String categoryName = getPara("category");
		  if(managementservice.addCategoryByName(categoryName)){
			  JsonResult result = new JsonResult("200", "保存成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "保存失败！");
			  renderJson(result);
		  }
	  }
	  //查看商品类别
	  public void searchCategory(){
		  List<Record> category = managementservice.searchCategory();
		  if(category != null){
			  JsonResult result = new JsonResult("200", "succeed", category);
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "查询失败！");
			  renderJson(result);
		  }
	  }
	  //删除商品类别
	  public void deleteCategory(){
		  int categoryId = getParaToInt("categoryId");
		  if(managementservice.deleteCategoryById(categoryId)){
			  JsonResult result = new JsonResult("200", "删除成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "删除失败！");
			  renderJson(result);
		  }
	  }
	  //增加商品
	  public void addCommodities(){
		  String commodityId = getPara("commodityId"); //商品编码
		  String name = getPara("name"); //商品名称
		  String imeiNumber = getPara("imeiNumber"); //imei码
		  String modelNumber = getPara("modelNumber"); //型号
		  int categoryId = getParaToInt("categoryId"); //种类
		  int unitId = getParaToInt("unitId");//单价
		  String place = getPara("place");//产地
		  int price = getParaToInt("price");//销售单价
		  int purchasingPrice = getParaToInt("purchasingPrice");//采购单价
		  int amount = getParaToInt("amount");//库存总量
		  String supplierId = getPara("supplierId");//供应商
		  String createTime = getPara("createTime");//创建时间
		  String brand = getPara("brand");//品牌
		  int createId = getParaToInt("createId");//创建者
		  String illustrate = getPara("illustrate");//说明
		  if(managementservice.addCommodity(commodityId,name ,imeiNumber,modelNumber, categoryId,unitId,place,price,purchasingPrice,amount,supplierId,createTime,brand,createId,illustrate)){
			  JsonResult result = new JsonResult("200", "删除成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "删除失败！");
			  renderJson(result);
		  }
		  
	  }
	  //查询商品空参数为空即为全部
	  public void searchCommodities(){
		  String name = getPara("name");
		  List<Record> commodity = managementservice.searchCommodityByName(name);
		  if(commodity != null){
			  JsonResult result = new JsonResult("200", "succeed", commodity);
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "查询失败！");
			  renderJson(result);
		  }
	  }
	  //删除商品
	  public void deleteCommodities(){
		  int id = getParaToInt("id");
		  if(managementservice.deleteCommodityById(id)){
			  JsonResult result = new JsonResult("200","删除成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0","删除失败！");
			  renderJson(result);
		  }
	  }
  //权限设置
	  public void changePower(){
		  int id = getParaToInt("id");
		  int power = getParaToInt("power");
		  if(userservice.changePowerById(id,power)){
			  JsonResult result = new JsonResult("200","更改权限成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0","更改权限失败！");
			  renderJson(result);
		  }
	  }
	  //更改密码
	  public void changePassword(){
		  int id = getParaToInt("id");
		  String passWord = getPara("password");
		  if(userservice.changePasswordById(id,passWord)){
			  JsonResult result = new JsonResult("200", "更改密码成功！");
			  renderJson(result);
		  } else {
			  JsonResult result = new JsonResult("0", "更改密码失败！");
			  renderJson(result);
		  }
	  }
	  //增加供应商
	  public void addSupplier(){
		  JsonResult result;
		  //getModel 用来接收页面表单域传递过来的 model 对象
		  try{
			  Supplier supplier = getModel(Supplier.class,"");
			  if(managementservice.getSameId("supplier",supplier.getStr("supplier_id"))){
				  supplier.save();
				  result = new JsonResult("200", "保存成功！");
			  } else {
				  result = new JsonResult("200", "id已存在！");
			  }
		  } catch(Exception e){
			  result = new JsonResult("0", "保存失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //修改供应商
	  public void modifySupplier(){
		  JsonResult result;
		  try{
			  Supplier supplier = getModel(Supplier.class,"");
			  supplier.update();
			  result = new JsonResult("200", "修改成功！");
		  }catch(Exception e){
			  result = new JsonResult("0", "修改失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //查找供应商，id为空则查找全部
	  public void searchSupplier(){
		  JsonResult result;
		  String supplierId = getPara("supplier_id");
		  try{
			  List<Record> supplier = managementservice.searchRowById("supplier",supplierId);
			  result = new JsonResult("200", "查找成功！",supplier);
		  }catch(Exception e){
			  result = new JsonResult("0", "查找失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //删除供应商
	  public void deleteSupplier(){
		  JsonResult result;
		  String supplierId = getPara("supplier_id");
		  try{
			  if(managementservice.deleteRowById("supplier",supplierId)){
			  result = new JsonResult("200", "删除成功！");
			  }else{
				  result = new JsonResult("200", "删除失败！");
			  }
		  }catch(Exception e){
			  result = new JsonResult("0", "删除失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //新增客户
	  public void addCustomer(){
		  JsonResult result;
		  //getModel 用来接收页面表单域传递过来的 model 对象
		  try{
			  Customer customer = getModel(Customer.class,"");
			  if(managementservice.getSameId("customer",customer.getStr("customer_id"))){
				  customer.save();
				  result = new JsonResult("200", "保存成功！");
			  } else {
				  result = new JsonResult("200", "id已存在！");
			  }
		  } catch(Exception e){
			  result = new JsonResult("0", "保存失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //修改客户
	  public void modifyCustomer(){
		  JsonResult result;
		  try{
			  Customer customer = getModel(Customer.class,"");
			  customer.update();
			  result = new JsonResult("200", "修改成功！");
		  }catch(Exception e){
			  result = new JsonResult("0", "修改失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //删除客户
	  public void deleteCustomer(){
		  JsonResult result;
		  String customerId = getPara("customer_id");
		  try{
			  if(managementservice.deleteRowById("customer",customerId)){
			  result = new JsonResult("200", "删除成功！");
			  }else{
				  result = new JsonResult("200", "删除失败！");
			  }
		  }catch(Exception e){
			  result = new JsonResult("0", "删除失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //查找客户,id为空，则全部查找
	  public void searchCustomer(){
		  JsonResult result;
		  String customerId = getPara("customer_id");
		  try{
			  List<Record> customer = managementservice.searchRowById("customer",customerId);
			  result = new JsonResult("200", "查找成功！",customer);
		  }catch(Exception e){
			  result = new JsonResult("0", "查找失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //增加仓库信息
	  public void addStorage(){
		  JsonResult result;
		  try{
			  Storage Storage = getModel(Storage.class,"");
			  if(managementservice.getSameId("Storage",Storage.getStr("Storage_id"))){
				  Storage.save();
				  result = new JsonResult("200", "保存成功！");
			  } else {
				  result = new JsonResult("200", "id已存在！");
			  }
		  } catch(Exception e){
			  result = new JsonResult("0", "保存失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	  //删除仓库信息
	  public void deleteStorage(){
		  JsonResult result;
		  String storageId = getPara("storage_id");
		  try{
			  if(managementservice.deleteRowById("storage",storageId)){
			  result = new JsonResult("200", "删除成功！");
			  }else{
				  result = new JsonResult("200", "删除失败！");
			  }
		  }catch(Exception e){
			  result = new JsonResult("0", "删除失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	//修改仓库信息
	  public void modifyStorage(){
		  JsonResult result;
		  try{
			  Storage storage = getModel(Storage.class,"");
			  storage.update();
			  result = new JsonResult("200", "修改成功！");
		  }catch(Exception e){
			  result = new JsonResult("0", "修改失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
	//查找仓库,id为空，则全部查找
	  public void searchStorage(){
		  JsonResult result;
		  String storageId = getPara("storage_id");
		  try{
			  List<Record> storage = managementservice.searchRowById("storage",storageId);
			  result = new JsonResult("200", "查找成功！",storage);
		  }catch(Exception e){
			  result = new JsonResult("0", "查找失败");
			  System.out.println(e);
		  }
		  renderJson(result);
	  }
}
