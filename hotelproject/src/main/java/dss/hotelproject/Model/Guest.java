package dss.hotelproject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String mobile;
    @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
    private String mail;
    @Column(unique = true)
    @Pattern(message = "Data is not correct: ${validatedValue}",
            regexp = "\\d{8}[A-HJ-NP-TV-Z]")
    private String DNI;


    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;

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

    public String getSurname() {
            return surname;
        }

    public void setSurname(String surname) {
            this.surname = surname;
        }

    public String getMobile() {
            return mobile;
        }

    public void setMobile(String mobile) {
            this.mobile = mobile;
        }

    public String getMail() {
            return mail;
        }

    public void setMail(String mail) {
            this.mail = mail;
        }

    public String getDNI() {
            return DNI;
        }

    public void setDNI(String DNI) {
            this.DNI = DNI;
        }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Guest() {
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", mail='" + mail + '\'' +
                ", DNI='" + DNI + '\'' +
                ", reservations=" + reservations +
                '}';
    }

    public static final class GuestBuilder {
        private Long id;
        private String name;
        private String surname;
        private String mobile;
        private String mail;
        private String DNI;

        public GuestBuilder() {
        }

        public static GuestBuilder aGuest() {
            return new GuestBuilder();
        }


        public GuestBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public GuestBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GuestBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public GuestBuilder withMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public GuestBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public GuestBuilder withDNI(String DNI) {
            this.DNI = DNI;
            return this;
        }

        public Guest build() {
            Guest guest = new Guest();
            guest.setId(id);
            guest.setName(name);
            guest.setSurname(surname);
            guest.setMobile(mobile);
            guest.setMail(mail);
            guest.setDNI(DNI);
            return guest;
        }
    }
}

