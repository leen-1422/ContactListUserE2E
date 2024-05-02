package pojos;


public class UserResponsePojo {

	private String firstName;
	private String lastName;
	private Integer __v;
	private String _id;
	private String email;

	public UserResponsePojo() {
	}

	public UserResponsePojo(String firstName, String lastName, Integer __v, String _id, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.__v = __v;
		this._id = _id;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer get__v() {
		return __v;
	}

	public void set__v(Integer __v) {
		this.__v = __v;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserPojo{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", __v=" + __v +
				", _id='" + _id + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}