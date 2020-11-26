package manager.interfaces;

import manager.exceptions.GreetingsException;

@FunctionalInterface
public interface ChangeUserName {
    String edit(String userName) throws GreetingsException;
}
