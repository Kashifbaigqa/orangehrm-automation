package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class LoginPageTest extends BaseClass {

@Test	
public void dummyTest() {

String title = driver.getTitle();
//assert title.equals("OrangeHRM"): "Test Failed - Title is Not Matching"; java asset syntax using testNG syntax instead

Assert.assertEquals(title, "OrangeHRM", "Title mismatch");
System.out.println("Test Passed - Title is Matching");

}
}

