package com.roadtoglory.entry.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_basic_info_id")
    private PersonalInformation agentPersonalInfo;

    @Column(name = "agent_fee")
    private BigInteger agentFee;

    @OneToMany(mappedBy = "playerAgent", cascade = CascadeType.ALL)
    @Column(name = "player_regd_id")
//    @JoinColumn(name = "player_regd_id")
    // NOTE: Cannot use the join column if u are using mapped by:
    /* Error creating bean with name 'entityManagerFactory' defined in class path resource
     [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]:
     Invocation of init method failed; nested exception is org.hibernate.AnnotationException:
     Associations marked as mappedBy must not define database mappings
     like @JoinTable or @JoinColumn: com.roadtoglory.entry.entity.Agent.playersUnderAgent
     */
    private List<Player> playersUnderAgent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalInformation getAgentPersonalInfo() {
        return agentPersonalInfo;
    }

    public void setAgentPersonalInfo(PersonalInformation agentPersonalInfo) {
        this.agentPersonalInfo = agentPersonalInfo;
    }

    public BigInteger getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(BigInteger agentFee) {
        this.agentFee = agentFee;
    }

    public List<Player> getPlayersUnderAgent() {
        return playersUnderAgent;
    }

    public void setPlayersUnderAgent(List<Player> playersUnderAgent) {
        this.playersUnderAgent = playersUnderAgent;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", agentPersonalInfo=" + agentPersonalInfo +
                ", agentFee=" + agentFee +
                ", playersUnderAgent=" + playersUnderAgent +
                '}';
    }
}
