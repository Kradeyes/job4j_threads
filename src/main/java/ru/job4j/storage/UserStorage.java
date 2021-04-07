package ru.job4j.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> storage = new ConcurrentHashMap<>();

    public final synchronized boolean add(User user) {
        return storage.putIfAbsent(user.getId(), user) != null;
    }

    public final synchronized boolean update(User user) {
        return storage.replace(user.getId(), user) != null;
    }

    public final synchronized boolean delete(User user) {
      return storage.remove(user.getId(), user);
    }

    public final synchronized boolean transfer(int idFrom, int idTo, int amount) {
      boolean result = false;
          User from = storage.get(idFrom);
          User to = storage.get(idTo);
          if (from != null && to != null && from.getAmount() >= amount) {
             User fromChange = new User(idFrom, from.getAmount() - amount);
             User toChange = new User(idTo, to.getAmount() + amount);
             storage.replace(idFrom, fromChange);
             storage.replace(idTo, toChange);
             result = true;
          }
      return result;
    }
}
