public abstract class Generator {

    /**
     * Creates a new Generator.
     *
     * @param aGenerator - the random number generator to feed this generator.
     */
    public Generator() {

    }

    /**
     * Get the name of the generator.
     *
     * @return the name of the generator
     */
    public abstract String getName();

    /**
     * Generates the next value.
     *
     * @return the next values
     */
    public abstract double next();


}
