package shs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class DepartmentTest {

	Department dept = new Department(0);
	@Before
	@Test
	void test() {
		

		System.out.println(dept.getdId());
		System.out.println(dept.getDeptartmentName());
		assertEquals(6, dept.getdId());
		//fail("Not yet implemented");
	}

}
