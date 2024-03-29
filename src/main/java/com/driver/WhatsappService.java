package com.driver;

import java.util.Date;
import java.util.List;

public class WhatsappService {
    WhatsappRepository whatsAppRepository = new WhatsappRepository();
    String createUser(String name , String mobile) throws Exception {return whatsAppRepository.createUser(name,mobile);}
    Group createGroup(List<User> user){return whatsAppRepository.createGroup(user);}
    int createMessage(String content){return whatsAppRepository.createMessage(content);}
    int sendMessage(Message message , User sender , Group group) throws Exception {return whatsAppRepository.sendMessage(message,sender,group);}
    String changeAdmin(User approver, User user, Group group) throws Exception {return whatsAppRepository.changeAdmin(approver,user,group);}
    int removeUser(User user) throws Exception {return whatsAppRepository.removeUser(user);}
    String findMessage(Date start, Date end, int K){return whatsAppRepository.findMessage(start,end,K);}
}
