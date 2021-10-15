package com.winchang.learn.service;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;

class FibonacciSupplier implements LongSupplier {
  private long first = 0L;
  private long second = 1L;

  public long getAsLong() {
      long ret = second;
      second = first + second;
      first = ret;

      return first;
  }
}


@Component()
//@Scope("prototype")
public class NumberService {

  public LongStream getLongStream() {
    return LongStream.generate(new FibonacciSupplier());
  }
}
