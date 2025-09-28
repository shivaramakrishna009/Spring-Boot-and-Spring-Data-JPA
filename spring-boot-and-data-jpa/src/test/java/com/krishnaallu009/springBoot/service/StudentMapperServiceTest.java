package com.krishnaallu009.springBoot.service;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperServiceTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("From Before All Method");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("From After All Method");
    }

    @BeforeEach
    void setUp() {
        System.out.println("From Before each Method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("From After each Method");
    }

    @Test
    void testMethod1() {
        System.out.println("From Test Method 1");
    }

    @Test
    void testMethod2() {
        System.out.println("From Test Method 2");
    }
}