package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the chats database table.
 * 
 */
@Entity
@Table(name="chats")
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idchats;

	private int seconduserid;

	//bi-directional many-to-one association to Chatmessage
	@OneToMany(mappedBy="chat")
	private List<Chatmessage> chatmessages;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="firstuserid")
	private User user;

	public Chat() {
	}

	public int getIdchats() {
		return this.idchats;
	}

	public void setIdchats(int idchats) {
		this.idchats = idchats;
	}

	public int getSeconduserid() {
		return this.seconduserid;
	}

	public void setSeconduserid(int seconduserid) {
		this.seconduserid = seconduserid;
	}

	public List<Chatmessage> getChatmessages() {
		return this.chatmessages;
	}

	public void setChatmessages(List<Chatmessage> chatmessages) {
		this.chatmessages = chatmessages;
	}

	public Chatmessage addChatmessage(Chatmessage chatmessage) {
		getChatmessages().add(chatmessage);
		chatmessage.setChat(this);

		return chatmessage;
	}

	public Chatmessage removeChatmessage(Chatmessage chatmessage) {
		getChatmessages().remove(chatmessage);
		chatmessage.setChat(null);

		return chatmessage;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idchats,seconduserid);
    }
	
}