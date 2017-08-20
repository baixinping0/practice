package com.baixinping.cvtepro.entity;

import com.baixinping.cvtepro.common.utils.format.FormatUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LogisticsModel extends BaseModel {
	  //发货单、其它出库、调拨单Code
	  private String do_code;
	  //组织名称（我放需要发货的组织名称）
	  private String organization_name;
	  //单据类型（包括发货通知使用字符串do；调拨单使用字符串allot、其它出库使用字符串delivery）
	  private String from_type;
	  //客户名称
	  private String cus_name;
	  //销售员
	  private String sales_man;
	  //出货仓库
	  private String warehouse ;
	  //提货地址
	  private String pickup_address ;
	  //销售型号
	  private String sales_model;
	  //生产批次（如果生产批次为“无”，则代表序列号为空）
	  private String batch_no ;
	  //数量
	  private int qty ;
	  //发货方式
	  private String delivery_type;
	  //发货日期
	  private Date delivery_date;
	  //是否成功撤单,1-已撤单,0-未撤单
	  private String is_revoke;
	  //类型（0表示新增，1代表已经传到且未修改，2表示修改,3表示删除，4表示追加序列号）
	  private int do_detail_type ;
	  
	  private String from_typeView;
	  private String delivery_dateView;
	  private String is_revokeView;
	  private String do_detail_typeView;
	  
	  public static Map<Integer, String> do_detail_typeMap = new HashMap<>();
	  public static final Integer DO_DETAIL_TYPE_ADD = 0;
	  public static final Integer DO_DETAIL_TYPE_NOTUPDATE = 1;
	  public static final Integer DO_DETAIL_TYPE_UPDATE = 2;
	  public static final Integer DO_DETAIL_TYPE_DELETE = 3;
	  public static final Integer DO_DETAIL_TYPE_ADDNUM = 4;
	  public static final String DO_DETAIL_TYPE_ADD_VIEW = "新增";
	  public static final String DO_DETAIL_TYPE_NOTUPDATE_VIEW = "传到且未修改";
	  public static final String DO_DETAIL_TYPE_UPDATE_VIEW = "修改";
	  public static final String DO_DETAIL_TYPE_DELETE_VIEW = "删除";
	  public static final String DO_DETAIL_TYPE_ADDNUM_VIEW = "追加序列号";
	  
	  public static Map<String, String> is_revokeMap = new HashMap<>();
	  public static final String IS_REVOKE_YES = "1";
	  public static final String IS_REVOKE_NO = "0";
	  public static final String IS_REVOKE_YES_VIEW = "已撤单";
	  public static final String IS_REVOKE_NO_VIEW = "未撤单";
	  
	  public static Map<String, String> from_typeMap = new HashMap<String, String>();
	  public static final String FROM_TYPE_DO = "do";
	  public static final String FROM_TYPE_ALLOT = "allot";
	  public static final String FROM_TYPE_DELIVERY  ="delivery";
	  public static final String FROM_TYPE_DO_VIEW = "发货通知";
	  public static final String FROM_TYPE_ALLOT_VIEW  = "调拨单";
	  public static final String FROM_TYPE_DELIVERY_VIEW   ="其它出库";
	  
	  
	  static{
		  from_typeMap.put(FROM_TYPE_DO, FROM_TYPE_DO_VIEW);
		  from_typeMap.put(FROM_TYPE_ALLOT, FROM_TYPE_ALLOT_VIEW);
		  from_typeMap.put(FROM_TYPE_DELIVERY, FROM_TYPE_DELIVERY_VIEW);
		  
		  is_revokeMap.put(IS_REVOKE_YES, IS_REVOKE_YES_VIEW);
		  is_revokeMap.put(IS_REVOKE_NO, IS_REVOKE_NO_VIEW);
		  
		  do_detail_typeMap.put(DO_DETAIL_TYPE_ADD, DO_DETAIL_TYPE_ADD_VIEW);
		  do_detail_typeMap.put(DO_DETAIL_TYPE_NOTUPDATE, DO_DETAIL_TYPE_NOTUPDATE_VIEW);
		  do_detail_typeMap.put(DO_DETAIL_TYPE_UPDATE, DO_DETAIL_TYPE_UPDATE_VIEW);
		  do_detail_typeMap.put(DO_DETAIL_TYPE_DELETE, DO_DETAIL_TYPE_DELETE_VIEW);
		  do_detail_typeMap.put(DO_DETAIL_TYPE_ADDNUM, DO_DETAIL_TYPE_ADDNUM_VIEW);
	  }
	  
	  
	  
	public String getDo_detail_typeView() {
		return do_detail_typeView;
	}
	public String getIs_revokeView() {
		return is_revokeView;
	}
	public String getDelivery_dateView() {
		return delivery_dateView;
	}
	public String getFrom_typeView() {
		return from_typeView;
	}
	public String getDo_code() {
		return do_code;
	}
	public void setDo_code(String do_code) {
		this.do_code = do_code;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public String getFrom_type() {
		return from_type;
	}
	public void setFrom_type(String from_type) {
		this.from_type = from_type;
		this.from_typeView = from_typeMap.get(from_type);
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getSales_man() {
		return sales_man;
	}
	public void setSales_man(String sales_man) {
		this.sales_man = sales_man;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getPickup_address() {
		return pickup_address;
	}
	public void setPickup_address(String pickup_address) {
		this.pickup_address = pickup_address;
	}
	public String getSales_model() {
		return sales_model;
	}
	public void setSales_model(String sales_model) {
		this.sales_model = sales_model;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getDelivery_type() {
		return delivery_type;
	}
	public void setDelivery_type(String delivery_type) {
		this.delivery_type = delivery_type;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
		this.delivery_dateView = FormatUtil.formatDateTime(delivery_date.getTime());
	}
	public String getIs_revoke() {
		return is_revoke;
	}
	public void setIs_revoke(String is_revoke) {
		this.is_revoke = is_revoke;
		this.is_revokeView = is_revokeMap.get(is_revoke);
	}
	public int getDo_detail_type() {
		return do_detail_type;
	}
	public void setDo_detail_type(int do_detail_type) {
		this.do_detail_type = do_detail_type;
		this.do_detail_typeView = do_detail_typeMap.get(do_detail_type);
	}
	  
	  
}
