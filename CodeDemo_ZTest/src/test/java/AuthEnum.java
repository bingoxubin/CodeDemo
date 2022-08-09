import java.util.HashMap;
import java.util.Optional;

/**
 * @author bingoabin
 * @date 2022/8/9 14:17
 * @Description: 权限枚举
 */
public enum AuthEnum {
		IP("loginIp", "访问ip"),
		ROWS("rows", "行数"),
		STARTPERIOD("startPeriod", "开始有效期"),
		ENDPERIOD("endPeriod", "结束有效期"),
		TAG("Tag","标签"),
		DDL("DDL", "DDL权限"),
		DML("DML", "DML权限"),
		;

		public final String code;
		public final String description;

		private static class Meta {
			private final static HashMap<String, AuthEnum> codeMap;
			private final static HashMap<String, AuthEnum> descriptionMap;

			static {
				final AuthEnum[] values = AuthEnum.values();
				codeMap = new HashMap<String, AuthEnum>(values.length);
				descriptionMap = new HashMap<>(values.length);
				for (AuthEnum value : values) {
					codeMap.put(value.code, value);
					descriptionMap.put(value.description, value);
				}
			}
		}

		AuthEnum(String code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		/**
		 * equal
		 *
		 * @param code
		 * @return
		 */
		public boolean eq(String code) {
			if (code == null) {
				return false;
			}
			return code.toLowerCase().equals(this.getCode().toLowerCase());
		}

		/**
		 * not equal
		 *
		 * @param code
		 * @return
		 */
		public boolean ne(String code) {
			return !eq(code);
		}

		public static Optional<AuthEnum> of(String code) {
			if (code == null) {
				return Optional.empty();
			}
			return Optional.ofNullable(Meta.codeMap.get(code));
		}

		public static String description(String code, String val) {
			return of(code).map(AuthEnum::getDescription).orElse(val);
		}

		public static Optional<AuthEnum> ofDescription(String description) {
			if (description == null) {
				return Optional.empty();
			}
			return Optional.ofNullable(Meta.descriptionMap.get(description));
		}

		public static String code(String description, String val) {
			return ofDescription(description).map(AuthEnum::getCode).orElse(val);
		}

		@Override
		public String toString() {
			return AuthEnum.class.getSimpleName() + "{" +
					"code=" + code +
					", description='" + description + '\'' +
					'}';
		}
}
