package ru.job4j.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStorage {
    final Map<Integer, User> storage = new ConcurrentHashMap<>();

    public final synchronized boolean add(User user) {
        boolean result = false;
        if (!storage.containsKey(user.getId())) {
            storage.put(user.getId(), User.of(user));
            result = true;
        }
        return result;
    }

    public final synchronized boolean update(User user) {
        boolean result = false;
        if (storage.containsKey(user.getId())) {
            storage.put(user.getId(), User.of(user));
            result = true;
        }
        return result;
    }

    public final synchronized boolean delete(User user) {
        boolean result = false;
        if (storage.containsKey(user.getId())) {
            storage.remove(user.getId());
            result = true;
        }
        return result;
    }

    public final synchronized boolean transfer(int idFrom, int idTo, int amount) {
      boolean result = false;
          User from = storage.get(idFrom);
          User to = storage.get(idTo);
          if (from != null && to != null && from.getAmount() >= amount) {
             from.setAmount(from.getAmount() - amount);
             to.setAmount(to.getAmount() - amount);
             result = true;
          }
      return result;
    }
}
