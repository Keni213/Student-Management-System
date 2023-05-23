package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.FileInfoReader;

class AdminTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCheckLogIn() {
		ArrayList<Admin> adminInfo = FileInfoReader.readAdminInfo("adminInfo.txt");
		Admin admin01=adminInfo.get(0);
		//admin01 get login
		assertTrue(admin01.checkLogIn( adminInfo,"admin01", "password590"));
		
        Admin admin02=adminInfo.get(1);
		//no one get log in
		assertFalse(admin02.checkLogIn( adminInfo,"admin01", "password5911"));
	}

	@Test
	void testLogIn() {
		ArrayList<Admin> adminInfo = FileInfoReader.readAdminInfo("adminInfo.txt");
		Admin admin01=adminInfo.get(0);
		
		//test if input correct password and username
		assertTrue(admin01.logIn("admin01", "password590"));
		//test if input wrong password
		assertFalse(admin01.logIn("admin01", "password5911"));
		//test if input wrong username
		assertFalse(admin01.logIn("adminggg", "password590"));
		
		
		
		
        Admin admin02=adminInfo.get(1);
		
		//test if input correct password and username
		assertTrue(admin02.logIn("admin02", "password590"));
		//test if input wrong password
		assertFalse(admin02.logIn("admin02", "password5911"));
		//test if input wrong username
		assertFalse(admin02.logIn("adminggg", "password590"));
	}

}
