package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the jobap database table.
 * 
 */
@Entity
@Table(name="jobap")
@NamedQuery(name="Jobap.findAll", query="SELECT j FROM Jobap j")
public class Jobap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idjobap;

	private String jobcompany;

	@Lob
	private String jobdescription;

	private int jobincome;

	private String jobname;

	private String jobposition;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	//bi-directional many-to-one association to Jobapplication
	@OneToMany(mappedBy="jobap")
	private List<Jobapplication> jobapplications;

	public Jobap() {
	}

	public int getIdjobap() {
		return this.idjobap;
	}

	public void setIdjobap(int idjobap) {
		this.idjobap = idjobap;
	}

	public String getJobcompany() {
		return this.jobcompany;
	}

	public void setJobcompany(String jobcompany) {
		this.jobcompany = jobcompany;
	}

	public String getJobdescription() {
		return this.jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}

	public int getJobincome() {
		return this.jobincome;
	}

	public void setJobincome(int jobincome) {
		this.jobincome = jobincome;
	}

	public String getJobname() {
		return this.jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getJobposition() {
		return this.jobposition;
	}

	public void setJobposition(String jobposition) {
		this.jobposition = jobposition;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Jobapplication> getJobapplications() {
		return this.jobapplications;
	}

	public void setJobapplications(List<Jobapplication> jobapplications) {
		this.jobapplications = jobapplications;
	}

	public Jobapplication addJobapplication(Jobapplication jobapplication) {
		getJobapplications().add(jobapplication);
		jobapplication.setJobap(this);

		return jobapplication;
	}

	public Jobapplication removeJobapplication(Jobapplication jobapplication) {
		getJobapplications().remove(jobapplication);
		jobapplication.setJobap(null);

		return jobapplication;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idjobap,jobcompany,jobname,jobposition);
    }
}