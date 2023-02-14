package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for services class
public class ServicesTest {

    Services service1;
    Services service2;

    @BeforeEach
    public void runBefore() {

        service1 = new Services("LawnMowing&Trimming");
        service2 = new Services("YardCleanup");
    }

    @Test
    public void testServicesConstructor() {

        assertEquals(service1.getServiceDescription(), "LawnMowing&Trimming");
        assertEquals(service2.getServiceDescription(), "YardCleanup");
    }

}
