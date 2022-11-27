package com.technology.mvc.repository;

import com.technology.mvc.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcUserRepository implements UserRepository {
  private static final String CREATE_USER = "INSERT INTO users(name, password) VALUES (?, ?)";
  private static final String FIND_USERS = "SELECT * FROM users";
  private static final String VALIDATE_USER = "SELECT * FROM users WHERE name=? AND password=?";
  private static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE name=?";
  private static final String GET_ID_USER_BY_NAME = "SELECT id FROM users WHERE name=?";
  private static final String GET_SUGGESTED_FRIENDS = "SELECT * FROM users WHERE id !=? " +
      "AND id NOT IN (SELECT second_friend_id FROM friends WHERE first_friend_id=?)";
  private static final String GET_ALL_OUTGOING_REQUESTS =
      "SELECT * FROM users u INNER JOIN requests r ON u.id = r.recipient_id WHERE sender_id=?";
  private static final String GET_ALL_INCOMING_REQUESTS =
      "SELECT * FROM users u INNER JOIN requests r ON u.id = r.sender_id WHERE r.recipient_id=?";
  private static final String GET_ALL_FRIENDS =
      "SELECT * FROM users u JOIN friends f on u.id = f.second_friend_id WHERE f.first_friend_id=? " +
          "UNION SELECT * FROM users u JOIN friends f on u.id = f.first_friend_id WHERE f.second_friend_id=?";
  private static final String IS_EXISTS = "SELECT * FROM user WHERE name=?";
  private final Connection connection;

  @Override
  public void addUser(String name, String password) {
    try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
      statement.setString(1, name);
      statement.setString(2, password);
      statement.execute();
    } catch (SQLException e) {
      log.error("User not added to db. Name[{}], password[{}]. SQL exception{}", name, password, e);
    }
  }

  @Override
  public boolean validate(String name, String password) {
    try (PreparedStatement statement = connection.prepareStatement(VALIDATE_USER)) {
      statement.setString(1, name);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();

      return resultSet.next();

    } catch (SQLException e) {
      log.error("Validation error. Name[{}], password[{}]. SQL exception{}", name, password, e);
    }
    return false;
  }

  @Override
  public List<User> findUsers() {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(FIND_USERS);
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      log.error("SQL exception.", e);
    }
    return new ArrayList<>();
  }

  @Override
  public Optional<User> getUser(String name) {
    try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return Optional.of(buildUser(resultSet));
      }

    } catch (SQLException e) {
      log.error("No user data received. Name[{}]. SQL exception{}", name, e);
    }
    return Optional.empty();
  }

  @Override
  public List<User> filterUsersByName(String parameter) {
    final List<User> users = findUsers();
    return users.stream().filter(user -> user.getName().startsWith(parameter)).toList();
  }

  @Override
  public Optional<Long> getUserIdByName(String name) {
    try (PreparedStatement statement = connection.prepareStatement(GET_ID_USER_BY_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return Optional.of(resultSet.getLong("id"));
      }

    } catch (SQLException e) {
      log.error("User ID not received. Name[{}]. SQL exception{}", name, e);
    }
    return Optional.empty();
  }

  @Override
  public List<User> getSuggestedFriendsList(Long signedInUserId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_SUGGESTED_FRIENDS)) {
      statement.setLong(1, signedInUserId);
      statement.setLong(2, signedInUserId);
      ResultSet resultSet = statement.executeQuery();
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      log.error("List of suggested friends not received. SignedInUserId[{}]. SQL exception{}", signedInUserId, e);
    }
    return new ArrayList<>();
  }

  @Override
  public List<User> getAllOutgoingRequestList(Long senderId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_OUTGOING_REQUESTS)) {
      statement.setLong(1, senderId);
      ResultSet resultSet = statement.executeQuery();
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      log.error("The list of outgoing requests has not been received. SenderId[{}]. SQL exception{}", senderId, e);
    }
    return new ArrayList<>();
  }

  @Override
  public List<User> getAllIncomingRequestList(Long recipientId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_INCOMING_REQUESTS)) {
      statement.setLong(1, recipientId);
      ResultSet resultSet = statement.executeQuery();
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      log.error(
          "The list of incoming requests has not been received.  RecipientId[{}]. SQL exception{}",
          recipientId, e
      );
    }
    return new ArrayList<>();
  }

  @Override
  public List<User> getFriendsList(Long signedInUserId) {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_FRIENDS)) {
      statement.setLong(1, signedInUserId);
      statement.setLong(2, signedInUserId);
      ResultSet resultSet = statement.executeQuery();
      final List<User> users = new ArrayList<>();

      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }

      return users;

    } catch (SQLException e) {
      log.error("Friends list not received. SignedInUserI[{}]. SQL exception{}", signedInUserId, e);
    }
    return new ArrayList<>();
  }

  @Override
  public boolean isExists(String name) {
    try (PreparedStatement statement = connection.prepareStatement(IS_EXISTS)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      return  resultSet.next();
    } catch (SQLException e) {
      log.error("User already exists. Name[{}]. SQL exception{}", name, e);
    }
    return false;
  }

  private User buildUser(ResultSet resultSet) throws SQLException {
   return new User(
        resultSet.getLong("id"),
        resultSet.getString("name"),
        resultSet.getString("password"),
        resultSet.getTimestamp("date")
    );
  }
}
