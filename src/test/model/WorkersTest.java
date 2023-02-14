package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for workers class
public class WorkersTest {

    Workers worker1;
    Workers worker2;


    @BeforeEach
    public void runBefore() {
        worker1 = new Workers("Harman");
        worker2 = new Workers("Sahil");
    }

    @Test
    public void testWorkerConstructor() {
        assertEquals("Harman", worker1.getName());
        assertEquals("Sahil" , worker2.getName());
    }

}
