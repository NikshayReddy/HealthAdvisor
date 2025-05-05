package healthcare.healthcare.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

    @Column(nullable = false)
    private String name;

    public String getName() 
    {  
        return name;
    }

    public void setName(String name) 
    {  
        this.name = name;
    }
    
    @Column(unique = true, nullable = false)
    private String username;
    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    @Column(nullable = false)
    private String password;
    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    private String gender;
    public String getGender() 
    {
        return gender;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    private LocalDate dob;  
    public LocalDate getDob() 
    {
        return dob;
    }

    public void setDob(LocalDate dob) 
    {
        this.dob = dob;
    }

    @ElementCollection
    @CollectionTable(name = "user_health_conditions", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "health_condition")
    private List<String> healthConditions;

    public List<String> getHealthConditions() 
    { 
        return healthConditions;
    }

    public void setHealthConditions(List<String> healthConditions) 
    { 
        this.healthConditions = healthConditions;
    }

}
