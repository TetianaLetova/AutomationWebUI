package com.andersen.grouping;

import org.testng.annotations.Test;

public class Groups {

    @Test(groups = "first")
    public void one() {
        System.out.println("Test one from group FIRST");
    }

    @Test(groups = "second")
    public void two() {
        System.out.println("Test two from group SECOND");
    }

    @Test(groups = "first")
    public void three() {
        System.out.println("Test three from group FIRST");
    }

    @Test(groups = "second")
    public void four() {
        System.out.println("Test four from group SECOND");
    }

    @Test(groups = "first")
    public void five() {
        System.out.println("Test five from group FIRST");
    }

    @Test(groups = "second")
    public void six() {
        System.out.println("Test six from group SECOND");
    }

    @Test(groups = "first")
    public void seven() {
        System.out.println("Test seven from group FIRST");
    }

    @Test(groups = "second")
    public void eight() {
        System.out.println("Test eight from group SECOND");
    }
}
