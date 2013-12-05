package sjsu.cmpe273.project.beans;
// Person bean
public class PersonBean {
	int person_id;
	int person_type;
	String first_name;
	String last_name;
	String email_address;
	String passport_number;
	String address_line1;
	String address_line2;
	String city;
	String state;
	String country;
	String zip_code;
	int person_deleted;
	String dob;
	
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getPerson_type() {
		return person_type;
	}
	public void setPerson_type(int person_type) {
		this.person_type = person_type;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getPassport_number() {
		return passport_number;
	}
	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public int getPerson_deleted() {
		return person_deleted;
	}
	public void setPerson_deleted(int person_deleted) {
		this.person_deleted = person_deleted;
	}
	public String getDob () {
		return dob;
	}
	public void setDob (String dob) {
		this.dob = dob;
	}
	
	
}
