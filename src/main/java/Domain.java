import business_logic.Util;
import entity.Contact;
import entity.Project;
import entity.User;
import entity.UserProj;
import service.ContactService;
import service.ProjectService;
import service.UserProjService;
import service.UserService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Domain {
    public static void main(String[] args) {
        ContactService contactService = new ContactService();
        ProjectService projectService = new ProjectService();
        UserProjService userProjService = new UserProjService();
        UserService userService = new UserService();

        Contact contact = new Contact();
        contact.setId(1);
        contact.setPhone("+4612312");
        contact.setVkUrl("http://vk.com/durov");

        Project project = new Project();
        project.setId(1);
        project.setTitle("VK");

        User user = new User();
        user.setId(1);
        user.setName("Pasha");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1981, Calendar.JANUARY, 24);
        user.setBirthday(new Date(calendar.getTime().getTime()));
        user.setOs("Mac");

        UserProj userProj = new UserProj();
        userProj.setUserID(user.getId());
        userProj.setProjID(project.getId());

        contactService.add(contact);
        projectService.add(project);
        userProjService.add(userProj);
        userService.add(user);
    }
}
