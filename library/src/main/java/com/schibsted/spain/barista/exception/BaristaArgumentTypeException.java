package com.schibsted.spain.barista.exception;

public class BaristaArgumentTypeException extends RuntimeException {

  public BaristaArgumentTypeException() {
    super("The id argument must be R.id.* or R.string.*");
  }
}
