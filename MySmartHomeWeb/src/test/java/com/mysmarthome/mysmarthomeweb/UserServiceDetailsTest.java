package com.mysmarthome.mysmarthomeweb;

import com.mysmarthome.mysmarthomeweb.DAO.UserAccountRepository;
import com.mysmarthome.mysmarthomeweb.Entites.Role;
import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import com.mysmarthome.mysmarthomeweb.Services.UserServiceDetails;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class UserServiceDetailsTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserServiceDetails userServiceDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsername_UserFoundAndActive_ReturnsUserDetails() {
        // Arrange
        String mail = "john@example.com";
        String password = "password";
        Role role = new Role("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        UserAccount userAccount = new UserAccount(mail, password);
        Optional<UserAccount> optionalUserAccount = Optional.of(userAccount);
        userAccount.setActive(true);
        userAccount.setRoles(roles);
        when(userAccountRepository.findByMail(mail)).thenReturn(optionalUserAccount);

        // Act
        UserDetails result = userServiceDetails.loadUserByUsername(mail);

        // Assert
        assertEquals(mail, result.getUsername());
        assertEquals(password, result.getPassword());
        assertEquals(1, result.getAuthorities().size());
        assertEquals("ROLE_USER", result.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    public void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        // Arrange
        String mail = "john@example.com";
        Optional<UserAccount> optionalUserAccount = Optional.empty();
        when(userAccountRepository.findByMail(mail)).thenReturn(optionalUserAccount);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userServiceDetails.loadUserByUsername(mail);
        });
    }

    @Test
    public void loadUserByUsername_UserFoundButInactive_ThrowsUsernameNotFoundException() {
        // Arrange
        String mail = "john@example.com";
        String password = "password";
        Role role = new Role("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        UserAccount userAccount = new UserAccount(mail, password);
        Optional<UserAccount> optionalUserAccount = Optional.of(userAccount);
        userAccount.setActive(false);
        userAccount.setRoles(roles);
        when(userAccountRepository.findByMail(mail)).thenReturn(optionalUserAccount);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userServiceDetails.loadUserByUsername(mail);
        });
    }
}
