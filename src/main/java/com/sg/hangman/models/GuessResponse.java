package com.sg.hangman.models;


public class GuessResponse {
        private String status;
        private int locationInWord;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLocationInWord() {
        return locationInWord;
    }

    public void setLocationInWord(int locationInWord) {
        this.locationInWord = locationInWord;
    }
 
}