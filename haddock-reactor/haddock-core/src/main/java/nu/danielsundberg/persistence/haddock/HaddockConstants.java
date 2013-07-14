package nu.danielsundberg.persistence.haddock;

/**
 * Constant variables for Haddock Persistence
 */
public final class HaddockConstants {

    /**
     * Package name.
     */
    private static final String HADDOCK_PERSISTENCE = "nu.danielsundberg.persistence.haddock.ejb.HaddockPersistence";

    /**
     * Public framework version.
     */
    private static final String PUBLIC_VERSION = "0.0.1";

    /**
     * Hide public constructor.
     */
    private HaddockConstants() {}


    /**
     * Returns constant current public version
     * @return version of persistence framework
     */
    public static String getPublicVersion() {
        return PUBLIC_VERSION;
    }

    /**
     * Returns constant Haddock Persistence package name
     * @return package name of framework
     */
    public static String getHaddockPersistence() {
        return HADDOCK_PERSISTENCE;
    }

}
