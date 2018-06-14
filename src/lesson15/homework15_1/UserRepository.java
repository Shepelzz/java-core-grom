package lesson15.homework15_1;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User save(User user){
        if(user == null)
            return null;

        if(findUser(user.getId()) == null){
            for(int i = 0; i < users.length; i++){
                if( users[i] == null) {
                    users[i] = user;
                    return user;
                }
            }
        }
        return null;
    }

    public User update(User user){
        for(int i = 0; i < users.length; i++){
            if(users[i].equals(user)){
                users[i] = user;
                return user;
            }
        }
        return null;
    }

    public void delete(long id){
        for(int i = 0; i < users.length; i++){
            if(!users[i].equals(null) && users[i].getId() == id){
                users[i] = null;
                break;
            }
        }
    }

    public User findUser(long id){
        User result = null;

        for(User user : users){
            if( user != null && user.getId() == id){
                result = user;
                break;
            }
        }
        return result;
    }


    public User[] getUsers() {
        return users;
    }

    public String[] getUserNames(){
        String[] userNamesList = new String[users.length];
        for(int i = 0; i < userNamesList.length; i++){
            if(users[i] != null)
                userNamesList[i] = users[i].getName();
        }
        return userNamesList;
    }

    public long[] getUserIds(){
        int idxCount = 0;
        for(User user : users){
            if(user != null)
                idxCount++;
        }
        long[] idsList = new long[idxCount];
        for(User user : users){
            for(int i = 0; i < idsList.length; i++){
                if(idsList[i] == 0 && user != null){
                    idsList[i] = user.getId();
                    break;
                }
            }
        }
        return idsList;
    }

    public String getUserNameById(long id){
        String userName = null;
        for(User user : users){
            if(user != null && user.getId() == id) {
                return user.getName();
            }
        }
        return userName;
    }

    public User getUserByName(String name){
        User result = null;

        for(User user : users){
            if( user != null && user.getName() == name){
                result = user;
                break;
            }
        }
        return result;
    }

    public User getUserBySessionId(String session){
        User result = null;

        for(User user : users){
            if( user != null && user.getSessionId() == session){
                result = user;
                break;
            }
        }
        return result;
    }
}
