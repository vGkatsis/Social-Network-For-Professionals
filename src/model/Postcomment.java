package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the postcomment database table.
 * 
 */
@Entity
@Table(name="postcomment")
@NamedQuery(name="Postcomment.findAll", query="SELECT p FROM Postcomment p")
public class Postcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idpostcomment;

	@Lob
	private String comment;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="postid")
	private Post post;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Postcomment() {
	}

	public int getIdpostcomment() {
		return this.idpostcomment;
	}

	public void setIdpostcomment(int idpostcomment) {
		this.idpostcomment = idpostcomment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		
        return Objects.hash(idpostcomment,comment);
    }
}