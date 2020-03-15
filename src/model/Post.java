package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Table(name="posts")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idposts;

	@Lob
	private byte[] pmedia;

	@Temporal(TemporalType.TIMESTAMP)
	private Date postdate;

	@Lob
	private String text;

	//bi-directional many-to-one association to Postcomment
	@OneToMany(mappedBy="post")
	private List<Postcomment> postcomments;

	//bi-directional many-to-one association to Postinterest
	@OneToMany(mappedBy="post")
	private List<Postinterest> postinterests;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Post() {
	}

	public int getIdposts() {
		return this.idposts;
	}

	public void setIdposts(int idposts) {
		this.idposts = idposts;
	}

	public byte[] getPmedia() {
		return this.pmedia;
	}

	public void setPmedia(byte[] pmedia) {
		this.pmedia = pmedia;
	}

	public Date getPostdate() {
		return this.postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Postcomment> getPostcomments() {
		return this.postcomments;
	}

	public void setPostcomments(List<Postcomment> postcomments) {
		this.postcomments = postcomments;
	}

	public Postcomment addPostcomment(Postcomment postcomment) {
		getPostcomments().add(postcomment);
		postcomment.setPost(this);

		return postcomment;
	}

	public Postcomment removePostcomment(Postcomment postcomment) {
		getPostcomments().remove(postcomment);
		postcomment.setPost(null);

		return postcomment;
	}

	public List<Postinterest> getPostinterests() {
		return this.postinterests;
	}

	public void setPostinterests(List<Postinterest> postinterests) {
		this.postinterests = postinterests;
	}

	public Postinterest addPostinterest(Postinterest postinterest) {
		getPostinterests().add(postinterest);
		postinterest.setPost(this);

		return postinterest;
	}

	public Postinterest removePostinterest(Postinterest postinterest) {
		getPostinterests().remove(postinterest);
		postinterest.setPost(null);

		return postinterest;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null)
		{
			return false;
		}
	
		if (!Post.class.isAssignableFrom(obj.getClass())) 
		{
	        return false;
	    }
	
		Post comp_post = (Post) obj;
	
		if(this.idposts != comp_post.idposts)
		{
			return false;
		}
	
		return true;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idposts,text);
    }
}