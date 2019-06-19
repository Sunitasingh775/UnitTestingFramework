package com.sunita.unittestingframework;

class Helpers {

    private Helpers() {
    }

    static boolean validateUserName(String name) {
        return name != null && name.length() > 4 && name.length() < 20;
    }
}