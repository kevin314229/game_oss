package com.jcwx.game.exception;

public class GameException extends RuntimeException {

    private static final long serialVersionUID = -4897548649208070444L;

    public GameException() {

    }

    public GameException(String str) {
	super(str);
    }

    public GameException(String str, Throwable e) {
	super(str, e);
    }

    public GameException(Throwable ex) {
	super(ex);
    }

}