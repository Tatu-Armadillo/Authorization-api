package br.com.fiap.hackaton.authorization.model;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission", schema = "hackaton")
public class Permission implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    @Column(name = "description")
    private String description;

    public Permission() { }

    public Permission(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
