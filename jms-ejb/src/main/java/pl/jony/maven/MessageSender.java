package pl.jony.maven;

import javax.ejb.Local;

@Local
public interface MessageSender {
    void sendMessage();
}