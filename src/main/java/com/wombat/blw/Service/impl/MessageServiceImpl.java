package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Notification;
import com.wombat.blw.DO.Receiver;
import com.wombat.blw.DTO.NotificationDTO;
import com.wombat.blw.Enum.NotificationStatusEnum;
import com.wombat.blw.Form.NotificationForm;
import com.wombat.blw.Form.ReceiverForm;
import com.wombat.blw.Mapper.MessageMapper;
import com.wombat.blw.Service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void setRead(ReceiverForm receiverForm) {
        Receiver receiver = new Receiver();
        BeanUtils.copyProperties(receiverForm, receiver);
        receiver.setIsRead(NotificationStatusEnum.READ.getCode());
        messageMapper.readInformation(receiver);
    }

    @Override
    public void sendMessage(NotificationForm notificationForm, List<Integer> receiverIdList) {
        String listId = UUID.randomUUID().toString();
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationForm, notification);
        notification.setReceiveListId(listId);
        messageMapper.insertInformation(notification);
        List<Receiver> receiverList = receiverIdList.stream().map(e -> new Receiver(listId, e, NotificationStatusEnum.NOT_READ.getCode()))
                .collect(Collectors.toList());
        messageMapper.createReceiver(receiverList);
    }

    @Override
    public List<NotificationDTO> findNotRead(Integer userId) {
        List<Notification> notificationList = messageMapper.findNotificationListByUserIdInStatus(userId, NotificationStatusEnum.NOT_READ.getCode());
        return notificationList.stream().map(e -> new NotificationDTO(e.getContent(), e.getCreatedAt(), e.getReceiveListId()))
                .collect(Collectors.toList());
    }

}
