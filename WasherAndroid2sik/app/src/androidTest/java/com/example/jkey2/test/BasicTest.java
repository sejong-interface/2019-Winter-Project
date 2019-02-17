package com.example.jkey2.test;

import junit.framework.TestCase;

/**
 * Created by jkey2 on 2019-01-13.
 */
public class BasicTest extends TestCase {
    int aduinoresult= 100;
    public void testSimple(){
        Wahsertest washerTest = new Wahsertest();
        assertEquals(aduinoresult, washerTest.washerok(100));
    }
}
