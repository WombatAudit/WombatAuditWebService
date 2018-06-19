package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Notification;
import com.wombat.blw.DO.Receiver;
import com.wombat.blw.Enum.NotificationStatusEnum;
import com.wombat.blw.Form.NotificationForm;
import com.wombat.blw.Form.ReceiverForm;
import com.wombat.blw.Mapper.MessageMapper;
import com.wombat.blw.Service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<String> findNotReadNotification(Integer userId) {
        List<Receiver> receiverS = messageMapper.getListIdByUserId(userId);
        List<String> strings = new ArrayList<>();
        List<Notification> notificationList;
        for (Receiver receiver : receiverS) {
            String listId = receiver.getListId();
            notificationList = messageMapper.getInformationByListId(listId);
            for (Notification notification : notificationList) {
                String content = notification.getContent();
                strings.add(content);
            }
        }
        return strings;
    }

    @Override
    public void setRead(ReceiverForm receiverForm) {
        Receiver receiver = new Receiver();
        BeanUtils.copyProperties(receiverForm, receiver);
        messageMapper.readInformation(receiver);
    }

    @Override
    public void sendMessage(NotificationForm notificationForm, List<Integer> receiverIdList) {
        String listId = UUID.randomUUID().toString();
        List<Receiver> receiverList = receiverIdList.stream().map(e -> new Receiver(listId, e, NotificationStatusEnum.NOT_READ.getCode()))
                .collect(Collectors.toList());
        messageMapper.createReceiver(receiverList);
    }
}
