package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the postinterest database table.
 * 
 */
@Entity
@Table(name="postinterest")
@NamedQuery(name="Postinterest.findAll", query="SELECT p FROM Postinterest p")
public class Postinterest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idpostinterest;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="postid")
	private Post post;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Postinterest() {
	}

	public int getIdpostinterest() {
		return this.idpostinterest;
	}

	public void setIdpostinterest(int idpostinterest) {
		this.idpostinterest = idpostinterest;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public int hashCode() {
		
        return Objects.hashCode(idpostinterest);
    }
}