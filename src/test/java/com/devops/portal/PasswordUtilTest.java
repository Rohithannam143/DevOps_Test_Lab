package com.devops.portal;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class PasswordUtilTest{@Test void hashAndVerify(){String h=PasswordUtil.hash("StrongPass@123");assertTrue(PasswordUtil.verify("StrongPass@123",h));assertFalse(PasswordUtil.verify("wrong",h));assertNotEquals(h,PasswordUtil.hash("StrongPass@123"));}}
