package pl.jony.maven;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@MessageDriven(
        mappedName="ExampleTopic", activationConfig =  {
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic")
})
public class MessageReceivingClient implements javax.jms.MessageListener {

//    @Resource(mappedName="ConnectionFactory")
//    private ConnectionFactory connectionFactory;
//
//    @Resource(mappedName="ExampleTopic")
//    private Topic topic;

    public MessageReceivingClient () {
        System.out.println("Wywolanie konstruktora MessageReceivingClient");
    }

//    @PostConstruct
//    public void receiveMessages() {
//        System.out.println("Rozpoczytam wykonywanie metody receiveMessages()");
//        try {
//            Connection connect = connectionFactory.createConnection();
//            Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageConsumer consumer = session.createConsumer(topic);
//            consumer.setMessageListener(this);
//            connect.start();
//        } catch (JMSException e) {
//            System.out.println("MessageReceivingClient - nie udalo sie nawiazac polaczenia JMS");
//            e.printStackTrace();
//            throw new ExceptionInInitializerError(e);
//        }
//    }

    @Override
	public void onMessage(Message message) {
        TextMessage msg = null;

        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("MessageReceivingClient otrzymal wiadomosc: " +
                        msg.getText());
            } else {
                System.out.println("MessageReceivingClient otrzymal wiadomosc zlego typu: " +
                        message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}