/*
 * Copyright 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.aerilys.ageoftrinity.client.tools;

public class Vector {
  public double x, y;
  
  public Vector() {
    this(0, 0);
  }
  
  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public Vector(Vector v) {
    this(v.x, v.y);
  }
  
  public void add(double x, double y) {
    this.x += x;
    this.y += y;
  }
  
  public void add(Vector v) {
    add(v.x, v.y);
  }
  
  public void sub(Vector v) {
    sub(v.x, v.y);
  }
  
  public void sub(double x, double y) {
    this.x -= x;
    this.y -= y;
  }
  
  public void mult(double x, double y) {
    this.x *= x;
    this.y *= y;
  }
  
  public void mult(Vector v) {
    mult(v.x, v.y);
  }
  
  public void mult(double c) {
    mult(c, c);
  }
  
  public double mag() {
    if (x == 0 && y == 0) {
      return 0;
    } else {
      return Math.sqrt(x * x + y * y);
    }
  }
  
  public double magSquared() {
    return x * x + y * y;
  }
  
  public void set(Vector v) {
    x = v.x;
    y = v.y;
  }
  
  public static Vector sub(Vector a, Vector b) {
    return new Vector(a.x - b.x, a.y - b.y);
  }
  
  public static Vector mult(Vector v, double c) {
    return new Vector(v.x * c, v.y * c);
  }
}