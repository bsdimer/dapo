package com.dapo.auth.service.impl;

import com.dapo.auth.dto.AuthorityUpdateRequest;
import com.dapo.auth.dto.UserUpdateRequest;
import com.dapo.auth.exceptions.OperationException;
import com.dapo.auth.service.UserService;
import com.dapo.common.authentication.StandardUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public class JpaUserService implements UserService<StandardUser> {

    @Override
    public StandardUser findByUsername(String username) {
        return null;
    }

    @Override
    public Page<StandardUser> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public StandardUser findByEmail(String email) {
        return null;
    }

    @Override
    public Page<StandardUser> findAllByNameContains(String namePart, Pageable pageable) {
        return null;
    }

    @Override
    public Page<StandardUser> findAllByEmailContains(String emailPart, Pageable pageable) {
        return null;
    }

    @Override
    public Page<StandardUser> findAllByNameAndEmailContains(String namePart, String emailPart, Pageable pageable) {
        return null;
    }

    @Override
    public List<StandardUser> findAll() {
        return null;
    }

    @Override
    public void save(StandardUser user, String remoteUrl) throws OperationException {

    }

    @Override
    public boolean userExist(String username) {
        return false;
    }

    @Override
    public boolean emailExist(String email) {
        return false;
    }

    @Override
    public void setValid(String username) {

    }

    @Override
    public String startRecovery(String email, String username, String passwordUrl) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void recovery(String nonce, String password) throws OperationException {

    }

    @Override
    public StandardUser authenticate(Authentication authentication) throws OperationException {
        return null;
    }

    @Override
    public void addDetail(String username, String key, String value) {

    }

    @Override
    public void updateDetail(String username, String key, String value) {

    }

    @Override
    public void addUserAuthority(String username, String authority) throws OperationException {

    }

    @Override
    public void removeUserAuthority(String username, String authority) throws OperationException {

    }

    @Override
    public void resendConfirmationEmail(String username) {

    }

    @Override
    public StandardUser update(UserUpdateRequest request) throws OperationException {
        return null;
    }

    @Override
    public StandardUser updateAuthorityAll(AuthorityUpdateRequest request) {
        return null;
    }

    @Override
    public StandardUser confirmRegistration(String nonce) {
        return null;
    }
}
