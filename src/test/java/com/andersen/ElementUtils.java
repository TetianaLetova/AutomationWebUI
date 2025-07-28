package com.andersen;

import org.openqa.selenium.WebElement;

public class ElementUtils {

    public static void compareElements(WebElement el1, WebElement el2) {
        int el1X = el1.getLocation().getX();
        int el1Y = el1.getLocation().getY();
        int el1Width = el1.getSize().getWidth();
        int el1Height = el1.getSize().getHeight();

        int el2X = el2.getLocation().getX();
        int el2Y = el2.getLocation().getY();
        int el2Width = el2.getSize().getWidth();
        int el2Height = el2.getSize().getHeight();

        if (el1Y < el2Y) {
            System.out.println("The first element is higher on the page.");
        } else if (el1Y > el2Y) {
            System.out.println("The second element is higher on the page.");
        } else {
            System.out.println("Both elements are at the same vertical position.");
        }

        if (el1X < el2X) {
            System.out.println("The first element is to the left.");
        } else if (el1X > el2X) {
            System.out.println("The second element is to the left.");
        } else {
            System.out.println("Both elements are at the same horizontal position.");
        }

        int el1Area = el1Width * el1Height;
        int el2Area = el2Width * el2Height;
        if (el1Area > el2Area) {
            System.out.println("The first element takes up more space.");
        } else if (el1Area < el2Area) {
            System.out.println("The second element takes up more space.");
        } else {
            System.out.println("Both elements take up the same amount of space.");
        }
    }
}
