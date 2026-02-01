package com.cs.ku.registration.stub;

import com.cs.ku.registration.UserRepository;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UserRepositoryStub implements UserRepository {

    private final Map<String, UserRecord> inMemoryDatabase = new HashMap<>();

    @Override
    public boolean isUsernameAvailable(String username) {
        return inMemoryDatabase.get(username) == null;
    }

    @Override
    public void addUserToDatabase(String username, String hashPassword) {
        inMemoryDatabase.put(username, new UserRecord(username, "", hashPassword));
    }

}
