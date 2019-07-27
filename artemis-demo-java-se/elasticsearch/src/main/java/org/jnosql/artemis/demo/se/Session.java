package org.jespanol.session;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.util.Objects;

@Entity
public class Session {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String conference;

    @Column
    private Integer speaker;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getConference() {
        return conference;
    }

    public Integer getSpeaker() {
        return speaker;
    }

    public void update(Session sessionUpdated) {
        this.name = sessionUpdated.name;
        this.title = sessionUpdated.title;
        this.description = sessionUpdated.description;
        this.conference = sessionUpdated.conference;
        this.speaker = sessionUpdated.speaker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", conference='" + conference + '\'' +
                ", speaker='" + speaker + '\'' +
                '}';
    }

    public static Session of(SessionDTO dto) {
        Objects.requireNonNull(dto, "dto is required");
        Session session = new Session();
        session.id = dto.getId();
        session.name = dto.getName();
        session.title = dto.getTitle();
        session.description = dto.getDescription();
        session.conference = dto.getConference();
        session.speaker = dto.getSpeaker();
        return session;
    }

}
