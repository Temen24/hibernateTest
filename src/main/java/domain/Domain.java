package domain;

import entity.Contact;
import entity.Project;
import entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.ContactRepository;
import repository.ProjectRepository;
import repository.UserRepository;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Domain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ContactRepository contactRepository = context.getBean(ContactRepository.class);
        ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setPhone("+4612312");
        contact.setVkUrl("http://vk.com/durov");

        Project project = new Project();
        project.setId(1L);
        project.setTitle("VK");

        User user = new User();
        user.setId(1L);
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

        contactRepository.save(contact);
        projectRepository.save(project);
        userRepository.save(user);

        System.out.println(userRepository.findByOs("Mac"));
    }
}
