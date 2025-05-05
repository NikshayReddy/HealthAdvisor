package healthcare.healthcare.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import healthcare.healthcare.entity.Consultation;
import healthcare.healthcare.entity.Doctor;
import healthcare.healthcare.entity.User;
import healthcare.healthcare.repository.ConsultationRepository;
import healthcare.healthcare.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ParentController 
{	
    @Autowired
    private UserRepository userRepository;
    
    //Handler for HomePage
    @GetMapping("/")
    public String show(Model model)
    {
        User latestUser = userRepository.findTopByOrderByUseridDesc();

        if (latestUser != null) {
            model.addAttribute("username", latestUser.getName());
        } else {
            model.addAttribute("username", "Guest");
        }

        return "home"; 
    }
    
    //Handler for Registration Page
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registrationPage";
    }

    //Handler to lead to Login Page
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) 
    {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("message", "Username already exists. Choose a different one.");
            return "registration";
        }
        
        String hashpwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashpwd);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "Data Saved Successfully! Please log in.");
        return "loginPage";
    }

    //Handler for Login Page
    @GetMapping("/loginPage")
    public String LoginPage(Model model) 
    {
        model.addAttribute("user", new User());  
        return "loginPage";
    }

    //Handler to Validate Login Page
    @PostMapping("/login")
    public String LoginProcess(@ModelAttribute("user") User user, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) 
    {
        User dbUser = userRepository.findByUsername(username);

        if (dbUser == null) 
        {
            redirectAttributes.addFlashAttribute("message", "User not found!");
            return "redirect:/loginPage"; 
        }

        Boolean isPwdMatch = BCrypt.checkpw(password, dbUser.getPassword());

        if (isPwdMatch) 
        {  
            session.setAttribute("username", dbUser.getUsername()); 
            session.setAttribute("name", dbUser.getName()); 

            redirectAttributes.addFlashAttribute("message", "Login Successful!");
            return "redirect:/mainpage";  
        } 
        else 
        {
            redirectAttributes.addFlashAttribute("message", "Failed To Login");
            return "redirect:/loginPage";  
        }
    }

    //Handler for Main Page
    @GetMapping("/mainpage")
    public String mainPage(Model model, HttpSession session) 
    {
        String name = (String) session.getAttribute("name");
        System.out.println("Retrieved name from session: " + name); 

        if (name != null) 
        {
            model.addAttribute("username", name);
        } 
        else 
        {
            model.addAttribute("username", "Guest");
        }

        return "mainpage";  
    }

    //Handler for Doctors On Board
    @GetMapping("/doctors")
    public String getDoctors(Model model) 
    {
        List<Doctor> doctors = List.of(
            new Doctor("Dr. John Smith", "Cardiologist", "9 AM - 3 PM", "+1 123 456 789"),
            new Doctor("Dr. Sarah Johnson", "Neurologist", "10 AM - 4 PM", "+1 987 654 321"),
            new Doctor("Dr. Emily Davis", "Pediatrician", "8 AM - 2 PM", "+1 456 789 123"),
            new Doctor("Dr. Daniel Brown", "Dermatologist", "11 AM - 5 PM", "+1 789 123 456"),
            new Doctor("Dr. Olivia Wilson", "Orthopedic", "7 AM - 1 PM", "+1 321 654 987"),
            new Doctor("Dr. Benjamin White", "Endocrinologist", "12 PM - 6 PM", "+1 654 987 321"),
            new Doctor("Dr. Sophia Martinez", "Gastroenterologist", "9 AM - 3 PM", "+1 234 567 890"),
            new Doctor("Dr. Ethan Harris", "Oncologist", "12 PM - 6 PM", "+1 222 333 444"),
            new Doctor("Dr. Ava Roberts", "Rheumatologist", "9 AM - 2 PM", "+1 444 555 666"),
            new Doctor("Dr. William Carter", "Pulmonologist", "8 AM - 1 PM", "+1 777 888 999"),
            new Doctor("Dr. Isabella Green", "Ophthalmologist", "10 AM - 4 PM", "+1 666 777 888"),
            new Doctor("Dr. James Walker", "Urologist", "1 PM - 7 PM", "+1 333 444 555"),
            new Doctor("Dr. Charlotte Nelson", "Gynecologist", "9 AM - 3 PM", "+1 555 666 777"),
            new Doctor("Dr. Henry Adams", "General Physician", "7 AM - 1 PM", "+1 111 222 999")
        );

        model.addAttribute("doctors", doctors);
        return "doctors"; 
    }
    
    //Handler for Book Consultation
    @GetMapping("/bookConsultation")
    public String bookConsultationPage(Model model, HttpSession session) 
    {
        String username = (String) session.getAttribute("name");
        if (username == null) {
            username = "Guest";
        }
        model.addAttribute("username", username);

        Map<String, List<String>> doctorList = new HashMap<>();
        doctorList.put("Cardiology", Arrays.asList("Dr. John Smith", "Dr. Sophia Martinez"));
        doctorList.put("Neurology", Arrays.asList("Dr. Sarah Johnson", "Dr. Ethan Harris"));
        doctorList.put("Pediatrics", Arrays.asList("Dr. Emily Davis"));
        doctorList.put("Dermatology", Arrays.asList("Dr. Daniel Brown"));
        doctorList.put("Orthopedic", Arrays.asList("Dr. Olivia Wilson"));
        doctorList.put("Endocrinology", Arrays.asList("Dr. Benjamin White"));
        doctorList.put("Gastroenterology", Arrays.asList("Dr. Sophia Martinez"));
        doctorList.put("Oncology", Arrays.asList("Dr. Ethan Harris"));
        doctorList.put("Rheumatology", Arrays.asList("Dr. Ava Roberts"));
        doctorList.put("Pulmonology", Arrays.asList("Dr. William Carter"));
        doctorList.put("Ophthalmology", Arrays.asList("Dr. Isabella Green"));
        doctorList.put("Urology", Arrays.asList("Dr. James Walker"));
        doctorList.put("Gynecology", Arrays.asList("Dr. Charlotte Nelson"));
        doctorList.put("General Medicine", Arrays.asList("Dr. Henry Adams"));

        model.addAttribute("doctorList", doctorList);
        
        return "book-consultation"; 
    }
    
    @Autowired
    private ConsultationRepository consultationRepository;

    // Save Consultation
    @PostMapping("/bookConsultation")
    public String saveConsultation(HttpSession session, @RequestParam(defaultValue = "No description provided") String patientProblem, @RequestParam(defaultValue = "General Medicine") String specialty, @RequestParam(defaultValue = "No doctor assigned") String doctorAssigned, @RequestParam(required = false) LocalDate appointmentDate, @RequestParam(required = false) LocalTime appointmentTime, RedirectAttributes redirectAttributes) 
    {
        String patientName = (String) session.getAttribute("username"); 
        if (patientName == null) {
            return "loginPage"; 
        }

        if (appointmentDate == null) {
            appointmentDate = LocalDate.now().plusDays(1);
        }
        if (appointmentTime == null) {
            appointmentTime = LocalTime.of(9, 0);
        }

        Consultation consultation = new Consultation(patientName, patientProblem, specialty, doctorAssigned, appointmentDate, appointmentTime);
        consultationRepository.save(consultation);

        redirectAttributes.addFlashAttribute("message", "Consultation booked successfully!");
        return "view-appointments";
    }

    // View Appointments Page
    @GetMapping("/view-appointments")
    public String viewAppointments(Model model, HttpSession session) 
    {
        String username = (String) session.getAttribute("username"); 

        if (username == null) 
        {
            return "loginPage"; 
        }

        List<Consultation> userConsultations = consultationRepository.findByPatientName(username);
        model.addAttribute("consultations", userConsultations);

        return "view-appointments"; 
    }
    
    @PostMapping("/view-appointments")
    public String postViewAppointments(HttpSession session, Model model) 
    {
        String patientName = (String) session.getAttribute("patientName"); 

        if (patientName == null) {
            return "login";  
        }

        List<Consultation> userConsultations = consultationRepository.findByPatientName(patientName);
        
        if (userConsultations.isEmpty()) 
        {
            model.addAttribute("message", "No appointments found.");
        } 
        else 
        {
            model.addAttribute("consultations", userConsultations);
        }

        return "view-appointments"; 
    }

}
