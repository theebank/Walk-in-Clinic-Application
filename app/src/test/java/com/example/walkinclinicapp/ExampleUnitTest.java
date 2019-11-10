package com.example.walkinclinicapp;

import org.junit.Test;

import static java.util.Objects.hash;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void hash_iscorrect1() {
        assertEquals("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", MainActivity.hash("123"));
    }
    @Test
    public void hash_iscorrect2(){
        assertEquals("71c480df93d6ae2f1efad1447c66c9525e316218cf51fc8d9ed832f2daf18b73",MainActivity.hash("abcdefghijklmnopqrstuvwxyz"));
    }
    @Test
    public void serviceTest(){
        Service a = new Service("Tooth Extraction","Extraction of Teeth","Nurse");
        assertEquals("Tooth Extraction",a.getType());
    }
    @Test
    public void ClinicTest(){
        Clinic a = new Clinic("uottawa");
        assertEquals("uottawa",a.getName());
    }
    @Test
    public void UserTest(){
        User a = new User("username","password","role");
        assertEquals(MainActivity.hash("password"),MainActivity.hash(a.getPassword()));
    }
}