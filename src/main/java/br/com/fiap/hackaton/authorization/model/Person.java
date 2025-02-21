package br.com.fiap.hackaton.authorization.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person", schema = "hackaton")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "cpf", nullable = false)
    private String cpf;
    
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne(mappedBy = "person")
    private User user;

    public Person() { }

    public Person(Integer age, String fullName, String cpf, String gender, String contact, String address) {
        this.age = age;
        this.fullName = fullName;
        this.cpf = cpf;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
