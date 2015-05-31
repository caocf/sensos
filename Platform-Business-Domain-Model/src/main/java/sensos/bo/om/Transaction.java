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

import java.io.Serializable;
import java.sql.Date;

/**
 * A record of business activity that involves a financial and/or merchandise
 * unit exchange or the granting of access to conduct business at a specific
 * device, at a specific point in time for a specific employee.
 * 
 * @author sensos
 *
 */
public class Transaction implements Serializable {

	/**
	 * SessionStartTransactionID (FK) A universally unique identifier (UUID) for
	 * the Transaction. This may be assembled from alternate key members.
	 * ID_TRN_SSN_SRT IdentityUUID char(32) Session (CO_SSN)
	 */
	String sessionStartTransactionID;

	/**
	 * String TransactionID (PK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32)
	 */
	String transactionID;

	/**
	 * OperatorID (FK) A unique, automatically assigned number used to identify
	 * a workstation OPERATOR. ID_OPR IdentityUUID char(32) Session (CO_SSN)
	 **/
	String operatorID;

	/**
	 * WorkstationID (FK) The unique identifier for the WORKSTATION, typically
	 * the serial number. ID_WS IdentityUUID char(32) Workstation (AS_WS)
	 */
	String WorkStationID;

	/**
	 * BusinessDayDate (FK) The calendar date of the BusinessDay. DC_DY_BSN
	 * DateCalendar date BusinessDay (CA_DY_BSN)
	 **/
	Date businessDayDate;

	/**
	 * BusinessUnitID (FK) A unique retailer assigned identifier for an
	 * RetailStore, DistributionCenter or AdministrationCenter ID_BSN_UN
	 * IdentityUUID char(32) BusinessUnit (LO_BSN_UN)
	 */
	String businessUnitID;

	/**
	 * TransactionTypeCode (FK) A code to denote the type of Transtion. e.g.
	 * RetailTransaction, ControlTransaction, TenderControlTrnsaction, etc...
	 * TY_TRN Code6 char(6) TransactionType (CO_TYP_TRN)
	 */
	String transactionTypeCode;

	/**
	 * SequenceNumber SequenceNumber is a counter used to uniquely identify
	 * individual transaction within a transaction set identified by:
	 * BusinessUnit (store or channel location), BusinessDayDate (day this
	 * sale/return is attributed to for accounting purposes), WorkstationID
	 * (terminal, register where transaction was entered), OperatorID (the
	 * cashier or sales associate entering the transaction) and sequence number
	 * which exists only to distinguish each transaction. With this in mind, the
	 * sequence number is a counter and IS RESET to 1 when a BusinessUnit,
	 * BusinessDayDate, WorkstationID, OperatorID control break is encountered.
	 * AI_TRN Number integer
	 */
	int sequenceNumber;

	/**
	 * BeginDateTimestamp The time and date a transaction is initiated.
	 * TS_TRN_BGN EffectiveDateTime datetime
	 */
	Date beginDateTimeStamp;

	/**
	 * EndDateTimestamp The time and date stamp a transaction is completed.
	 * TS_TRN_END ExpirationDateTime datetime
	 */
	Date endDateTimestamp;

	/**
	 * TrainingFlag A flag to signify whether the workstation is in training
	 * mode. FL_TRG_TRN Flag integer
	 */
	byte trainingFlag;

	/**
	 * KeyedOfflineFlag A code that indiates the online/offline state of how a
	 * transaction and its components was entered at a work station. Valid
	 * values include 'OL' online, 'SA' Standalone (offline), and 'BO' both
	 * online and offline. BO means that some line items (e.g. payment
	 * authorizations) were processed online while some (e.g. item scanning/key
	 * entry) were done offline. FL_KY_OFL Code2 char(2)
	 */
	String keyedOfflineFlag;

	/**
	 * CancelledFlag A flag denoting that this entire transaction has been
	 * cancelled before it was completed at the POS. FL_CNCL Flag integer
	 */
	int cancelledFlag;

	/**
	 * VoidedFlag A flag denoting that this entire transaction has been voided
	 * (and reversed) after it was completed at the POS via a
	 * PostVoidTransaction. FL_VD Flag integer
	 **/
	int voidedFlag;

	/**
	 * SuspendedFlag A flag denoting that this entire transaction has been
	 * suspended before it was completed at the POS. FL_SPN Flag integer
	 **/
	int suspendedFlag;

	/**
	 * RevenueCostCenterID (FK) A unique retailer assigned identifier for an
	 * accounting budget, that owns merchandise and is used to track the
	 * financial performance of the retail enterprise. ID_CTR_RVN_CST Identity
	 * integer RevenueCostCenter (CO_CTR_RVN_CST)
	 */
	int revenueCostCenterID;

	/**
	 * METARWeatherCondiitionID (FK) Token ID for each METAR Weather condition
	 * row. Typically METAR reports are made once per hour per ICAO location.
	 * Only locations tied to retailer Sites will have weather conditions
	 * reported. Also, retailers may choose to limit the forecasts to once or
	 * twice a day. The granularity of weather tracking for retail sales
	 * analysis and reporting is less rigorous thant for flight safety and
	 * control. ID_METAR_WTHR_CN IdentityUUID char(32) METARWeatherCondition
	 * (CO_METAR_WTHR_CN)
	 **/
	String METARWeatherConditionID;

	/**
	 * METARWeatherForecastID (FK) Token identifier for each instance of a METAR
	 * weather forecast entity. Forecast periods, unlike METARWeatherCondition
	 * (which are done on an hourly basis or more in volatile weather
	 * situations) these cover periods of multiple days. ID_METAR_WTHR_FRCST
	 * IdentityUUID char(32) METARWeatherForecast (CO_METAR_WTHR_FRCST)
	 */
	String METARWeatherForecastID;

	/**
	 * ForceFlag A boolean indicator that if set to YES tells the retailer that
	 * this ControlTransaction was used to override a standard operating
	 * procedure and set a state/status to a desired value. Examples of forced
	 * control transactions include: FORCED_SIGNOFF FORCED_SIGNON
	 * FORCED_ENDOFDAY_CLOSE etc. The ForceFlag, when set to YES indicates an
	 * exception condition that should be reviewed by store operations and
	 * internal auditing. FL_FRCD Flag integer
	 **/
	boolean forceFlag;

	/**
	 * 
	 * @param TransactionID
	 */
	public Transaction(String transactionID) {

		this.transactionID = transactionID;

	}

	public String getSessionStartTransactionID() {
		return sessionStartTransactionID;
	}

	public void setSessionStartTransactionID(String sessionStartTransactionID) {
		this.sessionStartTransactionID = sessionStartTransactionID;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getWorkStationID() {
		return WorkStationID;
	}

	public void setWorkStationID(String workStationID) {
		WorkStationID = workStationID;
	}

	public Date getBusinessDayDate() {
		return businessDayDate;
	}

	public void setBusinessDayDate(Date businessDayDate) {
		this.businessDayDate = businessDayDate;
	}

	public String getBusinessUnitID() {
		return businessUnitID;
	}

	public void setBusinessUnitID(String businessUnitID) {
		this.businessUnitID = businessUnitID;
	}

	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Date getBeginDateTimeStamp() {
		return beginDateTimeStamp;
	}

	public void setBeginDateTimeStamp(Date beginDateTimeStamp) {
		this.beginDateTimeStamp = beginDateTimeStamp;
	}

	public Date getEndDateTimestamp() {
		return endDateTimestamp;
	}

	public void setEndDateTimestamp(Date endDateTimestamp) {
		this.endDateTimestamp = endDateTimestamp;
	}

	public byte getTrainingFlag() {
		return trainingFlag;
	}

	public void setTrainingFlag(byte trainingFlag) {
		this.trainingFlag = trainingFlag;
	}

	public String getKeyedOfflineFlag() {
		return keyedOfflineFlag;
	}

	public void setKeyedOfflineFlag(String keyedOfflineFlag) {
		this.keyedOfflineFlag = keyedOfflineFlag;
	}

	public int getCancelledFlag() {
		return cancelledFlag;
	}

	public void setCancelledFlag(int cancelledFlag) {
		this.cancelledFlag = cancelledFlag;
	}

	public int getVoidedFlag() {
		return voidedFlag;
	}

	public void setVoidedFlag(int voidedFlag) {
		this.voidedFlag = voidedFlag;
	}

	public int getSuspendedFlag() {
		return suspendedFlag;
	}

	public void setSuspendedFlag(int suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	public int getRevenueCostCenterID() {
		return revenueCostCenterID;
	}

	public void setRevenueCostCenterID(int revenueCostCenterID) {
		this.revenueCostCenterID = revenueCostCenterID;
	}

	public String getMETARWeatherConditionID() {
		return METARWeatherConditionID;
	}

	public void setMETARWeatherConditionID(String mETARWeatherConditionID) {
		METARWeatherConditionID = mETARWeatherConditionID;
	}

	public String getMETARWeatherForecastID() {
		return METARWeatherForecastID;
	}

	public void setMETARWeatherForecastID(String mETARWeatherForecastID) {
		METARWeatherForecastID = mETARWeatherForecastID;
	}

	public boolean isForceFlag() {
		return forceFlag;
	}

	public void setForceFlag(boolean forceFlag) {
		this.forceFlag = forceFlag;
	}

}
