package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MyChatMessage {
		@Id
		@GeneratedValue
		private Long id;
		private String message;

		public MyChatMessage() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return String.format("Chat [id=%s, message=%s]", id, message);
		}

	}