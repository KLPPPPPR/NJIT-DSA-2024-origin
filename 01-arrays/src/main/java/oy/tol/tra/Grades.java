package oy.tol.tra;
public class Grades {
   
   private Integer [] grades = null;

   public Grades(Integer [] grades) {
      this.grades = new Integer [grades.length];
      for (int counter = 0; counter < grades.length; counter++) {
         this.grades[counter] = grades[counter];
      }
   }

   public void reverse() {
      /* TODO:
       1. Edit the test data files to see if the reverse() really works or not.
       2. Execute the IntArrayTests to see that some of them fail.
       3. Study the code below and try to find what is the issue.
       4. Use the debugger to see the execution and variable values if necessary.
       5. Fix the issue.
       6. Transform the algorithm to <strong>use</strong> the generic one from Algorithms.java, as instructed in the readme file.
      */
      int i = 0;
      while (i < grades.length/2) {
         int temp = grades[i];
         grades[i] = grades[grades.length-i-1];
         grades[grades.length-i-1] = temp;
         i++;
     }
   }

   public void sort() {
      for (int i = 1; i < grades.length; i++) {
         int key = grades[i];
         int j = i - 1;
         while (j >= 0 && grades[j] > key) {
            grades[j + 1] = grades[j];
            j = j - 1;
         }
         grades[j + 1] = key;
      }
   }

   public Integer [] getArray() {
      return grades;
   }
}
