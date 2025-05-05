package healthcare.healthcare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "consultations")
public class Consultation 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String patientProblem;
    private String specialty;
    private String doctorAssigned;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    public Consultation() 
    {
    	
    }

    public Consultation(String patientName, String patientProblem, String specialty, String doctorAssigned, LocalDate appointmentDate, LocalTime appointmentTime) 
    {
        this.patientName = patientName;
        this.patientProblem = patientProblem;
        this.specialty = specialty;
        this.doctorAssigned = doctorAssigned;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    // ✅ Getters and Setters
    public Long getId() 
    { 
    	return id; 
    }
    
    public void setId(Long id) 
    { 
    	this.id = id; 
    }

    public String getPatientName() 
    { 
    	return patientName; 
    }
    
    public void setPatientName(String patientName) 
    { 
    	this.patientName = patientName; 
    }

    public String getPatientProblem() 
    { 
    	return patientProblem; 
    }
    
    public void setPatientProblem(String patientProblem) 
    { 
    	this.patientProblem = patientProblem; 
    }

    public String getSpecialty() 
    { 
    	return specialty; 
    }
    
    public void setSpecialty(String specialty) 
    { 
    	this.specialty = specialty; 
    }

    public String getDoctorAssigned() 
    { 
    	return doctorAssigned; 
    }
    
    public void setDoctorAssigned(String doctorAssigned) 
    { 
    	this.doctorAssigned = doctorAssigned; 
    }

    public LocalDate getAppointmentDate() 
    { 
    	return appointmentDate; 
    }
    
    public void setAppointmentDate(LocalDate appointmentDate) 
    { 
    	this.appointmentDate = appointmentDate; 
    }

    public LocalTime getAppointmentTime() 
    { 
    	return appointmentTime; 
    }
    
    public void setAppointmentTime(LocalTime appointmentTime) 
    { 
    	this.appointmentTime = appointmentTime; 
    }
}
