package com.dapo.auth.service;

import com.dapo.auth.dto.AuthorityUpdateRequest;
import com.dapo.auth.dto.UserUpdateRequest;
import com.dapo.auth.exceptions.OperationException;
import com.dapo.common.authentication.StandardUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created by dimer on 17.10.16.
 */
public interface UserService<T> {

    T findByUsername(String username);

    Page<T> findAll(Pageable pageable);

    T findByEmail(String email);

    Page<T> findAllByNameContains(String namePart, Pageable pageable);

    Page<T> findAllByEmailContains(String emailPart, Pageable pageable);

    Page<T> findAllByNameAndEmailContains(String namePart, String emailPart, Pageable pageable);

    List<T> findAll();

    void save(T user, String remoteUrl) throws OperationException;

    boolean userExist(String username);

    boolean emailExist(String email);

    void setValid(String username);

    String startRecovery(String email, String username, String passwordUrl) throws UsernameNotFoundException;

    void recovery(String nonce, String password) throws OperationException;

    T authenticate(Authentication authentication) throws OperationException;

    void addDetail(String username, String key, String value);

    void updateDetail(String username, String key, String value);

    void addUserAuthority(String username, String authority) throws OperationException;

    void removeUserAuthority(String username, String authority) throws OperationException;

    void resendConfirmationEmail(String username);

    T update(UserUpdateRequest request) throws OperationException;

    T updateAuthorityAll(AuthorityUpdateRequest request);

    T confirmRegistration(String nonce);
}
