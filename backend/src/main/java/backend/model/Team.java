 package backend.model;

import java.util.HashSet;
import java.util.Set;

public class Team {

    private Set<Player> members = new HashSet<>();

    public Team() {
    }

    public Team(Set<Player> members) {
        this.members = members;
    }

    public Set<Player> getMembers() {
        return members;
    }

    public void addMember(Player player) {
        this.members.add(player);
    }

    public void setMembers(Set<Player> members) {
        this.members = members;
    }
}
