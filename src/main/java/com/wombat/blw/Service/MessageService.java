package com.wombat.blw.Service;

import com.wombat.blw.DTO.NotificationDTO;
import com.wombat.blw.Form.NotificationForm;
import com.wombat.blw.Form.ReceiverForm;

import java.util.List;

public interface MessageService {

    void setRead(ReceiverForm receiverForm);

    void sendMessage(NotificationForm notificationForm, List<Integer> receiverIdList);

    List<NotificationDTO> findNotRead(Integer userId);
}
