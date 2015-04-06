package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private static int personCount = 0;

  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

  public static List<Person> getNewardFamily() {
    List<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15, 0));
    family.add(new Person("Charlotte", 43, 150000));
    return family;
  }

  public static int count() {
    return personCount;
  }
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    ++personCount;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int value) {
    if (value < 0) {
      throw new IllegalArgumentException();
    }
    age = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    name = value;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double value) {
    salary = value;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Person)) {
      return false;
    }
    Person p = (Person)other;
    return (this.name.equals(p.name) && this.age == p.age);
  }

  public String toString() {
    return String.format("[Person name:%s age:%d salary:%.2f]",
                         name, age, salary);
  }

  @Override
  public int compareTo(Person other) {
    return (int)other.salary - (int)salary;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }
}
