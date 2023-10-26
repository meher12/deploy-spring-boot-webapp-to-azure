package com.guru2azure.tools;

import java.util.List;

public class Utils {

    String blogTitle = "Welcome to Microsoft Azure ";

    String blogTitleDuplicate = blogTitle;

    private List<String> blogInList = List.of("guru", "2", "azure");

    public int add(int a, int b) {
        return a + b;
    }

    public Object checkNull(Object obj) {
        if (obj != null) {
            return obj;
        }
        return null;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public String getBlogTitleDuplicate() {
        return blogTitleDuplicate;
    }

    public Boolean isGreater(int n1, int n2) {
        if (n1 > n2) {
            return true;
        }
        return false;
    }

    public List<String> getBlogInList() {
        return blogInList;
    }

    public String throwException(int a) throws Exception {
        if (a < 0) {
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }

    public void checkTimeout() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }
}
