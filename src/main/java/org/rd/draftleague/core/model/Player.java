package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "players")
@NamedQueries({
        @NamedQuery(name = "org.rd.draftleague.core.model.Player.findAll",
                query = "select p from Player p"),
        @NamedQuery(name = "org.rd.draftleague.core.model.Player.findByName",
                query = "select p from Player p "
                        + "where p.name like :name "
                        + "or p.nickName like :name")
})
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "playerName", nullable = false)
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "startDate")
    private Date startDate;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"players", "drafts"})
    private List<League> leagues;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"league", "draftPlayers", "pickCount", "draftedCards"})
    private List<Draft> drafts;

    public Player() {
        this.startDate = new Date();
    }

    public Player(String name, String nickName, Date startDate, String email, List<League> leagues, List<Draft> drafts) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.startDate = startDate;
        this.leagues = leagues;
        this.drafts = drafts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<Draft> getDrafts() {
        return drafts;
    }

    public void setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!(o instanceof Player)) {
            return false;
        }

        final Player that = (Player) o;

        return Objects.equals(this.hashCode(), that.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate);
    }

    public Player update(Player player) {
        this.name = player.getName();
        this.nickName = player.getNickName();
        this.email = player.getEmail();
        this.leagues = player.getLeagues();
        this.drafts = player.getDrafts();

        return this;
    }

    public void joinLeague(League league) {
        if(!this.getLeagues().contains(league)) {
            this.getLeagues().add(league);
        } else {
            //TODO Logs...
        }
    }

    public void joinDraft(Draft draft) {
        if(!this.getDrafts().contains(draft)) {
            if(this.getLeagues().contains(draft.getLeague())){
                //TODO Don't join draft if draft is already started
                this.getDrafts().add(draft);
            } else {
                //TODO Log if Draft & Player aren't in the same league...
            }
        } else {
            //TODO Logs...
        }
    }
}
