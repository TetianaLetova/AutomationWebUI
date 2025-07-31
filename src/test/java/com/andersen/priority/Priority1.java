package com.andersen.priority;

import org.apache.hc.core5.annotation.Obsolete;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Priority1 {
    @Test
    @Order(7)
    public void a() {
        assertTrue(true);
    }

    @Test
    @Order(6)
    public void b() {
        assertTrue(true);
    }

    @Test
    @Order(5)
    public void c() {
        assertTrue(true);
    }

    @Test
    @Order(4)
    public void d() {
        assertTrue(true);
    }

    @Test
    @Order(3)
    public void e() {
        assertTrue(true);
    }

    @Test
    @Order(2)
    public void f() {
        assertTrue(true);
    }

    @Test
    @Order(1)
    public void g() {
        assertTrue(true);
    }

}
