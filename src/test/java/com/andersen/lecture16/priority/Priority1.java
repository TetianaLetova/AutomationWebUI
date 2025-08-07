package com.andersen.lecture16.priority;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
I use intentionally both testng and junit
I run junit by run whole class and run testng by testng.XML file
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Priority1 {

    @org.testng.annotations.Test
    @Test
    @Order(7)
    public void a() {
        System.out.println("A");
        assertTrue(true);
    }

    @org.testng.annotations.Test
    @Test
    @Order(6)
    public void b() {
        System.out.println("B");
        assertTrue(true);
    }

    @org.testng.annotations.Test
    @Test
    @Order(5)
    public void c() {
        System.out.println("C");
        assertTrue(true);
    }

    @org.testng.annotations.Test
    @Test
    @Order(4)
    public void d() {
        System.out.println("D");
        assertTrue(true);
    }

    @org.testng.annotations.Test
    @Test
    @Order(3)
    public void e() {
        System.out.println("E");
        assertTrue(true);
    }

    @org.testng.annotations.Test
    @Test
    @Order(2)
    public void f() {
        System.out.println("F");
        assertTrue(true);
    }

    @org.testng.annotations.Test
    @Test
    @Order(1)
    public void g() {
        System.out.println("G");
        assertTrue(true);
    }
}
