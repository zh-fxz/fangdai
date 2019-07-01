package com.fxz.app.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HousingLoanModel {
	//1根据不同类型分别计算房贷
	//1.1 等额本金：每月还款金额 = （贷款本金 / 还款月数）+（本金- 已归还本金累计额）×每月利率
	//1.1.1限制输入贷款各项参数条件
	/**
	 * 验证参数
	 * @param loanPrincipal 贷款本金
	 * @param repaymentMonth 还款月数
	 * @param repaymentMoney 已还款金额
	 * @param monthInterestRase 月利率
	 * @throws IllegalArgumentException 如果贷款本金小于等于0或为无穷或者NaN
	 * @throws IllegalArgumentException 如果还款月份小于0
	 * @throws IllegalArgumentException 如果月利率小于等于0或者大于1
	 * @throws IllegalArgumentException 如果已还款金额小于0或为无穷或者NaN
	 */
	private static void validateInput(double loanPrincipal,int repaymentMonth,double repaymentMoney,double monthInterestRase) {
		if(loanPrincipal <= 0 || Double.isNaN(loanPrincipal)||Double.isInfinite(loanPrincipal)) {
			throw new IllegalArgumentException("贷款本金不能小于等于0或者无穷或者NaN");
		}
		if(repaymentMonth <= 0) {
			throw new IllegalArgumentException("还款月份不能小于等于0");
		}
		if(reserveNumber(monthInterestRase) < 0 || reserveNumber(monthInterestRase) > 1) {
			throw new IllegalArgumentException("还款月利率不能小于等于0或者大于1");
		}
		if(repaymentMoney < 0 || Double.isNaN(repaymentMoney)||Double.isInfinite(repaymentMoney)){
			throw new IllegalArgumentException("已还款金额不能小于0或者无穷或者NaN");
		}
	}
	//1.2 计算还款金额
	//1.2.1  等额本息：每月还款金额=〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷ {〔（1＋月利率）＾还款月数 }－1〕
	/**
	 * 计算等额本息
	* @param loanPrincipal 贷款本金
	* @param repaymentMonth 还款月数
	* @param monthInterestRase 月利率
	* @return monthrepayMoney 每月还款金额
	*/
	public static double equivalentPrincipalAndInterest(double loanPrincipal,int repaymentMonth,double monthInterestRase){
		validateInput(loanPrincipal,repaymentMonth,0,monthInterestRase);
		double monthrepayMoney;
		double getNum = Math.pow((1+monthInterestRase),repaymentMonth);
		monthrepayMoney = (loanPrincipal*monthInterestRase*getNum)/(getNum-1);
		return monthrepayMoney;
	}
	//1.2.2  等额本金：每月还款金额 = （贷款本金 / 还款月数）+（本金 - 已归还本金累计额）×每月利率
	/**
	 * 计算等额本金
	* @param loanPrincipal 贷款本金
	* @param repaymentMonth 还款月数
	* @param monthInterestRase 月利率
	* @param repaymentMoney 已还款金额
	* @return monthrepayMoney 每月还款金额
	*/
	public static double equivalentPrincipal(double loanPrincipal,int repaymentMonth,double monthInterestRase,double repaymentMoney){
		validateInput(loanPrincipal,repaymentMonth,repaymentMoney,monthInterestRase);
		double monthrepayMoney;
		monthrepayMoney = (loanPrincipal/repaymentMonth)+(loanPrincipal-repaymentMoney)*monthInterestRase;
		return monthrepayMoney;
	}
	/**
	 * 保留double后两位小数
	 * @param 一个double型的数
	 * @return 计算后返回的数字
	 */
	public static double reserveNumber(double doubleNumber) {
		BigDecimal reserveNumber = new BigDecimal(doubleNumber);
		doubleNumber = reserveNumber.setScale(2, RoundingMode.UP).doubleValue();
		return doubleNumber;
	}

}
