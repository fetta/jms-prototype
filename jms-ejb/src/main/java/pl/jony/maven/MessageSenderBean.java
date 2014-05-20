package pl.jony.maven;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@Singleton
@Startup
public class MessageSenderBean implements MessageSender {

    @Resource(mappedName="ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName="ExampleTopic")
    private Topic topic;

    @PostConstruct
    public void postConstruct() {
        System.out.println("Wywołanie konstruktora MessageSenderBean");
        sendTextMessage("wiadomosc wyslana podczas wywolania konstruktora MessageSenderBean");
    }

    public void sendTextMessage(String message) {

//        InitialContext jndiContext = getInitialContext();

        Connection connect = null;
        Session session;
        MessageProducer producer;
        TextMessage textMsg;

//        try {
//            topic = (Topic) jndiContext.lookup(topicName);
//            //wyrzucic lookup
//        } catch (NamingException e) {
//            System.out.println("Nie udalo sie odnalezc zasobu");
//            e.printStackTrace();
//            return;
//        }

        try {
            try {
                connect = connectionFactory.createConnection();
                session = connect.createSession(true, 0);
                producer = session.createProducer(topic);
                textMsg = session.createTextMessage();
                textMsg.setText(message);
                producer.send(textMsg);
            } finally {
                if (connect != null) {
                    connect.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Nie udalo sie nadac komunikatu JMS");
            e.printStackTrace();
            return;
        }
	}

//    public InitialContext getInitialContext() {
//        try {
//            return new InitialContext();
//        } catch (NamingException e) {
//            System.out.println("Nie udalo sie uzyskac kontekstu InitialContext");
//            throw new ExceptionInInitializerError(e);
//        }
//    }

    @Override
    @Schedule(hour = "*", minute = "*", info="Every minute")
    public void sendMessage() {
        sendTextMessage("wiadomosc testowa");
        System.out.println("MessageSenderBean wyslal wiadomosc");
    }
}