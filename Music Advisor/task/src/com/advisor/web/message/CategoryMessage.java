package com.advisor.web.message;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
public class CategoryMessage extends Message {

    private static final String CATEGORIES = "---CATEGORIES---";

    public CategoryMessage(String content){
        super(content);
    }

    @Override
    public String toString() {
        return CATEGORIES + "\n" + getContent();
    }
}
