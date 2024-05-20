package zuhriddinscode.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zuhriddinscode.domain.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> { // Long edi

    Optional<UserEntity> getByEmail(String email); //asaxiy  Bobur


    // security
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Page<UserEntity> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "roles")
    UserEntity findByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    UserEntity findByActivationCode(String code);

    @Query("SELECT user.email FROM UserEntity user WHERE user.passwordResetCode = :code")
    Optional<String> getEmailByPasswordResetCode(String code);

    @EntityGraph(attributePaths = "roles")
    @Query("SELECT user FROM UserEntity user WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'firstName' THEN UPPER(user.firstName) " +
            "   WHEN :searchType = 'lastName' THEN UPPER(user.lastName) " +
            "   ELSE UPPER(user.email) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%'))")
    Page<UserEntity> searchUsers(String searchType, String text, Pageable pageable);
}