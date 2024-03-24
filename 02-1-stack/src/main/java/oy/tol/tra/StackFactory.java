package oy.tol.tra;

/**
 * This class instantiates different types of stacks implementing the {@code StackInterface} interface.
 * <p>
 * TODO: Students, implement the createCharacterStack method for instantiating {@code StackImplementation<Character>}
 * objects in the TASK-2 of this exercise.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public class StackFactory {

   private StackFactory() {
   }

    public static StackInterface<Integer> createIntegerStack() {
      return new StackImplementation<>();
   }

   public static StackInterface<Integer> createIntegerStack(int capacity) {
      return new StackImplementation<>(capacity);
   }
}
