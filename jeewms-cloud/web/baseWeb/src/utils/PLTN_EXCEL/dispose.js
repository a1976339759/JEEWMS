
/**
 *  检验单数据
 * @param  data 数据 
 */
import moment from 'moment'

export function JYD(data,index) {
	let objData = data.imBeizhu ? JSON.parse(data.imBeizhu) : ''
	let str = moment().format('YYYY-MM-DD')
	let orderN = 'YH'+moment().format('YYYYMMDDhhmmss') + index
	return [
        /*1*/ {
			A: '',
			B: "",
			C: "",
			D: "",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: ""
		},
        /*2*/ {
			A: '',
			B: "QC REQUEST #\r\n验货请求号",
			C: orderN,
			D: "ADD&CONTACT\r\n验货地址和联系人",
			E: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			F: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			G: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			H: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			I: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			J: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			K: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			L: "",
			M: "",
			N: ""
		},
        /*3*/ {
			A: '',
			B: "QC TYPE\r\n验货种类",
			C: data.totalamountvat,
			D: "ADD&CONTACT\r\n验货地址和联系人",
			E: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			F: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			G: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			H: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			I: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			J: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			K: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			L: "",
			M: "",
			N: ""
		},
        /*4*/ {
			A: '',
			B: "REQUESTED FOR\r\n验货的日期",
			C: data.query21,
			D: "ADD&CONTACT\r\n验货地址和联系人",
			E: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			F: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			G: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			H: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			I: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			J: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			K: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			L: "",
			M: "",
			N: ""
		},
        /*5*/ {
			A: '',
			B: "SHIPMENT DATE\r\n出货的日期",
			C: data.chukudate,
			D: "ADD&CONTACT\r\n验货地址和联系人",
			E: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			F: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			G: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			H: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			I: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			J: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			K: data.diZhi +'-'+ data.zhuLianXiRen+'-'+data.shouJi,
			L: "",
			M: "",
			N: ""
		},
        /*6*/ {
			A: '',
			B: "PURCHASER NAME\r\n采购名字",
			C: data.purchasename,
			D: "MERCHANDISER\r\n跟单",
			E: objData.query28,
			F: '',
			G: '',
			H: '',
			I: 'SALES INV NO.\r\n销售单号',
			J: data.u8ReturnCode,
			K: '',
			L: "",
			M: "",
			N: ""
		},
        /*7*/ {
			A: '',
			B: "",
			C: "",
			D: "",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: ""
		},
        /*8*/ {
			A: '',
			B: "REF\r\n号",
			C: data.contractlno,
			D: "ORDER QTY\r\n订单数量",
			E: data.goodsCount,
			F: "",
			G: "SPECIFICATIONS\r\n规格",
			H: "SPECIFICATIONS\r\n规格",
			I: "SPECIFICATIONS\r\n规格",
			J: "SPECIFICATIONS\r\n规格",
			K: "SPECIFICATIONS\r\n规格",
			L: "",
			M: "",
			N: ""
		},
        /*9*/ {
			A: '',
			B: "REF ID\r\n型号",
			C: data.goodsCode,
			D: "CATEGORY\r\n产品种类",
			E: data.chpShuXing,
			F: "",
			G: data.shpGuiGe,
			H: data.shpGuiGe,
			I: data.shpGuiGe,
			J: data.shpGuiGe,
			K: data.shpGuiGe,
			L: "",
			M: "",
			N: ""
		},
        /*10*/ {
			A: '',
			B: "CUSTOMER NAME\r\n客户名",
			C: data.cusName,
			D: "COMPANY NAME\r\n所属公司",
			E: data.xingYeFenLei,
			F: "",
			G: 'ADDITIONAL NOTES：其他的备注',
			H: 'ADDITIONAL NOTES：其他的备注',
			I: 'ADDITIONAL NOTES：其他的备注',
			J: 'ADDITIONAL NOTES：其他的备注',
			K: 'ADDITIONAL NOTES：其他的备注',
			L: "",
			M: "",
			N: ""
		},
        /*11*/ {
			A: '',
			B: "PRODUCT NAME\r\n产品名",
			C: data.goodsName,
			D: "",
			E: "",
			F: "",
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*12*/ {
			A: '',
			B: "VENDOR\r\n供应商",
			C: data.supName,
			D: "",
			E: "",
			F: "",
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*13*/ {
			A: '',
			B: "L3PO CTN MARK\r\n系统的箱麦",
			C: "",
			D: "",
			E: "",
			F: "",
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*14*/ {
			A: '',
			B: "BUYERS INSTRUCTIONS:采购验货指示",
			C: "BUYERS INSTRUCTIONS:采购验货指示",
			D: "BUYERS INSTRUCTIONS:采购验货指示",
			E: "BUYERS INSTRUCTIONS:采购验货指示",
			F: "BUYERS INSTRUCTIONS:采购验货指示",
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*15*/ {
			A: data.remark,
			B: data.remark,
			C: data.remark,
			D: data.remark,
			E: data.remark,
			F: data.remark,
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*16*/ {
			A: '',
			B: '',
			C: '',
			D: '',
			E: '',
			F: '',
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*17*/ {
			A: '',
			B: '',
			C: '',
			D: '',
			E: '',
			F: '',
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*18*/ {
			A: '',
			B: '',
			C: '',
			D: '',
			E: '',
			F: '',
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*19*/ {
			A: '',
			B: '',
			C: '',
			D: '',
			E: '',
			F: '',
			G: data.shpmiaoshu,
			H: data.shpmiaoshu,
			I: data.shpmiaoshu,
			J: data.shpmiaoshu,
			K: data.shpmiaoshu,
			L: "",
			M: "",
			N: ""
		},
        /*20*/ {
			A: '',
			B: "",
			C: "",
			D: "",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: ""
		},
        /*21*/ {
			A: '',
			B: "INSPECTION DATE\r\n验货日期",
			C: "年            月            日",
			D: "TOTAL HOURS\r\n时间",
			E: "H",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: ""
		},
        /*22*/ {
			A: '',
			B: "QTY CHECKED\r\n验货数量",
			C: "",
			D: "BAD PCS\r\n不良品数量",
			E: "             个           %",
			F: "",
			G: "GOOD PCS\r\n良品数量",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: ""
		},
        /*23*/ {
			A: '',
			B: "OBSERVATIONS\r\n备注",
			C: "DESCRIPTION\r\n描述",
			D: "MINOR\r\n可以接受的",
			E: "MAJOR\r\n不可以接受的",
			F: "",
			G: "............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG \r\n............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			H: "............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG \r\n............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			I: "............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG \r\n............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			J: "............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG \r\n............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			K: "............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG \r\n............. CTN         .......................... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			L: "",
			M: "",
			N: ""
		},
        /*24*/ {
			A: '',
			B: "#1",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "SPL PROVIDED OR NOT\r\n验货样板有没有",
			H: "SPL PROVIDED OR NOT\r\n验货样板有没有",
			I: "✓        ✘",
			J: "BRAND / SPECIAL ITEM CHECK 是不是品牌？是不是特殊项目？",
			K: "⚠",
			L: "",
			M: "",
			N: ""
		},
        /*25*/ {
			A: '',
			B: "#2",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "WORDING CHECK\r\n文字对还是没有",
			H: "WORDING CHECK\r\n文字对还是没有",
			I: "✓        ✘",
			J: "COMPOSITION LABEL\r\n产品是不是有材质标",
			K: "✓        ✘",
			L: "",
			M: "",
			N: ""
		},
        /*26*/ {
			A: '',
			B: "#3",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "QC PICTURE SENTQC\r\n图片已发",
			H: "QC PICTURE SENTQC\r\n图片已发",
			I: "✓        ✘",
			J: "PROTECTION\r\n纸箱是否能很好地保护货物",
			K: "✓        ✘",
			L: "",
			M: "",
			N: ""
		},
        /*27*/ {
			A: '',
			B: "#4",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "SALES PICTURE SENT\r\n销售图片已发",
			H: "SALES PICTURE SENT\r\n销售图片已发",
			I: " ✓ ",
			J: "NO CHINESE\r\n必须没有中文",
			K: " ✓ ",
			L: "",
			M: "",
			N: ""
		},
        /*28*/ {
			A: '',
			B: "#5",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			H: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			I: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			J: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			K: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			L: "",
			M: "",
			N: ""
		},
        /*29*/ {
			A: '',
			B: "#6",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			H: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			I: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			J: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			K: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和签名",
			L: "",
			M: "",
			N: ""
		},
        /*30*/ {
			A: '',
			B: "#7",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "BUYER CONFIRMATION\r\n采购什么时候确认",
			H: "BUYER CONFIRMATION\r\n采购什么时候确认",
			I: "BUYER CONFIRMATION\r\n采购什么时候确认",
			J: "BUYER CONFIRMATION\r\n采购什么时候确认",
			K: "BUYER CONFIRMATION\r\n采购什么时候确认",
			L: "",
			M: "",
			N: ""
		},
        /*31*/ {
			A: '',
			B: "#8",
			C: "",
			D: "个           %",
			E: "个           %",
			F: "",
			G: "BUYER CONFIRMATION\r\n采购什么时候确认",
			H: "BUYER CONFIRMATION\r\n采购什么时候确认",
			I: "BUYER CONFIRMATION\r\n采购什么时候确认",
			J: "BUYER CONFIRMATION\r\n采购什么时候确认",
			K: "BUYER CONFIRMATION\r\n采购什么时候确认",
			L: "",
			M: "",
			N: ""
		}
	]
}

/**
 *  包装领料单样式
 * @param data 数据
 */
export function LLD(data) {
	console.log(data);
	let arr = []
	for(let i = 0;i < 5;i++){
		arr.push({
			A: '',
			B: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsBatch:'',
			C: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsId:'',
			D: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsQua:'',
			E: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsName:'',
			F: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsName:'',
			G: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsName:'',
			H: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsName:'',
			I: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsName:'',
			J: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].goodsName:'',
			K: data.wmOmNoticeIList[i]?data.omBeizhu:'',
			L: data.wmOmNoticeIList[i]?data.omBeizhu:'',
			M: data.wmOmNoticeIList[i]?data.omBeizhu:'',
			N: data.wmOmNoticeIList[i]?data.omBeizhu:'',
			O: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].classification:'',
			P: data.wmOmNoticeIList[i]?data.wmOmNoticeIList[i].classification:'',
		})
	}
	return [
	/*1*/{
			A: '',
			B: "",
			C: "",
			D: "",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: "",
			O: "",
			P: "",
		},
		/*2*/{
			A: '',
			B: "SO\r\n生产单号",
			C: data.remarks,
			D: "QTY PACKED\r\n包装总数量",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "CTN DETAILS\r\n每箱数量",
			M: "CTN DETAILS\r\n每箱数量",
			N: "CTN DETAILS\r\n每箱数量",
			O: "CTN DETAILS\r\n每箱数量",
			P: "CTN DETAILS\r\n每箱数量",
		},
		/*3*/{
			A: '',
			B: "REQUEST TYPE\r\n请求种类",
			C: "RPK repack",
			D: "HOURS\r\n消耗时间",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			M: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			N: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			O: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			P: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
		},
		/*4*/{
			A: '',
			B: "REQUESTED FOR\r\n采购要包装的日期",
			C: data.packingDate,
			D: "UNIT COST\r\n单价",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			M: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			N: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			O: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			P: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
		},
		/*5*/{
			A: '',
			B: "DEADLINE\r\n期限",
			C: data.stopDate,
			D: "PROTRCTION\r\n包好了",
			E: "NO CHINESE\r\n没有中文",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			M: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			N: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			O: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			P: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
		},
		/*6*/{
			A: '',
			B: "PURCHASER NAME\r\n采购名字",
			C: data.createBy,
			D: "✓        ✘",
			E: "✓        ✘",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			M: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			N: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			O: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
			P: "........................................ CTN   .............................. PCS/CTN\r\n...................... x  ..................... x  .................... CM ................ KG",
		},
		/*7*/{
			A: '',
			B: "FINISHED OUTPUT REQUESTED",
			C: "FINISHED OUTPUT REQUESTED",
			D: "FINISHED OUTPUT REQUESTED",
			E: "FINISHED OUTPUT REQUESTED",
			F: "FINISHED OUTPUT REQUESTED",
			G: "FINISHED OUTPUT REQUESTED",
			H: "FINISHED OUTPUT REQUESTED",
			I: "FINISHED OUTPUT REQUESTED",
			J: "FINISHED OUTPUT REQUESTED",
			K: "FINISHED OUTPUT REQUESTED",
			L: "FINISHED OUTPUT REQUESTED",
			M: "FINISHED OUTPUT REQUESTED",
			N: "FINISHED OUTPUT REQUESTED",
			O: "FINISHED OUTPUT REQUESTED",
			P: "FINISHED OUTPUT REQUESTED",
		},
		/*8*/{
			A: '',
			B: "SKU \r\n型号",
			C: "QTY TO PACK\r\n包装数量",
			D: "FINISHED PRODUCT NAME\r\n成品名",
			E: "FINISHED PRODUCT NAME\r\n成品名",
			F: "FINISHED PRODUCT NAME\r\n成品名",
			G: "FINISHED PRODUCT NAME\r\n成品名",
			H: "FINISHED PRODUCT NAME\r\n成品名",
			I: "FINISHED PRODUCT NAME\r\n成品名",
			J: "FINISHED PRODUCT NAME\r\n成品名",
			K: "CUSTOMER NAME\r\n客户名",
			L: "CUSTOMER NAME\r\n客户名",
			M: "CUSTOMER NAME\r\n客户名",
			N: "CUSTOMER NAME\r\n客户名",
			O: "CATEGORY\r\n产品种类",
			P: "CATEGORY\r\n产品种类",
		},
		/*9*/{
			A: '',
			B: data.goodsId,
			C: data.zuixiaoshul,
			D: data.goodsName,
			E: data.goodsName,
			F: data.goodsName,
			G: data.goodsName,
			H: data.goodsName,
			I: data.goodsName,
			J: data.goodsName,
			K: data.omBeizhu,
			L: data.omBeizhu,
			M: data.omBeizhu,
			N: data.omBeizhu,
			O: data.classification,
			P: data.classification,
		},
		/*10*/{
			A: '',
			B: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			C: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			D: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			E: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			F: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			G: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			H: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			I: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			J: "BUYERS INSTRUCTIONS\r\n采购验货指示",
			K: "CTN MARK\r\n箱麦",
			L: "CTN MARK\r\n箱麦",
			M: "CTN MARK\r\n箱麦",
			N: "CTN MARK\r\n箱麦",
			O: "CTN MARK\r\n箱麦",
			P: "CTN MARK\r\n箱麦",
		},
		/*11*/{
			A: '',
			B: data.reMember,
			C: data.reMember,
			D: data.reMember,
			E: data.reMember,
			F: data.reMember,
			G: data.reMember,
			H: data.reMember,
			I: data.reMember,
			J: data.reMember,
			K: data.reMobile,
			L: data.reMobile,
			M: data.reMobile,
			N: data.reMobile,
			O: data.reMobile,
			P: data.reMobile,
		},
		/*12*/{
			A: '',
			B: "子PO",
			C: "SKU \r\n产品编码",
			D: "QTY NEEDED\r\n需要的数量",
			E: "SEMI-FINISHED PRODUCT NAME\r\n半成品名",
			F: "SEMI-FINISHED PRODUCT NAME\r\n半成品名",
			G: "SEMI-FINISHED PRODUCT NAME\r\n半成品名",
			H: "SEMI-FINISHED PRODUCT NAME\r\n半成品名",
			I: "SEMI-FINISHED PRODUCT NAME\r\n半成品名",
			J: "SEMI-FINISHED PRODUCT NAME\r\n半成品名",
			K: "CUSTOMER NAME\r\n客户名",
			L: "CUSTOMER NAME\r\n客户名",
			M: "CUSTOMER NAME\r\n客户名",
			N: "CUSTOMER NAME\r\n客户名",
			O: "CATEGORY\r\n产品种类",
			P: "CATEGORY\r\n产品种类",
		},
		...arr,
		/*18*/{
			A: '',
			B: "",
			C: "",
			D: "",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: "",
			O: "",
			P: "",
		},
		/*19*/{
			A: '',
			B: "REF\r\n项目编号",
			C: "QTY PREPARED\r\n总数量",
			D: "CTN DETAILS\r\n每箱数量",
			E: "CTN DETAILS\r\n每箱数量",
			F: "CTN DETAILS\r\n每箱数量",
			G: "PCS BAD\r\n不良品",
			H: "PCS GOOD\r\n良品",
			I: "QTY USED\r\n使用数量",
			J: "QTY LEFT\r\n剩余数量",
			K: "CTN DETAILS\r\n每箱多少个尺寸重量",
			L: "CTN DETAILS\r\n每箱多少个尺寸重量",
			M: "CTN DETAILS\r\n每箱多少个尺寸重量",
			N: "CTN DETAILS\r\n每箱多少个尺寸重量",
			O: "CATEGORY\r\n产品种类",
			P: "CATEGORY\r\n产品种类",
		},
		/*20*/{
			A: '',
			B: "",
			C: "",
			D: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			E: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			F: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			G: "个",
			H: "个",
			I: "个",
			J: "个",
			K: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			L: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			M: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			N: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			O: "✓",
			P: "✓",
		},
		/*21*/{
			A: '',
			B: "",
			C: "",
			D: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			E: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			F: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			G: "个",
			H: "个",
			I: "个",
			J: "个",
			K: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			L: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			M: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			N: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			O: "✓",
			P: "✓",
		},
		/*22*/{
			A: '',
			B: "",
			C: "",
			D: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			E: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			F: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			G: "个",
			H: "个",
			I: "个",
			J: "个",
			K: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			L: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			M: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			N: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			O: "✓",
			P: "✓",
		},
		/*23*/{
			A: '',
			B: "",
			C: "",
			D: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			E: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			F: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			G: "个",
			H: "个",
			I: "个",
			J: "个",
			K: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			L: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			M: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			N: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			O: "✓",
			P: "✓",
		},
		/*24*/{
			A: '',
			B: "",
			C: "",
			D: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			E: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			F: ".......... CTN                            .................. PCS/CTN\r\n.......... CTN                            .................. PCS/CTN",
			G: "个",
			H: "个",
			I: "个",
			J: "个",
			K: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			L: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			M: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			N: "............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG\r\n............. CTN        ............... PCS/CTN      .......... x  .......... x  .......... CM      .......... KG",
			O: "✓",
			P: "✓",
		},
		/*25*/{
			A: '',
			B: "",
			C: "",
			D: "",
			E: "",
			F: "",
			G: "",
			H: "",
			I: "",
			J: "",
			K: "",
			L: "",
			M: "",
			N: "",
			O: "",
			P: "",
		},
		/*26*/{
			A: '',
			B: "QCT\r\nQCT号",
			C: "PACKING DATE\r\n包装日期",
			D: "OBSERVATIONS\r\n备注",
			E: "OBSERVATIONS\r\n备注",
			F: "OBSERVATIONS\r\n备注",
			G: "TOTAL HOURS\r\n时间",
			H: "QTY PACKED\r\n包装数量",
			I: "TOTAL PACKED\r\n包装总数",
			J: "LEFT TO PACK\r\n剩余",
			K: "",
			L: "",
			M: "",
			N: "",
			O: "",
			P: "",
		},
		/*27*/{
			A: '',
			B: "",
			C: "年      月      日 ",
			D: "",
			E: "",
			F: "",
			G: "H",
			H: "套",
			I: "套",
			J: "套",
			K: "",
			L: "",
			M: "",
			N: "",
			O: "",
			P: "",
		},
		/*28*/{
			A: '',
			B: "",
			C: "年      月      日 ",
			D: "",
			E: "",
			F: "",
			G: "H",
			H: "套",
			I: "套",
			J: "套",
			K: "",
			L: "SPL PROVIDED\r\n验货样板有没有",
			M: "✓        ✘",
			N: "WORDING CHECK\r\n文字对还是没有",
			O: "✓        ✘",
			P: "✓        ✘",
		},
		/*29*/{
			A: '',
			B: "",
			C: "年      月      日 ",
			D: "",
			E: "",
			F: "",
			G: "H",
			H: "套",
			I: "套",
			J: "套",
			K: "",
			L: "QC PICTURE SENTQC\r\n图片已发",
			M: "✓",
			N: "BRAND / TYPE CHECK\r\n是不是品牌/特殊项目？",
			O: "⚠",
			P: "⚠",
		},
		/*30*/{
			A: '',
			B: "",
			C: "年      月      日 ",
			D: "",
			E: "",
			F: "",
			G: "H",
			H: "套",
			I: "套",
			J: "套",
			K: "",
			L: "SALES PICTURE SENT\r\n销售图片已发",
			M: "✓",
			N: "CARTON MARK CHECK\r\n箱麦文字对不对",
			O: "✓",
			P: "",
		},
		/*31*/{
			A: '',
			B: "",
			C: "年      月      日 ",
			D: "",
			E: "",
			F: "",
			G: "H",
			H: "套",
			I: "套",
			J: "套",
			K: "",
			L: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和员签名",
			M: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和员签名",
			N: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和员签名",
			O: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和员签名",
			P: "INSPECTOR COMMENTS & SIGNATURE\r\n验货员评语和员签名",
		},
		/*32*/{
			A: '',
			B: "",
			C: "年      月      日 ",
			D: "",
			E: "",
			F: "",
			G: "H",
			H: "套",
			I: "套",
			J: "套",
			K: "",
			L: "BUYER CONFIRMATION\r\n采购什么时候确认",
			M: "BUYER CONFIRMATION\r\n采购什么时候确认",
			N: "BUYER CONFIRMATION\r\n采购什么时候确认",
			O: "BUYER CONFIRMATION\r\n采购什么时候确认",
			P: "BUYER CONFIRMATION\r\n采购什么时候确认",
		},

	]
}


/**
 *  装箱单
 * @param data 数据
 */
export function ZXD(data) {
	return {
		omBeiZhu: 'omBeiZhu',
		state: 'state',
		xianghao: 'xianghao',
		sell: 'sell',
		cusName: 'cusName',
		// shipdepcode: 'shipdepcode',
		shipdepname: 'shipdepname',
		goodsId: 'goodsId',
		goodsName: 'goodsName',
		u8ReturnCode: 'u8ReturnCode',
		shippingmark: 'shippingmark',
		baseGoodscount: 'baseGoodscount',
		qtypercarton: 'qtypercarton',
		totalqtypercarton: 'totalqtypercarton',
		createBy: 'createBy',
		createName: 'createName',
		goodsUnitPrice: 'goodsUnitPrice',
		contractlno: 'contractlno',
		astreanum: 'astreanum',
		goodsjianchen: 'goodsjianchen',
		goodsguige: 'goodsguige',
		goodsTypeName: 'goodsTypeName',
		goodsTypeEnname: 'goodsTypeEnname',
		shpYanEnse: 'shpYanEnse',
		ctnsizel: 'ctnsizel',
		ctnsizew: 'ctnsizew',
		ctnsizeh: 'ctnsizeh',
		weightctn: 'weightctn',
		wolctn: 'wolctn',
		totalvol: 'totalvol',
		totalweight: 'totalweight',
		taxrefund: 'taxrefund',
		billingproductname: 'billingproductname',
		billingproductunit: 'billingproductunit',
		// shiporgcode:'shiporgcode',
		salespricewvat: 'salespricewvat',
		salespricecur: 'salespricecur',
		totalamount: 'totalamount',
		ladingname: 'ladingname',
		logo: 'logo',
		hscode: 'hscode',
		usagess: 'usagess',
		omBeiZhu: 'omBeiZhu',
		declarationelements: 'declarationelements',
	}
}