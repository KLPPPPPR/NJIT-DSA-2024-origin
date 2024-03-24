package oy.tol.tra;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;

    public Person(final Person person) {
        this.firstName = person.firstName;
        this.lastName = person.lastName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    /**
     * Calculates the hash value of the person based on their first and last names.
     *
     * @return Hash value of the person.
     */
    @Override
    public int hashCode() {
        int hash = 17;
        hash = (hash * 31) + firstName.hashCode();
        hash = (hash * 31) + lastName.hashCode();
        return Math.abs(hash);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Person person = (Person) other;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    /**
     * Compares two persons based on their last names and then first names.
     *
     * @param other The other person to compare to.
     * @return Returns a negative integer, zero, or a positive integer as this person
     *         is less than, equal to, or greater than the specified person.
     */
    @Override
    public int compareTo(Person other) {
        int lastNameComparison = lastName.compareTo(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return firstName.compareTo(other.firstName);
    }
}
