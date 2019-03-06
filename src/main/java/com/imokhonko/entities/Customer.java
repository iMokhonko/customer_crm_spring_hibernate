package com.imokhonko.entities;

import com.imokhonko.entities.validationRules.Сensorship;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
@Entity
@Table(name = "customer")
public class Customer {

    @XmlAttribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false)
    private int customerId;

    @XmlElement
    @Column(name = "first_name")
    @NotEmpty(message = "this field should not be empty")
    @Сensorship(forbiddenSubstrings = "KEK", message = "this word is not allowed")
    private String firstName;

    @XmlElement
    @Column(name = "last_name")
    @NotEmpty(message = "this field should not be empty")
    @Сensorship(forbiddenSubstrings = "KEK", message = "this word is not allowed")
    private String lastName;

    @XmlElement
    @Column(name = "email")
    @NotEmpty(message = "this field should not be empty")
    @Pattern(
            regexp = "^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$",
            message = "incorrect email"
    )
    @Сensorship(forbiddenSubstrings = "KEK", message = "this word is not allowed")
    private String email;

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerException{" + "customerId=" + customerId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
