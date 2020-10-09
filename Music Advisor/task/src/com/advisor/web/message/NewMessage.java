package com.advisor.web.message;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
public class NewMessage extends Message{

    private static final String NEW = "---NEW RELEASES---";

    public NewMessage(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return NEW + "\n" + getContent();
    }
}
