package com.technology.mvc.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcFriendRequestsRepository implements FriendRequestsRepository {
  private static final String CREATE_REQUEST = "INSERT INTO requests(sender_id, recipient_id) VALUES (?, ?)";
  private static final String DELETE_REQUEST = "DELETE FROM requests WHERE sender_id=? AND recipient_id=?";
  private static final String SELECT_REQUEST = "SELECT * FROM requests WHERE sender_id=? AND recipient_id=?";
  private final Connection connection;

  @Override
  public void createFriendRequest(Long senderId, Long recipientId) {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_REQUEST)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      statement.execute();
    } catch (SQLException e) {
      log.error(
          "Request not added to db. SenderId[{}], recipientId[{}]. SQL exception{}",
          senderId, recipientId, e
      );
    }
  }

  @Override
  public void deleteFriendRequest(Long senderId, Long recipientId) {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      statement.executeUpdate();
    } catch (SQLException e) {
      log.error(
          "Request not removed from db. SenderId[{}], recipientId[{}]. SQL exception{}",
          senderId, recipientId, e
      );
    }
  }

  @Override
  public boolean isRequestExists(Long senderId, Long recipientId) {
    try (PreparedStatement statement = connection.prepareStatement(SELECT_REQUEST)) {
      statement.setLong(1, senderId);
      statement.setLong(2, recipientId);
      ResultSet resultSet = statement.executeQuery();

      return resultSet.next();

    } catch (SQLException e) {
      log.error(
          "Request not found. SenderId[{}], recipientId[{}]. SQL exception{}",
          senderId, recipientId, e
      );
    }
    return false;
  }
}
