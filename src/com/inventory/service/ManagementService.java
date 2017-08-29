package com.inventory.service;

import java.util.List;

import com.jfinal.plugin.activerecord.*;

/**
 * 进销存管理
 * @return
 */
public class ManagementService {
	public void index(){
		
	}
	//增加计量单位
	public boolean add(String unitname, String introduction){
		Record unit = new Record().set("unitname", unitname).set("introduction", introduction);
		Db.save("units", unit);
		return true;
	}
	//删除计量单位
	public boolean deleteUnit(Integer unitId){
		Db.deleteById("units", unitId);
		return true;
	}
	//查询计量单位
	public List<Record> search(){
		List<Record> units = Db.find("select * from units");
		return units;
	}
	//增加商品类别
	public boolean addCategoryByName(String categoryName){
		Record category = new Record().set("categoryName", categoryName);
		Db.save("category", category);
		return true;
	}
	public List<Record> searchCategory(){
		List<Record> category = Db.find("select * from category");
		return category;
	}
	public boolean deleteCategoryById(Integer id){
		Db.deleteById("category", id);
		return true;
	}
	public boolean addCommodity(String commodityId,String name ,String imeiNumber,String modelNumber,int categoryId,int unitId,String place,int price,int purchasingPrice,int amount,String supplier,String createTime,String brand,int createId,String illustrate){
		Record commodity = new Record().set("commodityId", commodityId).set("name", name).set("categoryId", categoryId).set("unitId", unitId).set("place", place).set("price", price).set("purchasingPrice", purchasingPrice).set("amount", amount).set("supplier", supplier).set("createTime", createTime).set("brand", brand).set("createId", createId).set("illustrate", illustrate);
		Db.save("commodity", commodity);
		return true;
	}
	public List<Record> searchCommodityByName(String name){
		List<Record> commodity;
		if(name!=""){
			commodity = Db.find("select * from commodity where name=" + name);
		} else {
			commodity = Db.find("select * from commodity");
		}
		return commodity;
	}
	public boolean deleteCommodityById(Integer id){
		Db.deleteById("commodity", id);
		return true;
	}
	public boolean getSameId(String tablename,String id){
		List<Record> result;
		String sql ="select * from "+tablename+" where " + tablename +"_id = ";
		result = Db.find(sql + id);
		if(result.isEmpty()){//数据库没有此id，则返回true
			return true;
		}else {
			return false;
		}
	}
	public List<Record> searchRowById(String tablename,String id){
		List<Record> result;
		if(id!=null){
			result= Db.find("select * from "+ tablename+" where "+tablename+"_id = " + id);
		} else {
			result= Db.find("select * from "+tablename);
		}
		return result;
	}
	public boolean deleteRowById(String table,String id){
		try{
		Db.deleteById(table, id);
			return true;
		} catch(Exception e){
			return false;
		}
	}
}
