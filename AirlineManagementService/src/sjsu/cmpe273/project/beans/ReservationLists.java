package sjsu.cmpe273.project.beans;


public class ReservationLists {
	ReservationBean[] activeReservations;
	ReservationBean[] cancelledReservations;

	public ReservationBean[] getActiveReservations() {
		return activeReservations;
	}

	public void setActiveReservations(ReservationBean[] activeReservations) {
		this.activeReservations = activeReservations;
	}

	public ReservationBean[] getCancelledReservations() {
		return cancelledReservations;
	}

	public void setCancelledReservations(ReservationBean[] cancelledReservations) {
		this.cancelledReservations = cancelledReservations;
	}

}
