package message;

import java.util.Optional;

public class MessageService {
    private UserRepository userRepository;
    private MessageRepository messageRepository;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void registerUser(String username) {
        if (userRepository.findUserByName(username).isPresent()) {
            throw new IllegalArgumentException("Username is already taken: " + username);
        }
        userRepository.insertUser(username);
    }

    void sendMessage(User sender, User receiver, String message) {
        Optional<User> senderOpt = userRepository.findUserByName(sender.getUsername());
        Optional<User> receiverOpt = userRepository.findUserByName(receiver.getUsername());

        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            long sender_id = senderOpt.get().getId();
            long receiver_id = receiverOpt.get().getId();
            messageRepository.insertMessage(sender_id, receiver_id, message);
        }
    }

}
