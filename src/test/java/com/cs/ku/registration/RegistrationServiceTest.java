package com.cs.ku.registration;

import com.cs.ku.registration.stub.UserRecord;
import com.cs.ku.registration.stub.UserRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private final Map<String, UserRecord> stubUserRecord = Map.ofEntries(
        Map.entry("Harry",new UserRecord("Harry Potter", "harry_password", "harry_hashed_pw")),
        Map.entry("Ron", new UserRecord("Ron Weasley", "ron_password", "ron_hashed_pw")),
        Map.entry("Hermione", new UserRecord("Hermione Granger", "hermione_password", "hermione_hashed_pw"))
    );

    @BeforeEach
    void setup() {
        openMocks(this);

        // Stub user for UserRepository (Using Spy to call real-method and still able to stub and verify)
        this.userRepository = spy(new UserRepositoryStub());
        for (UserRecord user : stubUserRecord.values()) {
            userRepository.addUserToDatabase(user.username(), user.hashPassword());
        }

        // Reset spy to clear interactions count from line 43
        reset(userRepository);

        // CUT
        registrationService = new RegistrationService(passwordEncoder, userRepository);
    }

    @Test
    void testRegister_success() {
        // Given
        var draco = new UserRecord("Draco Malfoy", "draco_password", "draco_hashed_pw");

        // Stub
        when(passwordEncoder.hash(draco.password())).thenReturn(draco.hashPassword());

        // When
        registrationService.register(draco.username(), draco.password());

        // Then
        verify(userRepository, times(1)).isUsernameAvailable(draco.username());
        verify(passwordEncoder, times(1)).hash(draco.password());
        verify(userRepository, times(1)).addUserToDatabase(draco.username(), draco.hashPassword());
    }

    @Test
    void testRegister_usernameNotAvailable() {
        // Given
        var harryUser = stubUserRecord.get("Harry");

        // Stub
        when(passwordEncoder.hash(harryUser.password())).thenReturn(harryUser.hashPassword());

        // When
        registrationService.register(harryUser.username(), harryUser.password());

        // Then
        verify(userRepository, times(1)).isUsernameAvailable(harryUser.username());
        verify(passwordEncoder, times(0)).hash(harryUser.password());
        verify(userRepository, times(0)).addUserToDatabase(harryUser.username(), harryUser.hashPassword());
    }

    @SuppressWarnings("java:S5778")
    @Test
    void testRegister_databaseException() {
        // Given
        var ron = stubUserRecord.get("Ron");

        // Stub
        when(userRepository.isUsernameAvailable(ron.username())).thenThrow(new RuntimeException("Database error"));

        // When and Then
        var exception = assertThrows(RuntimeException.class, () -> registrationService.register(ron.username(), ron.password()));
        assertEquals("Database error", exception.getMessage());

        verify(userRepository, times(1)).isUsernameAvailable(ron.username());
        verify(passwordEncoder, times(0)).hash(ron.password());
        verify(userRepository, times(0)).addUserToDatabase(ron.username(), "");
    }

}
