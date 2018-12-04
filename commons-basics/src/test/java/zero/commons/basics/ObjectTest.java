package zero.commons.basics;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class ObjectTest {

	@Test
	public void showObj() {
		User user = new User();
		user.setName("admin");
		user.setAge("10");
		System.out.println(JSON.toJSON(ObjectUtil.getFiledsInfo(user)));
		System.out.println(JSON.toJSON(ObjectUtil.getFiledValues(user)));
	}
}

class User {
	private String name;
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
