package entity;

public class Contacts {
    private int id;
    private String phone;
    private String vkUrl;

    public Contacts(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacts contacts = (Contacts) o;

        if (id != contacts.id) return false;
        if (phone != null ? !phone.equals(contacts.phone) : contacts.phone != null) return false;
        return vkUrl != null ? vkUrl.equals(contacts.vkUrl) : contacts.vkUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (vkUrl != null ? vkUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", vkUrl='" + vkUrl + '\'' +
                '}';
    }
}
