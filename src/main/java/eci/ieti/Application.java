package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.ToDoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.ToDo;
import eci.ieti.data.model.User;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class Application implements CommandLineRunner  {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    
    
    @Autowired 
    private UserRepository userRepository;
    
    @Autowired
    private ToDoRepository toDoRepository;
   
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    @Override
    public void run(String... args) throws Exception {

        customerRepository.deleteAll();
        
        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All ne   w mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
        System.out.println();
        
        
        userRepository.deleteAll();
        
        userRepository.save(new User("Alice Smith", "AliceSmith@mail.com"));
        userRepository.save(new User("Alice Smith", "AliceSmit2h@mail.com"));
        userRepository.save(new User("Bob Marley", "BobMarley@mail.com"));
        userRepository.save(new User("Jimmy Page", "JimmyPage@mail.com"));
        userRepository.save(new User("FreddyMercury", "FreddyMercury@mail.com"));
        userRepository.save(new User("MichaelJackson", "MichaelJackson@mail.com"));
        
        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        
        userRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
                
        System.out.println("Users found with findByEmail():");
        System.out.println("-------------------------------");
        
        System.out.println(userRepository.findByEmail("AliceSmith@mail.com"));
        System.out.println(userRepository.findByEmail("BobMarley@mail.com"));
        System.out.println();
        
        System.out.println("Users found with findByName():");
        System.out.println("-------------------------------");
        
        userRepository.findByName("Alice Smith").stream().forEach(System.out::println);
        System.out.println();
        
        toDoRepository.deleteAll();
                
        toDoRepository.save(new ToDo( "description 0" , 1, new Date(2020, Calendar.JANUARY, 18) , "AliceSmith@mail.com", "Done"));        
        toDoRepository.save(new ToDo( "description 1" , 2, new Date(2019, Calendar.FEBRUARY, 13) , "AliceSmith@mail.com", "Done"));
        toDoRepository.save(new ToDo( "description 2" , 3, new Date(2020, Calendar.MAY, 14) , "BobMarley@mail.com", "pending"));
        toDoRepository.save(new ToDo( "description 3" , 4, new Date(2020, Calendar.DECEMBER, 15) , "BobMarley@mail.com", "pending"));
        toDoRepository.save(new ToDo( "description 4" , 5, new Date(2020, Calendar.JULY, 16) , "JimmyPage@mail.com", "in progress"));
        toDoRepository.save(new ToDo( "description 5" , 6, new Date(2019, Calendar.JANUARY, 17) , "JimmyPage@mail.com", "in progress"));
        toDoRepository.save(new ToDo( "description 6" , 7, new Date(2020, Calendar.JUNE, 18) , "FreddyMercury@mail.com", "pending"));
        toDoRepository.save(new ToDo( "description 7" , 8, new Date(2019, Calendar.SEPTEMBER, 19) , "FreddyMercury@mail.com", "pending"));
        toDoRepository.save(new ToDo( "description 8" , 9, new Date(2019, Calendar.AUGUST, 20) , "MichaelJackson@mail.com", "Done"));
        toDoRepository.save(new ToDo( "description 9" , 10, new Date(2020, Calendar.MAY, 22) , "MichaelJackson@mail.com", "in progress"));
        
        System.out.println("Todo found with findAll():");
        System.out.println("-------------------------------");
        
        toDoRepository.findAll().forEach(System.out::println);
        System.out.println();
        
        System.out.println("Todo found with findByDescriptionContaining() page 1:");
        System.out.println("-------------------------------");
        
        toDoRepository.findByDescriptionContaining("description", PageRequest.of(0, 2)).stream().forEach(System.out::println);;
        System.out.println();
        
        System.out.println("Todo found with findByResponsible() page 2:");
        System.out.println("-------------------------------");
        
        toDoRepository.findByResponsible("MichaelJackson@mail.com", PageRequest.of(1, 1)).stream().forEach(System.out::println);;
        System.out.println();
        
    }
    

}