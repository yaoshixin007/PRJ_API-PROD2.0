package com.jci.util;

public class ApplicationConstants {

	public static final int PAGINATION_OFFSET = 0;
	public static final int PAGINATION_LIMIT = 100;
	
	public static final String DATE_FORMAT_YYYY_MM_DD = "YYYY-MM-dd";
	
	public static final String MESSAGE_ID = "messageId";
	public static final String CHANNEL = "channel";
	
	public static final String EXCEP_FIELD_MATERIAL_NUM = "materialNum";
	public static final String EXCEP_TYPE_MATERIAL_NUM = "Array of Strings";
	
	public static final String EXCEP_FIELD_CMRL_NUM = "prdCmrlNum";
	public static final String EXCEP_TYPE_CMRL_NUM = "Array of Strings";
	
	public static final String EXCEP_FIELD_BATTERY_SN = "serailNo";
	public static final String EXCEP_TYPE_BATTERY_SN = "Array of String";
	
	public static final String PRD_EXCEP_VALIDATION_MESSAGE = "Parameter Error : Material Number should be numeric ";
	
	public static final String CMRL_PRD_EXCEP_VALIDATION_MESSAGE = "Parameter Error : Product Commercial Number should be numeric ";
	
	public static final String EXCEP_FIELD_BUSINESS_PARTNER_MDG = "mdgCustKey";
	public static final String EXCEP_TYPE_BUSINESS_PARTNER_MDG = "List of Strings";
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String AUTHENTICATION_SCHEME = "Bearer";
	
	public final class RestEndPoints {

		private RestEndPoints() {

		}// Hide to initiate

		public static final String MAPPING_GET_QADPRODUCTS_NUM = "/QADProducts/{materialNum}";
		public static final String MAPPING_GET_QADPRODUCTS = "/QADProducts";

		public static final String MAPPING_GET_MDGBUSINESSPARTNER = "/MDGBusinessPartner";
		
		public static final String MAPPING_GET_BATTERY_SN = "/BatterySN";
		
		public static final String MAPPING_BATTERY_SN_OPERATION = "/BatterySNOperation";
		
		//Version 2
		public static final String MAPPING_GET_PACKAGE_SN = "/PackageSN";
		public static final String MAPPING_GET_DUAL_SN = "/DualSN";		
		public static final String MAPPING_GET_MDGBUSINESSPARTNER_V2 = "/V2/filters/MDGBusinessPartner";
		public static final String MAPPING_GET_QADPRODUCTS_V2 = "/V2/filters/QADProducts/QADProductsGenericFilters";
		public static final String MAPPING_GET_CMRL_PRODUCTS = "/CMRLProducts";	
		
		//Authorization
		public static final String MAPPING_GET_PERMISSION = "/activity-permissions/roles";
		
	}

	public static final String SERIAL_NO = "serialNo";
	public static final String BATTERY_OPERATION_SERVICE = "batteryOperationService";
	

	public static final int DBSERIALNOLENGTH = 20;
	public static final String REQUESTPARAMPATTERN = "-?\\d+(\\.\\d+)?";
	public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
	
	public static final int DATEFIELDLENGTH = 8;
	public static final String QUERY_BUILDER_AND = " AND ";
	public static final String QUERY_BUILDER_OR = " OR ";
	public static final String QUERY_BUILDER_SELECT = "SELECT * FROM ";
	public static final String QUERY_BUILDER_WHERE = " WHERE ";
	public static final String QUERY_BUILDER_GREATER_THAN_EQUALS_TO = " >= ";
	public static final String QUERY_BUILDER_LESS_THAN_EQUALS_TO = " <= ";
	public static final String QUERY_BUILDER_SINGLE_QUOTE = "'";
	
	public final class ProductQAD {

		private ProductQAD() {

		}
		
		public static final String TABLE_NAME = "MTRL_DIM_CN";
		public static final String MTRL_QAD_NBR = "MTRL_QAD_NBR";
		public static final String MTRL_PLM_NBR = "MTRL_PLM_NBR";
		public static final String PRD_STAT_PLM = "PRD_STAT_PLM";
		public static final String PRD_STAT_QAD = "PRD_STAT_QAD";
		public static final String PRD_LN_CDE_CN = "PRD_LN_CDE_CN";
		public static final String PRD_BRND_CDE_CN = "PRD_BRND_CDE_CN";
		public static final String GRP_SIZE_CDE_CN = "GRP_SIZE_CDE_CN";
		public static final String MTRL_CMRL_NBR = "MTRL_CMRL_NBR";
		public static final String PLM_QAD_SYNC_DATE = "PLM_QAD_SYNC_DATE";
		public static final String PRD_EXP_DATE = "PRD_EXP_DATE";
		public static final String PRD_ACTIVE_DATE = "PRD_ACTIVE_DATE";
		public static final String LAST_MDFD_DATE_QAD = "LAST_MDFD_DATE_QAD";
	}
	
	public final class BusinessPartnerMDG {

		private BusinessPartnerMDG() {

		}
		
		public static final String TABLE_NAME = "BUS_PRTNR_DIM_CN";
		public static final String MDG_CUST_KEY = "MDG_CUST_KEY";
		public static final String BUS_PRTNR_STAT = "BUS_PRTNR_STAT";
		public static final String BUS_PRTNR_TYP = "BUS_PRTNR_TYP";
		public static final String BUS_PRTNR_CDE = "BUS_PRTNR_CDE";
		public static final String BUS_PRTNR_CNTRY_CDE = "BUS_PRTNR_CNTRY_CDE";
	}
	
	
}
