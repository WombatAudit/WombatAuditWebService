package com.wombat.blw.Service;

import com.wombat.blw.Form.NotificationForm;
import com.wombat.blw.Form.ReceiverForm;

import java.util.List;

public interface MessageService {

    List<String> findNotReadNotification(Integer userId);

    void setRead(ReceiverForm receiverForm);

    void sendMessage(NotificationForm notificationForm, List<Integer> receiverIdList);
}
