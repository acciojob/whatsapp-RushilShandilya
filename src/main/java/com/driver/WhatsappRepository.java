package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.

    private HashMap<String,User> userDB;
    private HashMap<Group , List<User>> groupUserDB;
    private HashMap<Group , List<User>> personalChatDB;
    private HashMap<Integer, Message> messageDB;
    private HashMap<Group, List<Message>> groupMessageDB;
    private HashMap<User,Group> adminDB;
    private HashMap<User, List<Message>> senderDB;
    private int customGroupCount;
    private int messageId;
    private int totalMessage;

    public WhatsappRepository(){
        userDB = new HashMap<>();
        groupUserDB = new HashMap<>();
        personalChatDB = new HashMap<>();
        messageDB = new HashMap<>();
        groupMessageDB = new HashMap<>();
        adminDB = new HashMap<>();
        senderDB = new HashMap<>();

        this.customGroupCount = 0;
        this.messageId = 0;
        this.totalMessage = 0;
    }

    public String createUser(String name, String mobile) throws Exception {
        if(userDB.containsKey(mobile)) throw new Exception("User already exists");
        else{
            userDB.put(mobile, new User(name,mobile));
            return "SUCCESS";
        }
    }

    public Group createGroup(List<User> user){
        if(user.size()<=2){
            Group group = new Group(user.get(1).getName(),user.size());
            personalChatDB.put(group,user);
            return group;
        }else {
            Group group = new Group(("Group" + " " + user.size()), user.size());
            adminDB.put(user.get(0), group);
            groupUserDB.put(group, user);
            ++customGroupCount;
            return group;
        }
    }
    public int createMessage(String content){
        messageDB.put(messageId,new Message(messageId,content));
        ++messageId;
        ++totalMessage;
        return this.messageId;
    }
    public int sendMessage(Message message, User sender, Group group) throws Exception {
        if(!groupUserDB.containsKey(group)) throw new Exception("Group does not exist");
        if(!groupUserDB.get(group).contains(sender)) throw new Exception("You are not allowed to send message");

        if(!groupMessageDB.containsKey(group)) groupMessageDB.put(group,new ArrayList<>());
        List<Message> list = groupMessageDB.get(group);
        list.add(message);
        groupMessageDB.put(group,list);

        if(!senderDB.containsKey(sender)) senderDB.put(sender,new ArrayList<>());
        List<Message> messageList = senderDB.get(sender);
        messageList.add(message);
        senderDB.put(sender,messageList);

        return groupMessageDB.get(group).size();
    }
    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(!groupUserDB.containsKey(group)) throw new Exception("Group does not exist");
        if(!groupUserDB.get(group).get(0).getName().equals(approver.getName())) throw new Exception("Approver does not have rights");
        if(!groupUserDB.get(group).contains(user)) throw new Exception("User is not a participant");

        int indexOfUser = groupUserDB.get(group).indexOf(user);
        List<User> changeAdmin = groupUserDB.get(group);
        changeAdmin.set(0,user);
        changeAdmin.set(indexOfUser,approver);

        return "SUCCESS";
    }

    public int removeUser(User user) throws Exception {
        if(!userDB.containsKey(user.getMobile())) throw new Exception("User doesn't exist");
        if(adminDB.containsKey(user)) throw new Exception("Cannot remove admin");

        Group groupUser = null;
        for(Group group : groupUserDB.keySet()) if(groupUserDB.get(group).contains(user)) groupUser = group;
        for(Message userMessage : senderDB.get(user)) {
            for(Message message : groupMessageDB.get(groupUser)){
                if(message.equals(userMessage)){
                    groupMessageDB.get(groupUser).remove(message);
                    --totalMessage;
                }
            }
        }

        groupUserDB.get(groupUser).remove(user);
        senderDB.remove(user);
        return groupUserDB.get(groupUser).size() + groupMessageDB.get(groupUser).size() + totalMessage;
    }

    public String findMessage(Date start, Date end, int k){
        return "";
    }
}
