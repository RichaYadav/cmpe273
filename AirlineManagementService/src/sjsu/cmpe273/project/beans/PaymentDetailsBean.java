package sjsu.cmpe273.project.beans;

public class PaymentDetailsBean {
	int payment_id;
	int payment_method;
	int card_number;
	int account_number;
	int amount_paid;
	int payment_status;
	boolean isPaymentCancelled;

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public int getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(int payment_method) {
		this.payment_method = payment_method;
	}

	public int getCard_number() {
		return card_number;
	}

	public void setCard_number(int card_number) {
		this.card_number = card_number;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public int getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public boolean isPaymentCancelled() {
		return isPaymentCancelled;
	}

	public void setPaymentCancelled(boolean isPaymentCancelled) {
		this.isPaymentCancelled = isPaymentCancelled;
	}
}
