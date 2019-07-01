package com.fxz.app.View;
import com.fxz.app.ConsoleUtil;
import com.fxz.app.model.*;

public class HousingLoanView {
	public static void paint() {
		//1.欢迎语
		System.out.println("欢迎使用房贷计算器！！");
		//2.获取用户输入
		//2.1获取用户贷款方式
		int userWayToInterestRase = getWayToInterestRase();
		//2.2 获取本金
		double userLoanPrincipal = getLoanPrincipal();
		//2.3获取还款月数
		int userRepaymentMonth = getRepaymentMonth();
		//2.4获取已还款金额
		double userRepaymentMoney = getRepaymentMoney();
		//2.5获取月利率
		double userMonthInterestRase = getMonthInterestRase();
		//3.调用业务模型获得业务结果
		try {
			double result = getMonthrepayMoney(userWayToInterestRase,userLoanPrincipal,userRepaymentMonth,userMonthInterestRase,userRepaymentMoney);
			System.out.println("您每月需要还款金额为："+result);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("欢迎下次使用");
		}
		//4.将业务结果展示给用户
	}
	
	/**
	 * 获取用户输入本金
	 * @return double型的loanPrincipal
	 */
	public static double getLoanPrincipal() {
		final double defaultloanPrincipal = 10000;
		double userInput = ConsoleUtil.readDouble("请输入您的贷款本金（单位：元）："+defaultloanPrincipal+">", defaultloanPrincipal);
		while(userInput <= 0){
			userInput = ConsoleUtil.readDouble("非法输入，请重新输入："+defaultloanPrincipal+">", defaultloanPrincipal);
		}
		return userInput;
	}
	/**
	 * 获取用户输入还款月数
	 * @return double型的repaymentMonth
	 */
	public static int getRepaymentMonth() {
		final int defaultloanPrincipal = 12;
		int userInput = ConsoleUtil.readInt("请输入您的还款月数："+defaultloanPrincipal+">", defaultloanPrincipal);
		while(userInput <= 0 || userInput >360){
			userInput = ConsoleUtil.readInt("非法输入，请重新输入："+defaultloanPrincipal+">", defaultloanPrincipal);
		}
		return userInput;
	}
	/**
	 * 获取用户输入已还款金额
	 * @return double型的repaymentMoney
	 */
	public static double getRepaymentMoney() {
		final double defaultloanPrincipal = 0;
		double userInput = ConsoleUtil.readDouble("请输入您的已还款金额（单位：元）："+defaultloanPrincipal+">", defaultloanPrincipal);
		while(userInput < 0){
			userInput = ConsoleUtil.readDouble("非法输入，请重新输入："+defaultloanPrincipal+">", defaultloanPrincipal);
		}
		return userInput;
	}
	/**
	 * 获取用户输入月利率
	 * @return double型的monthInterestRase
	 */
	public static double getMonthInterestRase() {
		final double defaultloanPrincipal = 0.004;
		double userInput = ConsoleUtil.readDouble("请输入您的月利率："+defaultloanPrincipal+">", defaultloanPrincipal);
		while(userInput <= 0){
			userInput = ConsoleUtil.readDouble("非法输入，请重新输入："+defaultloanPrincipal+">", defaultloanPrincipal);
		}
		return userInput;
	}
	/**
	 * 获取用户输入的还款方式
	 * 等额本息：1
	 * 等额本金：2
	 * @return double型的monthInterestRase
	 */
	public static int getWayToInterestRase() {
		final int defaultloanPrincipal = 1;
		int userInput = ConsoleUtil.readInt("请输入您的还款方式："+defaultloanPrincipal+">", defaultloanPrincipal);
		while(userInput < 1 || userInput > 3){
			userInput = ConsoleUtil.readInt("非法输入，请重新输入："+defaultloanPrincipal+">", defaultloanPrincipal);
		}
		return userInput;
	}
	
	/**
	 * 通过还款方式取得还款金额
	* @param wayToInterestRase 还款方式
	* @param loanPrincipal 贷款本金
	* @param repaymentMonth 还款月数
	* @param monthInterestRase 月利率
	* @param repaymentMoney 已还款金额
	* @return monthrepayMoney 每月还款金额
	*/
	public static double getMonthrepayMoney(int wayToInterestRase,double loanPrincipal,int repaymentMonth,double monthInterestRase,double repaymentMoney) {
		double monthrepayMoney;
		if(wayToInterestRase == 1) {
			monthrepayMoney = HousingLoanModel.reserveNumber(HousingLoanModel.equivalentPrincipalAndInterest(loanPrincipal, repaymentMonth, monthInterestRase));
		}else {
			monthrepayMoney = HousingLoanModel.reserveNumber(HousingLoanModel.equivalentPrincipal(loanPrincipal, repaymentMonth, monthInterestRase,repaymentMoney));
		}
		return monthrepayMoney;
	}
	
}
	