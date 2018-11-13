package ite.librarymaster.domain.model;


import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
public class ApplicationUser {
    @Id
    private Name dn;

    @Attribute(name = "cn")
    private String fullName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "uid")
    private String username;

    @Attribute(name = "userPassword", type = Attribute.Type.BINARY)
    private byte[] password;

    @Transient
    @DnAttribute(value = "dc", index = 0)
    private String country;

    @Transient
    @DnAttribute(value = "dc", index = 1)
    private String company;


    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return new String(password);
    }

    public void setPassword(String password) {
        this.password = password.getBytes();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApplicationUser{");
        sb.append("dn=").append(dn);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", password='").append(new String(password)).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

