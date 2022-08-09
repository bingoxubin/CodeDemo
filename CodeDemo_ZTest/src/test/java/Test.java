import com.bingoabin.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author bingoabin
 * @date 2022/8/9 14:43
 * @Description: 测试方法
 */
public class Test {
	public static void main(String[] args){
		// UserAuthBo userAuthBo = new UserAuthBo();
		// ListUtil.stream(userAuths).map(e -> {
		// 	if (AuthEnum.IP.eq(e.getAuthType())) {
		// 		userAuthBo.setLoginIp(e.getAuthValue());
		// 	}
		// 	if (AuthEnum.ROWS.eq(e.getAuthType())) {
		// 		userAuthBo.setRows(e.getAuthValue());
		// 	}
		// 	return userAuthBo;
		// });

		List<AuthBo> userAuths = new ArrayList<>();
		userAuths.add(new AuthBo("loginip","127.0.0.1"));
		userAuths.add(new AuthBo("rows","3000"));

		UserBo userBo = new UserBo();
		for (AuthBo bo : userAuths) {
			if (AuthEnum.IP.eq(bo.getAuthtype())) {
				userBo.setIp(bo.getAuthvalue());
			}
			if (AuthEnum.ROWS.eq(bo.getAuthtype())) {
				userBo.setRows(bo.getAuthvalue());
			}
		}
		System.out.println(userBo);

		Set<UserBo> collect = ListUtil.stream(userAuths).map(e -> {
			if (AuthEnum.IP.eq(e.getAuthtype())) {
				userBo.setIp(e.getAuthvalue());
			}
			if (AuthEnum.ROWS.eq(e.getAuthtype())) {
				userBo.setRows(e.getAuthvalue());
			}
			return userBo;
		}).collect(Collectors.toSet());
		System.out.println(collect);
	}
}
