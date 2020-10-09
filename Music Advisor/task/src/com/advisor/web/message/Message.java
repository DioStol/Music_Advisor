package com.advisor.web.message;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
public abstract class Message {

    private String content;

    public Message(String content) {
        this.content = content;
    }

    public static void printErrorMessage(){
        System.out.println("Please, provide access for application.");
    }

    public String getContent() {
        return content;
    }

    public void print(){
        System.out.println(this);
    }
}
