package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

    Team team;

    @BeforeEach
    public void setup() {
        team = new Team("test-team");    
    }

    @Test
    public void toString_returns_correct_string() {
        assertEquals("Team(name=test-team, members=[])", team.toString());
    }


    @Test
    public void getName_returns_correct_name() {
       assert(team.getName().equals("test-team"));
    }


    // case 1
    @Test
    public void testEquals_sameObject() {
        assertEquals(true, team.equals(team));
    }

    // case 2
    @Test
    public void testEquals_differentClass() {
        Object obj = new Object();
        assertEquals(false, team.equals(obj));
    }

    // case 3a
    @Test
    public void testEquals_sameNameSameEmptyMembers() {
        Team otherTeam = new Team("test-team");
        assertEquals(true, team.equals(otherTeam));
    }

    // case 3b
    @Test
    public void testEquals_sameNameDifferentMembers() {
        Team otherTeam = new Team("test-team");
        otherTeam.addMember("member1");
        assertEquals(false, team.equals(otherTeam));
    }

    // case 3c
    @Test
    public void testEquals_differentNameSameEmptyMembers() {
        Team otherTeam = new Team("other-team");
        assertEquals(false, team.equals(otherTeam));
    }

    // case 3d
    @Test
    public void testEquals_differentNameDifferentMembers() {
        Team otherTeam = new Team("other-team");
        otherTeam.addMember("member1");
        assertEquals(false, team.equals(otherTeam));
    }

    // case 3e
    @Test
    public void testEquals_sameNameSameNonEmptyMembers() {
        team.addMember("member1");
        team.addMember("member2");
        
        Team otherTeam = new Team("test-team");
        otherTeam.addMember("member1");
        otherTeam.addMember("member2");
        
        assertEquals(true, team.equals(otherTeam));
    }

    // hashcode test
    @Test
    public void testHashCode_equalObjects() {
        Team t1 = new Team();
        t1.setName("test1");
        t1.addMember("member1");
        
        Team t2 = new Team();
        t2.setName("test1");
        t2.addMember("member1");
        
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void testHashCode_notZeroForNonEmptyTeam() {
        Team t = new Team("test-name");
        t.addMember("member1");
        
        assertNotEquals(0, t.hashCode());
    }
    
    @Test
    public void testHashCode_differentBitwiseOperation() {

        Team team1 = new Team("A"); 
        
        int originalHashCode = team1.hashCode();
        
        int simulatedAndHashCode = team1.getName().hashCode() & team1.getMembers().hashCode();
        
        assertNotEquals(simulatedAndHashCode, originalHashCode);
    }

}
