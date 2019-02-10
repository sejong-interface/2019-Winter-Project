package com.example.jkey2.test;

import junit.framework.TestCase;

/**
 * Created by jkey2 on 2019-01-13.
 */

public class BasicTest extends TestCase {
    int aduinoresult= 100; // 아두이노에서 세탁기가 돌아가는 걸 체크하고 남은 시간을 저장
    public void testSimple(){
        wahsertest washerTest = new wahsertest();
        assertEquals(aduinoresult, washerTest.washerok(100));
    }

}
