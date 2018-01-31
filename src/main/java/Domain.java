import business_logic.HibernateUtil;
import entity.Contact;
import entity.Project;
import entity.User;
import service.ContactService;
import service.ProjectService;
import service.UserService;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;

public class Domain {
    public static void main(String[] args) {
        ContactService contactService = new ContactService();
        ProjectService projectService = new ProjectService();
        UserService userService = new UserService();

        Contact contact = new Contact();
        contact.setPhone("+4612312");
        contact.setVkUrl("http://vk.com/durov");

        Project project = new Project();
        project.setTitle("VK");

        User user = new User();
        user.setName("Pasha");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1981, Calendar.JANUARY, 24);
        user.setBirthday(new Date(calendar.getTime().getTime()));
        user.setOs("Mac");
        user.setContact(contact);

        Set<User> users = new HashSet<>();
        users.add(user);
        project.setUsers(users);

        Set<Project> projects = new HashSet<>();
        projects.add(project);
        user.setProjects(projects);

        contactService.add(contact);
        userService.add(user);
        projectService.add(project);

        HibernateUtil.shutdown();
    }
}
