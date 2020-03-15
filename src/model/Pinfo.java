package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the pinfo database table.
 * 
 */
@Entity
@Table(name="pinfo")
@NamedQuery(name="Pinfo.findAll", query="SELECT p FROM Pinfo p")
public class Pinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idpinfo;

	private String bachelor;

	private String doctorate;

	private String master;

	@Lob
	private String misc;

	private String position;

	private String school;

	private String workspace;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="userid")
	private User user;

	public Pinfo() {
	}

	public int getIdpinfo() {
		return this.idpinfo;
	}

	public void setIdpinfo(int idpinfo) {
		this.idpinfo = idpinfo;
	}

	public String getBachelor() {
		return this.bachelor;
	}

	public void setBachelor(String bachelor) {
		this.bachelor = bachelor;
	}

	public String getDoctorate() {
		return this.doctorate;
	}

	public void setDoctorate(String doctorate) {
		this.doctorate = doctorate;
	}

	public String getMaster() {
		return this.master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getMisc() {
		return this.misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getWorkspace() {
		return this.workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idpinfo,bachelor,doctorate,master);
    }
}