package ru.job4j;

import java.util.*;

/**
 * Class for merging users with the same email
 */
public class Merger {

    /**
     * User mergers. The problem is reduced to the problem of finding the connected components in a graph.
     * @param users - users
     * @return - users after the merger
     */
    public Set<User> merge(Set<User> users) {
        Set<User> usersSet = new HashSet<>();
        Map<String, List<User>> emailsMap = new HashMap<>();

        for (User user : users) {
            usersSet.add(user);
            for (String email : user.getEmails()) {
                if (emailsMap.containsKey(email)) {
                    emailsMap.get(email).add(user);
                } else {
                    emailsMap.put(email, new ArrayList<>(List.of(user)));
                }
            }
        }

        Set<User> result = new HashSet<>();

        while (!usersSet.isEmpty()) {
            User initUser = usersSet.iterator().next();
            Set<User> nodes = new HashSet<>(Set.of(initUser));
            usersSet.remove(initUser);

            Queue<String> edges = new LinkedList<>(initUser.getEmails());
            while (!edges.isEmpty()) {
                String email = edges.poll();
                if (emailsMap.containsKey(email)) {
                    for (User user : emailsMap.get(email)) {
                        if (nodes.add(user)) {
                            edges.addAll(user.getEmails());
                            usersSet.remove(user);
                        }
                    }
                    emailsMap.remove(email);
                }
            }

            Iterator<User> it = nodes.iterator();
            User user = it.next();
            while (it.hasNext()) {
                for (String mail : it.next().getEmails()) {
                    user.addEmail(mail);
                }
            }
            result.add(user);
        }

        return result;
    }
}
