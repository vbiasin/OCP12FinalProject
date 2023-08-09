package com.mysmarthome.mysmarthomeadministration;

import com.mysmarthome.mysmarthomeadministration.DAO.RoleRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.UserAccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;

import com.mysmarthome.mysmarthomeadministration.Entites.Role;
import com.mysmarthome.mysmarthomeadministration.Entites.UserAccount;
import com.mysmarthome.mysmarthomeadministration.Services.UserAccountServiceImpl;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAccountServiceImplTest {
    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserAccountServiceImpl userAccountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    @DisplayName("Should register user account successfully")
    public void shouldRegisterUserAccountSuccessfully() throws Exception {
        // Given
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        Optional<Role> defaultRole = Optional.of(role);

        UserAccount account = new UserAccount();
        account.setId(1);
        account.setMail("test@test.com");
        account.setPassword("password");

        Optional<UserAccount> newUser = Optional.empty();

        Collection<Role> roles = new ArrayList<Role>();
        roles.add(defaultRole.get());

        when(roleRepository.findByName("USER")).thenReturn(defaultRole);
        when(userAccountRepository.findByMail("test@test.com")).thenReturn(newUser);
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userAccountRepository.save(account)).thenReturn(account);

        // When
        UserAccount savedAccount = userAccountService.register(account);

        // Then
        assertNotNull(savedAccount);
        assertEquals(account.getId(), savedAccount.getId());
        assertEquals(account.getMail(), savedAccount.getMail());
        assertEquals("encodedPassword", savedAccount.getPassword());
        assertEquals(roles, savedAccount.getRoles());
    }

    @Test
    @DisplayName("Should throw an exception when default role is not found")
    public void shouldThrowExceptionWhenDefaultRoleNotFound() throws Exception {
        // Given
        Optional<Role> defaultRole = Optional.empty();

        UserAccount account = new UserAccount();
        account.setMail("test@test.com");
        account.setPassword("password");

        when(roleRepository.findByName("USER")).thenReturn(defaultRole);

        // When, Then
        assertThrows(Exception.class, () -> userAccountService.register(account));
    }

    @Test
    @DisplayName("Should throw an exception when user with the same mail already exists")
    public void shouldThrowExceptionWhenUserWithSameMailExists() throws Exception {
        // Given
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        Optional<Role> defaultRole = Optional.of(role);

        UserAccount account = new UserAccount();
        account.setMail("test@test.com");
        account.setPassword("password");

        Optional<UserAccount> newUser = Optional.of(account);

        when(roleRepository.findByName("USER")).thenReturn(defaultRole);
        when(userAccountRepository.findByMail("test@test.com")).thenReturn(newUser);

        // When, Then
        assertThrows(Exception.class, () -> userAccountService.register(account));
    }


    @Test
    void testIsValidWithCorrectCredentials() throws Exception {
        // Arrange
        String mail = "user@test.com";
        String password = "password";
        UserAccount userAccount = new UserAccount(mail,password);
        userAccount.setActive(true);
        when(userAccountRepository.findByMail(mail)).thenReturn(Optional.of(userAccount));

        // Act
        boolean result = userAccountService.isValid(userAccount);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValidWithIncorrectCredentials() {
        // Arrange
        String mail = "user@test.com";
        String password = "password";
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        UserAccount userAccount = new UserAccount(mail, encodedPassword);
        userAccount.setActive(true);
        when(userAccountRepository.findByMail(mail)).thenReturn(Optional.of(userAccount));

        // Act & Assert
        assertThrows(Exception.class, () -> {
            userAccountService.isValid(new UserAccount(mail, "wrong_password"));
        });
    }

    @Test
    void testIsValidWithNonexistentUser() {
        // Arrange
        String mail = "user@test.com";
        when(userAccountRepository.findByMail(mail)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> {
            userAccountService.isValid(new UserAccount(mail, "password"));
        });
    }

    @Test
    void testGetUserAccount() throws Exception {
        // given
        String mail = "test@test.com";
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1);
        userAccount.setMail(mail);
        Optional<UserAccount> optionalUserAccount = Optional.of(userAccount);
        when(userAccountRepository.findByMail(mail)).thenReturn(optionalUserAccount);

        // when
        UserAccount result = userAccountService.getUserAccount(mail);

        // then
        assertEquals(userAccount, result);
    }

    @Test
    void testGetUserAccountWhenMailNotFound() {
        // given
        String mail = "test@test.com";
        Optional<UserAccount> optionalUserAccount = Optional.empty();
        when(userAccountRepository.findByMail(mail)).thenReturn(optionalUserAccount);

        // when and then
        assertThrows(Exception.class, () -> {
            userAccountService.getUserAccount(mail);
        });
    }

    @Test
    void testRemoveUserAccount() throws Exception {
        // Créer un objet UserAccount pour le test
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1);


        // Configurer le mock pour renvoyer l'utilisateur
        when(userAccountRepository.findById(userAccount.getId())).thenReturn(Optional.of(userAccount));

        // Appeler la méthode à tester
        userAccountService.removeUserAccount(userAccount.getId());

        // Vérifier que la méthode de suppression de compte utilisateur
        verify(userAccountRepository, times(1)).delete(userAccount);
    }

    @Test
    void testSearchUserAccountWithEmailExists() throws Exception {
        String email = "test@example.com";
        UserAccount userAccount = new UserAccount();
        userAccount.setMail(email);

        when(userAccountRepository.findByMail(email)).thenReturn(Optional.of(userAccount));

        List<UserAccount> result = userAccountService.searchUserAccount(email);

        assertEquals(1, result.size());
        assertEquals(userAccount, result.get(0));
    }

    @Test
    void testSearchUserAccountWithEmailDoesNotExist() throws Exception {
        String email = "nonexistent@example.com";

        when(userAccountRepository.findByMail(email)).thenReturn(Optional.empty());

        List<UserAccount> result = userAccountService.searchUserAccount(email);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchUserAccountWithTotoEmail() throws Exception {
        String totoEmail = "toto@exemple.com";
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount());
        userAccounts.add(new UserAccount());

        when(userAccountRepository.findAll()).thenReturn(userAccounts);

        List<UserAccount> result = userAccountService.searchUserAccount(totoEmail);

        assertEquals(userAccounts, result);
    }


}
