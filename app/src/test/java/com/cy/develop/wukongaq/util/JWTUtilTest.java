package com.cy.develop.wukongaq.util;

//import static org.junit.Assert.*;

import org.junit.Test;

public class JWTUtilTest {
    @Test
    public void test(){
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJpc3MiOiJTZXJ2aWNlIiwiaWQiOjEsImV4cCI6MTU2MjMyNjAzNiwiaWF0IjoxNTYxNzIxMjM2fQ.S1FQIEEVQg5aTsgeQgRwhDrpT3Hkbjguprsn9SHO_6A";
        System.out.println(JWTUtil.getUidFromToken(token));
    }


}