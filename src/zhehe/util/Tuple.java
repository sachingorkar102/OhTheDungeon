/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhehe.util;

public class Tuple<A, B> {
   private A a;
   private B b;

   public Tuple(A a, B b) {
      this.a = a;
      this.b = b;
   }

   public A getFirst() {
      return this.a;
   }

   public B getSecond() {
      return this.b;
   }
}

