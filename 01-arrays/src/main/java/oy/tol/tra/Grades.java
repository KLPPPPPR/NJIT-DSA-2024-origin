package oy.tol.tra;

public class Grades<T extends Comparable<T>> {

   private Algorithms<T> algorithms;

   public Grades(T[] grades) {
      this.algorithms = new Algorithms<>(grades);
   }

   public void reverse() {
      algorithms.reverse();
   }

   public void sort() {
      algorithms.sort();
   }

   public T[] getArray() {
      return algorithms.getArray();
   }
}
