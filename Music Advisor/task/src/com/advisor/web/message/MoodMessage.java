package com.advisor.web.message;

/**
 * @author Dionysios Stolis 10/4/2020 <dstolis@b-open.com>
 */
public class MoodMessage extends Message{

    private static final String MOOD = "---MOOD PLAYLISTS---";

    public MoodMessage(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return MOOD + "\n" + getContent();
    }
}
