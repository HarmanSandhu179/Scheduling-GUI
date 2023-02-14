package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for date class
public class DateTest {

    private Dates date1;
    private Dates date2;

    ArrayList availableDates;

    @BeforeEach
    public void runBefore() {
        date1 = new Dates("July", 25);
        date2 = new Dates("August", 1);

        availableDates = new ArrayList<>();
    }

    @Test
    public void testDateConstructor () {
        assertEquals(date1.getMonth(), "July");
        assertEquals(date2.getMonth(), "August");

        assertEquals(date1.getDay(), 25);
        assertEquals(date2.getDay(), 1);
    }

}
