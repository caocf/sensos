/**
 *  Sensos IoT Platform.
 *  Copyright (C) 2015 sensos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sensos.bo.om;

import java.sql.Date;

/**
 * Logical name : CustomerAccount Physical name: LE_CTAC A charge account or
 * other accounting relationship a customer has with the store or enterprise. An
 * account exists to allow the store to record a series of transactions with the
 * same customer and keep an ongoing record of monies owed by the customer and
 * monies due to the customer. CustomerAccount is subtyped into a number of
 * different kinds of accounts such as loyalty account, installment account,
 * etc. Each has additional attributes required to describe the reponsibilities
 * and obligations associated with each account type.
 * 
 * @author sensos
 *
 */
public class CustomerAccount {

	/**
	 * CustomerAccountID (PK) A unique identifier for a customer account.
	 * ID_CTAC Identity integer
	 */
	long customerAccountID;

	/**
	 * FinancialLedgerAccountID (FK) The reference for the
	 * FinancialLedgeraAccount ID_ACNT_LDG Identity integer
	 * FinancialLedgerAccount (CO_LE_ACNT_FN)
	 */
	int financialLedgerAccountID;

	/**
	 * CustomerAccountTypeCode Determines what kind of customer account this is.
	 * Examples include a trade account, a school account, a not-for-profit,
	 * regular customer, etc. LOYALTY - customer loyalty account for tracking
	 * points earned/redeemed TRADE - customer business credit account
	 * CONSUMER_CHG - customer individual consumer credit extended by retailer
	 * INSTALLMENT - credit account set up for one time purchase LAYAWAY - cash
	 * time payment account (not credit) RENTAL - customer rental for videos,
	 * equipment TY_CTAC Code2 char(2)
	 */
	String customerAccountTypeCode;

	/**
	 * CustomerAccountName Short description for the Customer Account. NM_CTAC
	 * Name varchar(40)
	 */
	String customerAccountName;

	/**
	 * CustomerIdentityRequiredFlag Flag that if set to true requires the
	 * customer to provide proof of his/her identity to verify that he/she may
	 * use the CUSTOMER ACCOUNT. FL_IDN_CT_RQ Flag integer
	 */
	boolean customerIdentityRequiredFlag;

	/**
	 * FrequentShopperPointsType A retailer assigned code that defines which
	 * kind of frequent shopper points are being accumulated by the customer.
	 * Sample Values * Silver * Gold * Platinum TY_PNT_FQ_SHPR Code2 char(2)
	 */
	String frequentShopperPointsType;

	/**
	 * CustomerIdentityTypeCode Determines the type of customer identification
	 * proof required. Examples include drivers license, store charge card,
	 * trade association identification card, etc. TY_IDN_CT Code2 char(2)
	 */
	String customerIdentityTypeCode;

	/**
	 * CumulativeFrequentShopperPointsCount A counter that accumulates frequent
	 * shopper points earned by a customer over a period of time and which may
	 * entitle a customer to a free gift or additional price reductions on
	 * selected items. QU_PNT_FQ_SHPR_CM Quantity DECIMAL(9,2)
	 */
	double cumulativeFrequentShopperPointsCount;

	/**
	 * CustomerID (FK) A unique system assigned identifier for a person or
	 * organization that purchases a product or service from the retailer. ID_CT
	 * Identity integer KeyCustomer (PA_KY_CT)
	 */
	long customerID;

	/**
	 * CustomerAccountEffectiveDate The date and time this CustomerAccount
	 * instance is ready for use by a customer. DT_EF EffectiveDateTime datetime
	 */
	Date customerAccountEffectiveDate;

	/**
	 * CustomerAccountExpirationDate The date and time this CustomerAccount
	 * instance may no longer be used by a customer DT_EP ExpirationDate date
	 */
	Date customerAccountExpirationDate;

	/**
	 * StatusCode The current status of this CustomerAccount instance. Note that
	 * this status is different from CreditStatusCode which designates the
	 * availability of credit to a customer using this account. This StatusCode
	 * refers to the current state of the entity instance Valid values are A
	 * ACTIVE I INACTIVE Note that the account maintenance process has to
	 * synchronize CreditStatusCode values and StatusCode values. CD_STS
	 * Code2Status char(2)
	 */
	String statusCode;

	/**
	 * UnpaidBalanceInterestRatePercent The rate of interest charged by a
	 * retailer on the unpaid balance of the CustomerAccount. The way the rate
	 * of interest is used in calculating accrued interest due is determined by
	 * the retailer. PE_UNPD_BLNC_INTRST_RT Percent decimal(7,4)
	 **/
	double unpaidBalanceInterestRatePercent;

	/**
	 * GracePeriodDayCount The number of days a Customer Account may carry a
	 * debit balance (customer owing the retailer) without incurring an interest
	 * charge. Note that it is up to the retailer to determine how grace days
	 * are applied - i.e. on a per CustomerAccountTenderLineItem (individual
	 * credit purchase) or on a customer account balance forward basis. In the
	 * data model this is a place to store the value used for grace period.
	 * TI_GRC_PRD_DY default char(18)
	 */
	String gracePeriodDayCount;

	/**
	 * BalanceAmount The current debit or credit balance in a customer's account
	 * that reflects the amount owed or due to a customer based on cumulative
	 * PaymentOnAccountLineItems (which record payments which credit the account
	 * reducing the amount owed) and CustomerAccountTenderLineItems (which
	 * record customer use of credit to purchas products and services increasing
	 * the amount owed). As modeled here BalanceAmount always reflects the
	 * current balance. Note that accrued interest is NOT reflected in this
	 * amoun. Accrued interest is modeled as a separate and distinct attribute
	 * to facilate tracking different income streams. Also note that for layaway
	 * accounts, customer payments represent a liability to the retailer which
	 * are CREDITS until the goods are taken and the sale is consumated.
	 * MO_BLNC_AMT Money decimal(16,5)
	 */
	double balanceAmount;

	/**
	 * AccruedInterestAmount The interest amount accrued against the unpaid
	 * blance of this customer account. MO_ACRL_INTRST_AMT Money decimal(16,5)
	 */
	double accruedInterestAmount;

	/**
	 * LatePaymentPenaltyAmount The balance owed for late payment penalty. A
	 * late penalty is typically charged when an account balance goes unpaid for
	 * some number of days beyond the grace period (e.g. over 30 days past grace
	 * period due date). MO_LT_PYM_PNTY Money decimal(16,5)
	 */
	double latePaymentPenaltyAmount;

	/**
	 * CreditStatusCode A distinct type of status code that indicates the
	 * current credit state of the CustomerAccount. SAMPLE vales include: GOOD -
	 * Customer is current and may charge purchases SUSPENDED - customer account
	 * is behind and customer may not charge purchase to this account until it
	 * is paid off. This is a temporary state. CLOSED - customer has outstanding
	 * balance deemed a poor risk and the account is closed so it cannot be used
	 * These are samples only, retailers may define their own codes. CD_CR_STS
	 * Code varchar(20)
	 */
	String creditStatusCode;

	/**
	 * CreditLimitAmount A limit on the total value of credit to be extended to
	 * this CustomerAccount. MO_CR_LM Money decimal(16,5)
	 */
	String creditLimitAmount;

	public CustomerAccount(long CustomerAccountID) {

		this.customerAccountID = CustomerAccountID;

	}

	public long getCustomerAccountID() {
		return customerAccountID;
	}

	public void setCustomerAccountID(long customerAccountID) {
		this.customerAccountID = customerAccountID;
	}

	public int getFinancialLedgerAccountID() {
		return financialLedgerAccountID;
	}

	public void setFinancialLedgerAccountID(int financialLedgerAccountID) {
		this.financialLedgerAccountID = financialLedgerAccountID;
	}

	public String getCustomerAccountTypeCode() {
		return customerAccountTypeCode;
	}

	public void setCustomerAccountTypeCode(String customerAccountTypeCode) {
		this.customerAccountTypeCode = customerAccountTypeCode;
	}

	public String getCustomerAccountName() {
		return customerAccountName;
	}

	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}

	public boolean isCustomerIdentityRequiredFlag() {
		return customerIdentityRequiredFlag;
	}

	public void setCustomerIdentityRequiredFlag(
			boolean customerIdentityRequiredFlag) {
		this.customerIdentityRequiredFlag = customerIdentityRequiredFlag;
	}

	public String getFrequentShopperPointsType() {
		return frequentShopperPointsType;
	}

	public void setFrequentShopperPointsType(String frequentShopperPointsType) {
		this.frequentShopperPointsType = frequentShopperPointsType;
	}

	public String getCustomerIdentityTypeCode() {
		return customerIdentityTypeCode;
	}

	public void setCustomerIdentityTypeCode(String customerIdentityTypeCode) {
		this.customerIdentityTypeCode = customerIdentityTypeCode;
	}

	public double getCumulativeFrequentShopperPointsCount() {
		return cumulativeFrequentShopperPointsCount;
	}

	public void setCumulativeFrequentShopperPointsCount(
			double cumulativeFrequentShopperPointsCount) {
		this.cumulativeFrequentShopperPointsCount = cumulativeFrequentShopperPointsCount;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public Date getCustomerAccountEffectiveDate() {
		return customerAccountEffectiveDate;
	}

	public void setCustomerAccountEffectiveDate(
			Date customerAccountEffectiveDate) {
		this.customerAccountEffectiveDate = customerAccountEffectiveDate;
	}

	public Date getCustomerAccountExpirationDate() {
		return customerAccountExpirationDate;
	}

	public void setCustomerAccountExpirationDate(
			Date customerAccountExpirationDate) {
		this.customerAccountExpirationDate = customerAccountExpirationDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public double getUnpaidBalanceInterestRatePercent() {
		return unpaidBalanceInterestRatePercent;
	}

	public void setUnpaidBalanceInterestRatePercent(
			double unpaidBalanceInterestRatePercent) {
		this.unpaidBalanceInterestRatePercent = unpaidBalanceInterestRatePercent;
	}

	public String getGracePeriodDayCount() {
		return gracePeriodDayCount;
	}

	public void setGracePeriodDayCount(String gracePeriodDayCount) {
		this.gracePeriodDayCount = gracePeriodDayCount;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public double getAccruedInterestAmount() {
		return accruedInterestAmount;
	}

	public void setAccruedInterestAmount(double accruedInterestAmount) {
		this.accruedInterestAmount = accruedInterestAmount;
	}

	public double getLatePaymentPenaltyAmount() {
		return latePaymentPenaltyAmount;
	}

	public void setLatePaymentPenaltyAmount(double latePaymentPenaltyAmount) {
		this.latePaymentPenaltyAmount = latePaymentPenaltyAmount;
	}

	public String getCreditStatusCode() {
		return creditStatusCode;
	}

	public void setCreditStatusCode(String creditStatusCode) {
		this.creditStatusCode = creditStatusCode;
	}

	public String getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(String creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}

	@Override
	public String toString() {
		return "CustomerAccount [customerAccountID=" + customerAccountID
				+ ", financialLedgerAccountID=" + financialLedgerAccountID
				+ ", customerAccountTypeCode=" + customerAccountTypeCode
				+ ", customerAccountName=" + customerAccountName
				+ ", customerIdentityRequiredFlag="
				+ customerIdentityRequiredFlag + ", frequentShopperPointsType="
				+ frequentShopperPointsType + ", customerIdentityTypeCode="
				+ customerIdentityTypeCode
				+ ", cumulativeFrequentShopperPointsCount="
				+ cumulativeFrequentShopperPointsCount + ", customerID="
				+ customerID + ", customerAccountEffectiveDate="
				+ customerAccountEffectiveDate
				+ ", customerAccountExpirationDate="
				+ customerAccountExpirationDate + ", statusCode=" + statusCode
				+ ", unpaidBalanceInterestRatePercent="
				+ unpaidBalanceInterestRatePercent + ", gracePeriodDayCount="
				+ gracePeriodDayCount + ", balanceAmount=" + balanceAmount
				+ ", accruedInterestAmount=" + accruedInterestAmount
				+ ", latePaymentPenaltyAmount=" + latePaymentPenaltyAmount
				+ ", creditStatusCode=" + creditStatusCode
				+ ", creditLimitAmount=" + creditLimitAmount + "]";
	}

}
