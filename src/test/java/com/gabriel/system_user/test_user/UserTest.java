package com.gabriel.system_user.test_user;

import com.gabriel.system_user.model.User;
import com.gabriel.system_user.repository.UserRepository;
import com.gabriel.system_user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("Salvar o usuário")
    void save() {

        User user = User.builder()
                .id(1L)
                .name("Gabriel")
                .lastName("Lucas")
                .email("contatohatz7@gmail.com")
                .password("gulosos123")
                .cpf("12331231211")
                .telephone("81988599899")
                .status("ACTIVE")
                .isActive(true)
                .build();

        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepository.existsByCpf(any(String.class))).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saveService = userService.save(user);

        assertNotNull(saveService);
        assertEquals("Gabriel", saveService.getName());
        assertEquals("contatohatz7@gmail.com", saveService.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("atualizar o usuário")
    void test() {

        User existsUser = User.builder()
                .id(1L)
                .name("Gabriel")
                .lastName("Lucas")
                .email("contatohatz7@gmail.com")
                .password("guloso123")
                .cpf("12331231211")
                .telephone("8198859899")
                .status("ACTIVE")
                .isActive(true)
                .build();

        User updateUser = User.builder()
                .id(1L)
                .name("Gabriel Lucas")
                .lastName("Silva")
                .email("contatop@gmail.com")
                .password("gabrielpk1")
                .cpf("12331231211")
                .telephone("81988599899")
                .status("ACTIVE")
                .isActive(true)
                .build();

        lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(existsUser));
        lenient().when(userRepository.findByEmail("contatohatz7@gmail.com")).thenReturn(Optional.of(existsUser));
        lenient().when(userRepository.findByCpf("12331231211")).thenReturn(Optional.of(existsUser));
        lenient().when(userRepository.save(any(User.class))).thenReturn(updateUser);

        User updateService = userService.save(updateUser);

        assertNotNull(updateService);
        assertEquals("Gabriel Lucas", updateService.getName());
        assertEquals("contatop@gmail.com", updateService.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Deleter usuário")
    void delete() {

        lenient().doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);

    }
}
