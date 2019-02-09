package com.dapo.auth.model;
import com.dapo.auth.common.oauth2.AuthProvider;
import com.dapo.auth.common.oauth2.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column
    private Boolean agreedStandardUser = false;

    @Column
    private Boolean agreedCollectInformation = false;

    @Column
    private Boolean agreedAgentContactMe = false;

    private String phone;

    @JsonIgnore
    private String password;

    @OneToMany
    private List<UserProperty> properties;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<UserProperty> properties) {
        this.properties = properties;
    }

    public Boolean getAgreedStandardUser() {
        return agreedStandardUser;
    }

    public void setAgreedStandardUser(Boolean agreedStandardUser) {
        this.agreedStandardUser = agreedStandardUser;
    }

    public Boolean getAgreedCollectInformation() {
        return agreedCollectInformation;
    }

    public void setAgreedCollectInformation(Boolean agreedCollectInformation) {
        this.agreedCollectInformation = agreedCollectInformation;
    }

    public Boolean getAgreedAgentContactMe() {
        return agreedAgentContactMe;
    }

    public void setAgreedAgentContactMe(Boolean agreedAgentContactMe) {
        this.agreedAgentContactMe = agreedAgentContactMe;
    }
}
