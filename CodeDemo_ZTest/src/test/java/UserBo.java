/**
 * @author bingoabin
 * @date 2022/8/9 14:46
 * @Description: userbo
 */
public class UserBo {
	private String ip;
	private String rows;

	public UserBo() {
	}

	public UserBo(String ip, String rows) {
		this.ip = ip;
		this.rows = rows;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "UserBo{" +
				"ip='" + ip + '\'' +
				", rows='" + rows + '\'' +
				'}';
	}
}
