/**
 * @author bingoabin
 * @date 2022/8/9 14:45
 * @Description: authbo
 */
public class AuthBo {
	private String authtype;
	private String authvalue;

	public AuthBo() {
	}

	public AuthBo(String authtype, String authvalue) {
		this.authtype = authtype;
		this.authvalue = authvalue;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public String getAuthvalue() {
		return authvalue;
	}

	public void setAuthvalue(String authvalue) {
		this.authvalue = authvalue;
	}

	@Override
	public String toString() {
		return "AuthBo{" +
				"authtype='" + authtype + '\'' +
				", authvalue='" + authvalue + '\'' +
				'}';
	}
}
