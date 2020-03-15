package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the jobapplication database table.
 * 
 */
@Entity
@Table(name="jobapplication")
@NamedQuery(name="Jobapplication.findAll", query="SELECT j FROM Jobapplication j")
public class Jobapplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idjobapplication;

	//bi-directional many-to-one association to Jobap
	@ManyToOne
	@JoinColumn(name="jobapid")
	private Jobap jobap;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Jobapplication() {
	}

	public int getIdjobapplication() {
		return this.idjobapplication;
	}

	public void setIdjobapplication(int idjobapplication) {
		this.idjobapplication = idjobapplication;
	}

	public Jobap getJobap() {
		return this.jobap;
	}

	public void setJobap(Jobap jobap) {
		this.jobap = jobap;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idjobapplication);
    }
	
}