package entity;


import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "VK_URL")
    private String vkUrl;

    public Contact(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVkUrl() {
        return vkUrl;
    }

    public void setVkUrl(String vkUrl) {
        this.vkUrl = vkUrl;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", vkUrl='" + vkUrl + '\'' +
                '}';
    }
}
