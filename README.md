This is a working example of two Java Beans ready for deployment 
to an application server in a EAR package containing an EJB package.

Deployed and tested on Websphere AS 8.5

MessageSenderBean sends a JMS message during construction and then every minute.
MessageReceivingClient is a MDB that prints to SystemOut when it receives a message.

To make it work you have to create and configure Service Integration Bus and create the following JMS resources:
- JMS Connection Factory named "ConnectionFactory"
- JMS Topic named "ExampleTopic"
- Activation Specification named "ActivationSpecification"

Also you have to correctly map these resources when you deploy this package to application server.

The messages and prints to SystemOut are in polish, but there is no important information there so it doesn't matter I hope.
