package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the relations database table.
 * 
 */
@Entity
@Table(name="relations")
@NamedQuery(name="Relation.findAll", query="SELECT r FROM Relation r")
public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idrelations;

	private int seconduserid;

	private byte status;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="firstuserid")
	private User user;

	public Relation() {
	}

	public int getIdrelations() {
		return this.idrelations;
	}

	public void setIdrelations(int idrelations) {
		this.idrelations = idrelations;
	}

	public int getSeconduserid() {
		return this.seconduserid;
	}

	public void setSeconduserid(int seconduserid) {
		this.seconduserid = seconduserid;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idrelations,seconduserid,status);
    }
}