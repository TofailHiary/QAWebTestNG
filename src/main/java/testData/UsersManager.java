package testData;

public class UsersManager {

    public String username;
    public String password;

    public static UsersManager userValid;
    public static UsersManager userInvalid;
    /**
     * Constructor for the UsersManager class.
     * Initializes a new instance of the UsersManager.
     */
    public UsersManager() {
        // Constructor content
    }

    /**
     * Loads and initializes predefined valid and invalid user credentials.
     * This method should be called before getting user objects to ensure they are initialized.
     */
    public static void loadUsers() {
        // Valid user
        userValid = new UsersManager();
        userValid.username = "Admin";
        userValid.password = "admin123";

        // Invalid user
        userInvalid = new UsersManager();
        userInvalid.username = "Admin";
        userInvalid.password = "Admin";
    }

    /**
     * Provides a UsersManager object based on the specified user type.
     * <p>
     * This method loads predefined users and returns either a valid or invalid user object 
     * based on the input string. If no matching type is found, returns null.
     * </p>
     *
     * @param user A string representing the type of user to retrieve. Can be 'uservalid' or 'userinvalid'.
     * @return A UsersManager object corresponding to the specified user type or null if no match is found.
     */
    public static UsersManager getUserObject(String user) {
        loadUsers(); // Ensure users are loaded before fetching
        switch (user.toLowerCase()) {
            case "uservalid":
                return userValid;
            case "userinvalid":
                return userInvalid;
            default:
                return null; // If no matching case, return null
        }
    }
}
