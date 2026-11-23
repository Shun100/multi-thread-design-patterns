package org.example.c01_single_thread_execution;

public class Gate {
  synchronized void pass(String name, String address) {
    System.out.printf("BEGIN %s / %s\n", name, address);
    if (!name.equals(address.replace("address-", "user-"))) {
      System.out.println(" inconsistent data");
    }
    System.out.printf("End %s\n", name);
  }
}
