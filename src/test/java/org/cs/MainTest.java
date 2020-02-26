package org.cs;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by 2308157 on 1/30/20.
 */
public class MainTest {


    // private String date = "07/06/2013 10:11:59";
    private String date = "06-JAN-2015 13:15:34";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStringToDate() throws Exception {

        DateTime dateFormat = Main.stringToDate(this.date);
        System.out.println(dateFormat.toDate());

    }

    @Test
    public void testStringToDate1() throws Exception {

        String dateFormat = Main.stringToDate1(this.date);
        System.out.println("Result -> " + dateFormat);

    }
}
