package com.advisor.web.message;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
public class FeaturedMessage extends Message {

    private static final String FEATURED = "---FEATURED---";

    public FeaturedMessage(String content){
        super(content);
    }

    @Override
    public String toString() {
        return FEATURED + "\n" + getContent();
    }
}
