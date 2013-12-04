package sjsu.cmpe273.project.beans;

public class BookingDetailBean {
	int booking_id;
	int booking_status;
	int payment_id;
	int booking_cancelled;
	int journey_id;
	
	public int getJournry_id() {
		return journey_id;
	}
	public void setJournry_id(int journey_id) {
		this.journey_id = journey_id;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getBooking_status() {
		return booking_status;
	}
	public void setBooking_status(int booking_status) {
		this.booking_status = booking_status;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getBooking_cancelled() {
		return booking_cancelled;
	}
	public void setBooking_cancelled(int booking_cancelled) {
		this.booking_cancelled = booking_cancelled;
	}
	
	
}
