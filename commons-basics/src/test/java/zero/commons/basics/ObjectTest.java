package zero.commons.basics;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import zero.commons.basics.vertify.VerificationHelper;
import zero.commons.basics.vertify.Verify;
import zero.commons.basics.vertify.VertifyType;

public class ObjectTest {

	@Test
	public void showObj() {
		User user = new User();
		user.setName("admin");
//		user.setAge("10");
//		System.out.println(JSON.toJSON(ObjectUtil.getFiledsInfo(user)));
//		System.out.println(JSON.toJSON(ObjectUtil.getFiledValues(user)));
		System.out.println(VerificationHelper.vertifyParam(user, VertifyType.INSERT));
	}
}

class User {

	@Verify(insertable = true, updateable = true, deleteable = true, nullable = false)
	private String name;
	@Verify(insertable = true, updateable = true, deleteable = true, nullable = false)
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
