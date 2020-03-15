package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idusers;

	private String email;

	private String image;

	private String name;

	private String password;

	private String surname;

	private BigInteger telnumber;

	private String type;

	//bi-directional many-to-one association to Chat
	@OneToMany(mappedBy="user")
	private List<Chat> chats;

	//bi-directional many-to-one association to Jobap
	@OneToMany(mappedBy="user")
	private List<Jobap> jobaps;

	//bi-directional many-to-one association to Jobapplication
	@OneToMany(mappedBy="user")
	private List<Jobapplication> jobapplications;

	//bi-directional one-to-one association to Pinfo
	@OneToOne(mappedBy="user")
	private Pinfo pinfo;

	//bi-directional many-to-one association to Postcomment
	@OneToMany(mappedBy="user")
	private List<Postcomment> postcomments;

	//bi-directional many-to-one association to Postinterest
	@OneToMany(mappedBy="user")
	private List<Postinterest> postinterests;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	//bi-directional many-to-one association to Relation
	@OneToMany(mappedBy="user")
	private List<Relation> relations;

	public User() {
	}

	public int getIdusers() {
		return this.idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public BigInteger getTelnumber() {
		return this.telnumber;
	}

	public void setTelnumber(BigInteger telnumber) {
		this.telnumber = telnumber;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Chat> getChats() {
		return this.chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	public Chat addChat(Chat chat) {
		getChats().add(chat);
		chat.setUser(this);

		return chat;
	}

	public Chat removeChat(Chat chat) {
		getChats().remove(chat);
		chat.setUser(null);

		return chat;
	}

	public List<Jobap> getJobaps() {
		return this.jobaps;
	}

	public void setJobaps(List<Jobap> jobaps) {
		this.jobaps = jobaps;
	}

	public Jobap addJobap(Jobap jobap) {
		getJobaps().add(jobap);
		jobap.setUser(this);

		return jobap;
	}

	public Jobap removeJobap(Jobap jobap) {
		getJobaps().remove(jobap);
		jobap.setUser(null);

		return jobap;
	}

	public List<Jobapplication> getJobapplications() {
		return this.jobapplications;
	}

	public void setJobapplications(List<Jobapplication> jobapplications) {
		this.jobapplications = jobapplications;
	}

	public Jobapplication addJobapplication(Jobapplication jobapplication) {
		getJobapplications().add(jobapplication);
		jobapplication.setUser(this);

		return jobapplication;
	}

	public Jobapplication removeJobapplication(Jobapplication jobapplication) {
		getJobapplications().remove(jobapplication);
		jobapplication.setUser(null);

		return jobapplication;
	}

	public Pinfo getPinfo() {
		return this.pinfo;
	}

	public void setPinfo(Pinfo pinfo) {
		this.pinfo = pinfo;
	}

	public List<Postcomment> getPostcomments() {
		return this.postcomments;
	}

	public void setPostcomments(List<Postcomment> postcomments) {
		this.postcomments = postcomments;
	}

	public Postcomment addPostcomment(Postcomment postcomment) {
		getPostcomments().add(postcomment);
		postcomment.setUser(this);

		return postcomment;
	}

	public Postcomment removePostcomment(Postcomment postcomment) {
		getPostcomments().remove(postcomment);
		postcomment.setUser(null);

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
		postinterest.setUser(this);

		return postinterest;
	}

	public Postinterest removePostinterest(Postinterest postinterest) {
		getPostinterests().remove(postinterest);
		postinterest.setUser(null);

		return postinterest;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	public List<Relation> getRelations() {
		return this.relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public Relation addRelation(Relation relation) {
		getRelations().add(relation);
		relation.setUser(this);

		return relation;
	}

	public Relation removeRelation(Relation relation) {
		getRelations().remove(relation);
		relation.setUser(null);

		return relation;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idusers,name,email,password);
    }
}