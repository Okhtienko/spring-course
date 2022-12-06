package com.technology.mvc.repository;

import com.technology.mvc.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcMessengerRepository implements MessengerRepository {
  private static final String CREATE_MESSAGE =
      "INSERT INTO messages (first_friend_id, second_friend_id, message_text) VALUES (?, ?, ?)";
  private static final String GET_MESSAGES = "SELECT * FROM messages WHERE " +
      "first_friend_id=? AND second_friend_id=? OR second_friend_id=? AND first_friend_id=?";
  private static final String DELETE_MESSAGES = "DELETE FROM messages WHERE " +
      "(first_friend_id=? AND second_friend_id=?) OR (second_friend_id=? AND first_friend_id=?)";
  private final Connection connection;

  @Override
  public void saveMessage(String text, Long senderId, Long recipientId) {
    try(PreparedStatement statement = connection.prepareStatement(CREATE_MESSAGE)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      statement.setString(3, text);
      statement.execute();
    } catch (SQLException e) {
      log.error("Message not added to db. SenderId[{}], RecipientId[{}]. SQL exception{}",
          senderId, recipientId, e);
    }
  }

  @Override
  public List<Message> getMessages(Long senderId, Long recipientId) {
    try(PreparedStatement statement = connection.prepareStatement(GET_MESSAGES)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      statement.setLong(3, senderId);
      statement.setLong(4, recipientId);
      ResultSet resultSet = statement.executeQuery();
      final List<Message> messages = new ArrayList<>();

      while (resultSet.next()) {
        messages.add(buildMessage(resultSet));
      }

      return messages;

    } catch (SQLException e) {
      log.error("List of messages not received. SenderId[{}], RecipientId[{}]. SQL exception{}",
          senderId, recipientId, e);
    }
    return new ArrayList<>();
  }

  @Override
  public void deleteMessages(Long senderId, Long recipientId) {
    try(PreparedStatement statement = connection.prepareStatement(DELETE_MESSAGES)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      statement.setLong(3, senderId);
      statement.setLong(4, recipientId);
      statement.executeUpdate();
    } catch (SQLException e) {
      log.error("Messages are not deleted from db. SenderId[{}], RecipientId[{}]. SQL exception{}",
          senderId, recipientId, e);
    }

  }

  private Message buildMessage(ResultSet resultSet) throws SQLException {
    return new Message(
        resultSet.getLong("id"),
        resultSet.getLong("first_friend_id"),
        resultSet.getLong("second_friend_id"),
        resultSet.getString("message_text"),
        resultSet.getTimestamp("date")
    );
  }
}
