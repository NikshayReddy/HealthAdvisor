package healthcare.healthcare.entity;

public class Doctor 
{
    private String name;
    private String specialty;
    private String officeHours;
    private String contactNumber;

    // Constructor
    public Doctor(String name, String specialty, String officeHours, String contactNumber) 
    {
        this.name = name;
        this.specialty = specialty;
        this.officeHours = officeHours;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getSpecialty() 
    {
        return specialty;
    }

    public void setSpecialty(String specialty) 
    {
        this.specialty = specialty;
    }

    public String getOfficeHours() 
    {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) 
    {
        this.officeHours = officeHours;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }
}
