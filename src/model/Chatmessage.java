package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the chatmessages database table.
 * 
 */
@Entity
@Table(name="chatmessages")
@NamedQuery(name="Chatmessage.findAll", query="SELECT c FROM Chatmessage c")
public class Chatmessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idchatmessages;

	@Lob
	private String message;

	private int senderid;

	//bi-directional many-to-one association to Chat
	@ManyToOne
	@JoinColumn(name="chatid")
	private Chat chat;

	public Chatmessage() {
	}

	public int getIdchatmessages() {
		return this.idchatmessages;
	}

	public void setIdchatmessages(int idchatmessages) {
		this.idchatmessages = idchatmessages;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSenderid() {
		return this.senderid;
	}

	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}

	public Chat getChat() {
		return this.chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
    public int hashCode() {
		
        return Objects.hash(idchatmessages,message);
    }
	
}