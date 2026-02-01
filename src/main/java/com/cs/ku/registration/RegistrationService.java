package com.cs.ku.registration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void register(String username, String password) {
        if (userRepository.isUsernameAvailable(username)) {
            String hashedPassword = passwordEncoder.hash(password);
            userRepository.addUserToDatabase(username, hashedPassword);
        }
    }

}
