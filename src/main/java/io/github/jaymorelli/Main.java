package io.github.jaymorelli;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> ss = new Stack<>();
        ss.push("1");
        ss.push("2");
        ss.push("3");
        System.out.println(ss.peek());
        System.out.println(ss.get(1));
        System.out.println(ss.firstElement());
        System.out.println(ss.lastElement());
        System.out.println(ss.pop());
        System.out.println(ss.remove(1));
        System.out.println("Hello Lists!");
    }

}