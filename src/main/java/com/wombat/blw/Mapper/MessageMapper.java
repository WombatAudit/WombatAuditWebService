package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Notification;
import com.wombat.blw.DO.Receiver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface MessageMapper {

    @Select("SELECT * FROM receiver WHERE user_id=#{userId} and isread = 0")
    @Results({
            @Result(property = "listId", column = "list_id", javaType = String.class),
            @Result(property = "user_id", column = "userId", javaType = Integer.class),
            @Result(property = "isRead", column = "isread", javaType = Integer.class)
    })
    List<Receiver> getListIdByUserId(int userId);

    @Select("SELECT * FROM notification WHERE receivelist_id = #{listId}")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "senderId", column = "sender_id", javaType = Integer.class),
            @Result(property = "receiveListId", column = "receivelist_id", javaType = String.class),
            @Result(property = "content", column = "content", javaType = String.class)
    })
    List<Notification> getInformationByListId(String listId);


    @Insert("INSERT INTO notification(sender_id,receivelist_id,content) VALUES(#{senderId}," +
            "#{receiveListId},#{content})")
    void insertInformation(Notification notification);

    @Insert("INSERT INTO receiver(list_id,user_id,isread) VALUES(#{listId},#{userId}," +
            "#{isRead})")
    void insertUserToList(Receiver receiver);

    @Update("update receiver set isread =#{isRead} where list_id=#{listId} and user_id=#{userId}")
    void readInformation(Receiver receiver);

    @Insert("<script> " +
            "insert into receiver (list_id, user_id, isread) values " +
            "<foreach item='receiver' collection='receiverList' open='' separator=',' close=''> " +
            "(#{receiver.listId}, #{receiver.userId}, #{receiver.isRead}) " +
            "</foreach> </script>")
    void createReceiver(@Param("receiverList") List<Receiver> receiverList);

    @Select("select * from receiver join notification on receiver.list_id = notification.receivelist_id where receiver.user_id = #{userId} " +
            "and isread = #{status}")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "senderId", column = "sender_id", javaType = Integer.class),
            @Result(property = "receiveListId", column = "receivelist_id", javaType = String.class),
            @Result(property = "content", column = "content", javaType = String.class),
            @Result(property = "createdAt", column = "createdat", javaType = Date.class)
    })
    List<Notification> findNotificationListByUserIdInStatus(@Param("userId") Integer userId, @Param("status") Integer status);
}
