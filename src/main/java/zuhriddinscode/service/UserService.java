package zuhriddinscode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zuhriddinscode.domain.dto.UserDto;
import zuhriddinscode.domain.model.Role;
import zuhriddinscode.domain.model.UserEntity;
import zuhriddinscode.exp.ServerBadRequestException;
import zuhriddinscode.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository repository;

    public UserDto createe(UserDto dto ){
        if(!isValid(dto)){
            throw new ServerBadRequestException("Incoming data is incorrect");
        }

        Optional<UserEntity> optional = repository.getByEmail(dto.getEmail());
        if( optional.isPresent()){
               // return null;   bazaga saqlanmaydi
             throw new ServerBadRequestException("Email already exsists");

        }

        UserEntity entity = new UserEntity();

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());

        entity.setContact(dto.getContact());
        entity.setRole(dto.getRole());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(LocalDateTime.now());

        this.repository.save(entity);

        dto.setUserid(entity.getUserid());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
    public boolean isValid( UserDto dto ){
        if (dto.getEmail() == null  || dto.getEmail().isEmpty() || dto.getEmail().trim().length()<6 ){
            return false;
        } else if (dto.getName()== null || dto.getName().isEmpty() || dto.getEmail().length()<3) {
            return false;
        }
        return true;
    }



// security

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public UserEntity create(UserEntity user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserEntity getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}