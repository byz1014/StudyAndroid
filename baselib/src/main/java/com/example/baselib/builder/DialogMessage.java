package com.example.baselib.builder;

/**
 * Created by byz on 2017/11/7.
 */

public class DialogMessage {
    private String message;
    private String left;
    private String right;
    private DialogMessage(){}

    private DialogMessage(DialogMessage dialogMessage){
        this.message = dialogMessage.message;
        this.left = dialogMessage.left;
        this.right = dialogMessage.right;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public static class Builder{
        private DialogMessage dialogMessage;

        public Builder() {
            dialogMessage = new DialogMessage();
        }

        public Builder left(String left){
            dialogMessage.left = left;
            return this;
        }
        public Builder right(String right){
            dialogMessage.right = right;
            return this;
        }
        public Builder message(String message){
            dialogMessage.message = message;
            return this;
        }

        public DialogMessage build(){
            return new DialogMessage(dialogMessage);
        }

    }
}
