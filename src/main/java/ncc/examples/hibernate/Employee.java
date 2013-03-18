package ncc.examples.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

import java.util.UUID;

@Entity
@Table (name = "EMPLOYEE")
public class Employee {
    @Id
    private UUID id = UUID.randomUUID();

    @Column (name = "NAME")
    private String name;

    public Employee() {}

    public Employee(String name) { this.name = name; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public UUID getId() { return id; }
}
