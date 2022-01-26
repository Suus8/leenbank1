package project.demo.repositories;


/**
 * Singleton class for using different repositories
 */
public class RepoManager {
    //region ATTRIBUTES
    private static RepoManager repoManager;

    //endregion

    // CONSTRUCTOR
    private RepoManager() {

    }

    //region METHODS
//    public boolean containsAbstractClient(String username){
//        return abstractClientRepo.findAbstractClientByUsername(username);
//    }
//
//    public AbstractClient getAbstractClientByUsername(String username){
//        return abstractClientRepo.getAbstractClientByUsername(username);
//    }
    //endregion

    //region GETTERS & SETTERS
    public static RepoManager getRepoManager() {
        if (repoManager == null) {
            repoManager = new RepoManager();
        }
        return repoManager;
    }

    //endregion
}
